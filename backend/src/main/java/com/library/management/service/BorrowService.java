package com.library.management.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entity.Book;
import com.library.management.entity.Borrow;
import com.library.management.entity.Student;
import com.library.management.repository.BookRepository;
import com.library.management.repository.BorrowRepository;
import com.library.management.repository.StudentRepository;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    public Borrow borrowBook(Long studentId, Long bookId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableQuantity() <= 0) {
            throw new RuntimeException("Book is not available");
        }

        Borrow borrow = new Borrow();

        borrow.setStudent(student);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusDays(14));
        borrow.setStatus("BORROWED");
        borrow.setFine(0);

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);

        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }

    
public Borrow returnBook(Long borrowId) {

    Borrow borrow = borrowRepository.findById(borrowId)
            .orElseThrow(() -> new RuntimeException("Borrow record not found"));

    if (borrow.getStatus().equals("RETURNED")) {
        throw new RuntimeException("Book already returned");
    }

    borrow.setReturnDate(LocalDate.now());

    borrow.setStatus("RETURNED");

    Book book = borrow.getBook();

    book.setAvailableQuantity(book.getAvailableQuantity() + 1);

    long lateDays =
            ChronoUnit.DAYS.between(
                    borrow.getDueDate(),
                    LocalDate.now());

    if (lateDays > 0) {

        borrow.setFine(lateDays * 5);

    } else {

        borrow.setFine(0);

    }

    bookRepository.save(book);

    return borrowRepository.save(borrow);

}
public List<Borrow> getBorrowedBooks(Long studentId) {

    return borrowRepository.findByStudentStudentIdAndStatus(
            studentId,
            "BORROWED");

}
public List<Borrow> getBorrowHistory(Long studentId) {

    return borrowRepository.findByStudentStudentId(studentId);

}
public long borrowedBooksCount() {
    return borrowRepository.countByStatus("BORROWED");
}

public long returnedBooksCount() {
    return borrowRepository.countByStatus("RETURNED");
}

public double totalFineCollected() {

    Double fine = borrowRepository.totalFineCollected();

    return fine == null ? 0 : fine;
}

}