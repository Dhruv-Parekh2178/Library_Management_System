package com.LMS.library.controller;

import com.LMS.library.model.ApiResponse;
import com.LMS.library.model.Publisher;
import com.LMS.library.model.User;
import com.LMS.library.service.publisher.PublisherService;
import com.LMS.library.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
//@Controller
@RequestMapping("/library")
@RequiredArgsConstructor
public class PublisherController {
    @Autowired
    private final PublisherService publisherService;

    @GetMapping("/publisher/getAll")
    public ResponseEntity<ApiResponse<List<Publisher>>> getAllPublishers(){
        List<Publisher> publishers = publisherService.getPublishers();
        return ResponseEntity.ok(new ApiResponse<>(true , "success" , publishers));
    }

    @GetMapping("/publisher/get/{id}")
    public ResponseEntity<ApiResponse<Publisher>> getPublisherById(@PathVariable Long id){
        Publisher publisher = publisherService.getPublisherById(id);
        if(publisher==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false ,"fail" , null));
        return ResponseEntity.ok(new ApiResponse<>(true, "success" , publisher));
    }

    @PostMapping("/publisher/add")
    public ResponseEntity<ApiResponse<String>> addPublisher(@RequestBody Publisher publisher){
        publisherService.savePublisher(publisher);
        return ResponseEntity.ok(new ApiResponse<>(true,"success","Publisher added successfully."));
    }

    @PostMapping("/publisher/put/{id}")
    public ResponseEntity<ApiResponse<String>> updatePublisher(@RequestBody Publisher publisher , @PathVariable Long id){
        Publisher savedPublisher = publisherService.getPublisherById(id);
        if(savedPublisher == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Publisher with id " + id + " is not found." ));
        }
        publisherService.updatePublisher(publisher , id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Publisher with id " + id + " updated successfully."));
    }

    @PostMapping("/publisher/delete/{id}")
    public ResponseEntity<ApiResponse<String>> DeleteAuthor(@PathVariable Long id){
        Publisher savedPublisher = publisherService.getPublisherById(id);
        if(savedPublisher == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Publisher with id " + id + " is not found." ));
        }
        publisherService.deletePublisher(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Publisher with id " + id + " deleted successfully."));
    }
}
