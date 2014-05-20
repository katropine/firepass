/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.model;

/**
 *
 * @author kriss
 */
public class Language {
    
    private String name = "English";
    private String code = "en";
    private boolean def = true;
    
    public Language(){}

    public Language(String name, String code) {
        this.name = name;
        this.code = code;
        this.def = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isDef() {
        return def;
    }

    public void setDef(boolean def) {
        this.def = def;
    }
    
    
    
}
