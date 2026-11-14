package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GreetingController.class)
@Import(GreetingService.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloReturns200() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void greetReturnsPersonalMessage() throws Exception {
        mockMvc.perform(get("/api/greet/Asha"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Asha!"));
    }

    
    @Test
    void blankNameReturns400() throws Exception {
        mockMvc.perform(get("/api/greet/{name}", " "))
                .andExpect(status().isBadRequest());
    }
}
