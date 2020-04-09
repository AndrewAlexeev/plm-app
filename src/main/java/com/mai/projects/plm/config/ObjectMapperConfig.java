package com.mai.projects.plm.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class ObjectMapperConfig  {

    @Bean
    JacksonProperties jacksonProperties(){
        JacksonProperties jacksonProperties = new JacksonProperties();
        jacksonProperties.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        jacksonProperties.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        return jacksonProperties;
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().modules(new JavaTimeModule(), new Jdk8Module()).build()
//                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//
////        ObjectMapper objectMapper= new ObjectMapper();
////
////        JavaTimeModule javaTimeModule = new JavaTimeModule();
////        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));
////        objectMapper.registerModule(javaTimeModule);
////
////        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////
////
////        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
//    }




    @Bean
    @Primary
    public ObjectMapper objectMapper(){
         ObjectMapper objectMapper= new ObjectMapper();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));
        objectMapper.registerModule(javaTimeModule);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return  objectMapper;
    }
//
//    @Bean
//    public Jackson2ObjectMapperBuilder jacksonBuilder() {
//        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
//        b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        return b;
//    }


/*
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);

        objectMapper.registerModule(javaTimeModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return objectMapper;
    }*/
}
