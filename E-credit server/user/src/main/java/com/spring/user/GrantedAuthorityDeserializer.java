package com.spring.user;
/*
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GrantedAuthorityDeserializer extends StdDeserializer<Set<GrantedAuthority>> {

    public GrantedAuthorityDeserializer() {
        super(Set.class);
    }

    @Override
    public Set<GrantedAuthority> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Set<GrantedAuthority> authorities = new HashSet<>();
        JsonNode node = jp.getCodec().readTree(jp);
        for (JsonNode authority : node) {
            authorities.add(new SimpleGrantedAuthority(authority.asText()));
        }
        return authorities;
    }
}
*/