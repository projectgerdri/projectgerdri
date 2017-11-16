/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;

/**
 *
 * @author adria
 */
public class Resources {
    private String language;
    private String xmlFile;
    private HashMap<String, String> getResources;
    
    public Resources(String w_language, String w_xml){
        language = w_language;
        xmlFile = w_xml;
    }
    
    public void fillHashMap (){
        //temporal
        getResources = new HashMap<String, String>();
        getResources.put("title", "Project Gerdri");
        getResources.put("username", "Usuario");
        getResources.put("password", "Contraseña");
        
        
    }
    
    //temporal
    
    
    
    
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getXmlFile() {
        return xmlFile;
    }
    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }
    public HashMap<String, String> getGetResources() {
        return getResources;
    }

    public void setReturnResources(HashMap<String, String> getResources) {
        this.getResources = getResources;
    }

}
