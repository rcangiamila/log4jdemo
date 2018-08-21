package it.partec.log4jdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;

@Service
public class Log4jdemoService {

    @Autowired
    ResourceLoader resourceLoader;

    @Value("classpath:logga.json")
    private Resource logga;

    @Value("classpath:logcollector.json")
    private Resource logcollector;

    @Value("classpath:frodi.json")
    private Resource frodi;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${log.max.characters.string}")
    private Integer logMaxCharactersString;

    public String getJsonLog(OCPLOGDEST ocplogdest) throws Exception {

        String result = "";

        switch (ocplogdest) {
            case LOGGA:
                result = getJsonLog(logga);
                break;
            case LOGCOLLECTOR:
                result = getJsonLog(logcollector);
                break;
            case FRODI:
                result = getJsonLog(frodi);
                break;
            default:
                result = getRandomLog();
        }

        return result;
    }

    private String getRandomLog() throws Exception {

        try (InputStream in = logga.getInputStream()) {


            JsonNode node = objectMapper.readTree(in);
            ObjectNode objectNode = (ObjectNode)node;

            objectNode.put("timestamp", new Date().getTime());
            objectNode.put("generatedrandomlog", RandomStringUtils.random(logMaxCharactersString, true, true));

            return node.toString();

        } catch (Exception e) {
            throw e;
        }
    }

    private String getJsonLog(Resource jsonResource) throws Exception {

        try (InputStream in = jsonResource.getInputStream()) {

            JsonNode node = objectMapper.readTree(in);
            ObjectNode objectNode = (ObjectNode)node;

            objectNode.put("timestamp", new Date().getTime());

            return node.toString();

        } catch (Exception e) {
            throw e;
        }
    }
}
