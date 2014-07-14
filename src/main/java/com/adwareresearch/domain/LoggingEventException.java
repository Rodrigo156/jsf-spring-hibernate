package com.adwareresearch.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;


@NamedNativeQueries({
	@NamedNativeQuery(name = "findByEvent", 
                            query = "select * from logging_event_exception l where l.event_id =:eventId",
                            resultClass=LoggingEventException.class)				  
})

@Entity
@Table(name="logging_event_exception",catalog="jsf_example")
public class LoggingEventException implements Serializable {
    
	private static final long serialVersionUID = 1831893196237939214L;
	
	private int eventId;
    private int i;
    private String traceLine;
    private int id;

    public LoggingEventException() {}
    
    public LoggingEventException(int eventId, int i, String traceLine) {
        this.eventId = eventId;
        this.i = i;
        this.traceLine = traceLine;       
    }

    @Column(name="event_id", nullable=false)
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Column(name="i", nullable=false)
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Column(name="trace_line", nullable=false, length = 255)
    public String getTraceLine() {
        return traceLine;
    }

    public void setTraceLine(String traceLine) {
        this.traceLine = traceLine;
    }

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoggingEventException other = (LoggingEventException) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
