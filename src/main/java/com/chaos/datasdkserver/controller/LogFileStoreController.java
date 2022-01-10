package com.chaos.datasdkserver.controller;


import com.chaos.datasdkserver.entity.Result;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class LogFileStoreController {
    private Logger logger = LoggerFactory.getLogger(LogFileStoreController.class);
    private Gson gson = new Gson();

    @PostMapping("/log")
    public Result uploadDataToLog(@RequestBody Map<String, Object> data) {
        Result result = new Result();
        try {
            StringBuilder values = new StringBuilder();
            for(Object value :data.values()){
                if (value instanceof LinkedHashMap) {
                    values.append(gson.toJson(value)).append("\t");
                } else {
                    values.append(value).append("\t");
                }
            }
            values.deleteCharAt(values.length() - 1);
            logger.info(values.toString());
            result.setCode(0);
            return result;
        } catch (Exception e) {
            result.setCode(-1);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
