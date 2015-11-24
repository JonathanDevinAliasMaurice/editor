package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject  {

    private Vector<GraphicsObject> m_objectList;
    private int m_ID;

    public Group() {

        m_objectList = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().New_ID();
    }

    public Group(String json) {
        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex =str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex-2));
        parseGroups(str.substring(groupsIndex+8,endIndex - 1));
    }

    public void add(Object object) {
        m_objectList.add((GraphicsObject)object);
    }


    public boolean isClosed(Point pt, double distance) {
        boolean closed=true;
        if(this.size()==0){
            closed=false;
        }else {
            for (GraphicsObject o : m_objectList) {
                if (o.isClosed(pt, distance) == false) {
                    closed = false;
                }
            }
        }
        return closed;
    }


    public Vector<GraphicsObject> get_element(){
        Vector<GraphicsObject> list= new Vector<GraphicsObject>();
        for (GraphicsObject o : m_objectList) {
            list.add(o);
        }
        return list;
    }

    public Group copy() {
        Group g = new Group();

        for (GraphicsObject o : m_objectList) {
            g.add(o.copy());
        }
        return g;
    }

    public int getID() {
        return m_ID;
    }

    public void move(Point delta) {
        Group g = new Group();

        for (GraphicsObject o : m_objectList) {
            o.move(delta);
        }
    }

    private int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    private void parseGroups(String groupsStr) {
        while (!groupsStr.isEmpty()) {
            int separatorIndex = searchSeparator(groupsStr);
            String groupStr;

            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(groupStr));
            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
    }

    private void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    public int size() {
        int size = 0;

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
                size += element.size();
            }
        return size;
    }

    public String toJson() {
        String str = "{ type: group, objects : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(!(element instanceof Group)){
                str += element.toJson();
                if (i < m_objectList.size() - 1) {
                    str += ", ";
                }
            }

        }
        str += " }, groups : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(element instanceof Group) {
                str += element.toJson();
            }
        }
        return str + " } }";
    }

    public String toString() {
        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(!(element instanceof Group)) {
                if (!(str.equals("group[["))) {
                    str += ", ";
                }
                str += element.toString();

            }

        }
        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(element instanceof Group) {
                str += element.toString();
            }
        }
        return str + "]]";
    }



}
