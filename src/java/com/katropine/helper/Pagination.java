/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.helper;


/**
 *
 * @author kriss
 */
public class Pagination {
    
    protected int numberOfResoults;
    protected int numberOfLinks;
    protected int page;

    /**
     * 
     * @param numberOfResoults How meni resoults to display per page
     * @param numberOfLinks Number of links in paging meni
     */
    public Pagination(int numberOfResoults, int numberOfLinks) {
        this.numberOfResoults = numberOfResoults;
        this.numberOfLinks = numberOfLinks;
    }
    /**
     *
     * @param int page $_POST['curent_page'] or $_GET['curent_page']
     * @param int rp how meni resoults displayd per page
     * @param int total count with saime criteria [sql] as you pull data
     * @return array $pg_data['pg'] ----------------------
     *               $paging[‘start’] = starting page value
     *               $paging[‘end’] = ending page value
     *               $paging[‘last’] = last page
     *               $paging[‘total’] = number of results
     *               $paging[‘istart’] = starting result number for current page
     *               $paging[‘iend’] = ending result number for current page
     */
    public Object calc(int page, int total){

        page = (page == 0)? 1 : page;

        this.page = page;

        this.numberOfLinks -= 1;

        double mid = Math.floor(this.numberOfLinks/2);
        
        int numpages = 1;
        if (total>this.numberOfResoults){
            numpages = (int) Math.ceil(total/this.numberOfResoults);
        }
        if (page>numpages){ 
            page = numpages;
        }

        int npage = page;

        while ((npage-1)>0&&npage>(page-mid)&&(npage>0)){
            npage -= 1;
        }
        int lastpage = npage + this.numberOfLinks;

        if (lastpage>numpages){
            npage = numpages - this.numberOfLinks + 1;
            if (npage<0) npage = 1;
            lastpage = npage + this.numberOfLinks;
            if (lastpage>numpages) lastpage = numpages;
        }

        while ((lastpage-npage)<this.numberOfLinks) npage -= 1;

        if (npage<1) npage = 1;
        
        class Pagging{
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
            
            
            public Pagging(){}
        }
        
        Pagging paging = new Pagging();
        
        paging.first = 1;
        if (page>1) paging.prev = page - 1; else paging.prev = 1;
        paging.start = npage;
        paging.end = lastpage;
        paging.page = page;
        if ((page+1)<numpages) paging.next = page + 1; else paging.next = numpages;
        paging.last = numpages;
        paging.total = total;
        paging.iend = page * this.numberOfResoults;
        paging.istart = (page * this.numberOfResoults) - this.numberOfResoults + 1;

        if ((page * this.numberOfResoults)>total) paging.iend = total;

        return (Object) paging;
    }
    /**
     *
     * @return int Number or rows per page
     */
    public int getLimit(){
        return this.numberOfResoults;
    }
    /**
     *
     * @param int $page The number of current page
     * @return int to start from record number
     */
    public int getOffset(){
        return ((this.page-1) * this.numberOfResoults);
    }
    /**
     * Record number in iteration
     * @param int count
     * @return int 
     *
     * Tutorial !!!!!!!!:
     * 
     * $count = 0;
     *
     * foreach ($array as $row){
     *
     *   $count = $Paging->getRecordNumber($count);
     *
     *   echo $count;
     *
     * }
     *
     */
    public int getRecordNumber(int count){
        int offset = 0;
        if(count == 0){offset = this.getOffset();}
        count = count + 1 + offset;
        return count;
    }
}
