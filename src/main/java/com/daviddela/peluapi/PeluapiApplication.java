package com.daviddela.peluapi;

import com.daviddela.peluapi.domain.Customer;
import com.daviddela.peluapi.repository.CustomerRepository;
import org.hibernate.id.GUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PeluapiApplication {
    final CustomerRepository customerRepository;

    public PeluapiApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PeluapiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            List<Customer> customerList = new ArrayList<Customer>();
            Customer customer = new Customer();
            customer.setName("David");
            customer.setSurname("de la Cruz");
            customer.setPhone("666777888");
            customerList.add(customer);
            customer = new Customer();
            customer.setName("Mariló");
            customer.setSurname("Sabalete");
            customer.setPhone("666888999");
            customerList.add(customer);
            customerRepository.saveAll(customerList);
        };
    }

}
