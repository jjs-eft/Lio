package com.linkinone.Lio.config;

import com.linkinone.Lio.chat.ChatBoxServlet;
import com.linkinone.Lio.chat.ChatListServlet;
import com.linkinone.Lio.chat.ChatSubmitServlet;
import com.linkinone.Lio.chat.ChatUnreadServlet;
import com.linkinone.Lio.data.DataTrendServlet;
import com.linkinone.Lio.research.ResearchAddServlet;
import com.linkinone.Lio.research.ResearchDeleteServlet;
import com.linkinone.Lio.research.ResearchListServlet;
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
    @Bean
    public ServletRegistrationBean getServletRegistrationBean2()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ChatListServlet());
        registrationBean.addUrlMappings("/ChatListServlet");

        return registrationBean;
    }
    @Bean
    public ServletRegistrationBean getServletRegistrationBean3()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ChatSubmitServlet());
        registrationBean.addUrlMappings("/ChatSubmitServlet");

        return registrationBean;
    }
    @Bean
    public ServletRegistrationBean getServletRegistrationBean4()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ChatUnreadServlet());
        registrationBean.addUrlMappings("/ChatUnreadServlet");

        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean5()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ChatBoxServlet());
        registrationBean.addUrlMappings("/ChatBoxServlet");

        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean6()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ResearchAddServlet());
        registrationBean.addUrlMappings("/ResearchAddServlet");

        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean7()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ResearchListServlet());
        registrationBean.addUrlMappings("/ResearchListServlet");

        return registrationBean;
    }


    @Bean
    public ServletRegistrationBean getServletRegistrationBean8()
    {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ResearchDeleteServlet());
        registrationBean.addUrlMappings("/ResearchDeleteServlet");

        return registrationBean;
    }
}
