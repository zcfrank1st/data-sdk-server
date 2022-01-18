package com.chaos.datasdkserver.controller;


import com.chaos.datasdkserver.entity.Result;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LogFileStoreController {
    private Logger logger = LoggerFactory.getLogger(LogFileStoreController.class);
    private Gson gson = new Gson();
    private static String validToken = "";

    @PostMapping("/logs")
    public Result uploadDataToLog(@RequestHeader("token") String token, @RequestBody List<Map<String, Object>> uploadData) {
        Result result = new Result();

        if (!validToken.equals(token)) {
            result.setCode(-1);
            result.setMessage("invalid token");
            return result;
        }

        try {
            for(Map<String, Object> data : uploadData) {
                StringBuilder values = new StringBuilder();
                for (Object value : data.values()) {
                    if (value instanceof LinkedHashMap) {
                        values.append(gson.toJson(value)).append("\t");
                    } else {
                        values.append(value).append("\t");
                    }
                }
                values.deleteCharAt(values.length() - 1);
                logger.info(values.toString());
            }
            return result;
        } catch (Exception e) {
            result.setCode(-1);
            result.setMessage(e.getMessage());
            return result;
        }
    }
}
