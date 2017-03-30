package edu.hm.renderer;

import java.lang.reflect.Type;


public class ArrayRenderer implements NonPrimitiveTypeRenderer {

    private Object target;

    public ArrayRenderer(Object target) {
        this.target = target;
    }

    public String render() {
        Object[] objArr = { target };

        String ret = "";
        for(Object o: objArr) {
            ret += o.toString();
        }

        return ret;
    }
}
