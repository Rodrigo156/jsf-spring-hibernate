package com.adwareresearch.service.impl;

import com.adwareresearch.dao.LoggingEventsDao;
import com.adwareresearch.domain.LoggingEvent;
import com.adwareresearch.service.LoggingEventsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoggingEventsServiceImpl implements LoggingEventsService {
    
	@Autowired
    private LoggingEventsDao dao;

    @Override
    public List<LoggingEvent> list() {
        return dao.list();
    }
}
