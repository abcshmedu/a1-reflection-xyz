package edu.hm.renderer;


import edu.hm.RenderMe;

import java.lang.reflect.Field;

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

    public String render() throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        Class objectClass = object.getClass();

        Field[] fields = objectClass.getDeclaredFields();

        // Class name
        objectClass.getName();

        for(Field field : fields) {
            if(field.isAnnotationPresent(RenderMe.class)) {

                // with value
                RenderMe renderMe = field.getAnnotation(RenderMe.class);
                String with = renderMe.with();
                // Class found with name
                Class arrayRenderer = Class.forName(with);

                Object obj = arrayRenderer.newInstance();


                Class type = field.getType();
                // type name (int)
                type.getCanonicalName();

                // field name (foo)
                field.getName();

                // value of variable foo
                field.get(getObject());


            }
        }

        return getObject().toString();
    }

}
