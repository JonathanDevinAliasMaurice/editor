package org.ulco;

import org.ulco.GraphicsObject;
import org.ulco.GraphicsObjects;
import org.ulco.Point;

import java.util.Vector;

/**
 * Created by jdevin on 27/11/15.
 */
public class Triangle extends GraphicsObject {
    protected  Point m_centre;
    protected  Point m_sommet1;
    protected  Point m_sommet2;
    protected  Point m_sommet3;

    public Triangle(Point center, Point s1, Point s2,Point s3) {
       m_centre=center;
        m_sommet1=s1;
        m_sommet2=s2;
        m_sommet3=s3;
    }

    public Triangle(String json) {
        String str = json.replaceAll("\\s+","");
        int centerIndex = str.indexOf("center");
        int sommet1Index = str.indexOf("sommet1");
        int sommet2Index = str.indexOf("sommet2");
        int sommet3Index = str.indexOf("sommet3");
        int endIndex = str.lastIndexOf("}");

        m_centre = new Point(str.substring(centerIndex + 7, sommet1Index - 1));
        m_sommet1 = new Point(str.substring(sommet1Index + 8,sommet2Index- 1));
        m_sommet2 = new Point(str.substring(sommet2Index + 8,sommet3Index- 1));
        m_sommet3 = new Point(str.substring(sommet3Index + 8,endIndex- 1));
    }

    @Override

    public GraphicsObject copy() {
        return new Triangle(m_centre.copy(),m_sommet1.copy(), m_sommet2.copy(),m_sommet3.copy());
    }

    @Override
    public String toJson() {
        return "{ type: triangle, center: " + m_centre.toJson() + ", sommet1: " + m_sommet1.toJson() + ", sommet2: " + m_sommet2.toJson()+", sommet3: " + m_sommet3.toJson()+ " }";
    }

    @Override
    public String toString() {
        return "triangle[" + m_centre.toString() + "," + m_sommet1.toString()+ "," + m_sommet2.toString() + m_sommet3.toString() + "]";
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Vector<GraphicsObject> get_element(){
        Vector<GraphicsObject> list= new Vector<GraphicsObject>();
        list.add(this);
        return list;
    }

    public boolean isClosed(Point pt, double distance){
        return this.resultat(pt,distance,m_centre);
    }

    public void move(Point delta){
        m_centre.move(delta);
    }

}
