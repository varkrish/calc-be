package com.redhat.openinnovationlabs.microservice.calculator.basic;


import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import com.redhat.openinnovationlabs.microservice.calculator.service.MulService;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;



@RestController
@Profile("mul")
public class MulController {

   @Autowired
   private MulService mulService;
  
   @GetMapping("/multiply")
   public ApiResult multiply(@RequestParam BigDecimal OperandOne, @RequestParam BigDecimal OperandTwo) {
      return mulService.mulCalc(OperandOne,OperandTwo); 
   }

   

}
