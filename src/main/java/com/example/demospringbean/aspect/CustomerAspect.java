package com.example.demospringbean.aspect;

import com.example.demospringbean.Model.Customer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class CustomerAspect {

    // cắt vào trước khi phương thức lấy tất cả khách hàng ra
    @Before("execution(* com.example.demospringbean.Controller.CustomerController.getAll())")
    public void getAllCustomerAdviceBefore(){
        System.out.println(" preparing get customer!");
    }
   ///cắt vào trước khi phương thức lấy tất cả khách hàng ra
    @After("execution(* com.example.demospringbean.Controller.CustomerController.getAll())")
    public void getAllCustomerAdviceAfter(){
        System.out.println(" getted customer!");
    }

    //cắt sau khi return kết quả trả về
    @AfterReturning(pointcut="execution(* com.example.demospringbean.Controller.CustomerController.*(..))", returning="returnCustomers")
    public void getCustomerReturningAdvice(List<Customer>  returnCustomers){
        System.out.println("getCustomerReturningAdvice executed. Returned String="+returnCustomers);
    }

    //cắt vào anotation RestController
    @Before("@annotation(com.example.demospringbean.aspect.RestController)")
    public void RestController(){
        System.out.println("Executing RestController!!");
    }


}
