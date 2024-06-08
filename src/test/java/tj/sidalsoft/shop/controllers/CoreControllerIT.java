package tj.sidalsoft.shop.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tj.sidalsoft.shop.controllers.dto.CalculatePriceRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
public class CoreControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${server.port}")
    String port;

    @Test
    void calculatePrice() throws Exception {
        var path = String.format("http://localhost:%s/calculate-price", port);
        var request = new CalculatePriceRequestDto(1, "IT12345678900", "P10");
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                                              .content(objectMapper.writeValueAsString(request)))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().json("{ 'price': 91.8 }"));
    }
}
