package com.LMS.library.service.publisher;

import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Book;
import com.LMS.library.model.Publisher;
import com.LMS.library.model.User;
import com.LMS.library.repository.BookRepository;
import com.LMS.library.repository.PublisherRepository;
import com.LMS.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private final PublisherRepository publisherRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Override
    public List<Publisher> getPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepository.findPublisherById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Publisher" ,"PublisherId" , id));
    }

    @Override
    public void savePublisher(Publisher publisher) {
        if (publisher.getBooks() != null) {
            List<Book> managedBooks = new ArrayList<>();
            for (Book book : publisher.getBooks()) {
                Book managedBook = bookRepository.findBooksById(book.getId())
                        .orElseThrow(()-> new ResourceNotFoundException("Book"  , "bookId" , book.getId()));
                managedBooks.add(managedBook);
            }
            publisher.setBooks(managedBooks);
        }
        publisherRepository.save(publisher);
    }
    @Transactional
    @Override
    public void updatePublisher(Publisher publisher, Long id) {
        Publisher savedPublisher = publisherRepository.findPublisherById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", id));


        savedPublisher.setName(publisher.getName());

        savedPublisher.setDeleted(publisher.isDeleted());

        if (publisher.getBooks() != null) {
            List<Book> managedBooks = new ArrayList<>();
            for (Book book : publisher.getBooks()) {
                Book managedBook = bookRepository.findBooksById(book.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Book", "bookId", book.getId()));
                managedBooks.add(managedBook);
            }
            savedPublisher.setBooks(managedBooks);
        }

        publisherRepository.save(savedPublisher);
    }

    @Override
    public void deletePublisher(Long id) {
        Publisher savedPublisher = publisherRepository.findPublisherById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher" , "PublisherId" , id));

        savedPublisher.setDeleted(true);
        publisherRepository.save(savedPublisher);
    }
}
