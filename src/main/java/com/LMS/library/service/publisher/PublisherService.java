package com.LMS.library.service.publisher;

import com.LMS.library.model.Publisher;
import com.LMS.library.model.User;

import java.util.List;

public interface PublisherService {
    List<Publisher> getPublishers();

    Publisher getPublisherById(Long id);

    void savePublisher(Publisher publisher);

    void updatePublisher(Publisher publisher, Long id);

    void deletePublisher(Long id);
}