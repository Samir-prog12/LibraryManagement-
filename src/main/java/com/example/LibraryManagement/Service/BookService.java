package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.BookDTO;
import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks() {
        return  bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Book book  = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found with id: " + id));
        return book;
    }

    public Book addBook(BookDTO bookDTO){
        Book book  = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setIsAvailable(bookDTO.getIsAvailable());
        book.setQuantity(bookDTO.getQuantity());
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());

       return bookRepository.save(book);

    }


    public Book updateBoko(Long id, BookDTO bookDTO) {

        Book oldBook  = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found with id : " + id));

         oldBook.setAuthor(bookDTO.getAuthor());
         oldBook.setIsAvailable(bookDTO.getIsAvailable());
         oldBook.setQuantity(bookDTO.getQuantity());
         oldBook.setIsbn(bookDTO.getIsbn());
         oldBook.setTitle(bookDTO.getTitle());


         return bookRepository.save(oldBook);

    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
