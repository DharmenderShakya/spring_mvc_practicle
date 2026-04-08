package com.srping_mvc_library_practicle.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.srping_mvc_library_practicle.entity.Book;

public interface BookService {

    //  Get all books with pagination
    Page<Book> getAllBooks(Pageable pageable);

    //  Get book by ID
    Book getBook(Long id);

    //  Create book
    Book createBook(Book book);

    //  Update book
    Book updateBook(Long id, Book updatedBook);

    //  Delete book
    void deleteBook(Long id);
}