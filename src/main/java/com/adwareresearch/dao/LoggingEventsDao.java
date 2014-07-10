package com.adwareresearch.dao;

import com.adwareresearch.dao.common.GenericDao;
import com.adwareresearch.domain.LoggingEvent;

import java.util.List;

public interface LoggingEventsDao extends GenericDao<LoggingEvent, String> {
    public List<LoggingEvent> list();
}
