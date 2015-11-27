package org.ulco;

import java.util.Vector;

public class Circle extends GraphicsObject {
    public Circle(Point center, double radius) {
        this.m_center = center;
        this.m_radius = radius;
    }

    public Circle(String json) {
        String str = json.replaceAll("\\s+", "");

        int endIndex = str.lastIndexOf("}");
        int centerIndex = str.indexOf("center");
        int radiusIndex = str.indexOf("radius");


        m_center = new Point(str.substring(centerIndex + 7, radiusIndex - 1));
        m_radius = Double.parseDouble(str.substring(radiusIndex + 7, endIndex));
    }

    public GraphicsObject copy() {
        return new Circle(m_center.copy(), m_radius);
    }

    public Point getCenter() { return m_center; }

    public boolean isClosed(Point pt, double distance) {
        return this.resultat(pt,distance,m_center);
    }

    void move(Point delta) { m_center.move(delta); }

    public String toJson() {
        return "{ type: circle, center: " + m_center.toJson() + ", radius: " + this.m_radius + " }";
    }

    public String toString() {
        return "circle[" + m_center.toString() + "," + m_radius + "]";
    }

    public int size(){
        return 1;
    }
    public Vector<GraphicsObject> get_element(){
        Vector<GraphicsObject> list= new Vector<GraphicsObject>();
        list.add(this);
        return list;
    }
    private final Point m_center;
    private final double m_radius;
}
