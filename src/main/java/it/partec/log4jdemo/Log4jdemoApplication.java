package it.partec.log4jdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

import java.util.Random;

@Log4j2
@SpringBootApplication
public class Log4jdemoApplication implements ApplicationRunner {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Log4jdemoService log4jdemoService;

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

            log.info(log4jdemoService.getJsonLog(OCPLOGDEST.LOGGA));
            log.info(log4jdemoService.getJsonLog(OCPLOGDEST.LOGCOLLECTOR));
            log.info(log4jdemoService.getJsonLog(OCPLOGDEST.FRODI));
            log.info(log4jdemoService.getJsonLog(OCPLOGDEST.RANDOM));

            Thread.currentThread().sleep((random.nextInt(10) + 1) * 1000);
        }
    }
}
