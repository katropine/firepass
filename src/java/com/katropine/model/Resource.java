/**
* @package FirePass - katropine
* @author Kristian Beres <kristian@katropine.com>
* @copyright Katropine (c) 2014, www.katropine.com
* @since March 24, 2014
* @licence MIT
*
* Copyright (c) 2014 Katropine - Kristian Beres, http://www.katropine.com/
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
package com.katropine.model;

import com.katropine.controller.ResourceServlet;
import com.katropine.libs.SymmetricEncryption;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author kriss
 */
@Entity
@Table(name="resource")
@NamedQueries({
    @NamedQuery(name="Resource.getAll", query="SELECT e FROM Resource e WHERE e.title LIKE :title AND e.group IN :resourceGroup"),
    @NamedQuery(name="Resource.countAll", query="SELECT COUNT(e) FROM Resource e WHERE e.title LIKE :title AND e.group IN :resourceGroup"),
    @NamedQuery(name="Resource.getAllByGroup", query="SELECT e FROM Resource e WHERE e.group.id=:groupid AND e.group IN :resourceGroup"),
    @NamedQuery(name="Resource.countAllByGroup", query="SELECT COUNT(e) FROM Resource e WHERE e.group.id=:groupid AND e.group IN :resourceGroup")
})
public class Resource implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(unique=true)
    private String title;
    
    @Column(length=100000)
    private byte[] body;
    
    @Column(name="body_iv", length=100000)
    private byte[] bodyIv;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date created = new Date();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date modified = new Date();
    
    @ManyToOne
    private ResourceGroup group = new ResourceGroup();

    public Resource(int id, String title) {
        this.id = id;
        this.title = title;
    }
    
    public Resource(String title) {
        this.title = title;
    }
    
    public Resource() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        if(this.body == null) return "";
        try {
            // im shure this should this be done better?!
            SymmetricEncryption se = new SymmetricEncryption();
            return se.decrypt(this.body, this.bodyIv);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }

    public void setBody(String body) throws UnsupportedEncodingException {
        try {
            SymmetricEncryption se = new SymmetricEncryption();
            se.encrypt(body);

            this.body = se.getCiphertext();
            this.bodyIv = se.getIv();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(ResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResourceGroup getGroup() {
        return group;
    }

    public void setGroup(ResourceGroup group) {
        this.group = group;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
    
    @Override
    public String toString(){
        return this.id+" "+this.title;
    }
}
