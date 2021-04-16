package com.mbigger.sigar.bean;

import lombok.Data;

@Data
public class Strategy {
    private String id;
    private String name;
    private String type;
    private String content;
    private String host;
    private String status;
    private double commonly;
    private double serious;
    private String mail;
    private String time;
}
