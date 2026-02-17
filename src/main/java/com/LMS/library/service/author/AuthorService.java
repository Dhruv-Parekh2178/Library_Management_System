package com.LMS.library.service.author;

import com.LMS.library.dtos.AuthorDTO;
import com.LMS.library.dtos.AuthorRequestDTO;
import com.LMS.library.model.Author;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAuthors();

    AuthorDTO getAuthorById(Long id);

    void deleteAuthor(Long id);

    void saveAuthorWithBooks(AuthorRequestDTO authorRequestDTO, List<Long> bookIds);

    AuthorDTO updateAuthorWithBooks(AuthorRequestDTO authorRequestDTO, List<Long> bookIds , Long id);
}