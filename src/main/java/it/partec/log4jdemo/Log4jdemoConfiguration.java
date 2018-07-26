package it.partec.log4jdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4jdemoConfiguration {

    @Bean(name = "loremIpsum")
    public Lorem loremIpsum() {
        return LoremIpsum.getInstance();
    }

    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
