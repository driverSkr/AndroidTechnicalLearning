package com.example.internet.handler;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {

   private String nodeName = "";
   private StringBuilder id;
   private StringBuilder name;
   private StringBuilder version;

   //在开始XML解析的时候调用
   @Override
   public void startDocument() throws SAXException {
      id = new StringBuilder();
      name = new StringBuilder();
      version = new StringBuilder();
   }

   //在开始解析某个节点的时候调用
   @Override
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      //记录当前节点名
      nodeName = localName;
      Log.d("boge","uri is " + uri);
      Log.d("boge","localName is " + localName);
      Log.d("boge","qName is " + qName);
      Log.d("boge","attributes is " + attributes);
   }

   //在获取节点中内容的时候调用
   //characters()方法可能会被调用多次，一些换行符也被当作内容解析出来，我们需要针对这种情况在代码中做好控制
   @Override
   public void characters(char[] ch, int start, int length) throws SAXException {
      //根据当前节点名判断讲内容添加到哪一个StringBuilder对象中
      switch (nodeName){
         case "id":
            id.append(ch,start,length);
            break;
         case "name":
            name.append(ch,start,length);
            break;
         case "version":
            version.append(ch,start,length);
            break;

      }
   }

   //在完成解析某个节点的时候调用
   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
      if ("app" == localName){
         //id、name和version中都可能是包括回车或换行符的，因此在打印之前我们还需要调用一下trim()方法
         Log.d("boge","id is " + id.toString().trim());
         Log.d("boge","name is " + name.toString().trim());
         Log.d("boge","version is " + version.toString().trim());
         //最后要将StringBuilder清空
         id.setLength(0);
         name.setLength(0);
         version.setLength(0);
      }
   }

   //在完成整个XML解析的时候调用
   @Override
   public void endDocument() throws SAXException {
   }
}
