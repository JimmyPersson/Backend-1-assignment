package com.example.backend1assignment.Controllers;

import com.example.backend1assignment.Models.DTO.BuyOrderDTO;
import com.example.backend1assignment.Models.Items;
import com.example.backend1assignment.Repos.BuyOrdersRepository;
import com.example.backend1assignment.Repos.CustomerRepository;
import com.example.backend1assignment.Repos.ItemsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.backend1assignment.Models.Customer;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemsRepository mockRepository;

    @MockBean
    private BuyOrdersRepository BOMockRepository;

    @MockBean
    private CustomerRepository customerMockRepository;

    @BeforeEach
    public void init() {

        Items i1 = new Items(1L, "Toothbrush", "HY01");
        Items i2 = new Items(2L, "Socks", "CL01");
        Items i3 = new Items(3L, "Lightbulb", "EL01");

        when(mockRepository.findById(2L)).thenReturn(Optional.of(i2));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(i1, i2, i3));

        Customer c1 = new Customer(1L, "Anna", "Anna address", "anna@email.com", "secret1");
        when(customerMockRepository.findById(1L)).thenReturn(Optional.of(c1));

    }

    @Test
    void getAllItems() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/items/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Toothbrush\",\"productNumber\":\"HY01\"}," +
                        "{\"id\":2,\"name\":\"Socks\",\"productNumber\":\"CL01\"},"+
                        "{\"id\":3,\"name\":\"Lightbulb\",\"productNumber\":\"EL01\"}]"));
    }

    @Test
    void getItemById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/items/find/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":2,\"name\":\"Socks\",\"productNumber\":\"CL01\"}"));
    }

    @Test
    void addItem() throws Exception {
        String requestBody = "{\"id\":5,\"name\":\"Candle\",\"productNumber\":\"HD01\"}";
        mvc.perform(MockMvcRequestBuilders.post("/items/add")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void newOrder() throws Exception {

        BuyOrderDTO buyOrderDTO = new BuyOrderDTO(1L,2L,"1E");
        mvc.perform(MockMvcRequestBuilders.post("/items/buy")
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(buyOrderDTO)))
                        .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}