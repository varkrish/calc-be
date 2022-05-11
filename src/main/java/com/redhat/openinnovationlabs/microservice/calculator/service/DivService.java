package com.redhat.openinnovationlabs.microservice.calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import static com.redhat.openinnovationlabs.microservice.calculator.model.ResultBuilder.getResult;
import org.springframework.stereotype.Component;

@Component
public class DivService {

    public ApiResult divCalc(BigDecimal OperandOne,BigDecimal OperandTwo) {
        return getResult(OperandOne.divide(OperandTwo, RoundingMode.HALF_EVEN));
         
    }
}
