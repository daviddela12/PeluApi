package com.daviddela.peluapi.services.impl;

import com.daviddela.peluapi.domain.Customer;
import com.daviddela.peluapi.exception.ResourceNotFoundException;
import com.daviddela.peluapi.repository.CustomerRepository;
import com.daviddela.peluapi.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById( Long id ) {
       return this.customerRepository
               .findById(id)
               .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public List<Customer> list() {
       return this.customerRepository.findAll();
    }

    @Override
    public void save( Customer customer) {
        this.customerRepository.save(customer);
    }
}
