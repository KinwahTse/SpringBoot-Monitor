package com.mbigger.sigar.mapper;

import com.mbigger.sigar.bean.Journal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface JournalMapper {

    int insertJournal (Journal journal);

    List<Journal> findAllJournal();
}
