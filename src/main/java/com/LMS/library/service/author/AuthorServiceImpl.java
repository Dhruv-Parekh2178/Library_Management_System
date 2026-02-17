package com.LMS.library.service.author;

import com.LMS.library.dtos.AuthorDTO;
import com.LMS.library.dtos.AuthorRequestDTO;
import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Author;
import com.LMS.library.model.Book;
import com.LMS.library.repository.AuthorRepository;
import com.LMS.library.repository.BookRepository;
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
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private  final BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override

    public List<AuthorDTO> getAuthors() {
              List<Author> authors = authorRepository.findAll().stream()
                .filter(author -> !author.isDeleted()).toList();

              List<AuthorDTO> authorDTOS = authors.stream()
                      .map(author -> modelMapper.map(author,AuthorDTO.class)).toList();
        return authorDTOS;
    }

    @Override
    @Cacheable(value = "author" , key="#id")
    public AuthorDTO getAuthorById(Long id){
        System.out.println("Fetching author with "+id+" from DB...");
        Author author = authorRepository.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuhtorId" , id));

        return modelMapper.map(author, AuthorDTO.class);
    }


    @Override
    @CacheEvict(value = "author" , key = "#id")
    public void deleteAuthor(Long id) {
        Author savedAuthor = authorRepository.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuthorId" , id));

        savedAuthor.setDeleted(true);
        authorRepository.save(savedAuthor);
    }
    @Override
    @Transactional
    public void saveAuthorWithBooks(AuthorRequestDTO dto, List<Long> bookIds) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setAge(dto.getAge());

        List<Book> books = new ArrayList<>();
        if (bookIds != null && !bookIds.isEmpty()) {
            books = bookRepository.findAllById(bookIds);
            if (books.size() != bookIds.size()) {
                throw new RuntimeException("One or more Book IDs are invalid");
            }
        }

        author.setBooks(books);

        authorRepository.save(author);
        for (Book book : books) {
            book.getAuthors().add(author);
        }
    }

    @Override
    @Transactional
    @CachePut(value = "author", key = "#id")
    public AuthorDTO updateAuthorWithBooks(
            AuthorRequestDTO dto,
            List<Long> bookIds,
            Long id) {

        Author savedAuthor = authorRepository.findAuthorById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Author", "AuthorId", id));


        savedAuthor.setName(dto.getName());
        savedAuthor.setAge(dto.getAge());
        for (Book book : savedAuthor.getBooks()) {
            book.getAuthors().remove(savedAuthor);
        }
        savedAuthor.getBooks().clear();
        List<Book> newBooks = new ArrayList<>();

        if (bookIds != null && !bookIds.isEmpty()) {
            newBooks = bookRepository.findAllById(bookIds);

            if (newBooks.size() != bookIds.size()) {
                throw new RuntimeException("One or more Book IDs are invalid");
            }
        }
        savedAuthor.setBooks(newBooks);
        for (Book book : newBooks) {
            book.getAuthors().add(savedAuthor);
        }
        return modelMapper.map(savedAuthor, AuthorDTO.class);
    }


}
