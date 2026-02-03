package com.LMS.library.controller;

import com.LMS.library.model.Publisher;
import com.LMS.library.service.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/library/publisher")
@RequiredArgsConstructor
public class PublisherController {
    @Autowired
    private final PublisherService publisherService;

    @GetMapping("/getAll")
    public String getAllPublishers(){
        List<Publisher> publishers = publisherService.getPublishers();
        return "";
    }

    @GetMapping("/get/{id}")
    public String getPublisherById(@PathVariable Long id){
        Publisher publisher = publisherService.getPublisherById(id);
        if(publisher==null)
            return "";
        return "";
    }

    @PostMapping("/add")
    public String addPublisher(@RequestBody Publisher publisher){
        publisherService.savePublisher(publisher);
        return "";
    }

    @PostMapping("/put/{id}")
    public String updatePublisher(@RequestBody Publisher publisher , @PathVariable Long id){
        Publisher savedPublisher = publisherService.getPublisherById(id);
        if(savedPublisher == null){
            return "";
        }
        publisherService.updatePublisher(publisher , id);
        return "";
    }

    @PostMapping("/delete/{id}")
    public String DeleteAuthor(@PathVariable Long id){
        Publisher savedPublisher = publisherService.getPublisherById(id);
        if(savedPublisher == null){
            return "";
        }
        publisherService.deletePublisher(id);
        return "";
    }
}