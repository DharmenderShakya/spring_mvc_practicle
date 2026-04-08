package com.srping_mvc_library_practicle.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.srping_mvc_library_practicle.entity.Author;

public interface AutherService {

     Page<Author> getAllAuthors(Pageable pageable);

     Author getAuthorById(Long id) ;

     Author createAuthor(Author author) ;

     Author updateAuthor(Long id, Author updatedAuthor);

     void deleteAuthor(Long id) ;
}
