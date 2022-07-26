package com.example.springmockitoexample;

import com.example.springmockitoexample.model.Employee;
import com.example.springmockitoexample.model.Response;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)

public class SpringMockitoExampleApplicationTests {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    ObjectMapper om = new ObjectMapper();

    @Before()
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void addEmployeeTest() throws Exception {
        Employee employee = new Employee();
        employee.setName("Ravi");
        employee.setDept("IT");
        String jsonRequest = om.writeValueAsString(employee);
        System.out.println(jsonRequest);
        MvcResult result = mockMvc.perform(post("/EmployeeService/addEmployee").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        Assert.assertTrue(response.isStatus() == Boolean.TRUE);

    }

    @Test
    public void getEmployeesTest() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/EmployeeService/getEmployees").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
         Assert.assertTrue(response.isStatus() == Boolean.TRUE);

    }


      @Test
      public void add(    ){
      int a=2;
    int   b =3;
          int n = a+b;

          assertEquals(n, 5);

      }
}

 
