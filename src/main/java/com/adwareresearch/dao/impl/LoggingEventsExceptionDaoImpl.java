package com.adwareresearch.dao.impl;

import com.adwareresearch.dao.LoggingEventsExceptionDao;
import com.adwareresearch.dao.common.GenericDaoImpl;
import com.adwareresearch.domain.LoggingEventException;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class LoggingEventsExceptionDaoImpl extends GenericDaoImpl<LoggingEventException, String> implements LoggingEventsExceptionDao {

    public LoggingEventsExceptionDaoImpl() {
        super(LoggingEventException.class);
    }

    @Override
    public List<LoggingEventException> findByEvent(int eventId) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("eventId", eventId);
		return findByNamedQueryWithParameters("findByEvent", parameters);
    }
    
}
