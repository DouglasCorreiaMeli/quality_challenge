package com.meli.homeAppraisal.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.homeAppraisal.domain.error.ResourceNotFoundException;
import com.meli.homeAppraisal.repository.NeighborhoodRepository;
import com.meli.homeAppraisal.service.HomeService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeService service;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private NeighborhoodRepository repository;

    @Test
    public void shouldPostAnalyzeHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/analyzeHome")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"propName\":\"House\",\"propDistrict\":\"santana\"," +
                        "\"rooms\":[{\"roomName\":\"Sala de Jantar\",\"roomWidth\":3.87,\"roomLength\":2.53}," +
                        "{\"roomName\":\"Cozinha\",\"roomWidth\":1.48,\"roomLength\":4.03}," +
                        "{\"roomName\":\"Banheiro\",\"roomWidth\":1.18,\"roomLength\":2.53}," +
                        "{\"roomName\":\"Quarto\",\"roomWidth\":3.58,\"roomLength\":2.53}]}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalSquareMeters").value("27.8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.propertyValue").value("189306.42"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biggestRoom.roomName").value("Sala de Jantar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biggestRoom.squareMeters").value("9.79"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomsSquareMeters", Matchers.hasSize(4)));

    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenDistrictDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/analyzeHome")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"propName\":\"House\",\"propDistrict\":\"no match\"," +
                        "\"rooms\":[{\"roomName\":\"Sala de Jantar\",\"roomWidth\":3.87,\"roomLength\":2.53}," +
                        "{\"roomName\":\"Cozinha\",\"roomWidth\":1.48,\"roomLength\":4.03}," +
                        "{\"roomName\":\"Banheiro\",\"roomWidth\":1.18,\"roomLength\":2.53}," +
                        "{\"roomName\":\"Quarto\",\"roomWidth\":3.58,\"roomLength\":2.53}]}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof ResourceNotFoundException));
    }

    @Test
    public void shouldThrowMethodArgumentNotValidExceptionWhenPropNameStartWithLowerCase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/analyzeHome")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"propName\":\"house\",\"propDistrict\":\"no match\"," +
                        "\"rooms\":[{\"roomName\":\"Sala de Jantar\",\"roomWidth\":3.87,\"roomLength\":2.53}," +
                        "{\"roomName\":\"Cozinha\",\"roomWidth\":1.48,\"roomLength\":4.03}," +
                        "{\"roomName\":\"Banheiro\",\"roomWidth\":1.18,\"roomLength\":2.53}," +
                        "{\"roomName\":\"Quarto\",\"roomWidth\":3.58,\"roomLength\":2.53}]}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    public void shouldThrowMethodArgumentNotValidExceptionWhenRoomNameStartWithLowerCase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/analyzeHome")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"propName\":\"House\",\"propDistrict\":\"no match\"," +
                        "\"rooms\":[{\"roomName\":\"sala de Jantar\",\"roomWidth\":3.87,\"roomLength\":2.53}," +
                        "{\"roomName\":\"cozinha\",\"roomWidth\":1.48,\"roomLength\":4.03}," +
                        "{\"roomName\":\"banheiro\",\"roomWidth\":1.18,\"roomLength\":2.53}," +
                        "{\"roomName\":\"quarto\",\"roomWidth\":3.58,\"roomLength\":2.53}]}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof MethodArgumentNotValidException));
    }

}
