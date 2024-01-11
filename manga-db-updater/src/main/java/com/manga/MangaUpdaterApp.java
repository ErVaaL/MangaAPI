package com.manga;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.manga")
@RequiredArgsConstructor
public class MangaUpdaterApp implements CommandLineRunner {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Override
    public void run(String... args) throws Exception {
    }
}