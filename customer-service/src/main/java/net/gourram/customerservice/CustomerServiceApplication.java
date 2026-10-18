package net.gourram.customerservice;

import net.gourram.customerservice.entities.Customer;
import net.gourram.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start (CustomerRepository repository) {
        return args -> {
            repository.save(Customer.builder()
                    .name("souky").email("souky@gmail.com").build());
            repository.save(Customer.builder()
                    .name("said").email("said@gmail.com").build());
            repository.save(Customer.builder()
                    .name("reda").email("reda@gmail.com").build());
        };
    }

}
