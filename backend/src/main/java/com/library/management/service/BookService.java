package com.library.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entity.Book;
import com.library.management.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public Book addBook(Book book){

        book.setAvailableQuantity(book.getQuantity());

        return repository.save(book);

    }

    public List<Book> getAllBooks(){

        return repository.findAll();

    }
    public Book updateBook(Long id, Book updatedBook) {

    Book book = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));

    book.setTitle(updatedBook.getTitle());
    book.setAuthor(updatedBook.getAuthor());
    book.setCategory(updatedBook.getCategory());
    book.setIsbn(updatedBook.getIsbn());
    book.setQuantity(updatedBook.getQuantity());
    book.setAvailableQuantity(updatedBook.getAvailableQuantity());
    book.setShelfNo(updatedBook.getShelfNo());

    return repository.save(book);
}

public void deleteBook(Long id){

    repository.deleteById(id);

}



public List<Book> searchBooks(String keyword) {

    List<Book> books = repository.findByTitleContainingIgnoreCase(keyword);

    if (!books.isEmpty()) {
        return books;
    }

    books = repository.findByAuthorContainingIgnoreCase(keyword);

    if (!books.isEmpty()) {
        return books;
    }

    books = repository.findByCategoryContainingIgnoreCase(keyword);

    if (!books.isEmpty()) {
        return books;
    }

    return repository.findByIsbnContainingIgnoreCase(keyword);
}

public long totalBooks() {
    return repository.count();
}

public long availableBooks() {

    return repository.findAll()
            .stream()
            .filter(book -> book.getAvailableQuantity() > 0)
            .count();
}

}
