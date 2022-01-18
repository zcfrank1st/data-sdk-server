package com.chaos.datasdkserver.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UploadDataLog {
    private List<Map<String, Object>> data;
}
