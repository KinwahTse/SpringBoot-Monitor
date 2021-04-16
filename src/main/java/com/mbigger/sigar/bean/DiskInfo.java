package com.mbigger.sigar.bean;

import lombok.Data;

@Data
public class DiskInfo {
    private String dirName;
    private Double diskusedPercent;
    private Long devTotal;
    private String time;
}
