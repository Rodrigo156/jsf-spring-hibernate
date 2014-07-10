package com.adwareresearch.dao;

import com.adwareresearch.dao.common.GenericDao;
import com.adwareresearch.domain.LoggingEventException;

import java.util.List;

public interface LoggingEventsExceptionDao extends GenericDao<LoggingEventException, String> {
    public List<LoggingEventException> findByEvent(int eventId);
}
