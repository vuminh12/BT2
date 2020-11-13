package com.example.demospringbean.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// chỉ đến đối tượng Controller để cắt
@ComponentScan("com.example.demospringbean.Controller")
// đánh dấu nó là 1 file cấu hình
@Configuration
// thường đi với @Configuration, dùng để tự động cắt vào CustomerController
@EnableAspectJAutoProxy
public class AspectConfig {
}
