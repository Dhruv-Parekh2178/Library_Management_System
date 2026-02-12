package com.LMS.library.service.category;

import com.LMS.library.dtos.CategoryDTO;
import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Author;
import com.LMS.library.model.Book;
import com.LMS.library.model.Category;
import com.LMS.library.repository.BookRepository;
import com.LMS.library.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class  CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll().stream()
                .filter(category -> !category.isDeleted()).toList();
        return categories;
    }

    @Override
    @Cacheable(value = "category" , key = "#id")
    public CategoryDTO getCategoryById(Long id) {
        System.out.println("Fetching category with "+id+" from DB...");
        Category category= categoryRepository.findCategoryById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category" ,"CategoryId" , id));
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    @CacheEvict(value = "category" , key = "#id")
    public void deleteCategory(Long id) {
        Category savedCategory = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "CateegoryId" , id));

        savedCategory.setDeleted(true);
        categoryRepository.save(savedCategory);
    }

    @Override
    @Transactional
    public void saveCategoryWithBooks(Category category, List<Long> bookIds) {
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        categoryRepository.save(category);


        for (Book book : books) {
            book.getCategories().add(category);
        }
    }

    @Override
    @Transactional
    @CachePut(value = "category" , key = "#id")
    public void updateCaregoryWithBooks(Category category, List<Long> bookIds, Long id) {
        Category savedCategory = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category" , "CateegoryId" , id));

        savedCategory.setName(category.getName());

        if (savedCategory.getBooks() != null) {
            for (Book book : savedCategory.getBooks()) {
                book.getCategories().remove(savedCategory);
            }
        }

        List<Book> newBooks = bookRepository.findAllById(bookIds);
        if (newBooks.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        for (Book book : newBooks) {
            book.getCategories().add(savedCategory);
        }
    }
}