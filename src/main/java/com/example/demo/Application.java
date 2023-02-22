package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student maria = new Student(
                    "Maria",
                    "Johns",
                    "mariajons@gmail.com",
                    21);

            Student adiener = new Student(
                    "Adiener",
                    "Lopez Velazquez",
                    "adienerlopez@gmail.com",
                    28);

            Student alberto = new Student(
                    "Alberto",
                    "Lontra",
                    "lontra@gmail.com",
                    24);

            System.out.println("----Aggiungo maria, adiener e alberto----");
            studentRepository.saveAll(List.of(maria, adiener, alberto));

            System.out.println("----Numero studenti: ----");
            System.out.println(studentRepository.count());

            System.out.println("----Seleziono lo studente con id 2----");
            studentRepository
                    .findById(2L)
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with id 2 not found"));

            System.out.println("----Seleziono tutti gli studenti----");
            List<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);

            System.out.println("----Elimino maria----");
            studentRepository.deleteById(1L);

            System.out.println("----Numero studenti: ----");
            System.out.println(studentRepository.count());
        };
    }

}
