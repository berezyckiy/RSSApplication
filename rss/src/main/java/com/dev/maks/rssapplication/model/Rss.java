package com.dev.maks.rssapplication.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Класс представляющий XML элемент rss
 */

@Root(name = "rss")
public class Rss {

    @Element(name = "channel")
    private Channel channel;

    @Attribute(name = "version")
    private String version;

    public Channel getChannel() {
        return channel;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "ClassPojo [channel = " + channel + ", version = " + version + "]";
    }
}
