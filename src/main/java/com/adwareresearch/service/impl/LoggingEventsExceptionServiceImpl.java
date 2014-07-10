package com.adwareresearch.service.impl;

import com.adwareresearch.dao.LoggingEventsExceptionDao;
import com.adwareresearch.domain.LoggingEventException;
import com.adwareresearch.service.LoggingEventsExceptionService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoggingEventsExceptionServiceImpl implements LoggingEventsExceptionService {

    @Autowired
    private LoggingEventsExceptionDao dao;
    
    @Override
    public List<LoggingEventException> findByEvent(int eventId) {
        return dao.findByEvent(eventId);
    }
    
}
