package com.miqueias.r.api_rest_spring_boot.config;

import com.miqueias.r.api_rest_spring_boot.serialization.converter.YamlJackson2HttpMesageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Value("${web.cors.originPatterns:default}")
    private String corsOriginPatters;

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorParameter(true).parameterName("mediaType")
//                .ignoreAcceptHeader(true)
//                .useRegisteredExtensionsOnly(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("json", MediaType.APPLICATION_JSON)
//                .mediaType("xml", MediaType.APPLICATION_XML);
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMesageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       List<String> allowOrigins = new ArrayList<>();

       for (String origin: corsOriginPatters.split(",")){
           allowOrigins.add(origin.trim());
       }
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins(allowOrigins.toArray(new String[0])).allowCredentials(true);
    }
}
