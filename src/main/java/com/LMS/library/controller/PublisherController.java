package com.LMS.library.controller;

import com.LMS.library.model.Publisher;
import com.LMS.library.model.User;
import com.LMS.library.service.publisher.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;


@Controller
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {
    @Autowired
    private final PublisherService publisherService;

    @GetMapping
    public String getAllPublishers(Model model){
        List<Publisher> publishers = publisherService.getPublishers();
        model.addAttribute("publishers" , publishers);
        return "publisher/publisher_list";
    }

    @GetMapping("/get/{id}")
    public String getPublisherById(@PathVariable Long id , Model model){
        Publisher publisher = publisherService.getPublisherById(id);
        if(publisher==null)
            return "redirect:/publisher";
        model.addAttribute("publisher"  , publisher);
        return "publisher/publisher_by_id";
    }

    @GetMapping("/add")
    public String showAddPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher/publisher_form";
    }

    @PostMapping("/add")
    public String addPublisher(@Valid @ModelAttribute Publisher publisher){
        List<Long> bookIds = List.of();

        try {
            if (publisher.getBookIdsJson() != null &&
                    !publisher.getBookIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                bookIds = mapper.readValue(
                        publisher.getBookIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }

            publisherService.savePublisherWithBooks(publisher, bookIds);

        } catch (Exception e) {
            System.out.println( e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/publisher";
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Publisher publisher = publisherService.getPublisherById(id);
        model.addAttribute("user", publisher);
        return "publisher/edit_publisher";
    }

    @PostMapping("/put/{id}")
    public String updatePublisher(@Valid @ModelAttribute Publisher publisher , @PathVariable Long id){
        List<Long> bookIds = List.of();

        try {
            if (publisher.getBookIdsJson() != null &&
                    !publisher.getBookIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                bookIds = mapper.readValue(
                        publisher.getBookIdsJson(),
                        new TypeReference<List<Long>>() {
                        }
                );
            }

            publisherService.updatePublisherWithBooks(publisher, bookIds , id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/publisher";
    }

    @GetMapping("/delete/{id}")
    public String DeleteAuthor(@PathVariable Long id){
        publisherService.deletePublisher(id);
        return "redirect:/publisher";
    }
}