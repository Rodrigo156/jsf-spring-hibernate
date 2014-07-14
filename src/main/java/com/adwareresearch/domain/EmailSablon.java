package com.adwareresearch.domain;

import java.io.Serializable;

public class EmailSablon implements Serializable {

	private static final long serialVersionUID = -4704734571247022659L;
	
	private String receiver;
    private String sender;
    private String subject;
    private String body;

    private EmailSablon() {}

    public String getReceiver() {
            return receiver;
    }

    public void setReceiver(String receiver) {
            this.receiver = receiver;
    }

    public String getSender() {
            return sender;
    }

    public void setSender(String sender) {
            this.sender = sender;
    }

    public String getSubject() {
            return subject;
    }

    public void setSubject(String subject) {
            this.subject = subject;
    }

    public String getBody() {
            return body;
    }

    public void setBody(String body) {
            this.body = body;
    }
}
