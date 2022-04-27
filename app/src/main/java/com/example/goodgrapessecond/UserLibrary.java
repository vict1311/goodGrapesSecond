package com.example.goodgrapessecond;

import java.util.ArrayList;

public class UserLibrary {
    /**
     * ID of a specific Library
     * this page corresponds to what we call favorites as the user wanting it to be named that
     */
    public int userIDLib;
    /**
     * ArrayList of WineIDs in a specific UserLibrary
     * we remember to use the wrapper class Integer here
     */
    public ArrayList<String> winesInLib = new ArrayList<String>();

    /**
     * Constructor for a UserLibrary
     * @param user who should be associated with the library by their ID
     * we set userIDLib to be equal to the user's lib, thereby ensuring an aggregation
     */
    public UserLibrary(User user) {
        this.winesInLib = new ArrayList<String>();
        userIDLib = user.userID;
    }

    /**
     * browseLibrary() finds all wines in a user's library to be shown
     * @return ArrayList of Wines
     * create winesInLib using createWines() method
     * for loop to iterate over all wines in winesInLib
     * use findWine to check for the Wine objects that have the same IDs as the one in the UserLibrary
     * this method uses the object attribute of winesInLib, thus making it part of the function component Browse wine, which has access to the User component
     * using findWine means browseLibrary gets access to the Information Retrieval function component
     */
    public ArrayList<Wine> browseLibrary() {
        ArrayList<Wine> winesToPrint = new ArrayList<Wine>();
        for (int i = 0; i < winesInLib.size(); i++) {
            String tempWine = winesInLib.get(i);
            Wine newWineToAdd = Wine.findWine(tempWine);
            winesToPrint.add(newWineToAdd);
        }
        return winesToPrint;
    }

    /**
     * Method addWineToLibrary() lets users add a Wine to UserLibrary
     * @param wine is a Wine object to be added
     * we add the wineID to the winesInLib attribute of the object calling the method
     * this method is part of the library modification function component
     * calling it from a UserLibrary makes the User model component have access to it
     * this means that the Library Modification function component gets access to the Wine model component
     * this method is to be added to the UserLibrary component in our component design
     */
    public void addWineToLibrary(Wine wine) {
        wine.libSaved = true;
        this.winesInLib.add(wine.wineID);
    }

    /**
     *Method removeWineFromLibrary() lets users remove a Wine from UserLibrary
     * @param wine is a Wine object to be removed
     * we rome the wineID from the winesInLib attribute of the object calling the method
     * this method is part of the library modification function component
     * calling it from a UserLibrary makes the User model component have access to it
     * this means that the Library Modification function component gets access to the Wine model component
     * this method is to be added to the UserLibrary component in our component design
     */
    public void removeWineFromLibrary(Wine wine) {
        wine.libSaved = false;
        this.winesInLib.remove(wine.wineID);
    }

}
