package com.mbigger.sigar.controller;

import java.net.UnknownHostException;
import java.util.List;

import com.mbigger.sigar.bean.*;
import com.mbigger.sigar.utils.getIpUtils;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbigger.sigar.service.SystemInformationService;

@RestController
@RequestMapping("/sigar")
public class SigarController {

    private Sigar sigar;

    @Autowired
    private SystemInformationService service;

    /*@GetMapping("/cpuInfo")
    public CpuUsage getCpuInfo() {
        return service.getCpuInfo();
    }
    @GetMapping("/memInfo")
    public MemUsage getMemInfo() {
        return service.getMemInfo();
    }*/

    @GetMapping("/cpuInfo")
    public JsonResult<CpuUsage> getCpuInfo() {
        return service.getCpuInfo();
    }

    @GetMapping("/memInfo")
    public JsonResult<MemUsage> getMemInfo() {
        return service.getMemInfo();
    }

    /*@GetMapping("/diskInfo")
    public List<DiskInfo> getDiskInfo() {
        return service.getDiskInfo();
    }*/
    @GetMapping("/diskInfo")
    public JsonResult<List> getDiskInfo() {
        return service.getDiskInfo();
    }

    @GetMapping("/diskUsage")
    public JsonResult<List> getDiskUsage() {
        return service.getDiskUsage();
    }

    /*@GetMapping("/basicInfo")
    public BasicInfo getBasicInfo() throws UnknownHostException {
        return service.getBasicInfo();
    }*/
    @GetMapping("/basicInfo")
    public JsonResult<BasicInfo> getBasicInfo() throws UnknownHostException {
        return service.getBasicInfo();
    }

}
