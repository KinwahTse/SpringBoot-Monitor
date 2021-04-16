package com.mbigger.sigar.bean;

import lombok.Data;

@Data
public class WarnMessage {
    private String id;
    private String host;
    private String content;
    private String rank;
    private String ifread;
    private String time;
}
