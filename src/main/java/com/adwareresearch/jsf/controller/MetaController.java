package com.adwareresearch.jsf.controller;

import com.adwareresearch.domain.Meta;
import com.adwareresearch.jsf.annotation.SpringViewScoped;
import com.adwareresearch.jsf.util.ColumnModel;
import com.adwareresearch.jsf.util.JsfMessageUtil;
import com.adwareresearch.service.MetaService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SpringViewScoped
public class MetaController implements Serializable {
    
    @Autowired
    private MetaService metaService;
    private List<Meta> metaList;
    private List<ColumnModel> columns;
    private Meta meta;
    
    public MetaController() {
        this.meta = new Meta();
        this.columns = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        this.metaList = metaService.list();
        createDynamicColumns();
    }
    
    public void createDynamicColumns() {    
        columns.clear();        
        columns.add(new ColumnModel("Password complexity", "passwordComplexity")); 
        columns.add(new ColumnModel("Password expiry (in days)", "passwordExpiry")); 
        columns.add(new ColumnModel("Session timeout (in minutes)", "sessionTimeout")); 
    }
    
    public void saveOrUpdate() {
        //if(
        		metaService.save(getMeta());
        		//) {
            JsfMessageUtil.addSuccessMessage("Meta saved!");
            setMetaList(metaService.list());
            setMeta(new Meta());
//        } else {
//            JsfMessageUtil.addErrorMessage("Error while saving meta!");
//            LoggerFactory.getLogger(MetaController.class).error("Error while saving meta!");
//        }
    }
    
    public void delete() {
        //if(
        		metaService.delete(getMeta());
        		//) {
            JsfMessageUtil.addSuccessMessage("Meta deleted!");
            setMetaList(metaService.list());
            setMeta(new Meta());
//        } else {
//            JsfMessageUtil.addErrorMessage("Error while deleting meta!");
//            LoggerFactory.getLogger(MetaController.class).error("Error while deleting meta!");
//        }
    }

    public List<Meta> getMetaList() {
        return metaList;
    }

    public void setMetaList(List<Meta> metaList) {
        this.metaList = metaList;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
