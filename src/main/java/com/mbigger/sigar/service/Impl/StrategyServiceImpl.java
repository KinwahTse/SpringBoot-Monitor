package com.mbigger.sigar.service.Impl;

import com.mbigger.sigar.bean.Strategy;
import com.mbigger.sigar.mapper.StrategyMapper;
import com.mbigger.sigar.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StrategyService")
public class StrategyServiceImpl implements StrategyService {
    @Autowired
    StrategyMapper strategyMapper;

    @Override
    public Strategy strategy(String id){
        return strategyMapper.strategy(id);
    }

    @Override
    public int insertStrategy(Strategy strategy){
        return strategyMapper.insertStrategy(strategy);
    }

    @Override
    public List<Strategy> findAllStrategy(){
        return strategyMapper.findAllStrategy();
    }

    @Override
    public int deleteById(String id){
        return strategyMapper.deleteById(id);
    }
}
