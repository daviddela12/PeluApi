package com.daviddela.peluapi.web;

import com.daviddela.peluapi.domain.Customer;
import com.daviddela.peluapi.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private Customer customerMocked;

    @BeforeEach
    void setMockOutput() {
        customerMocked = new Customer();
        customerMocked.setId(1L);
        customerMocked.setName("David");
        customerMocked.setSurname("de la Cruz");
        customerMocked.setPhone("666777888");
        customerMocked.setEmail("david@david.com");
        customerMocked.setPassword("A123456789#");
        //le decimos a mockito que cuando se llame a ese save del repository lo devuelva
        when(customerService.save(any(Customer.class))).thenReturn(customerMocked);
    }

    @DisplayName("Se guarda un customer correctamente")
    @Test
    void save() throws Exception {
        Customer customer = new Customer();
        customer.setName("David");
        customer.setSurname("de la Cruz");
        customer.setPhone("666777888");
        customer.setEmail("david@david.com");
        customer.setPassword("A123456789#");
        Customer response = customerController.save(customer);

        assertNotNull(response.getId());
    }
}