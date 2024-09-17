package br.com.everaldocq.startup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // https://www.baeldung.com/spring-mvc-content-negotiation-json-xml
        // (Deprecated) Using URL suffixes (extensions) in the request (eg .xml/.json)

        // Via EXTENTION. http://localhost:8080/api/person/v1.xml DEPRECATED on SpringBoot 2.6

        // Via QUERY PARAM. http://localhost:8080/api/person/v1?mediaType=xml

        configurer.favorParameter(true)
        .parameterName("mediaType")
        .ignoreAcceptHeader(true)
        .useRegisteredExtensionsOnly(false)
        .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("json", MediaType.APPLICATION_JSON)
            .mediaType("xml", MediaType.APPLICATION_XML);

            // Via QUERY PARAM. http://localhost:8080/api/person/v1?mediaType=xml
    
            configurer.favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .mediaType("xml", MediaType.APPLICATION_XML);
    
    }

}
