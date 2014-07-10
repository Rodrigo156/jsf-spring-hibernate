package com.adwareresearch.jsf.controller;

import com.adwareresearch.domain.Meta;
import com.adwareresearch.jsf.annotation.SpringApplicationScoped;
import com.adwareresearch.service.MetaService;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SpringApplicationScoped
public class SystemProperties implements Serializable {
    
    @Autowired
    private MetaService metaService;
    private String passwordComplexity;
    private int passwordExpiry;
    private int sessionTimeout;

    public String getPasswordComplexity() {
        List<Meta> list = metaService.list();
        if(list.isEmpty()) {
            return "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        } else {
            return list.get(0).getPasswordComplexity();
        }
    }

    public int getPasswordExpiry() {
        List<Meta> list = metaService.list();
        if(list.isEmpty()) {
            return 180;
        } else {
            return list.get(0).getPasswordExpiry();
        }
    }

    public int getSessionTimeout() {     
        List<Meta> list = metaService.list();
        if(list.isEmpty()) {
            return 30;
        } else {
            return list.get(0).getSessionTimeout();
        }
    }
}
