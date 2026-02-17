package com.LMS.library.controller;

import com.LMS.library.dtos.*;
import com.LMS.library.model.ApiResponse;
import com.LMS.library.model.Author;
import com.LMS.library.service.author.AuthorService;
import com.LMS.library.service.book.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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



    private final AuthorService authorService;
    private final BookService bookService;
    private final ModelMapper modelMapper;


    @GetMapping
    public String getAllAuthors(Model model){
        List<AuthorDTO> authors = authorService.getAuthors();
        model.addAttribute("authors",authors);
        return "author/author_list";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<AuthorDTO>>> getAllAuthorsData(){
        List<AuthorDTO> authors = authorService.getAuthors();
        return ResponseEntity.ok(new ApiResponse<>(true , "success" , authors));
    }

    @GetMapping("/get/allDetails")
    public ResponseEntity<byte[]> downloadAllDetailsPdf() {

        List<AuthorDTO> authors = authorService.getAuthors();

        List<AuthorPdfDTO> list = authors.stream()
                .map(author -> {

                    String bookNames = author.getBooks().stream()
                            .map(book -> book.getName())
                            .collect(Collectors.joining(", "));

                    return new AuthorPdfDTO(
                            author.getId(),
                            author.getName(),
                            author.getAge(),
                            bookNames
                    );
                })
                .collect(Collectors.toList());

        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(list);

        try (InputStream inputStream =
                     new ClassPathResource("reports/author.jrxml").getInputStream()) {

            JasperReport report = JasperCompileManager.compileReport(inputStream);

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(report, null, source);

            byte[] bytes =
                    JasperExportManager.exportReportToPdf(jasperPrint);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=authors.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(bytes);

        } catch (IOException | JRException e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }


    @GetMapping("/get/{id}")
    public String getAuthorById(@PathVariable Long id , Model model){
        AuthorDTO author = authorService.getAuthorById(id);
        if(author==null)
            return "redirect:/author";

        model.addAttribute("author",author);
        return "author/author_by_id";
    }

    @GetMapping("/get/{id}/data")
    public ResponseEntity<ApiResponse<AuthorDTO>> getAuthorByIdData(@PathVariable Long id){
        AuthorDTO author = authorService.getAuthorById(id);
        if(author==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,"fail",null));

        return ResponseEntity.ok(new ApiResponse<>(true , "success" , author));
    }

    @GetMapping("/get/details/{id}")
    public ResponseEntity<byte[]> downloadDetailsPdf(@PathVariable Long id){
        AuthorDTO author = authorService.getAuthorById(id);

        String bookNames = author.getBooks().stream()
                .map(book -> book.getName())
                .collect(Collectors.joining(", "));


        AuthorPdfDTO pdfData = new AuthorPdfDTO(
                author.getId(),
                author.getName(),
                author.getAge(),
                bookNames
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
        model.addAttribute("author", new AuthorRequestDTO());
        model.addAttribute("books", bookService.getbooks());
        return "author/author_form";
    }


    @PostMapping("/add")
    public String addAuthor(@Valid @ModelAttribute AuthorRequestDTO authorRequestDTO){
        authorService.saveAuthorWithBooks(
                authorRequestDTO,
                authorRequestDTO.getBookIds()
        );

        return "redirect:/author";
    }

    @GetMapping("add/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> addAuthorData(){
        return ResponseEntity.ok(new ApiResponse<>(true,"success","Author added successfully."));
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AuthorRequestDTO authorRequestDTO = modelMapper.map(authorService.getAuthorById(id), AuthorRequestDTO.class);
        model.addAttribute("author", authorRequestDTO);
        model.addAttribute("books", bookService.getbooks());
        return "author/edit_author";
    }


    @PostMapping("/put/{id}")
    public String updateAuthor(@Valid @ModelAttribute AuthorRequestDTO authorRequestDTO, @PathVariable Long id) {
        authorService.updateAuthorWithBooks(
                authorRequestDTO,
                authorRequestDTO.getBookIds(),
                id
        );

        return "redirect:/author";
    }

    @GetMapping("/put/{id}/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> updateAuthorData(@PathVariable Long id){
        AuthorDTO savedAuthor = authorService.getAuthorById(id);
        if(savedAuthor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Author with id " + id + " is not found." ));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Author with id " + id + " updated successfully."));
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/author";
    }

    @GetMapping("/delete/{id}/data")
    public ResponseEntity<ApiResponse<String>> deleteAuthorData(@PathVariable Long id) {
        AuthorDTO savedAuthor = authorService.getAuthorById(id);
        if(savedAuthor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Author with id " + id + " is not found." ));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Author with id " + id + " deleted successfully."));
    }

}