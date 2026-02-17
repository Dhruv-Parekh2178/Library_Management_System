package com.LMS.library.controller;

import com.LMS.library.dtos.PublisherDTO;
import com.LMS.library.dtos.PublisherRequestDTO;
import com.LMS.library.model.ApiResponse;
import com.LMS.library.model.Publisher;
import com.LMS.library.service.book.BookService;
import com.LMS.library.service.publisher.PublisherService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;
    private final BookService bookService;

    @GetMapping
    public String getAllPublishers(Model model){
        List<PublisherDTO> publishers = publisherService.getPublishers();
        model.addAttribute("publishers" , publishers);
        return "publisher/publisher_list";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<PublisherDTO>>> getAllPublishersData(Model model){
        List<PublisherDTO> publishers = publisherService.getPublishers();
        return ResponseEntity.ok(new ApiResponse<>(true , "success" , publishers));
    }

    @GetMapping("/get/{id}")
    public String getPublisherById(@PathVariable Long id , Model model){
        PublisherDTO publisher = publisherService.getPublisherById(id);
        if(publisher==null)
            return "redirect:/publisher";
        model.addAttribute("publisher"  , publisher);
        return "publisher/publisher_by_id";
    }

    @GetMapping("/get/{id}/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<PublisherDTO>> getPublisherByIdData(@PathVariable Long id ){
        PublisherDTO publisher = publisherService.getPublisherById(id);
        if(publisher==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false ,"fail" , null));
        return ResponseEntity.ok(new ApiResponse<>(true, "success" , publisher));
    }

    @GetMapping("/add")
    public String showAddPublisherForm(Model model) {
        model.addAttribute("publisher", new PublisherRequestDTO());
        model.addAttribute("books", bookService.getbooks());
        return "publisher/publisher_form";
    }

    @GetMapping("/add/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> addPublisherData(){
        return ResponseEntity.ok(new ApiResponse<>(true,"success","Publisher added successfully."));
    }

    @PostMapping("/add")
    public String addPublisher(@Valid @ModelAttribute PublisherRequestDTO publisherRequestDTO){
            publisherService.savePublisherWithBooks(publisherRequestDTO, publisherRequestDTO.getBookIds());

        return "redirect:/publisher";
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        PublisherDTO publisher = publisherService.getPublisherById(id);
        model.addAttribute("publisher", publisher);
        model.addAttribute("books", bookService.getbooks());
        return "publisher/edit_publisher";
    }

    @GetMapping("/put/{id}/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> updatePublisherData(@PathVariable Long id){
        PublisherDTO savedPublisher = publisherService.getPublisherById(id);
        if(savedPublisher == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Publisher with id " + id + " is not found." ));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Publisher with id " + id + " updated successfully."));
    }

    @PostMapping("/put/{id}")
    public String updatePublisher(@Valid @ModelAttribute PublisherRequestDTO publisherRequestDTO, @PathVariable Long id){
            publisherService.updatePublisherWithBooks(publisherRequestDTO, publisherRequestDTO.getBookIds() , id);

        return "redirect:/publisher";
    }

    @GetMapping("/delete/{id}")
    public String DeleteAuthor(@PathVariable Long id){
        publisherService.deletePublisher(id);
        return "redirect:/publisher";
    }

    @GetMapping("/delete/{id}/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> DeleteAuthorData(@PathVariable Long id){
        PublisherDTO savedPublisher = publisherService.getPublisherById(id);
        if(savedPublisher == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Publisher with id " + id + " is not found." ));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Publisher with id " + id + " deleted successfully."));
    }
}