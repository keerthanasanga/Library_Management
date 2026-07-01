package com.library.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entity.Student;
import com.library.management.repository.StudentRepository;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public boolean login(String email, String password) {

        Optional<Student> student = studentRepository.findByEmail(email);

        if (student.isPresent()) {
            return student.get().getPassword().equals(password);
        }

        return false;
    }

    public Student getStudent(Long id) {

    return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));

}

public Student updateStudent(Long id, Student updatedStudent) {

    Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));

    student.setName(updatedStudent.getName());
    student.setEmail(updatedStudent.getEmail());
    student.setPhone(updatedStudent.getPhone());
    student.setDepartment(updatedStudent.getDepartment());
    student.setYear(updatedStudent.getYear());

    return studentRepository.save(student);

}

public long totalStudents() {
    return studentRepository.count();
}


public Student getStudentByEmail(String email) {

    return studentRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Student not found"));

}

public List<Student> getAllStudents() {

    return studentRepository.findAll();

}



public List<Student> getAllStudents() {
    return studentRepository.findAll();
}

public Student addStudent(Student student) {
    return studentRepository.save(student);
}

public void deleteStudent(Long id) {
    studentRepository.deleteById(id);
}

public List<Student> searchStudents(String keyword) {

    return studentRepository
            .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    keyword,
                    keyword);

}
}