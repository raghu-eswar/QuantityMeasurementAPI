package com.bridgelabz.quantitymeasurement.controller;

import com.bridgelabz.quantitymeasurement.enumeration.UnitTypes;
import com.bridgelabz.quantitymeasurement.exceptions.UnitConversionFailedException;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.scrvice.UnitConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.bridgelabz.quantitymeasurement.enumeration.Units;

import static com.bridgelabz.quantitymeasurement.enumeration.UnitTypes.*;
import static com.bridgelabz.quantitymeasurement.enumeration.Units.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MeasurementControllerTest {

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
    void givenUnitsInUpperCaseAndValues_convertUnits_shouldReturnConvertedQuantity() {
        try {
            when(converter.convert(any(Quantity.class), any())).thenReturn(new Quantity(1, CENTIMETER));
            MvcResult result = mockMvc.perform(get("/measurements/convert/MILLIMETER/10/CENTIMETER"))
                    .andExpect(status()
                            .isOk())
                    .andReturn();
            Quantity quantity = objectMapper.readValue(result.getResponse().getContentAsString(), Quantity.class);
            assertEquals(quantity.getValue(), 1);
            assertEquals(quantity.getUnit(), CENTIMETER);
            verify(converter).convert(any(Quantity.class), any());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenUnitsInLowerCaseAndValues_convertUnits_shouldReturnConvertedQuantity() {
        try {
            when(converter.convert(any(Quantity.class), any())).thenReturn(new Quantity(1, CENTIMETER));
            MvcResult result = mockMvc.perform(get("/measurements/convert/millimeter/10/centimeter"))
                    .andExpect(status()
                            .isOk())
                    .andReturn();
            Quantity quantity = objectMapper.readValue(result.getResponse().getContentAsString(), Quantity.class);
            assertEquals(quantity.getValue(), 1);
            assertEquals(quantity.getUnit(), CENTIMETER);
            verify(converter).convert(any(Quantity.class), any());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenUnitsAndValues_convertUnits_shouldReturnResponseAsBadRequest() {
        try {
            MvcResult result = mockMvc.perform(get("/measurements/convert/MILLIMETER_/10/CENTIMETER"))
                    .andExpect(status()
                            .isBadRequest()).andReturn();
            assertEquals("MILLIMETER_ is not a proper unit", result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenInvalidUnitsAndValues_convertUnits_shouldReturnResponseAsBadRequest() {
        try {
            when(converter.convert(any(Quantity.class), any()))
                .thenThrow(new UnitConversionFailedException("can not convert CELSIUS to CENTIMETER", HttpStatus.BAD_REQUEST));
            MvcResult result = mockMvc.perform(get("/measurements/convert/CELSIUS/10/CENTIMETER"))
                    .andExpect(status()
                            .isBadRequest()).andReturn();
            assertEquals("can not convert CELSIUS to CENTIMETER", result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenZeroPathVariables_controller_shouldReturnArrayOfAllUnitTypes() {
        try {
            UnitTypes[] unitTypes = {LENGTH, TEMPERATURE, VOLUME};
            when(converter.getAllUnitTypes()).thenReturn(unitTypes);
            MvcResult result = mockMvc.perform(get("/measurements/"))
                    .andExpect(status()
                            .isOk()).andReturn();
            assertEquals(objectMapper.writeValueAsString(unitTypes), result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenUnitTypeInUpperCase_controller_shouldReturnArrayOfAllValidUnits() {
        try {
            Units[] expectedUnits = new Units[]{ INCH, FEET, YARD, MILLIMETER, MILE};
            when(converter.getValidUnitsOf(any(UnitTypes.class))).thenReturn(expectedUnits);
            MvcResult result = mockMvc.perform(get("/measurements/LENGTH"))
                    .andExpect(status()
                            .isOk()).andReturn();
            assertEquals(objectMapper.writeValueAsString(expectedUnits), result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenUnitTypeInLowerCase_controller_shouldReturnArrayOfAllValidUnits() {
        try {
            Units[] expectedUnits = new Units[]{ INCH, FEET, YARD, MILLIMETER, MILE};
            when(converter.getValidUnitsOf(any(UnitTypes.class))).thenReturn(expectedUnits);
            MvcResult result = mockMvc.perform(get("/measurements/length"))
                    .andExpect(status()
                            .isOk()).andReturn();
            assertEquals(objectMapper.writeValueAsString(expectedUnits), result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenInvalidUnitType_getValidUnitsOf_shouldReturnResponseAsBadRequest() {
        try {
            when(converter.getValidUnitsOf(any(UnitTypes.class)))
                    .thenThrow(new UnitConversionFailedException("Volume_ is not a proper unit", HttpStatus.BAD_REQUEST));
            MvcResult result = mockMvc.perform(get("/measurements/Volume_"))
                    .andExpect(status()
                            .isBadRequest()).andReturn();
            assertEquals("Volume_ is not a proper unit", result.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}