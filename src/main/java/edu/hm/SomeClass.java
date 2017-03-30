package edu.hm;

import java.util.*;

/**
 * Test-Class for our RenderMe-Annotation.
 *
 * @author J. Behrmann, J. Witteck
 * @version 30.03.2017
 */
public class SomeClass {

    @RenderMe private int foo;

    @RenderMe (with = "edu.hm.renderer.ArrayRenderer") int[] array = {1, 2, 3, };

    @RenderMe private Date date = new Date(123456789); // it's magic

    /**
     * This is a javadoc-comment.
     * @param foo bar
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }

}