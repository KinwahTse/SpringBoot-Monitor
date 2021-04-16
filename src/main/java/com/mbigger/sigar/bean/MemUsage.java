package com.mbigger.sigar.bean;

import lombok.Data;


@Data
public class MemUsage {
    private String name;
    private Double usedPercent;
    private Double swapusedPercent;
    private String time;
}