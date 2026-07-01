package com.library.management.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.management.service.BookService;
import com.library.management.service.BorrowService;
import com.library.management.service.StudentService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/stats")
    public Map<String, Object> stats() {

        Map<String, Object> stats = new HashMap<>();

        stats.put("totalBooks", bookService.totalBooks());
        stats.put("availableBooks", bookService.availableBooks());
        stats.put("borrowedBooks", borrowService.borrowedBooksCount());
        stats.put("returnedBooks", borrowService.returnedBooksCount());
        stats.put("totalStudents", studentService.totalStudents());
        stats.put("totalFine", borrowService.totalFineCollected());

        return stats;
    }
}