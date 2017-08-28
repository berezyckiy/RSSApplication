package com.dev.maks.rssapplication.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Класс представляющий XML элемент channel
 */

@Root(name = "channel", strict = false)
public class Channel {

    @Element(name = "title")
    private String title;

    @Element(name = "description")
    private String description;

    @Element(name = "docs")
    private String docs;

    @ElementList(entry = "item", inline = true)
    private List<Item> items;

    @Element(name = "generator")
    private String generator;

    @Element(name = "copyright")
    private String copyright;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDocs() {
        return docs;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getGenerator() {
        return generator;
    }

    public String getCopyright() {
        return copyright;
    }

    @Override
    public String toString() {
        return "ClassPojo [title = " + title + ", description = " + description + ", docs = " +
                docs + ", item = " + items + ", generator = " + generator + ", copyright = " +
                copyright + "]";
    }
}
