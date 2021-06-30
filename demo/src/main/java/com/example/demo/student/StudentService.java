package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(long studentID) {

        boolean exists = studentRepository.existsById(studentID);
        if (!exists){
            throw new IllegalStateException("student with given id" + studentID + "does not exist");
        }
        studentRepository.deleteById(studentID);
    }

    @Transactional
    public void modifyStudent(long studentId, String name, String email) {
        Student s = studentRepository.findById(studentId).orElseThrow(
                ()-> new IllegalStateException("student with given id"+studentId + "doesn`t exist"));
        if (name!=null &&
                name.length()>0 &&
                !Objects.equals(s.getName(),name)){
            s.setName(name);
        }
        if(email!=null &&
        email.length()>0 &&
        !Objects.equals(email,s.getEmail())){
            Optional<Student> student = studentRepository.findStudentByEmail(email);
            if (student.isPresent()){
                throw new IllegalStateException("student with given email already exists");
            }
            s.setEmail(email);
        }
    }

    public Student getStudent(long studentId) {
        Student s = studentRepository.findById(studentId).orElseThrow(
                ()-> new IllegalStateException(("Student with given id doesn`t exist")));
        return s;
    }
}
