package com.mbigger.sigar.service;

import com.mbigger.sigar.bean.*;
import com.mbigger.sigar.bean.DiskUsage;
import com.mbigger.sigar.utils.ByteUtils;
import com.mbigger.sigar.utils.DateUtils;
import com.mbigger.sigar.utils.getIpUtils;
import lombok.extern.slf4j.Slf4j;

import org.hyperic.sigar.*;
import org.hyperic.sigar.CpuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

@Service
@Slf4j
public class SigarService implements SystemInformationService {

    @Autowired
    private Sigar sigar;

    /*@Override
    public CpuUsage getCpuInfo() {
        return getGeneralCpuUsage();
    }*/

    @Override
    public JsonResult<CpuUsage> getCpuInfo() {
        return new JsonResult<>(getGeneralCpuUsage(),"获取CPU信息成功!");
    }

    private CpuUsage getGeneralCpuUsage() {
        CpuUsage cpuUsage = new CpuUsage();
        cpuUsage.setName("CPU");
        cpuUsage.setTime(DateUtils.getTime());
        try {
            CpuPerc cpu = sigar.getCpuPerc();
            Double  value = (double) Math.round(cpu.getCombined() * 10000) / 100;
            cpuUsage.setUsedPercent(value);
            //String value = String.format("%.2f",cpu.getCombined()*100);
            /*int value = (int) (cpu.getCombined()*100);
            cpuUsage.setUsedPercent(value);*/
        } catch (SigarException e) {
            log.warn("{}", e.getMessage());
            //cpuUsage.setUsedPercent(0);
            return null;
        }
        return cpuUsage;
    }


    @Override
    //public List<DiskUsage> getDiskUsage() {
    public JsonResult<List> getDiskUsage() {
        List<DiskUsage> list = new ArrayList<>();
        String dirName = "";
        double diskReads = 0;
        double diskWrites = 0;
        try {
            FileSystem fslist[] = sigar.getFileSystemList();
            for (FileSystem fs : fslist) {
                // 只处理本地文件系统，否则中止，下一个
                FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
                if (fs.getType() == FileSystem.TYPE_LOCAL_DISK) {
                        diskReads += usage.getDiskReads();
                        diskWrites += usage.getDiskWrites();
                }
            }
            DiskUsage du = new DiskUsage();
            du.setDirName("allDsik");
            du.setDiskReads(diskReads / 1024 / 1024);
            du.setDiskWrites(diskWrites / 1024 / 1024);
            du.setTime(DateUtils.getTime());
            list.add(du);
        } catch (SigarException e) {
            log.warn("{}", e.getMessage());
        }
        //return list;
        return new JsonResult<>(list,"获取系统磁盘读写成功!");
    }

/*
    public JsonResult<List> getLinuxDiskUsage() {
        List<DiskUsage> list = new ArrayList<>();
        String dirName = "";
        double diskReads = 0;
        double diskWrites = 0;
        try {
            FileSystem fslist[] = sigar.getFileSystemList();
            for (FileSystem fs : fslist) {
                // 只处理本地文件系统，否则中止，下一个
                FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
                DiskUsage du = new DiskUsage();
                if (fs.getType() == FileSystem.TYPE_LOCAL_DISK) {
                    if (fs.getDirName().equals("/")) {
                        du.setDirName(fs.getDirName());
                        du.setDiskReads(ByteUtils.formatByteSize(usage.getDiskReads()));
                        du.setDiskWrites(ByteUtils.formatByteSize(usage.getDiskWrites()));
                        du.setTime(DateUtils.getTime());
                        list.add(du);
                    }
                    else {
                        dirName += fs.getDirName();
                        diskReads += usage.getDiskReads();
                        diskWrites += usage.getDiskWrites();
                    }
                }
            }
            DiskUsage du1 = new DiskUsage();
            du1.setDirName(dirName);
            du1.setDiskReads(ByteUtils.formatByteSize((long) diskReads));
            du1.setDiskWrites(ByteUtils.formatByteSize((long) diskWrites));
            du1.setTime(DateUtils.getTime());
            list.add(du1);
        } catch (SigarException e) {
            log.warn("{}", e.getMessage());
        }
        //return list;
        return new JsonResult<>(list,"获取Linux磁盘读写成功!");
    }*/


