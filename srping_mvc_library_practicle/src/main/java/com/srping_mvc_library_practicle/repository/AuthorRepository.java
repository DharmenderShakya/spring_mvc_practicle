package com.srping_mvc_library_practicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srping_mvc_library_practicle.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}