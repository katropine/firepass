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
     * @param page
     * @param total
     * @return 
     */
    public PaginationResource calc(int page, int total){

        page = (page == 0)? 1 : page;

        this.page = page;

        this.numberOfLinks -= 1;

        double mid = Math.floor(this.numberOfLinks/2);
        
        int numpages = 1;
        if (total>this.numberOfResoults){
            numpages = (int) Math.ceil(total/(double)this.numberOfResoults);
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

        PaginationResource paging = new PaginationResource();
        
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
        
        paging.numberOfRowsPerPage = this.numberOfResoults;
        return paging;
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
