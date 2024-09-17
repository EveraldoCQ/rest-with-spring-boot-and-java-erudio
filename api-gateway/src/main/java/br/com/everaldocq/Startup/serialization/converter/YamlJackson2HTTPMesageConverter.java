package br.com.everaldocq.startup.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlJackson2HTTPMesageConverter extends AbstractJackson2HttpMessageConverter {

    public YamlJackson2HTTPMesageConverter() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),
        MediaType.parseMediaType("application/x-yaml")
        );
    }

}
