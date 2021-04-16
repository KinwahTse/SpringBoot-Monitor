package com.mbigger.sigar.bean;

import lombok.Data;

@Data
public class BasicInfo {
    private String name;
    private String cpuVendor;
    private String cpuModel;
    private Integer cpuCoreness;
    private long memTotal;
    private String hostName;
    private String system;
    private String edition;
    private String arch;
    private String ip;
    private String time;
}
