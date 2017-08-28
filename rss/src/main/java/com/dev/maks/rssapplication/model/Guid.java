package com.dev.maks.rssapplication.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Класс представляющий XML элемент guid
 */

@Root(name = "guid", strict = false)
public class Guid {

    @Element(name = "content", required = false)
    private String content;

    @Attribute(name = "isPermaLink", required = false)
    private String isPermaLink;

    public String getContent() {
        return content;
    }

    public String getIsPermaLink() {
        return isPermaLink;
    }

    @Override
    public String toString() {
        return "ClassPojo [content = " + content + ", isPermaLink = " + isPermaLink + "]";
    }
}
