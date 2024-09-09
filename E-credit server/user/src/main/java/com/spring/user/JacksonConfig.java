package com.spring.user;

/*
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Set;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        ObjectMapper objectMapper = builder.build();

        // Register custom deserializer for GrantedAuthority
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Set.class, new GrantedAuthorityDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
*/