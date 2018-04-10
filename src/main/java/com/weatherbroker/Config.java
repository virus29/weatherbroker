package com.weatherbroker;

import com.weatherbroker.controller.OrganizationController;
import com.weatherbroker.service.OrganisationService;
import com.weatherbroker.service.impl.OrganizationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan("com.weatherbroker.*")
//@ComponentScan(basePackages = {"com.weatherbroker.view", "com.weatherbroker.repository", "com.weatherbroker.service", "com.weatherbroker.service.impl","com.weatherbroker.controller"})
@EnableWebMvc
@EnableJpaRepositories
@Import({DBConfig.class})
//extends WebMvcConfigurerAdapter deprecated, because we use this implementation WebMvcConfigurer
public class Config implements WebMvcConfigurer {

//    @Bean
//    public UrlBasedViewResolver setupViewResolver() {
//        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        // указываем где будут лежать наши веб-страницы
//        resolver.setPrefix("/WEB-INF/jsp/");
//        // формат View который мы будем использовать
//        resolver.setSuffix(".jsp");
////        resolver.setViewClass(InternalResourceView.class,JstlView.class);
//        return resolver;
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/*");
//    }
}
