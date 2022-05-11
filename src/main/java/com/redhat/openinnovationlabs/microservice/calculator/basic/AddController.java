package com.redhat.openinnovationlabs.microservice.calculator.basic;


import java.math.BigDecimal;


import com.redhat.openinnovationlabs.microservice.calculator.model.ApiResult;
import com.redhat.openinnovationlabs.microservice.calculator.service.AddService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;


@RestController
@Profile("add")
public class AddController {

@Autowired
private AddService addService;

   @GetMapping("/add")
   public ApiResult add(@RequestParam BigDecimal OperandOne, @RequestParam BigDecimal OperandTwo) {
      //return addService.addCalc(OperandOne,OperandTwo);
      return null;
   }

   
}
