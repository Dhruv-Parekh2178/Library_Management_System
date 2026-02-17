package com.LMS.library.config;

import com.LMS.library.security.CustomAccessDeniedHandler;
import com.LMS.library.security.CustomAuthenticationEntryPoint;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration

public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



}
