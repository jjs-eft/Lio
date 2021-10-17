package com.linkinone.Lio.config;

import com.linkinone.Lio.data.DataTrendServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletRegistrationConfig
{
    @Bean
    public ServletRegistrationBean getServletRegistrationBean()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new DataTrendServlet());
        registrationBean.addUrlMappings("/DataTrendServlet");
        return registrationBean;
    }
}