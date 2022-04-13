package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Models.BuyOrders;
import com.example.backend1assignment.Models.Customer;
import com.example.backend1assignment.Repos.CustomerRepository;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository mockRepository;

    @BeforeEach
    public void init() {

        Customer c1 = new Customer(1L, "Anna", "Anna address", "anna@email.com", "secret1");
        Customer c2 = new Customer(2L, "Bob", "Bobs address", "bob@email.com", "secret2");
        Customer c3 = new Customer(3L, "Cecile", "Ceciles address", "cecile@email.com", "secret3");

        when(mockRepository.findById(1L)).thenReturn(Optional.of(c1));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(c1, c2, c3));
    }

    @Test
    void getAllCustomers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Anna\",\"address\":\"Anna address\",\"email\":\"anna@email.com\",\"password\":\"secret1\"}," +
                        "{\"id\":2,\"name\":\"Bob\",\"address\":\"Bobs address\",\"email\":\"bob@email.com\",\"password\":\"secret2\"},"+
                        "{\"id\":3,\"name\":\"Cecile\",\"address\":\"Ceciles address\",\"email\":\"cecile@email.com\",\"password\":\"secret3\"}]"));
    }

    @Test
    void findCustomerById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers/find/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Anna\",\"address\":\"Anna address\",\"email\":\"anna@email.com\",\"password\":\"secret1\"}"));
    }

    @Test
    void addCustomer() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", "john");
        json.put("address", "address");
        json.put("email", "email");
        json.put("password", "pass");
        mvc.perform(MockMvcRequestBuilders.post("/customers/add")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json.toString()))
                .andExpect(status().isCreated());
    }
}