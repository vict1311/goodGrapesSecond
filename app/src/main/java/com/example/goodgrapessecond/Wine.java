package com.example.goodgrapessecond;

//import android.os.Trace;

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
     * the countryu the wine is from
     */
    public String country;
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

    /**
     * wineLinked checks for the existence of product and traceability information objects
     * it is a list of boolean values, where the first is traceability information
     * and the second is product information
     */
    public boolean[] wineLinked = new boolean[2];

    /** constructor to create Wine objects
     * @param wineID is a string that is the ID of the wine
     * @param name is a String defining the name
     * @param country is the wine's origin country
     * @param grape is the grape sort of a Wine
     * @param type is the general type of wine
     * @param year is the release year of the Wine
     * start by defining a wine as NOT saved by setting libSaved to false
     * libSaved is an attribute dynamically evaluated during runtime and NOT saved in database
     * because we can then change libSaved based on the user who is using the app
     * set both booleans of wineLinked to be false
     */
    public Wine(String wineID, String name, String country, String grape, String type, int year) {
        this.wineID = wineID;
        this.name = name;
        this.country = country;
        this.grape = grape;
        this.type = type;
        this.year = year;
        libSaved = false;
        wineLinked[0] = false;
        wineLinked[1] = false;
    }

    /**
     * createWines() handles creating the different Wines of our system
     * sources are from LWIN, and the year is the year the wine was added to LWIN
     * @return ArrayList of Wines that is added to for every object created
     */
    public static ArrayList<Wine> createWines(){
        ArrayList<Wine> wineList = new ArrayList<Wine>();
        Wine wine1 = new Wine("0", "Schieferkopf, Sylvaner", "France", "Sylvaner", "White", 2019);
        wineList.add(wine1);
        Wine wine2 = new Wine("1", "Trimbach, Pinot Noir Reserve", "France", "Pinot Noir Reserve", "Red", 2018);
        wineList.add(wine2);
        Wine wine3 = new Wine("2", "Alta Vista, Alto, Mendoza", "Argentina","Alto", "Red", 2011);
        wineList.add(wine3);
        Wine wine4 = new Wine("3", "Deutz, Rose NV", "France","Rose NV", "Rosé", 2011 );
        wineList.add(wine4);
        Wine wine5 = new Wine("4", "Magpie Estate, Blacksock, Barossa Valley", "Australia", "Blacksock", "Red", 2014);
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
        //ArrayList<Wine> wineListAll = createWines();
        for (int i = 0; i < MainActivity.wineList.size(); i ++) {
            Wine tempWine = MainActivity.wineList.get(i);
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

    /**
     * Method to find the ProductInformation object for a wine
     * It runs determineProductInformation() with the Wine that is calling that method (using "this" as the keyword)
     * @return the newProduct object for the Wine
     * This is part of the function component Information Retrieval which has access to a Wine object (in this case the object calling the method)
     * Through this Wine object we can run determineProductInformation, which comes from TraceabilityInformation class
     * This does however mean that it is unclear exactly where the line between the Wine component and the Product Information Database component goes
     * In this current implementation it seems that the Traceability Information becomes part of the Wine component
     * But we model it as separate components to future proof
     */

    public ProductInformation productRetrieval() {
        ProductInformation newProduct = ProductInformation.determineProductInformation(this);
        return newProduct;
    }
}
