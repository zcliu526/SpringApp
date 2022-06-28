package com.example.demo.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    //test
    @Bean
    CommandLineRunner commandLineRunner
            (StudentRepository repository) {
        return args -> {
                  Student cole =  new Student(
                            "Cole",
                            LocalDate.of(2000, Month.JANUARY, 5),
                            "cole.liu@gmail.com"
                    );

            Student genre =  new Student(
                    "Genre",
                    LocalDate.of(1999, Month.JANUARY, 5),
                    "genre.sun@gmail.com"
            );

            repository.saveAll(
                    List.of(cole,genre)
            );
        };
    }
}
