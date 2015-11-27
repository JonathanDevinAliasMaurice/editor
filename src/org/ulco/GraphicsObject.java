package org.ulco;

import java.util.Vector;

abstract public class GraphicsObject {
    public GraphicsObject() {
        m_ID  = ID.getInstance().New_ID();
    }

    abstract public GraphicsObject copy();

    public int getID() {
        return m_ID;
    }

    abstract boolean isClosed(Point pt, double distance);

    abstract void move(Point delta);

    abstract public String toJson();

    abstract public String toString();

    abstract public int size();

    abstract public Vector<GraphicsObject> get_element();

    public boolean resultat(Point pt, double distance,Point center){
        return Math.sqrt((center.getX() - pt.getX()) * (center.getX() - pt.getX()) +
                ((center.getY() - pt.getY()) * (center.getY() - pt.getY()))) <= distance;
    }

    private int m_ID;
}
