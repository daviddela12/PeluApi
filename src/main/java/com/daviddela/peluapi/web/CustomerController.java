package com.daviddela.peluapi.web;

import com.daviddela.peluapi.domain.Customer;
import com.daviddela.peluapi.services.CustomerService;
import com.daviddela.peluapi.validator.PasswordConstraint;
import com.daviddela.peluapi.validator.PasswordValidator;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Customer>> list() {
       List<Customer> customerList = customerService.list();
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
    public boolean isValidPassword( @PathVariable("password") @PasswordConstraint String password,
                                    BindingResult result) {
        if(result.hasErrors()) {
            return false;
        }
        return true;
    }
}
