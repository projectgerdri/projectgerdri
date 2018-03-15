package utils;

import java.util.HashMap;
import java.io.*;
import java.util.List;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author adria
 */
public class Resources {
    private String language;
    private String xmlFile;
    private HashMap<String, String> resourcesMap;
    
    public Resources(String language, String xml){
        this.language = language;
        this.xmlFile = xml;
        resourcesMap = new HashMap<>();
    }
    
    public Resources(String xml){
        this.xmlFile = xml;
        resourcesMap = new HashMap<>();
    }
    
    public void fillHashMap() throws JDOMException, IOException{        
        SAXBuilder saxBuilder = new SAXBuilder();
        File inputFile = new File(xmlFile); 
        Document document = saxBuilder.build(inputFile);
        Element root = document.getRootElement();
        List allResources = root.getChildren();

        for (int i = 0; i < allResources.size(); i++) {
            Element el;
            el = (Element) allResources.get(i);
            String key, value;
            key = el.getAttributeValue("id");
            value = el.getValue();
            resourcesMap.put(key, value);
        }
    }   
    
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
    public HashMap<String, String> getResourcesMap() {
        return resourcesMap;
    }

    public void setResourcesMap(HashMap<String, String> resourcesMap) {
        this.resourcesMap = resourcesMap;
    }

}
