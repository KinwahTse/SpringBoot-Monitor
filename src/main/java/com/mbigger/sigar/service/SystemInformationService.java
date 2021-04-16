package com.mbigger.sigar.service;

import com.mbigger.sigar.bean.*;

import java.net.UnknownHostException;
import java.util.List;

public interface SystemInformationService {
    JsonResult<CpuUsage> getCpuInfo();

    //CpuUsage getCpuInfo();

    JsonResult<MemUsage> getMemInfo();

    //MemUsage getMemInfo();

    JsonResult<List>getDiskUsage();

    //JsonResult<List>getLinuxDiskUsage();

    //List<DiskUsage> getDiskUsage();

    //CpuInfo getCpuInformation();

    JsonResult<List> getDiskInfo();

    //List<DiskInfo> getDiskInfo();

    JsonResult<BasicInfo> getBasicInfo() throws UnknownHostException;

    //BasicInfo getBasicInfo() throws UnknownHostException;
}
