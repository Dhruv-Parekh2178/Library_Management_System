package com.LMS.library.service.category;

import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Author;
import com.LMS.library.model.Book;
import com.LMS.library.model.Category;
import com.LMS.library.repository.BookRepository;
import com.LMS.library.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category" ,"CategoryId" , id));
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getBooks() != null) {
            List<Book> managedBooks = new ArrayList<>();
            for (Book book : category.getBooks()) {
                Book managedBook = bookRepository.findBooksById(book.getId())
                        .orElseThrow(()-> new ResourceNotFoundException("Book"  , "bookId" , book.getId()));
                managedBooks.add(managedBook);
            }
            category.setBooks(managedBooks);
        }
        categoryRepository.save(category);
    }
    @Transactional
    @Override
    public void updateCategory(Category category, Long id) {
        Category savedCategory = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "AuthorId", id));


        savedCategory.setName(category.getName());

        savedCategory.setDeleted(category.isDeleted());

        if (category.getBooks() != null) {
            List<Book> managedBooks = new ArrayList<>();
            for (Book book : category.getBooks()) {
                Book managedBook = bookRepository.findBooksById(book.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Book", "bookId", book.getId()));
                managedBooks.add(managedBook);
            }
            savedCategory.setBooks(managedBooks);
        }

        categoryRepository.save(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category savedCategory = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuthorId" , id));

        savedCategory.setDeleted(true);
        categoryRepository.save(savedCategory);
    }
}
