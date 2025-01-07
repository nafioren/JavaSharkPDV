package com.proyecto.JavaSharkPDV;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.proyecto.JavaSharkPDV.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(SpringExtension.class)
public class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new GlobalExceptionHandler()).build();
    }

   /* @Test
    void testHandleSpecificException() throws Exception {

        mockMvc.perform(get("/api/test-specific-exception"))  // Endpoint ficticio
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Specific Exception occurred"));
    }*/

    @Test
    void testHandleGenericException() throws Exception {
        // Simulamos que se lanza la excepción genérica
        mockMvc.perform(get("/api/test-generic-exception"))  // Endpoint ficticio
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Server Error"));
    }
}
