package edu.hm.renderer;



public class Renderer {

    private Object object;

    public Renderer(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String render() {
        return getObject().toString();
    }

}
