package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.*;

public class LayerTest extends TestCase {
    public void testType() throws Exception {
        Document document = new Document();
        int oldID =ID.getInstance().ID;
        Layer layer = document.createLayer();

        layer.add(new Square(new Point(2, 8), 10));

        assertEquals(layer.getID(), oldID + 1);
        assertEquals(layer.get(0).getID(), oldID + 2);
    }

    public void testJSON() throws Exception {
        Layer l = new Layer();
        Square s = new Square(new Point(0, 0), 5);
        Circle c = new Circle(new Point(5, 5), 4);

        l.add(s);
        l.add(c);
        assertEquals(l.toJson(), "{ type: layer, objects : { { type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 5.0 }, " +
                "{ type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0 } } }");
    }

    public void testSize3() throws Exception {
        Layer l = new Layer();
        Group g = new Group();
        Square s = new Square(new Point(0,0), 5);
        Circle c = new Circle(new Point(5,5), 4);
        Group g2 = new Group();
        Rectangle r = new Rectangle(new Point(-6,10), 5.2, 9);

        g.add(s);
        g.add(c);
        g2.add(g);
        g2.add(r);
        l.add(g2);
        System.out.println(g.size());
        System.out.println(g2.size());
        System.out.println(l.getObjectNumber());
        assertEquals(l.getObjectNumber(), 3);
    }

    public void testSelect() throws Exception {
        Document document = new Document();
        Layer layer = document.createLayer();
        Circle c = new Circle(new Point(2, 8), 10);
        Group g = new Group();
        g.add(c);
        layer.add(g);


        System.out.println(g.isClosed(new Point(1, 1), 8));
        assertTrue(layer.select(new Point(1, 1), 8).size() == 1);
        System.out.println(layer.select(new Point(1, 1), 8).firstElement().getID());
        System.out.println(c.getID());
        assertTrue(layer.select(new Point(1, 1), 8).firstElement().getID() == c.getID());
    }
}