package com.mbigger.sigar.mapper;

import com.mbigger.sigar.bean.Strategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StrategyMapper {

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    Strategy strategy(String id);

    /**
     * 插入数据
     * @param strategy
     * @return
     */
    int insertStrategy(Strategy strategy);

    /**
     *
     * @return
     */
    List<Strategy> findAllStrategy();

    /**
     *
     * @param id
     * @return
     */
    int deleteById(String id);
}
