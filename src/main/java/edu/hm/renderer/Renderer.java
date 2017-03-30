package edu.hm.renderer;


import edu.hm.RenderMe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Provides rendering for fields with the @RenderMe-Annotation.
 *
 * @author J. Behrmann, J. Wittek
 * @version 31.03.2017
 */
public class Renderer {

    /**
     * Target object we are retrieving information from.
     */
    private Object target;

    /**
     * Constructor, assigns our target-object.
     * @param target object
     */
    public Renderer(Object target) {
        this.target = target;
    }

    /**
     * Renders our target-object.
     * @return rendered string
     * @throws ClassNotFoundException ignored
     * @throws NoSuchMethodException ignored
     * @throws IllegalAccessException ignored
     * @throws InstantiationException ignored
     * @throws InvocationTargetException ignored
     */
    @SuppressWarnings("unchecked")
    public String render() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InstantiationException, InvocationTargetException {
        Class targetClass = target.getClass();
        String out = "Instance of " + targetClass.getName() + ":\n";

        /* Get all fields of class */
        for (Field f: targetClass.getDeclaredFields()) {

            /* Set field accessible, no matter its modifier */
            f.setAccessible(true);

            /* Skip fields that don't have the RenderMe-annotation */
            if (!f.isAnnotationPresent(RenderMe.class)) {
                continue;
            }

            /* Add name and type of this field to out */
            out += f.getName();
            out += " (Type " + f.getType().getCanonicalName() + "): ";

            /* Value of the "with"-property */
            String typeRendererClassName = f.getAnnotation(RenderMe.class).with();

            /* Check wether specific renderer-class is set or not. */
            if (typeRendererClassName.length() > 0) {

                /* Specific renderer-class is used for this field, instantiate object
                *  of this class and invoke the method responsible for this fields type.
                 * That method will return the value of this field as a String. */
                Class specificRendererClass = Class.forName(typeRendererClassName);
                Object nptr = specificRendererClass.newInstance();
                Method m = specificRendererClass.getMethod("render", f.getType());
                String fieldValues = (String) m.invoke(nptr, f.get(target));

                out += fieldValues;
            } else {
                /* Default typeRenderer is used */
                out += f.get(target);
            }

            out += "\n";
        }

        return out;
    }

}
