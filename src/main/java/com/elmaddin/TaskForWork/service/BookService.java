package com.elmaddin.TaskForWork.service;


import com.elmaddin.TaskForWork.dto.BookResponse;
import com.elmaddin.TaskForWork.dto.BookSaveRequest;
import com.elmaddin.TaskForWork.exception.GenericException;
import com.elmaddin.TaskForWork.model.BookEntity;
import com.elmaddin.TaskForWork.model.UserEntity;
import com.elmaddin.TaskForWork.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserService userService;
    private final AuthService authService;

    @Transactional
    public BookResponse saveBook(BookSaveRequest bookSaveRequest) {
        UserEntity user = userService.findUserByUsername(authService.getLoggedInUsername());
        final BookEntity book = BookEntity.builder()
                .name(bookSaveRequest.getName())
                .price(bookSaveRequest.getPrice())
                .author(bookSaveRequest.getAuthor())
                .pageCount(bookSaveRequest.getPageCount())
                .build();
        final BookEntity fromDb = bookRepository.save(book);
        return BookResponse.builder()
                .id(fromDb.getId())
                .name(fromDb.getName())
                .price(fromDb.getPrice())
                .author(fromDb.getAuthor())
                .pageCount(fromDb.getPageCount())
                .build();
    }

    public List<BookResponse> getAllBook() {
        return bookRepository.findAll().stream().map(
                        model -> BookResponse.builder()
                                .id(model.getId())
                                .name(model.getName())
                                .price(model.getPrice())
                                .author(model.getAuthor())
                                .pageCount(model.getPageCount())
                                .build())
                .collect(Collectors.toList());

    }

    public BookResponse getBookByName(String name) {
        final BookEntity fromDb = bookRepository.findByName(name);
        return BookResponse.builder()
                .id(fromDb.getId())
                .name(fromDb.getName())
                .price(fromDb.getPrice())
                .author(fromDb.getAuthor())
                .pageCount(fromDb.getPageCount())
                .build();
    }

    public BookResponse getById(Long id) {
        final BookEntity fromDb = bookRepository.findById(id).orElseThrow(GenericException::new);
        return BookResponse.builder()
                .id(fromDb.getId())
                .name(fromDb.getName())
                .price(fromDb.getPrice())
                .author(fromDb.getAuthor())
                .pageCount(fromDb.getPageCount())
                .build();
    }
    public List<BookResponse> getByUserId() {
        UserEntity user = userService.findUserByUsername(authService.getLoggedInUsername());
        return bookRepository.findByUserId(user.getId()).stream().map(
                        model -> BookResponse.builder()
                                .id(model.getId())
                                .name(model.getName())
                                .price(model.getPrice())
                                .author(model.getAuthor())
                                .pageCount(model.getPageCount())
                                .build())
                .collect(Collectors.toList());
    }

    public void updateBook(Long id, BookSaveRequest request) {
        final BookEntity fromDb = bookRepository.findById(id).orElseThrow(GenericException::new);
        fromDb.setName(request.getName());
        fromDb.setAuthor(request.getAuthor());
        fromDb.setPrice(request.getPrice());
        fromDb.setPageCount(request.getPageCount());
        bookRepository.save(fromDb);
    }

    public void deleteBook(Long id) {
        final BookEntity fromDb = bookRepository.findById(id).orElseThrow(GenericException::new);
        bookRepository.delete(fromDb);
    }


}