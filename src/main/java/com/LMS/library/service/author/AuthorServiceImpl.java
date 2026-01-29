package com.LMS.library.service.author;

import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Author;
import com.LMS.library.model.Book;
import com.LMS.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;
    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    @Override
    public Author saveAuthor(Author author) {
        if (author.getBooks() != null) {
            for (Book book : author.getBooks()) {
                book.setAuthors(List.of(author));
            }
        }
        return authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Author author, Long id) {
      Author savedAuthor = authorRepository.findById(id)
                                           .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuthorId" , id));
      author.setId(savedAuthor.getId());
        if (author.getBooks() != null) {
            for (Book book : author.getBooks()) {
                book.setAuthors(List.of(author));
            }
        }
      authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author savedAuthor = authorRepository.findById(id)
                                             .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuthorId" , id));
        authorRepository.delete(savedAuthor);
    }
}
