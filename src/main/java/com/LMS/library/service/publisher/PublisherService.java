package com.LMS.library.service.publisher;

import com.LMS.library.dtos.PublisherDTO;
import com.LMS.library.dtos.PublisherRequestDTO;
import com.LMS.library.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<PublisherDTO> getPublishers();

    PublisherDTO getPublisherById(Long id);



    void deletePublisher(Long id);

    void savePublisherWithBooks(PublisherRequestDTO publisherRequestDTO, List<Long> bookIds);

    PublisherDTO updatePublisherWithBooks(PublisherRequestDTO publisherRequestDTO, List<Long> bookIds, Long id);
}