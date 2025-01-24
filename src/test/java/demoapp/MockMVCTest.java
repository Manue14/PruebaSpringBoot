package demoapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFormSubmission_ValidData() throws Exception {
        mockMvc.perform(post("/form/submit")
                        .param("name", "John Doe")
                        .param("age", "30"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/results"));
    }

    @Test
    public void testFormSubmission_InvalidName() throws Exception {
        // Invalid name (too short)
        mockMvc.perform(post("/form/submit")
                        .param("name", "J")  // Invalid name (less than 2 characters)
                        .param("age", "30"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("personForm", "name"))
                .andExpect(view().name("form"));
    }

    @Test
    public void testFormSubmission_InvalidAge() throws Exception {
        // Invalid age (too low)
        mockMvc.perform(post("/form/submit")
                        .param("name", "John Doe")
                        .param("age", "10"))  // Invalid age (less than 18)
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("personForm", "age"))
                .andExpect(view().name("form"));
    }

}
