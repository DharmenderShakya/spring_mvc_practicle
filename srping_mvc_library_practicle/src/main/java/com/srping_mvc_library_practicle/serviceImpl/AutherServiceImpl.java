package com.srping_mvc_library_practicle.serviceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.srping_mvc_library_practicle.entity.Author;
import com.srping_mvc_library_practicle.service.AutherService;

@Service
public class AutherServiceImpl implements AutherService {

	@Override
	public Page<Author> getAllAuthors(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author getAuthorById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author createAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author updateAuthor(Long id, Author updatedAuthor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAuthor(Long id) {
		// TODO Auto-generated method stub
		
	}

}
