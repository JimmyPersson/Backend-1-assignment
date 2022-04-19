package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Models.BuyOrders;
import com.example.backend1assignment.Models.Customer;
import com.example.backend1assignment.Models.Items;
import com.example.backend1assignment.Repos.BuyOrdersRepository;
import com.example.backend1assignment.Repos.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BuyOrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BuyOrdersRepository mockRepository;

    @MockBean
    private CustomerRepository customerMockRepository;

    @Test
    void getAllOrders() throws Exception {
        Customer c1 = new Customer(1L, "Bob", "Bobs address", "bob@email.com", "secret2");
        Items i1 = new Items(1L, "Toothbrush", "HY01");
        List<Items> l1 = new ArrayList<>();
        l1.add(i1);

        BuyOrders b1 = new BuyOrders(1L, "3E", c1, l1);
        BuyOrders b2 = new BuyOrders(2L, "2E", c1, l1);
        BuyOrders b3 = new BuyOrders(3L, "4E", c1, l1);
        List<BuyOrders> allOrders = new ArrayList<>();
        allOrders.add(b1);
        allOrders.add(b2);
        allOrders.add(b3);
        c1.setOrders(allOrders);

        when(mockRepository.findAll()).thenReturn(Arrays.asList(b1, b2, b3));

        mvc.perform(MockMvcRequestBuilders.get("/orders").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(allOrders))).andDo(MockMvcResultHandlers.print());

    }

    @Test
    void getOrderByCustomer() throws Exception {
        Customer c2 = new Customer(2L, "Karl", "Karls gata", "kg@email.com", "secretpassword");
        Items i2 = new Items(2L, "Pillow", "CO01");
        List<Items> items = new ArrayList<>();
        items.add(i2);
        BuyOrders bo = new BuyOrders(1L, "5E", c2, items);
        List<BuyOrders> orderedItems = new ArrayList<>();
        orderedItems.add(bo);
        c2.setOrders(orderedItems);

        when(customerMockRepository.findById(2L)).thenReturn(Optional.of(c2));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(bo));

        mvc.perform(MockMvcRequestBuilders.get("/orders/2")
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(asJsonString(orderedItems))).andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}