package com.redhat.openinnovationlabs.microservice.calculator.basic;

import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import com.redhat.openinnovationlabs.microservice.calculator.service.DivService;

import java.math.BigDecimal;


import com.redhat.openinnovationlabs.microservice.calculator.model.ResultBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;



@RestController
@Profile("div")
public class DivController {

   @Autowired
   private DivService devService;

   @GetMapping("/divide")
   public ApiResult divide(@RequestParam BigDecimal operand_1, @RequestParam BigDecimal operand_2) {
      if (operand_2.equals(BigDecimal.ZERO)) {
         return ResultBuilder.getResultFromError("no division by null");
      }
      return devService.divCalc(operand_1, operand_2);
   }

}
