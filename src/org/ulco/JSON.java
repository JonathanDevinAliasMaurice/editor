package org.ulco;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class JSON {
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));
        type= "org.ulco."+type.substring(0,1).toUpperCase()+type.substring(1,type.length());
        try{
            Class cl = Class.forName(type);
            Class[] types = new Class[]{String.class};
            Constructor ct = cl.getConstructor(types);
            Object test = ct.newInstance(str);
            o=(GraphicsObject)test;

        }catch (Exception e){
        e.printStackTrace();
        }
        ;

        return o;
    }

    /*static public Group parseGroup(String json) {
        return new Group(json);
    }*/

    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
