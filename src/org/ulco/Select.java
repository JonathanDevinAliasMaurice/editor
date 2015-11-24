package org.ulco;

/**
 * Created by jdevin on 24/11/15.
 */
public  class Select  {

    public static GraphicsObjects select(Point pt, double distance,Layer layer) {
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : layer.get()) {
            if (object.isClosed(pt, distance)) {
                list.addAll(object.get_element());
            }
        }
        return list;
    }

    public static GraphicsObjects select(Point pt, double distance,Document document) {
            GraphicsObjects list = new GraphicsObjects();

            for (Layer layer : document.get()) {
                list.addAll(layer.select(pt, distance));
            }
            return list;
        }
    }

