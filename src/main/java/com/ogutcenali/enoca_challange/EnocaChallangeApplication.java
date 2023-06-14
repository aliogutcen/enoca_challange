package com.ogutcenali.enoca_challange;

import com.ogutcenali.enoca_challange.model.Customer;
import com.ogutcenali.enoca_challange.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class EnocaChallangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnocaChallangeApplication.class, args);
    }


}
