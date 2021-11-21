package com.daviddela.peluapi.services.impl;

import com.daviddela.peluapi.domain.Customer;
import com.daviddela.peluapi.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

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
        when(customerRepository.save(any(Customer.class))).thenReturn(customerMocked);
    }

    @DisplayName("No inserta el customer porque el email es incorrecto")
    @Test
    void save() {
        Customer customer = new Customer();
        customer.setName("David");
        customer.setSurname("de la Cruz");
        customer.setPhone("666777888");
        customer.setEmail("david@david.com");
        customer.setPassword("A123456789#");
        Customer response = customerServiceImpl.save(customer);
        //verifica que en la llamada anterior se ha llamado una vez al save de customerRepository
        verify(customerRepository, times(1)).save(customer);

        assertNotNull(response.getId());
    }
}