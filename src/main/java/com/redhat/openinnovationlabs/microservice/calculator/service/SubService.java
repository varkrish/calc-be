package com.redhat.openinnovationlabs.microservice.calculator.service;

import java.math.BigDecimal;

import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import static com.redhat.openinnovationlabs.microservice.calculator.model.ResultBuilder.getResult;
import org.springframework.stereotype.Component;

@Component
public class SubService {

    public ApiResult subCalc(BigDecimal OperandOne,BigDecimal OperandTwo) {
        return getResult(OperandOne.subtract(OperandTwo));
    }
}
