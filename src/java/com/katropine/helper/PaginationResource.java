/**
* @package FirePass - katropine
* @author Kristian Beres <kristian@katropine.com>
* @copyright Katropine (c) 2014, www.katropine.com
* @since March 24, 2014
* @licence MIT
*
* Copyright (c) 2014 Katropine, http://www.katropine.com/
*
* Permission is hereby granted, free of charge, to any person obtaining
* a copy of this software and associated documentation files (the
* "Software"), to deal in the Software without restriction, including
* without limitation the rights to use, copy, modify, merge, publish,
* distribute, sublicense, and/or sell copies of the Software, and to
* permit persons to whom the Software is furnished to do so, subject to
* the following conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
* LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
* OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
* WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package com.katropine.helper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author kriss
 */
public class PaginationResource {
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
    
    public PaginationResource(){}
    
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
        String html ="<ul class=\"pagination\">";
        html += "<li><select name=\"rows\" class=\"form-control\" style=\"width:100px;\">";
        
        html += "<option value=\"10\">10</option>";
        html += "<option value=\"20\">20</option>";
        html += "<option value=\"50\">50</option>";
        html += "<option value=\"100\">100</option>";
        html += "<option value=\"500\">500</option>";
        
        html += "</select>";
        html += "</li></ul>";
        
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
