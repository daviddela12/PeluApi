package com.daviddela.peluapi.web;

import com.daviddela.peluapi.domain.Customer;
import com.daviddela.peluapi.services.CustomerService;
import com.daviddela.peluapi.validator.PasswordConstraint;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/v1")
@AllArgsConstructor
@RequestMapping("/customer")
@Validated
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Customer>> list() {
       List<Customer> customerList = customerService.findAll();
      return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Customer get( @PathVariable("id") Long id ) {
        return customerService.findById(id);
    }

    @PostMapping
    public Customer save(@Valid @RequestBody Customer customer ) {
        return customerService.save(customer);
    }

    @PutMapping
    public Customer update(@Valid @RequestBody Customer customer ) {
        return customerService.update(customer);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        customerService.delete(id);
    }

    @GetMapping("/password/{password}")
    public boolean isValidPassword(@PasswordConstraint @PathVariable("password") String password) {
        return true;
    }
}
