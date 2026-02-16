package com.LMS.library.controller;

import com.LMS.library.dtos.AuthorDTO;
import com.LMS.library.dtos.AuthorPdfDTO;
import com.LMS.library.model.Author;
import com.LMS.library.service.author.AuthorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {


    @Autowired
    private final AuthorService authorService;


    @GetMapping
    public String getAllAuthors(Model model){
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors",authors);
        return "author/author_list";
    }

    @GetMapping("/get/{id}")
    public String getAuthorById(@PathVariable Long id , Model model){
        AuthorDTO author = authorService.getAuthorById(id);
        if(author==null)
            return "redirect:/author";

        model.addAttribute("author",author);
        return "author/author_by_id";
    }

    @GetMapping("/get/details/{id}")
    public ResponseEntity<byte[]> downloadDetailsPdf(@PathVariable Long id){
        AuthorDTO author = authorService.getAuthorById(id);

        String bookNames = author.getBooks().stream()
                .map(book -> book.getName())
                .collect(Collectors.joining(", "));

        // Create a new DTO with formatted books
        AuthorPdfDTO pdfData = new AuthorPdfDTO(
                author.getId(),
                author.getName(),
                author.getAge(),
                bookNames  // Pass formatted string instead of list
        );
        List<AuthorPdfDTO> list = List.of(pdfData);

        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(list);

        try(InputStream inputStream = new ClassPathResource("/reports/author.jrxml").getInputStream()){

           JasperReport report = JasperCompileManager.compileReport(inputStream);

           JasperPrint jasperPrint = JasperFillManager.fillReport(report , null ,source);

           byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=author.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/author_form";
    }


    @PostMapping("/add")
    public String addAuthor(@Valid @ModelAttribute Author author){
        List<Long> bookIds = List.of();

        try {
            if (author.getBookIdsJson() != null &&
                    !author.getBookIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                bookIds = mapper.readValue(
                        author.getBookIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }

            authorService.saveAuthorWithBooks(author, bookIds);

        } catch (Exception e) {
            System.out.println( e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/author";
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AuthorDTO author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        return "author/edit_author";
    }


    @PostMapping("/put/{id}")
    public String updateAuthor(@Valid @ModelAttribute Author author, @PathVariable Long id) {
        List<Long> bookIds = List.of();

        try {
            if (author.getBookIdsJson() != null &&
                    !author.getBookIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                bookIds = mapper.readValue(
                        author.getBookIdsJson(),
                        new TypeReference<List<Long>>() {
                        }
                );
            }

            authorService.updateAuthorWithBooks(author, bookIds , id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/author";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/author";
    }

}