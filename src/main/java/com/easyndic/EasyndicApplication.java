package com.easyndic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.CommandLineRunner;
import com.easyndic.data.DataSeeder;

@SpringBootApplication(scanBasePackages = "com.easyndic")
@EntityScan("com.easyndic.*")
@ComponentScan({ "com.easyndic.*" })
public class EasyndicApplication implements CommandLineRunner {
    @Autowired
    private DataSeeder dataSeeder;

    public static void main(String[] args) {
        SpringApplication.run(EasyndicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Appelle la méthode seedData pour remplir la base de données avec des données fictives
        dataSeeder.seedData();
    }
}
