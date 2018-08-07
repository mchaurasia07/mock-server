package com.mock.mockserver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.mockserver.entity.ApplicationEntity;
import com.mock.mockserver.repository.ApplicationRepository;
import java.io.File;
import java.util.List;
import org.junit.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mayank.chaurasia on 06-08-2018.
 */


public class ApplicationControllerTest extends MockserverApplicationTests {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  ObjectMapper objectMapper;

  private MockMvc mockMvc;

  @MockBean
  private ApplicationRepository applicationRepository;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @org.junit.Test
  public void testing1() throws Exception {

    File file = new File(getClass().getResource("/json/applicationList.json").getFile());
    List<ApplicationEntity> applicationList = objectMapper.readValue(file, new TypeReference<List<ApplicationEntity>>(){});

    Mockito.when(applicationRepository.findAll()).thenReturn(applicationList);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/application/all").accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(1))).andReturn();


    System.out.println(result.getResponse());
    System.out.println(result.getResponse().getContentAsString());

  }




}
