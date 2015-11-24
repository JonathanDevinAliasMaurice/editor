package org.ulco;

import org.ulco.Point;

/**
 * Created by jdevin on 24/11/15.
 */
public class Builder {

    public Document createDocumentSquare(Point origin, int line, int column, double length){
    return new Document(origin,line,column,length);
    }

    public Document createDocumentCircle(Point center, int number, double radius, double delt){
        return new Document(center,number,radius,delt);
    }
}
