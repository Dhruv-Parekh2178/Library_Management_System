package com.LMS.library.service.author;

import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Author;
import com.LMS.library.model.Book;
import com.LMS.library.repository.AuthorRepository;
import com.LMS.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Author> getAuthors() {
        List<Author> authors = authorRepository.findAll().stream()
                .filter(author -> !author.isDeleted()).toList();
        return authors;
    }

    @Override
    public Author getAuthorById(Long id){
        return authorRepository.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuhtorId" , id));
    }



    @Override
    public void deleteAuthor(Long id) {
        Author savedAuthor = authorRepository.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuthorId" , id));

        savedAuthor.setDeleted(true);
        authorRepository.save(savedAuthor);
    }

    @Override
    @Transactional
    public void saveAuthorWithBooks(Author author, List<Long> bookIds) {
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        author.setBooks(books);

        authorRepository.save(author);
    }

    @Override
    @Transactional
    public void updateAuthorWithBooks(Author author, List<Long> bookIds , Long id) {
        Author savedAuthor = authorRepository.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "AuthorId", id));


        savedAuthor.setName(author.getName());
        savedAuthor.setAge(author.getAge());
        savedAuthor.setDeleted(author.isDeleted());
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        savedAuthor.setBooks(books);

        authorRepository.save(savedAuthor);
    }
}