package com.example.book_store.BookStore;

import com.example.book_store.Exception.BookstoreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/bookstore")
public class BookstoreController {
    @Autowired
    private BookstoreRepository bookstoreRepository;

    //get all books
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookstoreRepository.findAll();
    }

    //add book
    @PostMapping("/addBook")
    public Book createBook(@RequestBody Book book){
        return bookstoreRepository.save(book);
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,@RequestBody Book bookDetails){

        Book book = bookstoreRepository.findById(id)
                .orElseThrow(()->new BookstoreNotFoundException("Book with id "+id+" not found"));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setCost(bookDetails.getCost());
        book.setPublisher(bookDetails.getPublisher());
        book.setYear(bookDetails.getYear());

        Book updatedBook = bookstoreRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    // delete book
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteBook(@PathVariable Long id){
        Book book = bookstoreRepository.findById(id)
                .orElseThrow(()->new BookstoreNotFoundException("Book with id "+id+" not found"));

        bookstoreRepository.delete(book);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = bookstoreRepository.findById(id)
                .orElseThrow(()->new BookstoreNotFoundException("Book with id "+id+" not found"));

        return ResponseEntity.ok(book);
    }
    


}
