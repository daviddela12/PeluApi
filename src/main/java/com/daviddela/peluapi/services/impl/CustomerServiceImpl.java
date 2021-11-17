package com.daviddela.peluapi.services.impl;

import com.daviddela.peluapi.domain.Customer;
import com.daviddela.peluapi.exception.ResourceNotFoundException;
import com.daviddela.peluapi.repository.CustomerRepository;
import com.daviddela.peluapi.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    final CustomerRepository customerRepository;

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
    public Customer save( Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        this.customerRepository.deleteById(id);
    }
}
