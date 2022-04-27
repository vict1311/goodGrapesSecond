package com.example.goodgrapessecond;

import android.os.Trace;

import java.util.ArrayList;

public class Wine {

    //wineID is a String, because we need to use remove() and if it is an int, it removes position not object
    /**
     * ID for a specific wine
     */
    public String wineID;
    /**
     * name of the wine
     */
    public String name;
    /**
     * the type of wine (red, wine, etc.)
     */
    public String type;
    /**
     * the grape of the wine
     */
    public String grape;
    /**
     * the year the wine was produced
     */
    public int year;
    /**
     * libSaved checks for the wine being saved in the user's library (this is done during runtime as a local variable)
     */
    public boolean libSaved;

    /** constructor to create Wine objects
     * @param wineID is a string that is the ID of the wine
     * @param name is a String defining the name
     * @param grape is the grape sort of a Wine
     * @param type is the general type of wine
     * @param year is the release year of the Wine
     * start by defining a wine as NOT saved by setting libSaved to false
     * libSaved is an attribute dynamically evaluated during runtime and NOT saved in database
     * because we can then change libSaved based on the user who is using the app
     */
    public Wine(String wineID, String name, String grape, String type, int year) {
        this.wineID = wineID;
        this.name = name;
        this.grape = grape;
        this.type = type;
        this.year = year;
        libSaved = false;
    }

    /**
     * createWines() handles creating the different Wines of our system
     * sources are from Winefamly.com
     * @return ArrayList of Wines that is added to for every object created
     */
    public static ArrayList<Wine> createWines(){
        ArrayList<Wine> wineList = new ArrayList<Wine>();
        Wine wine1 = new Wine("0", "Barramundi", "Pinot Noir", "Red", 2019);
        wineList.add(wine1);
        Wine wine2 = new Wine("1", "Stepp", "Riesling", "White", 2020);
        wineList.add(wine2);
        Wine wine3 = new Wine("2", "Duckhorn", "Merlot", "Red", 2017);
        wineList.add(wine3);
        Wine wine4 = new Wine("3", "Johan Topf", "Zweigelt", "Rosé", 2021 );
        wineList.add(wine4);
        Wine wine5 = new Wine("4", "Tropical Moscato", "Mascato Bianco", "Sparkling", 2021);
        wineList.add(wine5);
        return wineList;
    }

    /**
     * Method browseAllWines() gets all wines in the system using createWines()
     * @return ArrayList of Wines
     * this method is part of the Browse Wine function component, and using createWines and loading these into an ArrayList gives it access to the Information Retreival function component
     */
    public static ArrayList<Wine> browseAllWines() {
        ArrayList<Wine> allWines = createWines();
        return allWines;
    }

    /**
     *Method findWine() looks for a specific wine in all created Wines
     * @param wineID is a String to be looked for
     * createWines() generates full list of all Wines
     * for loop to go through returned ArrayList of createWines()
     * if the wineID supplied is equal to a given Wine return the given Wine
     * this is part of the Identify Wine function component which has access to the Wine model component
     */
    public static Wine findWine(String wineID) {
        ArrayList<Wine> wineListAll = createWines();
        for (int i = 0; i < wineListAll.size(); i ++) {
            Wine tempWine = wineListAll.get(i);
            if (wineID == tempWine.wineID) {
                return tempWine;
            }
        }
        return null;
    }

    /**
     * Method to find the traceabilityInformation object for a wine
     * It runs determineTraceabilityInformation() with the Wine that is calling that method (using "this" as the keyword)
     * @return the newTraceability object for the Wine
     * This is part of the function component Information Retrieval which has access to a Wine object (in this case the object calling the method)
     * Through this Wine object we can run determineTraceabilityInformation, which comes from TraceabilityInformation class
     * This does however mean that it is unclear exactly where the line between the Wine component and the Traceability Database component goes
     * In this current implementation it seems that the Traceability Information becomes part of the Wine component
     * But we model it as separate components to future proof
     */
    public TraceabilityInformation traceabilityRetrieval() {
        TraceabilityInformation newTraceability = TraceabilityInformation.determineTraceabilityInformation(this);
        return newTraceability;
    }
}
