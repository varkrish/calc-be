package com.redhat.openinnovationlabs.microservice.calculator;

import com.redhat.openinnovationlabs.microservice.calculator.service.AddService;
import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("add")

public class AddServiceTests {

    @Autowired
    private AddService addservice;

    @Test
    void addCalc()
    {

        fail("Add is not implemented ");

    }

}
