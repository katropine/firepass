/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.katropine.dao;

import com.katropine.model.Language;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kriss
 */
public class LanguageService {
    
    private final List<Language> langs =  new ArrayList<>();
    
    public LanguageService(){
        this.langs.add(new Language());
        this.langs.add(new Language("Norsk", "no_NO"));
        this.langs.add(new Language("Srpski", "sr_RS"));
    }
    
    public List<Language> getAllLanguages(){
        return langs;
    }
    
    public Language getDefaultLanguage(){
        
        for(Language lng : this.langs){
            if(lng.isDef()){
                return lng;
            } 
        }
        return new Language();
    }
    
}
