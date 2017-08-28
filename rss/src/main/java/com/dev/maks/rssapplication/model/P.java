package com.dev.maks.rssapplication.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Класс представляющий XML элемент p
 */

@Root(name = "p")
public class P {

    @Element(name = "strong")
    private String strong;

    public String getStrong() {
        return strong;
    }

    @Override
    public String toString() {
        return "ClassPojo [strong = " + strong + "]";
    }
}
