package com.chaos.datasdkserver.controller;


import com.chaos.datasdkserver.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LogFileStoreController {
    Logger logger = LoggerFactory.getLogger(LogFileStoreController.class);

    @PostMapping("/log")
    public Result uploadDataToLog(@RequestBody Map<String, String> data) {
        Result result = new Result();
        try {
            StringBuilder values = new StringBuilder();
            for(String value :data.values()){
                values.append(value).append("\t");
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
