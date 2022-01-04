package by.grishkevich.food_store_web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebMvcTests {

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void unauthenticatedUsersTest() throws Exception {
        setUp();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void validationTest() throws Exception{
        setUp();
        String name = "Ab";
        String surname = "Ab";
        String email = "em";
        String password = "12";
        String address = "";
        String phone = "123";

        MvcResult result = mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                .param("name",name)
                .param("surname",surname)
                .param("email",email)
                .param("password",password)
                .param("address",address)
                .param("phone",phone)).andExpect(status().is4xxClientError()).andReturn();

        log.info(result.getResponse().getContentAsString());
    }
}
