package com.elmaddin.TaskForWork.controller;


import com.elmaddin.TaskForWork.BookService;
import com.elmaddin.TaskForWork.dto.BookResponse;
import com.elmaddin.TaskForWork.dto.BookSaveRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public ResponseEntity<BookResponse> saveBook(@RequestBody BookSaveRequest bookSaveRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.saveBook(bookSaveRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookResponse>> getAllBook() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getAllBook());
    }

    @GetMapping("/{name}")
    public ResponseEntity<BookResponse> searchByName(@PathVariable String name) {
        return ResponseEntity.ok(this.bookService.getBookByName(name));
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateBook(@PathVariable Long id, @RequestBody BookSaveRequest request) {
        bookService.updateBook(id, request);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }


}
