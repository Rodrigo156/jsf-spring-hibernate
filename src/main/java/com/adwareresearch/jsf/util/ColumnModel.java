package com.adwareresearch.jsf.util;

import java.io.Serializable;

public class ColumnModel implements Serializable {
    private static final long serialVersionUID = 1L;
	
    private String header;
    private String property;

    public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
    }

    public String getHeader() {
            return header;
    }

    public void setHeader(String header) {
            this.header = header;
    }

    public String getProperty() {
            return property;
    }

    public void setProperty(String property) {
            this.property = property;
    }
}
