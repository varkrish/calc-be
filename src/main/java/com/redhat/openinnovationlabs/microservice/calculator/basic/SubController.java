package com.redhat.openinnovationlabs.microservice.calculator.basic;


import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import com.redhat.openinnovationlabs.microservice.calculator.service.SubService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import java.math.BigDecimal;



@RestController
@Profile("sub")
public class SubController {

   @Autowired
   private SubService subService;

   @GetMapping("/subtract")
   public ApiResult subtract(@RequestParam BigDecimal operand_1, @RequestParam BigDecimal operand_2) {
      return subService.subCalc(operand_1,operand_2); 
   }

   

}
