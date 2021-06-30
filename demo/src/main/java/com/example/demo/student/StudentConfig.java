package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student s1 = new Student(1L,
                    "Spandan",
                    LocalDate.of(1997, Month.DECEMBER, 10),
                    "spandanpolu@gmail.com");
            Student s2 = new Student(
                    "Spandan1",
                    LocalDate.of(1997, Month.DECEMBER, 10),
                    "spandanpolu1@gmail.com");

            studentRepository.saveAll(List.of(s1,s2));
        };
    }
}
