package net.gourram.inventoryservice;

import net.gourram.inventoryservice.entities.Product;
import net.gourram.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("PC")
                    .price(4500.0)
                    .quantity(15)
                    .build());
            productRepository.save(Product.builder()
                    .name("Mouse")
                    .price(500.0)
                    .quantity(5)
                    .build());
            productRepository.save(Product.builder()
                    .name("Keyboard")
                    .price(900.0)
                    .quantity(9)
                    .build());
        };
    }

}
