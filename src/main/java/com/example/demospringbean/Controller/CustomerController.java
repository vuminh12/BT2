package com.example.demospringbean.Controller;

import com.example.demospringbean.Model.Customer;
import com.example.demospringbean.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    MessageSource messageSource;

    // lấy tất cả danh sách khách hàng
    @GetMapping("/customers/getall")
    public Iterable<Customer> getAll(){
        return customerService.getAll();
    }

    // xóa 1 khách hàng thông qua id
    @GetMapping("/customers/deleteById/{id}")
    public void deleteCustomerById(@PathVariable("id") Integer id){
        customerService.deleteCustomder(id);
    }

    // tạo mới 1 khách hàng
    @PostMapping("/customers/create")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    // lấy dữ liệu khách hàng thông qua id bằng ngôn ngữ việt nam
    @GetMapping("/customers/findById/{id}")
    public Optional<Customer> getCumstomer(@PathVariable("id") Integer id){
        return customerService.getCustomer(id);
    }


    @RequestMapping("/customers/languageEN/{id}")
    @ResponseBody
    public Customer getCustomerByEnglishLanguage (Locale locale,@PathVariable("id") Integer id) {
        Customer customer =  customerService.getCustomer(id).get();
        customer.setName(messageSource.getMessage(
                "name",null, locale));
        return  customer;
    }

}
