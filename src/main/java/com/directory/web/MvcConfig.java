package com.directory.web;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Mvc config.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }
}
