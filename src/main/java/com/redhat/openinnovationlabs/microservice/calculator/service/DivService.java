package com.redhat.openinnovationlabs.microservice.calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import static com.redhat.openinnovationlabs.microservice.calculator.model.ResultBuilder.getResult;
import org.springframework.stereotype.Component;

@Component
public class DivService {

    public ApiResult divCalc(BigDecimal operand_1,BigDecimal operand_2) {
        return getResult(operand_1.divide(operand_2, RoundingMode.HALF_EVEN));
         
    }
}
