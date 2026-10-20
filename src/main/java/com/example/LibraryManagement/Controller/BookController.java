package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Dto.BookDTO;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@RestController
@RequestMapping("/api/Books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getallbooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/getbookbyid/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/addbook")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }
@PutMapping("updateBook/{id}")
@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO ) {
    return ResponseEntity.ok(bookService.updateBoko(id,bookDTO));

    }
    @DeleteMapping("/deletebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return  ResponseEntity.ok().build();

    }
}