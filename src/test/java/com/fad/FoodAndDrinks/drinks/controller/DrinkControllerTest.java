package com.fad.FoodAndDrinks.drinks.controller;

import com.fad.FoodAndDrinks.drinks.model.Drink;
import com.fad.FoodAndDrinks.drinks.repository.DrinksRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DrinkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DrinksRepository drinksRepository;

    @Test
    void createNewDrinkTest() throws Exception {
        Drink drink = new Drink("TestDrink1", 1.0F);

        when(drinksRepository.save(drink)).thenReturn(drink);
        mockMvc.perform(post("/drink", 42L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(drink)))
                .andExpect(status().isOk());
    }

    @Test
    void getDrinksTest() throws Exception {
        Drink drink = new Drink("TestDrink1", 1.0F);
        drink.setId(1L);

        when(drinksRepository.findById(1L)).thenReturn(Optional.of(drink));

        mockMvc.perform(get("/drink/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"TestDrink1\",\"price\":1.0}"));
    }
}
