package com.adwareresearch.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="meta",catalog="jsf_example")
public class Meta implements Serializable {
    
    private int id;
    private String passwordComplexity;
    private int passwordExpiry;
    private int sessionTimeout;

    public Meta() {}
    
    public Meta(int id, String passwordComplexity, int passwordExpiry, int sessionTimeout) {
        this.id = id;
        this.passwordComplexity = passwordComplexity;
        this.passwordExpiry = passwordExpiry;
        this.sessionTimeout = sessionTimeout;
    }

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="password_complexity", nullable=true, length=200)
    public String getPasswordComplexity() {
        return passwordComplexity;
    }

    public void setPasswordComplexity(String passwordComplexity) {
        this.passwordComplexity = passwordComplexity;
    }

    @Column(name="password_expiry", nullable=true)
    public int getPasswordExpiry() {
        return passwordExpiry;
    }

    public void setPasswordExpiry(int passwordExpiry) {
        this.passwordExpiry = passwordExpiry;
    }

    @Column(name="session_timeout", nullable = true)
    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meta other = (Meta) obj;
		if (id != other.id)
			return false;
		return true;
	} 
}
