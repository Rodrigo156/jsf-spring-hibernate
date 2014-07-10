package com.adwareresearch.service;

import com.adwareresearch.domain.LoggingEventException;
import java.util.List;

public interface LoggingEventsExceptionService {
    public List<LoggingEventException> findByEvent(int eventId);
}
