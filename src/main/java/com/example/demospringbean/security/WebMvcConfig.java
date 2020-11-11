package com.example.demospringbean.security;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

// tại sao phải sử dụng mvc cho cấu hình đa ngôn ngữ? dùng cái khác được không ?
@EnableWebMvc
// đánh đấu là 1 file cấu hình ngôn ngữ
@Configuration
// WebMvcConfigurerAdapter không được dùng nữa từ spring 5 nên được gach đi

public class WebMvcConfig extends WebMvcConfigurerAdapter {
//    @Bean(name = "viewResolver")
//    // InternalResourceViewResolver sẽ map tất cả các file có đuôi .jsp nằm trong thư mục web-inf/jsp/...
//    public InternalResourceViewResolver getViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/jsp/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

    @Bean(name = "localeResolver")
    // định nghĩa xác định ngôn ngữ gì, cụ thể là tiếng anh, session được tạo ra để xác định ngôn ngữ của client
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // set ngôn ngữ tiếng anh
        slr.setDefaultLocale(Locale.UK);
        return slr;
    }

    @Bean
    // trước khi request đi qua controller, nó cần phải được LocaleChangeInterceptor thay đổi ngôn ngữ
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // set trên đường dẫn là ngôn ngữ gì ?
        lci.setParamName("lang");
        return lci;
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}

// tạo 1 file i18n(quốc tế ) để chứa các ngôn ngữ cần thiết