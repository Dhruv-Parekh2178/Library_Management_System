package com.LMS.library.controller;

import com.LMS.library.model.Author;
import com.LMS.library.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;


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
        Author author = authorService.getAuthorById(id);
        if(author==null)
            return "redirect:/author";

        model.addAttribute("author",author);
        return "author/author_by_id";
    }

    @GetMapping("/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/author_form";
    }


    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author){
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
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        return "author/edit_author";
    }


    @PostMapping("/put/{id}")
    public String updateAuthor(@ModelAttribute Author author, @PathVariable Long id) {
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