    @Override
    //public List<DiskInfo> getDiskInfo() {
    public JsonResult<List> getDiskInfo() {
        List<DiskInfo> list = new ArrayList<>();
        try {
            FileSystem fslist[] = sigar.getFileSystemList();
            for (FileSystem fs : fslist){
                if (fs.getType() == FileSystem.TYPE_LOCAL_DISK) {
                    FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
                    DiskInfo du = new DiskInfo();
                    du.setDirName(fs.getDirName());
                    du.setDiskusedPercent(usage.getUsePercent() * 100);
                    du.setDevTotal(usage.getTotal() / 1024 / 1024);
                    du.setTime(DateUtils.getTime());
                    list.add(du);
                }
            }
        }catch (SigarException e){
            log.warn("{}", e.getMessage());
        }
        //return list;
        return new JsonResult<>(list,"获取磁盘信息成功!");
    }


    @Override
    //public MemUsage getMemInfo() {
    public JsonResult<MemUsage> getMemInfo() {
        MemUsage memUsage = new MemUsage();
        memUsage.setName("Memory");
        //memUsage.setTime(DateUtils.formatDateTime(new Date()));
        memUsage.setTime(DateUtils.getTime());
        try {
            Mem mem = sigar.getMem();
            Swap swap = sigar.getSwap();
            double memUsedvalue = mem.getUsed();
            double memTotalvalue = mem.getTotal();
            double value1 = (double) Math.round((memUsedvalue / memTotalvalue) * 10000) / 100;
            //double value2 = mem.getUsed() * 1.0 / mem.getTotal() * 100;
            //double value = (double) Math.round(mem.getUsedPercent() * 100) / 100;
            /*log.info("getUsedPercent() = {}",(double) Math.round(mem.getUsedPercent() * 100) / 100);
            log.info("memUsedvalue() = {}",mem.getUsed());
            log.info("memTotalvalue() = {}",mem.getTotal());
            log.info("getUsedPercent()1 = {}",(double) Math.round((memUsedvalue / memTotalvalue) * 10000) / 100);*/
            double swapUsedvalue = swap.getUsed();
            double swapTotalvalue = swap.getTotal();
            double swapvalue = (double) Math.round((swapUsedvalue / swapTotalvalue) * 10000) / 100;
            //memUsage.setUsedPercent(value);
            memUsage.setUsedPercent(value1);
            //memUsage.setUsedPercent(value2);
            memUsage.setSwapusedPercent(swapvalue);
        } catch (SigarException e) {
            e.printStackTrace();
        }
        //return memUsage;
        return new JsonResult<>(memUsage,"获取内存信息成功!");
    }


    @Override
    public JsonResult<BasicInfo> getBasicInfo() throws UnknownHostException {
        BasicInfo basicInfo = new BasicInfo();  //创建一个BasicInfo对象
        basicInfo.setName("Computer");
        CpuInfo infos[] = null;
        InetAddress addr = InetAddress.getLocalHost();
        Properties props = System.getProperties();
        OperatingSystem OS = OperatingSystem.getInstance();
        try{
            infos = sigar.getCpuInfoList();  //获取cpu个数
            Mem mem = sigar.getMem();
            CpuInfo info = infos[0];
            basicInfo.setCpuVendor(info.getVendor());  //获取cpu生产厂商
            basicInfo.setCpuModel(info.getModel());  //获取cpu型号
            basicInfo.setCpuCoreness(Runtime.getRuntime().availableProcessors());  //获取cpu核数
            basicInfo.setMemTotal((mem.getTotal() / 1024 / 1024 / 1024) + 1);  //获取内存的总量
            basicInfo.setHostName(addr.getHostName());  //获取系统主机名称
            basicInfo.setSystem(props.getProperty("os.name"));  //获取主机的操作系统
            basicInfo.setEdition(OS.getVendor() + ' ' + props.getProperty("os.name") + ' ' + props.getProperty("os.version"));  //获取操作系统的版本
            basicInfo.setArch(OS.getArch());  //操作系统架构
            basicInfo.setIp(getIpUtils.getLocalIP());  //获取操作系统的ip地址
            basicInfo.setTime(DateUtils.formatDateTime(new Date()));  //设置获取时间
        }catch (SigarException | SocketException e){
            log.warn("{}",e.getMessage());
            return null;
        }
        return new JsonResult<>(basicInfo,"获取系统信息成功!");
    }

}
