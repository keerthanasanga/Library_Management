package com.library.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.management.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findByStudentStudentIdAndStatus(Long studentId, String status);
    List<Borrow> findByStudentStudentId(Long studentId);
    long countByStatus(String status);
    @Query("SELECT SUM(b.fine) FROM Borrow b")
Double totalFineCollected();

}