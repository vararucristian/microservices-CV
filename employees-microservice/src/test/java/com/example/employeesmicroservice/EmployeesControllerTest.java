package com.example.employeesmicroservice;

import com.example.employeesmicroservice.EmployeeService;
import com.example.employeesmicroservice.EmployeesController;
import com.example.employeesmicroservice.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesController.class)
public class EmployeesControllerTest {
    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    EmployeesController employeesController;

    @MockBean
    EmployeeService employeeService;

    private Response response;
    private Response1 response1;


    @Test
    public void verifyPositionTest() throws Exception {

        given(employeeService.verifyPosition(1)).willReturn(response);
            mockMvc.perform(get("/employees/try-get/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].exitCode", CoreMatchers.is(response.getExitCode())))
                    .andExpect(jsonPath("$[0].message", CoreMatchers.is(response.getMessage())));
    }

    @Test
    public void viewUnderlingsTest(){
        given(employeeService.viewUnderlings(1)).willReturn(response1);
        try {
            mockMvc.perform(get("/employees/viewUnderlings/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].exitCode", CoreMatchers.is(response1.getResponse().getExitCode())))
                    .andExpect(jsonPath("$[0].message", CoreMatchers.is(response1.getResponse().getMessage())))
                    .andExpect(jsonPath("$[1]", CoreMatchers.is(response1.getUnderlings())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}