package com.mbigger.sigar.bean;

import lombok.Data;

@Data
public class CpuUsage {
    private String name;
    //private Integer usedPercent;
    private Double usedPercent;
    private String time;
}
