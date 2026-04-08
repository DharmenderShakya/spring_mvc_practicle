package com.srping_mvc_library_practicle.serviceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.srping_mvc_library_practicle.entity.Book;
import com.srping_mvc_library_practicle.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Override
	public Page<Book> getAllBooks(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBook(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book createBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book updateBook(Long id, Book updatedBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(Long id) {
		// TODO Auto-generated method stub
		
	}

}
