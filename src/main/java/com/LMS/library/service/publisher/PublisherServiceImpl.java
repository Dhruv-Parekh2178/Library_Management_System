package com.LMS.library.service.publisher;

import com.LMS.library.dtos.PublisherDTO;
import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Book;
import com.LMS.library.model.Category;
import com.LMS.library.model.Publisher;
import com.LMS.library.model.User;
import com.LMS.library.repository.BookRepository;
import com.LMS.library.repository.PublisherRepository;
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
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private final PublisherRepository publisherRepository;

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Publisher> getPublishers() {


        List<Publisher> publishers = publisherRepository.findAll().stream()
                .filter(publisher -> !publisher.isDeleted()).toList();
        return publishers;
    }

    @Override
    @Cacheable(value = "publisher", key = "#id")
    public PublisherDTO getPublisherById(Long id) {
        System.out.println("Fetching publisher with "+id+" from DB...");
        Publisher publisher = publisherRepository.findPublisherById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Publisher" ,"PublisherId" , id));
        return modelMapper.map(publisher, PublisherDTO.class);
    }

    @Override
    public void deletePublisher(Long id) {
        Publisher savedPublisher = publisherRepository.findPublisherById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher" , "PublisherId" , id));

        savedPublisher.setDeleted(true);
        publisherRepository.save(savedPublisher);
    }

    @Override
    @Transactional
    public void savePublisherWithBooks(Publisher publisher, List<Long> bookIds) {
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        publisherRepository.save(publisher);


        for (Book book : books) {
            book.setPublisher(publisher);
        }
    }

    @Override
    @Transactional
    @CachePut(value = "publisher" , key = "#id")
    public void updatePublisherWithBooks(Publisher publisher, List<Long> bookIds, Long id) {
        Publisher savedPublisher = publisherRepository.findPublisherById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Publisher" ,"PublisherId" , id));
        savedPublisher.setName(publisher.getName());

        if (savedPublisher.getBooks() != null) {
            for (Book book : savedPublisher.getBooks()) {
                book.setPublisher(null);
            }
        }

        List<Book> newBooks = bookRepository.findAllById(bookIds);
        if (newBooks.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        for (Book book : newBooks) {
            book.setPublisher(savedPublisher);
        }
    }
}