package com.LMS.library.service.publisher;

import com.LMS.library.dtos.PublisherDTO;
import com.LMS.library.model.Publisher;
import com.LMS.library.model.User;

import java.util.List;

public interface PublisherService {
    List<Publisher> getPublishers();

    PublisherDTO getPublisherById(Long id);



    void deletePublisher(Long id);

    void savePublisherWithBooks(Publisher publisher, List<Long> bookIds);

    void updatePublisherWithBooks(Publisher publisher, List<Long> bookIds, Long id);
}