package com.adwareresearch.jsf.controller;

import com.adwareresearch.domain.LoggingEvent;
import com.adwareresearch.domain.LoggingEventException;
import com.adwareresearch.jsf.annotation.SpringViewScoped;
import com.adwareresearch.jsf.util.ColumnModel;
import com.adwareresearch.service.LoggingEventsExceptionService;
import com.adwareresearch.service.LoggingEventsService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SpringViewScoped
public class LoggingEventsController implements Serializable {
	
    @Autowired
    private LoggingEventsService eventsService;
    @Autowired
    private LoggingEventsExceptionService eventsExceptionService;
    private List<LoggingEvent> list;
    private LoggingEvent event;
    private List<ColumnModel> columns;
    private String exception;
    
    @PostConstruct
    public void init() {     
        this.list = eventsService.list();
        this.columns = new ArrayList<>();
        createDynamicColumns();
    }
    
     public void createDynamicColumns() {           
        columns.add(new ColumnModel("Date", "timeStamp")); 
        columns.add(new ColumnModel("Message", "formattedMessage")); 
        columns.add(new ColumnModel("Level", "level"));
        columns.add(new ColumnModel("Caller", "caller"));
        columns.add(new ColumnModel("User", "arg0"));
    }

    public LoggingEvent getEvent() {
        return event;
    }

    public void setEvent(LoggingEvent event) {
        this.event = event;
    }

    public LoggingEventsService getEventsService() {
        return eventsService;
    }

    public void setEventsService(LoggingEventsService eventsService) {
        this.eventsService = eventsService;
    }

    public List<LoggingEvent> getList() {
        return list;
    }

    public void setList(List<LoggingEvent> list) {
        this.list = list;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public String getException() {
        StringBuilder builder = new StringBuilder();
        for(LoggingEventException logging : eventsExceptionService.findByEvent(event.getEventId())) {
            builder.append(logging.getTraceLine()).append("\n");
        }
        return builder.toString();
    }
}
