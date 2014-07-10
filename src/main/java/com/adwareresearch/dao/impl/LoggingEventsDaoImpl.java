package com.adwareresearch.dao.impl;

import com.adwareresearch.dao.LoggingEventsDao;
import com.adwareresearch.dao.common.GenericDaoImpl;
import com.adwareresearch.domain.LoggingEvent;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class LoggingEventsDaoImpl extends GenericDaoImpl<LoggingEvent, String> implements LoggingEventsDao {

    public LoggingEventsDaoImpl() {
        super(LoggingEvent.class);
    }

    @Override
    public List<LoggingEvent> list() {
        return findAll();
    }
    
}
