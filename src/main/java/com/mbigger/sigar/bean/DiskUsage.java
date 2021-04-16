package com.mbigger.sigar.bean;

import lombok.Data;

@Data
public class DiskUsage {
    private String dirName;
    private Double diskReads;
    private Double diskWrites;
    private String time;
}
