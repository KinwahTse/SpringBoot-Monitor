package com.mbigger.sigar.service;

import com.mbigger.sigar.bean.Strategy;

import java.util.List;

public interface StrategyService {

    Strategy strategy(String id);

    int insertStrategy(Strategy strategy);

    List<Strategy> findAllStrategy();

    int deleteById(String id);
}
