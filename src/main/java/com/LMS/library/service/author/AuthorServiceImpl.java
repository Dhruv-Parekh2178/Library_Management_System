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
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id){
        return authorRepository.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuhtorId" , id));
    }

    @Override
    public Author saveAuthor(Author author) {

        if (author.getBooks() != null) {
            List<Book> managedBooks = new ArrayList<>();
            for (Book book : author.getBooks()) {
                Book managedBook = bookRepository.findBooksById(book.getId())
                                .orElseThrow(()-> new ResourceNotFoundException("Book"  , "bookId" , book.getId()));
                managedBooks.add(managedBook);
            }
            author.setBooks(managedBooks);
        }
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public void updateAuthor(Author author, Long id) {

        Author savedAuthor = authorRepository.findAuthorById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "AuthorId", id));


        savedAuthor.setName(author.getName());
        savedAuthor.setAge(author.getAge());
        savedAuthor.setDeleted(author.isDeleted());

        if (author.getBooks() != null) {
            List<Book> managedBooks = new ArrayList<>();
            for (Book book : author.getBooks()) {
                Book managedBook = bookRepository.findBooksById(book.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Book", "bookId", book.getId()));
                managedBooks.add(managedBook);
            }
            savedAuthor.setBooks(managedBooks);
        }

        authorRepository.save(savedAuthor);
    }


    @Override
    public void deleteAuthor(Long id) {
        Author savedAuthor = authorRepository.findAuthorById(id)
                                             .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuthorId" , id));

        savedAuthor.setDeleted(true);
        authorRepository.save(savedAuthor);
    }
}
