package com.elmaddin.TaskForWork.repository;


import com.elmaddin.TaskForWork.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity ,Long> {
    BookEntity findByName(String name);
    List<BookEntity> findByUserId(Long id);
}
