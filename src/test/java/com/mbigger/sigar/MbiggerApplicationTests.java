package com.mbigger.sigar;

import lombok.extern.slf4j.Slf4j;
import org.hyperic.sigar.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class MbiggerApplicationTests {

    private Sigar sigar;

    @Before
    public void contextLoads() {
        this.sigar = new Sigar();
    }

    @Test
    public void aTestCpu() {
        int cpuLength = 0;
        try {
            cpuLength = sigar.getCpuInfoList().length;
        } catch (SigarException e1) {
            e1.printStackTrace();
        }
        log.info("CPU数量: {}", cpuLength);

        log.info("{}", "CPU的总量（单位：HZ）及CPU的相关信息");
        CpuInfo infos[] = null;
        try {
            infos = sigar.getCpuInfoList();
        } catch (SigarException e1) {
            e1.printStackTrace();
        }
        for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
            CpuInfo info = infos[i];
            log.info("CPU的总量MHz,MHz={}", info.getMhz());
            log.info("CPU的卖主, Vendor={}", info.getVendor());
            log.info("CPU的型号, Model={}", info.getModel());
            log.info("缓冲存储器数量，Cache size={}", info.getCacheSize());
        }

        log.info("{}", "CPU的用户使用量、系统使用剩余量、总的剩余量、总的使用占用量等（单位：100%）");

        log.info("{}", "方式一，主要是针对一块CPU的情况");
        CpuPerc cpu;
        try {
            cpu = sigar.getCpuPerc();
            log.info("CPU用户使用率: {}", CpuPerc.format(cpu.getUser()));
            log.info("CPU系统使用率: {}", CpuPerc.format(cpu.getSys()));
            log.info("CPU当前等待率: {}", CpuPerc.format(cpu.getWait()));
            log.info("CPU当前Nice率: {}", CpuPerc.format(cpu.getNice()));
            log.info("CPU当前空闲率: {}", CpuPerc.format(cpu.getIdle()));
            log.info("CPU当前硬中断率: {}", CpuPerc.format(cpu.getIrq()));
            if (SigarLoader.IS_LINUX) {
                log.info("CPU当前软中断闲率: {}", CpuPerc.format(cpu.getSoftIrq()));
                log.info("CPU当前软中断闲率: {}", CpuPerc.format(cpu.getStolen()));
            }
            log.info("CPU总的使用率: {}", CpuPerc.format(cpu.getCombined()));
        } catch (SigarException e) {
            e.printStackTrace();
        }

        log.info("{}", "方式二，不管是单块CPU还是多CPU都适用 ");
        CpuPerc cpuList[] = null;
        try {
            cpuList = sigar.getCpuPercList();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < cpuList.length; i++) {
            log.info("{}, {}", i, cpuList[i].getCombined());
        }
    }

    @Test
    public void bTestMem() {
        // 物理内存信息
        Mem mem = null;
        try {
            mem = sigar.getMem();
        } catch (SigarException e1) {
            e1.printStackTrace();
        }
        // 内存总量
        log.info("Total = " + mem.getTotal() / 1024L / 1024 + "M av");
        // 当前内存使用量
        log.info("Used = " + mem.getUsed() / 1024L / 1024 + "M used");
        // 当前内存剩余量
        log.info("Free = " + mem.getFree() / 1024L / 1024 + "M free");

        // 系统页面文件交换区信息
        Swap swap = null;
        try {
            swap = sigar.getSwap();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        // 交换区总量
        log.info("Total = " + swap.getTotal() / 1024L + "K av");
        // 当前交换区使用量
        log.info("Used = " + swap.getUsed() / 1024L + "K used");
        // 当前交换区剩余量
        log.info("Free = " + swap.getFree() / 1024L + "K free");
    }

    @Test
    public void cTestSystem() {
        // 取到当前操作系统的名称
        String hostname = "";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception exc) {
            try {
                hostname = sigar.getNetInfo().getHostName();
            } catch (SigarException e) {
                hostname = "localhost.unknown";
            } finally {
                sigar.close();
            }
        }
        log.info(hostname);

        // 取当前操作系统的信息
        OperatingSystem OS = OperatingSystem.getInstance();
        // 操作系统内核类型如： 386、486、586等x86
        log.info("OS.getArch() = {}", OS.getArch());
        log.info("OS.getCpuEndian() = {}", OS.getCpuEndian());//
        log.info("OS.getDataModel() = {}", OS.getDataModel());//
        // 系统描述
        log.info("OS.getDescription() = {}", OS.getDescription());
        log.info("OS.getMachine() = {}", OS.getMachine());//
        // 操作系统类型
        log.info("OS.getName() = {}", OS.getName());
        log.info("OS.getPatchLevel() = {}", OS.getPatchLevel());//
        // 操作系统的卖主
        log.info("OS.getVendor() = {}", OS.getVendor());
        // 卖主名称
        log.info("OS.getVendorCodeName() = {}", OS.getVendorCodeName());
        // 操作系统名称
        log.info("OS.getVendorName() = {}", OS.getVendorName());
        // 操作系统卖主类型
        log.info("OS.getVendorVersion() = {}", OS.getVendorVersion());
        // 操作系统的版本号
        log.info("OS.getVersion() = {}", OS.getVersion());

        // 取当前系统进程表中的用户信息
        Who who[] = null;
        try {
            who = sigar.getWhoList();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        if (who != null && who.length > 0) {
            for (int i = 0; i < who.length; i++) {
                log.info("\n~~~~~~~~~" + String.valueOf(i) + "~~~~~~~~~");
                Who _who = who[i];
                log.info("getDevice() = {}", _who.getDevice());
                log.info("getHost() = {}", _who.getHost());
                log.info("getTime() = {}", _who.getTime());
                // 当前系统进程表中的用户名
                log.info("getUser() = {}", _who.getUser());
            }
        }
    }

    @Test
    public void dTestDisk() throws SigarException {
        // 取硬盘已有的分区及其详细信息（通过sigar.getFileSystemList()来获得FileSystem列表对象，然后对其进行编历
        FileSystem fslist[] = sigar.getFileSystemList();
        String dir = System.getProperty("user.home");// 当前用户文件夹路径
        log.info(dir + "   " + fslist.length);
        for (int i = 0; i < fslist.length; i++) {
            log.info("\n~~~~~~~~~~" + i + "~~~~~~~~~~");
            FileSystem fs = fslist[i];
            // 分区的盘符名称
            log.info("fs.getDevName() = " + fs.getDevName());
            // 分区的盘符名称
            log.info("fs.getDirName() = " + fs.getDirName());
            log.info("fs.getFlags() = " + fs.getFlags());//
            // 文件系统类型，比如 FAT32、NTFS
            log.info("fs.getSysTypeName() = " + fs.getSysTypeName());
            // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            log.info("fs.getTypeName() = " + fs.getTypeName());
            // 文件系统类型
            log.info("fs.getType() = " + fs.getType());
            FileSystemUsage usage = null;
            try {
                usage = sigar.getFileSystemUsage(fs.getDirName());
            } catch (SigarException e) {
                if (fs.getType() == 2)
                    continue;
            }
            switch (fs.getType()) {
                case 0: // TYPE_UNKNOWN ：未知
                    break;
                case 1: // TYPE_NONE
                    break;
                case 2: // TYPE_LOCAL_DISK : 本地硬盘
                    // 文件系统总大小
                    log.info(" Total = " + usage.getTotal() + "KB");
                    // 文件系统剩余大小
                    log.info(" Free = " + usage.getFree() + "KB");
                    // 文件系统可用大小
                    log.info(" Avail = " + usage.getAvail() + "KB");
                    // 文件系统已经使用量
                    log.info(" Used = " + usage.getUsed() + "KB");
                    double usePercent = usage.getUsePercent() * 100D;
                    // 文件系统资源的利用率
                    log.info(" Usage = " + usePercent + "%");
                    break;
                case 3:// TYPE_NETWORK ：网络
                    break;
                case 4:// TYPE_RAM_DISK ：闪存
                    break;
                case 5:// TYPE_CDROM ：光驱
                    break;
                case 6:// TYPE_SWAP ：页面交换
                    break;
            }
            log.info(" DiskReads = " + usage.getDiskReads());
            log.info(" DiskWrites = " + usage.getDiskWrites());
        }
    }

    @Test
    public void eTestNetwork() {
        log.info("{}", "当前机器的正式域名");
        try {
            log.info(InetAddress.getLocalHost().getCanonicalHostName());
        } catch (UnknownHostException e) {
            try {
                log.info(sigar.getFQDN());
            } catch (SigarException ex) {
            } finally {
                sigar.close();
            }
        }

        log.info("{}", "取到当前机器的IP地址");
        String address = null;
        try {
            address = InetAddress.getLocalHost().getHostAddress();
            // 没有出现异常而正常当取到的IP时，如果取到的不是网卡循回地址时就返回
            // 否则再通过Sigar工具包中的方法来获取
            log.info(address);
            if (!NetFlags.LOOPBACK_ADDRESS.equals(address)) {
            }
        } catch (UnknownHostException e) {
            // hostname not in DNS or /etc/hosts
        }
        try {
            address = sigar.getNetInterfaceConfig().getAddress();
        } catch (SigarException e) {
            address = NetFlags.LOOPBACK_ADDRESS;
        } finally {
        }
        log.info(address);

        log.info("{}", "取到当前机器的MAC地址");
        String[] ifaces = null;
        try {
            ifaces = sigar.getNetInterfaceList();
        } catch (SigarException e2) {
            e2.printStackTrace();
        }
        String hwaddr = null;
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = null;
            try {
                cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            } catch (SigarException e) {
                e.printStackTrace();
            }
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            hwaddr = cfg.getHwaddr();
            log.info(hwaddr);
            // break;
        }
        log.info(hwaddr != null ? hwaddr : null);

        log.info("{}", "获取网络流量等信息");
        String ifNames[] = null;
        try {
            ifNames = sigar.getNetInterfaceList();
        } catch (SigarException e1) {
            e1.printStackTrace();
        }
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = null;
            try {
                ifconfig = sigar.getNetInterfaceConfig(name);
            } catch (SigarException e1) {
                e1.printStackTrace();
            }
            log.info("name = " + name);// 网络设备名
            log.info("Address = " + ifconfig.getAddress());// IP地址
            log.info("Netmask = " + ifconfig.getNetmask());// 子网掩码
            if ((ifconfig.getFlags() & 1L) <= 0L) {
                log.info("!IFF_UP...skipping getNetInterfaceStat");
                continue;
            }
            try {
                NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
                log.info("RxPackets = " + ifstat.getRxPackets());// 接收的总包裹数
                log.info("TxPackets = " + ifstat.getTxPackets());// 发送的总包裹数
                log.info("RxBytes = " + ifstat.getRxBytes());// 接收到的总字节数
                log.info("TxBytes = " + ifstat.getTxBytes());// 发送的总字节数
                log.info("RxErrors = " + ifstat.getRxErrors());// 接收到的错误包数
                log.info("TxErrors = " + ifstat.getTxErrors());// 发送数据包时的错误数
                log.info("RxDropped = " + ifstat.getRxDropped());// 接收时丢弃的包数
                log.info("TxDropped = " + ifstat.getTxDropped());// 发送时丢弃的包数
            } catch (SigarNotImplementedException e) {
            } catch (SigarException e) {
                log.info(e.getMessage());
            }
        }

        log.info("{}", "一些其他的信息");
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = null;
            try {
                cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            } catch (SigarException e) {
                e.printStackTrace();
            }
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            log.info("cfg.getAddress() = " + cfg.getAddress());// IP地址
            log.info("cfg.getBroadcast() = " + cfg.getBroadcast());// 网关广播地址
            log.info("cfg.getHwaddr() = " + cfg.getHwaddr());// 网卡MAC地址
            log.info("cfg.getNetmask() = " + cfg.getNetmask());// 子网掩码
            log.info("cfg.getDescription() = " + cfg.getDescription());// 网卡描述信息
            log.info("cfg.getType() = " + cfg.getType());//
            log.info("cfg.getDestination() = " + cfg.getDestination());
            log.info("cfg.getFlags() = " + cfg.getFlags());//
            log.info("cfg.getMetric() = " + cfg.getMetric());
            log.info("cfg.getMtu() = " + cfg.getMtu());
            log.info("cfg.getName() = " + cfg.getName());
        }
    }

}
