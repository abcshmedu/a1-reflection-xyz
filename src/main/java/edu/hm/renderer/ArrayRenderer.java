package edu.hm.renderer;

/**
 * Adds support for rendering arrays.
 */
public class ArrayRenderer {

    /**
     * Render-Method for int[].
     * @param arr input to render
     * @return rendered String
     */
    public String render(int[] arr) {
        String ret = "[";
        for (int i = 0; i < arr.length; i++) {
            ret += arr[i] + ", ";
        }
        ret += "]";

        return ret;
    }
}
