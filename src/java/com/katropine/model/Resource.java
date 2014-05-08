/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    @NamedQuery(name="Resource.getAll", query="SELECT e FROM Resource e"),
    @NamedQuery(name="Resource.getAllByGroup", query="SELECT e FROM Resource e WHERE e.group.id=:groupid")
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
    
    @Override
    public String toString(){
        return this.id+" "+this.title;
    }
}
