package com.example.goodgrapessecond;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;


public class WineDB {
    private static final String ns = null;

//Instantiate the parser
//First the parser is initialized
//Then we use InputStream as input
//Then we start the parsing process with a call to nextTag and invokes the readWorkbook method which processes the data the app is interested in
    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return (List) readWorkbook(parser);
        } finally {
            in.close();
        }
    }
//Now it is time to read the workbook (file)
//The readWorkbook method process everything in the workbook
//It looks for the elements tagged in "entry" and starts pointing for recursively processing the workbook - meaning if something is not within an <entry> tag it skips it
//When the workbook has been recursively processed, the readWorkbook() returns a list containing all entries extracted from the feed
//The List is then returned by the parser
    private List readRow (XmlPullParser parser) throws XmlPullParserException,IOException {
        List row = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "workbook");
        while (parser.next() !=XmlPullParser.END_TAG) {
            continue;
        }
        String name = parser.getName();
        //Starts by looking for the row tag
        if (name.equals ("row")) {
            Row.add(readWorkbook(parser));
        } else {
            Skip(parser);
        }
        return row;
    }

    private void Skip(XmlPullParser parser) {
    }

    //Parse XML
//Now we identify the tags we want to include in the app
    public static class Row {
        public final String name;
        public final String wine;
        public final String country;
        public final String colour;

        private Row(String name, String wine, String country, String colour) {
            this.name = name;
            this.wine = wine;
            this.country = country;
            this.colour = colour;
        }

        public static void add(Row readWorkbook) {
        }
    }

    //Parses the contents of a row. If it encounters a name, wine, country and colour tag, hans them off
    //to their respective "read" methods for processing.
    //Otherwise skips the tag.
    private Row readWorkbook(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "row");
        String name = null;
        String wine = null;
        String country = null;
        String colour = null;
        while (parser.next() !=XmlPullParser.END_TAG){
            if (parser.getEventType() !=XmlPullParser.START_TAG) {
                continue;
            }
            String row = parser.getName();
            if (row.equals("name")) {
                name = readName(parser);
            } else if (name.equals("wine")) {
                wine = readWine(parser);
            } else if (name.equals("country")) {
                country = readCountry(parser);
            } else{
                skip(parser);
            }
        }
        return new Row(name, wine, country, colour);
    }
    //Processes name tags in the feed
    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return name;
    }
    //Processes wine tags in the feed
    private String readWine(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "wine");
        String wine = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "wine");
        return wine;
    }
    //Process country tags in the feed
    private String readCountry(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "country");
        String country = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "country");
        return country;
    }
    //Process colour tags in the feed
    private String readColour(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "colour");
        String colour = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "colour");
        return colour;
    }
    //For all tags extract their text values
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    //skips all tags from database that is not used.
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
    }





