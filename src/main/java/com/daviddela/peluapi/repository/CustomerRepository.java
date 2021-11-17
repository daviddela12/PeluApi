package com.daviddela.peluapi.repository;

import com.daviddela.peluapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByEmail(String email);
}
