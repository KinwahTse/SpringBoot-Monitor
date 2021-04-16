package com.mbigger.sigar.controller;

import com.mbigger.sigar.bean.WarnMessage;
import com.mbigger.sigar.service.Impl.WarnMessageServiceImpl;
import com.mbigger.sigar.utils.sendMail;
import com.mbigger.sigar.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Boot")
public class WarnMessageController {

    @Autowired
    WarnMessageServiceImpl warnMessageServiceImpl;

    @RequestMapping(value = "/generateMsg", method = RequestMethod.POST)
    public Map<Object,Object> generateMsg(@RequestBody WarnMessage warnMessage){
        warnMessage.setId(UUID.randomUUID().toString());
        warnMessage.setIfread("2");
        warnMessage.setTime(DateUtils.formatDateTime(new Date()));
        int result = warnMessageServiceImpl.generateMsg(warnMessage);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (result<=0){
            map.put("code",400);
        }
        else{
            map.put("code",200);
        }
        return map;
    }

    @RequestMapping(value = "/startStrategy", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object,Object> startStrategy(@RequestBody Map Amap) throws Exception {
        WarnMessage warnMessage = new WarnMessage();
        sendMail sendmail = new sendMail();
        int result = 0;
        String content;

        String data3 = Amap.get("data").toString();
        String data1 = Amap.get("commonly").toString();
        String data2 = Amap.get("serious").toString();
        Double data = Double.valueOf(data3);
        Double commonly = Double.valueOf(data1);
        Double serious = Double.valueOf(data2);
        String host = (String)Amap.get("host");
        String type = (String)Amap.get("type");
        String mail = (String)Amap.get("mail");
        if (data >= commonly){
            if(commonly <= data && data < serious){
                warnMessage.setId(UUID.randomUUID().toString());
                warnMessage.setIfread("2");
                warnMessage.setHost(host);
                warnMessage.setRank("一般告警");
                warnMessage.setTime(DateUtils.formatDateTime(new Date()));
                warnMessage.setContent(type + "超过" + commonly + "%");
                result = warnMessageServiceImpl.startStrategy(warnMessage);
            }else{
                warnMessage.setId(UUID.randomUUID().toString());
                warnMessage.setIfread("2");
                warnMessage.setHost(host);
                warnMessage.setRank("严重告警");
                warnMessage.setTime(DateUtils.formatDateTime(new Date()));
                warnMessage.setContent(type + "超过" + serious + "%");
                content = type + "超过" + serious + "%";
                result = warnMessageServiceImpl.startStrategy(warnMessage);
                sendmail.CreateMail(mail,content);
            }
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (result<=0){
            map.put("code",400);
        }
        else{
            map.put("code",200);
        }
        return map;
    }

    @RequestMapping("/findAllunReadMsg")
    public List<WarnMessage> findAllunReadMsg(String ifread){
        ifread = "2";
        return warnMessageServiceImpl.findAllunReadMsg(ifread);
    }

    @RequestMapping("/findAllReadMsg")
    public List<WarnMessage> findAllReadMsg(String ifread){
        ifread = "1";
        return warnMessageServiceImpl.findAllunReadMsg(ifread);
    }

    @RequestMapping("/findAllMsg")
    public List<WarnMessage> findAllMsg(){
        return warnMessageServiceImpl.findAllMsg();
    }

    @RequestMapping(value = "/updateMsgtoRead" , method = RequestMethod.POST)
    @ResponseBody
    public Map<Object,Object> updateMsgtoRead(@RequestBody Map Amap){
        String id = (String)Amap.get("id");
        String ifread = "1";
        int result = warnMessageServiceImpl.updateMsgtoRead(id,ifread);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (result<=0){
            map.put("code",400);
        }
        else{
            map.put("code",200);
        }
        return map;
    }

    @RequestMapping(value = "/deleteMsgById" , method = RequestMethod.POST)
    @ResponseBody
    public Map<Object,Object> deleteMsgById(@RequestBody Map Amap){
        String id = (String)Amap.get("id");
        int result = warnMessageServiceImpl.deleteMsgById(id);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (result<=0){
            map.put("code",400);
        }
        else{
            map.put("code",200);
        }
        return map;
    }

    @RequestMapping("/deleteAllRead")
    public Map<Object,Object> deleteAllRead(){
        String ifread = "1";
        int result = warnMessageServiceImpl.deleteAllRead(ifread);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (result<=0){
            map.put("code",400);
        }
        else{
            map.put("code",200);
        }
        return map;
    }
}
