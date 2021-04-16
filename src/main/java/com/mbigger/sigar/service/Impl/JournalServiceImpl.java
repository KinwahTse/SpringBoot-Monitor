package com.mbigger.sigar.service.Impl;

import com.mbigger.sigar.bean.Journal;
import com.mbigger.sigar.mapper.JournalMapper;
import com.mbigger.sigar.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("JournalService")
public class JournalServiceImpl implements JournalService {

    @Autowired
    JournalMapper journalMapper;

    @Override
    public int insertJournal(Journal journal){
        return journalMapper.insertJournal(journal);
    }

    @Override
    public List<Journal> findAllJournal(){
        return journalMapper.findAllJournal();
    }
}
