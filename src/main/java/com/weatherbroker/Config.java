package com.weatherbroker;

import org.apache.activemq.artemis.jms.client.ActiveMQXAConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Configuration
@ComponentScan("com.weatherbroker.*")
//@ComponentScan(basePackages = {"com.weatherbroker.view", "com.weatherbroker.repository", "com.weatherbroker.service", "com.weatherbroker.service.impl","com.weatherbroker.controller"})
@EnableWebMvc
@EnableJms
@EnableJpaRepositories
@PropertySource("classpath:app.properties")
@Import({DBConfig.class})
//extends WebMvcConfigurerAdapter deprecated, because we use this implementation WebMvcConfigurer
public class Config implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        // указываем где будут лежать наши веб-страницы
        resolver.setPrefix("/WEB-INF/jsp/");
        // формат View который мы будем использовать
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
//        resolver.setViewNames("index");
        return resolver;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        System.out.println(new InitialContext().lookup(env.getProperty("jms.jndi-name")).toString() + " 1 ВНИМАНИЕ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        InitialContext initialContext = new InitialContext();
        initialContext.lookup(env.getProperty("jms.jndi-name"));
        System.out.println(initialContext.getEnvironment()+" 2 ВНИМАНИЕ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return (ConnectionFactory) new InitialContext().lookup(env.getProperty("jms.jndi-name"));
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setPubSubDomain(true); // pub/sub
        System.out.println(template.toString() + " 3 ВНИМАНИЕ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws NamingException {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        System.out.println("Зашел в DefaultJmsListenerContainerFactory");
//  factory.setConcurrency("1-1");
        factory.setPubSubDomain(true); // pub/sub
        System.out.println(factory.toString() + " 4 ВНИМАНИЕ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return factory;
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/*");
//    }
}
