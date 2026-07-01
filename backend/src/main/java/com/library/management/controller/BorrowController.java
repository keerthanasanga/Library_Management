package com.library.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.management.entity.Borrow;
import com.library.management.service.BorrowService;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public Borrow borrowBook(@RequestParam Long studentId,
                             @RequestParam Long bookId) {

        return borrowService.borrowBook(studentId, bookId);
    }

  @GetMapping("/{studentId}")
public List<Borrow> borrowedBooks(@PathVariable Long studentId) {

    return borrowService.getBorrowedBooks(studentId);

}

@PutMapping("/return/{borrowId}")
public Borrow returnBook(@PathVariable Long borrowId){

    return borrowService.returnBook(borrowId);

}
@GetMapping("/history/{studentId}")
public List<Borrow> borrowHistory(@PathVariable Long studentId) {

    return borrowService.getBorrowHistory(studentId);

}

}