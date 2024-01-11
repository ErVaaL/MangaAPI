package com.manga;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.manga")
@RequiredArgsConstructor
public class MangaUpdaterApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MangaUpdaterApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}