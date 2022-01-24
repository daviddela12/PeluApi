package com.daviddela.peluapi.web;

import com.daviddela.peluapi.JsonUtil;
import com.daviddela.peluapi.domain.Customer;
import com.daviddela.peluapi.repository.CustomerRepository;
import com.daviddela.peluapi.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CustomerController.class)
@AutoConfigureRestDocs
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

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

        mvc.perform(
                    post("/customer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.toJson(customer))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andDo(document("customer/save"));;
    }
}