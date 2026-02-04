package com.LMS.library.service.publisher;

import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Book;
import com.LMS.library.model.Category;
import com.LMS.library.model.Publisher;
import com.LMS.library.model.User;
import com.LMS.library.repository.BookRepository;
import com.LMS.library.repository.PublisherRepository;
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
        List<Publisher> publishers = publisherRepository.findAll().stream()
                .filter(publisher -> !publisher.isDeleted()).toList();
        return publishers;
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepository.findPublisherById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Publisher" ,"PublisherId" , id));
    }

    @Override
    public void deletePublisher(Long id) {
        Publisher savedPublisher = publisherRepository.findPublisherById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher" , "PublisherId" , id));

        savedPublisher.setDeleted(true);
        publisherRepository.save(savedPublisher);
    }

    @Override
    public void savePublisherWithBooks(Publisher publisher, List<Long> bookIds) {
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        publisher.setBooks(books);

        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisherWithBooks(Publisher publisher, List<Long> bookIds, Long id) {
        Publisher savedPublisher = publisherRepository.findPublisherById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CatgoryId", id));


        savedPublisher.setName(publisher.getName());

        savedPublisher.setDeleted(publisher.isDeleted());
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        savedPublisher.setBooks(books);

        publisherRepository.save(savedPublisher);
    }
}