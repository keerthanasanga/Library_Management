package com.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.management.entity.Book;
import com.library.management.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(
    origins = "http://127.0.0.1:5501",
    methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.OPTIONS
    }
)
public class BookController {

    @Autowired
    BookService service;

    @PostMapping
    public Book addBook(@RequestBody Book book){

        return service.addBook(book);

    }

    @GetMapping
    public List<Book> getBooks(){

        return service.getAllBooks();

    }

    @PutMapping("/{id}")
public Book updateBook(@PathVariable Long id,
                       @RequestBody Book book) {

    return service.updateBook(id, book);

}
@DeleteMapping("/{id}")
public void deleteBook(@PathVariable Long id){

    service.deleteBook(id);

}

@GetMapping("/search")
public List<Book> searchBooks(@RequestParam String keyword) {

    return service.searchBooks(keyword);

}
}


