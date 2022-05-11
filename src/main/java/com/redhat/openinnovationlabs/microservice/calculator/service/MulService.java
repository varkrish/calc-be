package com.redhat.openinnovationlabs.microservice.calculator.service;

import java.math.BigDecimal;

import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import static com.redhat.openinnovationlabs.microservice.calculator.model.ResultBuilder.getResult;
import org.springframework.stereotype.Component;

@Component
public class MulService {

    public ApiResult mulCalc(BigDecimal OperandOne,BigDecimal OperandTwo) {
        return getResult(OperandOne.multiply(OperandTwo));
        
    }
}
