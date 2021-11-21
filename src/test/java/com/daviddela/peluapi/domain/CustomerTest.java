package com.daviddela.peluapi.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    private Validator validator;

    private Customer customer;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        customer = new Customer();
        customer.setName("David");
        customer.setSurname("de la Cruz");
        customer.setPhone("666777888");
        customer.setEmail("david@david.com");
        customer.setPassword("A123456789#");
    }

    @DisplayName("El campo email de Customer no pasa la validación")
    @Test
    public void emailFormatMustBeCorrect() {

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.isEmpty());

        customer.setEmail("david");
        violations = validator.validate(customer);
        assertFalse(violations.isEmpty());
    }

    @DisplayName("El campo password must be correct")
    @Test
    public void passwordMustBeCorrect() {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.isEmpty());

        customer.setPassword("1234");
        violations = validator.validate(customer);
        assertFalse(violations.isEmpty());

        customer.setPassword("1234?");
        violations = validator.validate(customer);
        assertFalse(violations.isEmpty());

        customer.setPassword("123456789");
        violations = validator.validate(customer);
        assertFalse(violations.isEmpty());

        customer.setPassword("A123456789");
        violations = validator.validate(customer);
        assertFalse(violations.isEmpty());

    }
}