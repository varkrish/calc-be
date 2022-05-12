package com.redhat.openinnovationlabs.microservice.calculator;

import com.redhat.openinnovationlabs.microservice.calculator.service.SubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;



@SpringBootTest
@ActiveProfiles("add")
public class SubServiceTests {

    @Autowired
    private SubService subService;

    @Test
    void subCalc()
    {

        BigDecimal val1 = new BigDecimal(2);
        BigDecimal val2 = new BigDecimal(2);
        String expResult = "5";
        //ApiResult result = addservice.addCalc(val1,val2);
        //assertEquals(expResult, null);
        fail("sub is not implemented ");

    }

}
