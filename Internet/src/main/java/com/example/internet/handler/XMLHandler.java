package com.example.internet.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
    //在开始XML解析的时候调用
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }
    //会在完成整个XML解析的时候调用
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
    //会在开始解析某个节点的时候调用
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
    }
    //会在完成解析某个节点的时候调用
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }
    //会在获取节点中内容的时候调用
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }
}
