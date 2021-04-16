package com.mbigger.sigar.controller;

import com.mbigger.sigar.bean.Strategy;
import com.mbigger.sigar.service.Impl.StrategyServiceImpl;
import com.mbigger.sigar.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Boot")
public class StrategyController {

    @Autowired
    private StrategyServiceImpl strategyServiceImpl;

    @RequestMapping("/getStrategy/{id}")
    public Strategy GetStrategy(@PathVariable String id){
        return strategyServiceImpl.strategy(id);
    }

    /*@RequestMapping(value = "/StrategyPost", method = RequestMethod.POST)
    public int insertStrategy(@RequestBody Strategy strategy){
        strategy.setId(UUID.randomUUID().toString());
        strategy.setTime(DateUtils.formatDateTime(new Date()));
        return strategyServiceImpl.insertStrategy(strategy);
    }*/

    @RequestMapping(value = "/StrategyPost", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object,Object> insertStrategy(@RequestBody Map Amap) {
        Strategy strategy = new Strategy();

        String name = (String)Amap.get("name");
        String host = (String)Amap.get("host");
        String type = (String)Amap.get("type");
        String mail = (String)Amap.get("mail");
        String data1 = Amap.get("commonly").toString();
        String data2 = Amap.get("serious").toString();
        Double commonly = Double.valueOf(data1);
        Double serious = Double.valueOf(data2);
        strategy.setName(name);
        strategy.setCommonly(commonly);
        strategy.setSerious(serious);
        strategy.setHost(host);
        strategy.setType(type);
        strategy.setStatus("停止");
        strategy.setContent(type + "超过" + commonly + "%为一般告警/超过" + serious + "%为严重告警");
        strategy.setMail(mail);
        strategy.setId(UUID.randomUUID().toString());
        strategy.setTime(DateUtils.formatDateTime(new Date()));
        int result = strategyServiceImpl.insertStrategy(strategy);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (result<=0){
            map.put("code",400);
        }
        else{
            map.put("code",200);
        }
        return map;
    }

    @RequestMapping("/findAllStrategy")
    public List<Strategy> ListStrategy(){
        return strategyServiceImpl.findAllStrategy();
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object,Object> deleteById(@RequestBody Map Amap){
        String id =(String)Amap.get("id");
        int result = strategyServiceImpl.deleteById(id);
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
