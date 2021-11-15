package com.daviddela.peluapi.services;

import com.daviddela.peluapi.domain.Customer;

import java.util.List;

public interface CustomerService {

    public Customer findById( Long id );
    public List<Customer> list();
    public void save( Customer customer);
}