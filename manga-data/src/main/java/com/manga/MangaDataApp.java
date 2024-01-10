package com.manga;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MangaDataApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MangaDataApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}