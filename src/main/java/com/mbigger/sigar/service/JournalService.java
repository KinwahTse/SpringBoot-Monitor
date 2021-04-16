package com.mbigger.sigar.service;

import com.mbigger.sigar.bean.Journal;

import java.util.List;

public interface JournalService {
    int insertJournal (Journal journal);

    List<Journal> findAllJournal();
}
