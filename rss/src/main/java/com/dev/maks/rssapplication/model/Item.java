package com.dev.maks.rssapplication.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Класс представляющий XML элемент item
 */

@Root(name = "item", strict = false)
public class Item {

    @Element(name = "guid")
    private Guid guid;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "author")
    private String author;

    @Element(name = "title")
    private String title;

    @Element(name = "category")
    private String category;

    @Element(name = "description")
    private String description;

    @Element(name = "link")
    private String link;

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Guid getGuid() {
        return guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "ClassPojo [guid = " + guid + ", pubDate = " + pubDate + ", author = " +
                author + ", title = " + title + ", category = " + category + ", description = " +
                description + ", link = " + link + "]";
    }
}
