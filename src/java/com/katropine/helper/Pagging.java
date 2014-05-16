/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.helper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author kriss
 */
public class Pagging {
    public int first = 1;
    public int prev = 0;
    public int start = 1;
    public int end = 1;
    public int page = 1;
    public int last = 1;
    public int total = 0;
    public int iend = 1;
    public int istart = 1;
    public int next = 0;
    public int numberOfRowsPerPage = 10;
    
    protected String url;
    protected HashMap<String, String> params = new HashMap<>();
    
    public Pagging(){}
    
    @Override
    public String toString(){
        StringBuilder paramsStr = new StringBuilder();
        paramsStr.append("first: "+first);
        paramsStr.append("prev: "+prev);
        paramsStr.append("start: "+start);
        paramsStr.append("end: "+end);
        paramsStr.append("page: "+page);
        paramsStr.append("last: "+last);
        paramsStr.append("total: "+total);
        paramsStr.append("next: "+next);
        paramsStr.append("istart: "+next);
        paramsStr.append("iend: "+next);
        return paramsStr.toString();
    }
    /**
     * 
     * @param url 
     */
    public void setUrl(String url){
        this.url = url;
    }
    /**
     * 
     * @param key
     * @param value 
     */
    public void setParam(String key, String value){
        System.out.println("key: "+key+" value: "+value);
        this.params.put(key, value);
    }
    /**
     * 
     * @return url string
     */
    public String getUi(){
        String html = "";
        html+="<ul class=\"pagination\">";
        
        if(this.total > this.numberOfRowsPerPage){
            html+="<li><a href=\""+this.url+"?page="+this.first+this.getParams().toString()+"\" title=\"Go to Page "+this.first+"\">&laquo;</a></li>";
            html+="<li><a href=\""+this.url+"?page="+this.prev+this.getParams().toString()+"\" title=\"Go to Page "+this.prev+"\">&lsaquo;</a></li>";
            for (int i=this.start;i<=this.end;i++) {
                String current = "";
                if (i==this.page){
                    current = "active";
                }
                html+= "<li class=\""+current+"\"><a href=\""+this.url+"?page="+i+this.getParams().toString()+"\" title=\"Go to Page "+i+"\" class=\"page "+current+"\">"+i+"</a></li>";

            }
            html+="<li><a href=\""+this.url+"?page="+this.next+this.getParams().toString()+"\" title=\"Go to Page "+this.next+"\">&rsaquo;</a></li>";
            html+="<li><a href=\""+this.url+"?page="+this.last+this.getParams().toString()+"\" title=\"Go to Page "+this.last+"\">&raquo;</a></li>";
        }
        html+= "<li class=\"disabled\"><a href=\"#\">"+this.page+"/"+this.end+"</a></li>";
        html+= "<li class=\"disabled\"><a href=\"#\">records: "+this.total+"</a></li>";
        html+="</ul>";
        return html;
    }
    public String getUiNumberOfRecords(){
        return this.iend+"/"+this.total;
    }
    
    public String getUiRowsPerPage(){
        
        String html = "<select name=\"rows\">";
        
        html += "<option value=\"10\">10</option>";
        html += "<option value=\"20\">20</option>";
        html += "<option value=\"50\">50</option>";
        html += "<option value=\"100\">100</option>";
        html += "<option value=\"500\">500</option>";
        
        html += "</select>";
        
        return html;
    }
    
    
    /**
     * 
     * @return StringBuilder
     */
    private StringBuilder getParams(){
         StringBuilder paramsStr = new StringBuilder();
         
         Map<String, String> map = this.params;
         Iterator it = map.entrySet().iterator();
         while(it.hasNext()){
             Map.Entry pairs = (Map.Entry)it.next();
             paramsStr.append("&");
             String s = pairs.getKey() + "=" + pairs.getValue();
             paramsStr.append(s);
         }
         
        return paramsStr;
    }
}
