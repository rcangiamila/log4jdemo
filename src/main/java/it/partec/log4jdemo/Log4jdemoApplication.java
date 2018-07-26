package it.partec.log4jdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedeanda.lorem.Lorem;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Log4j2
@SpringBootApplication
public class Log4jdemoApplication implements ApplicationRunner {

    @Autowired
    private Lorem loremIpsum;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${log.max.characters.string}")
    private Integer logMaxCharactersString;

    public static void main(String[] args) {

        SpringApplication.run(Log4jdemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        asyncWriteLog();
    }

    @Async
    public void asyncWriteLog() throws Exception {
        Random random = new Random();
        while (true) {

            Map<String, Object> fieldsMap = new HashMap<>();

            StringBuffer sb = new StringBuffer(4096);

            sb.append(RandomStringUtils.random(logMaxCharactersString, true, true));

            fieldsMap.put("key", sb.toString());

            log.info(objectMapper.writeValueAsString(fieldsMap));

            Thread.currentThread().sleep((random.nextInt(10) + 1) * 1000);
        }
    }
}
