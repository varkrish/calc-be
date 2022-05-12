package com.redhat.openinnovationlabs.microservice.calculator.service;

import java.math.BigDecimal;

import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import static com.redhat.openinnovationlabs.microservice.calculator.model.ResultBuilder.getResult;
import org.springframework.stereotype.Component;

@Component
public class SubService {

    public ApiResult subCalc(BigDecimal operand_1,BigDecimal operand_2) {
        return getResult(operand_1.subtract(operand_2));
    }
}
