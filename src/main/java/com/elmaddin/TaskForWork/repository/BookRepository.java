package com.elmaddin.TaskForWork.repository;


import com.elmaddin.TaskForWork.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity ,Long> {
    BookEntity findByName(String name);
}
