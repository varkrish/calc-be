package com.redhat.openinnovationlabs.microservice.calculator;

import com.redhat.openinnovationlabs.microservice.calculator.service.AddService;
import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
//@ActiveProfiles("add")
public class AddServiceTests {

    @Autowired
    private AddService addservice;

    @Test
    void addCalc()
    {

        BigDecimal val1 = new BigDecimal(2);
        BigDecimal val2 = new BigDecimal(2);
        String expResult = "5";
        //ApiResult result = addservice.addCalc(val1,val2);
        //assertEquals(expResult, null);
        fail("Add is not implemented ");

    }

}
