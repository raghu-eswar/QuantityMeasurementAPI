package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.scrvice.UnitConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.bridgelabz.quantitymeasurement.enumeration.Units.CENTIMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AppControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UnitConverter converter;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void givenUnitsAndValues_convertUnits_shouldReturnConvertedQuantity() {
        try {
            when(converter.convert(any(Quantity.class), any())).thenReturn(new Quantity(1, CENTIMETER));
            MvcResult result = mockMvc.perform(get("/quantity-measurements/convert/MILLIMETER/10/CENTIMETER"))
                    .andExpect(status().isOk()).andReturn();
            Quantity quantity = objectMapper.readValue(result.getResponse().getContentAsString(), Quantity.class);
            assertEquals(quantity.getValue(), 1);
            assertEquals(quantity.getUnit(), CENTIMETER);
            verify(converter).convert(any(Quantity.class), any());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}