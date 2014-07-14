package com.adwareresearch.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="logging_event",catalog="jsf_example")
public class LoggingEvent implements Serializable {
	
	private static final long serialVersionUID = -617840406006287801L;
	
	private int eventId;
    private String timeStamp;
    private String formattedMessage;
    private String loggerName;
    private String level;
    private String thread;
    private int referenceFlag;
    private String caller;
    private String arg0;
    private String arg1;
    private String arg2;
    private String arg3;
    private String callerClass;
    private String callerMethod;
    private String callerLine;

    public LoggingEvent() {
    }

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="event_id", unique=true, nullable=false)
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Column(name="timestmp", nullable=false)
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Column(name="formatted_message", nullable=false)
    public String getFormattedMessage() {
        return formattedMessage;
    }

    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

    @Column(name="logger_name", nullable=false, length=255)
    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    @Column(name="level_string", nullable=false, length=255)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Column(name="thread_name", nullable=false, length=255)
    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    @Column(name="reference_flag", nullable=false)
    public int getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(int referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    @Column(name="caller_filename", nullable=false, length=255)
    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    @Column(name="arg0", nullable=true, length=255)
    public String getArg0() {
        return arg0;
    }

    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    @Column(name="arg1", nullable=true, length=255)
    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    @Column(name="arg2", nullable=true, length=255)
    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    @Column(name="arg3", nullable=true, length=255)
    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }

    @Column(name="caller_class", nullable=false, length=255)
    public String getCallerClass() {
        return callerClass;
    }

    public void setCallerClass(String callerClass) {
        this.callerClass = callerClass;
    }

    @Column(name="caller_method", nullable=false, length=255)
    public String getCallerMethod() {
        return callerMethod;
    }

    public void setCallerMethod(String callerMethod) {
        this.callerMethod = callerMethod;
    }

    @Column(name="caller_line", nullable=false, length=255)
    public String getCallerLine() {
        return callerLine;
    }

    public void setCallerLine(String callerLine) {
        this.callerLine = callerLine;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.eventId;
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
        final LoggingEvent other = (LoggingEvent) obj;
        return this.eventId == other.eventId;
    }
}
