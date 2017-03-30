package edu.hm.renderer;


import edu.hm.RenderMe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Renderer {

    /**
     * Target object we are retrieving information from.
     */
    private Object target;

    /**
     * Constructor, assigns our target-object.
     * @param target
     */
    public Renderer(Object target) {
        this.target = target;
    }

    /**
     * Renders our target-object.
     * @return rendered string
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */
    public String render() throws IllegalAccessException, ClassNotFoundException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        Class targetClass = target.getClass();
        String ret = "Instance of " + targetClass.getName() + ":\n";

        /* Get all fields of class, no matter their access*/
        for(Field f: targetClass.getDeclaredFields()) {

            f.setAccessible(true);

            /* Skip fields that don't have the RenderMe-annotation*/
            if(!f.isAnnotationPresent(RenderMe.class)) {
                continue;
            }

            /* Fieldname */
            ret += f.getName();

            /* Value of the "with"-property*/
            String typeRendererClass = f.getAnnotation(RenderMe.class).with();

            /* Check wether the "with"-Property is set */
            if(typeRendererClass.length() > 0) {
                /* NonPrimitiveTypeRenderer is used */
                Class nonPrimitiveTypeRenderer = Class.forName(typeRendererClass);

                /* Instantiate nptr with field-value */
                NonPrimitiveTypeRenderer nptr =
                        (NonPrimitiveTypeRenderer)nonPrimitiveTypeRenderer
                                .getDeclaredConstructor(Object.class)
                                .newInstance(f.get(target));

                /* Render field */
                nptr.render();
            } else {
                /* Default typeRenderer is used */
                ret += " (" + f.getType().getCanonicalName() + "): ";
            }

            // Class found with name
            //Class arrayRenderer = Class.forName(with);

            //Object obj = arrayRenderer.newInstance();


            /*Class type = f.getType();
            // type name (int)
            type.getCanonicalName();

            // f name (foo)
            f.getName();

            // value of variable foo
            f.get(target);*/
        }

        return ret;
        //return getTarget().toString();
    }

}
