package com.example.goodgrapessecond;

import android.os.Trace;

import java.util.ArrayList;

public class TraceabilityInformation {

    public String wineIDTrace;
    public String wineType;
    public double agricultureImpact;
    public double ILUCImpact;
    public double processingImpact;
    public double packagingImpact;
    public double transportImpact;
    public double retailImpact;

    /**
     * Constructor for TraceabilityInformation objects
     * @param wineType is a String corresponding to the type of wine (e.g. red)
     * @param agricultureImpact is a double that is the environmental impact of agriculture for the wine
     * @param ILUCImpact is a double that is the environmental impact of ILUC for the wine
     * @param processingImpact is a double that is the environmental impact of processing for the wine
     * @param packagingImpact is a double that is the environmental impact of packaging for the wine
     * @param transportImpact is a double that is the environmental impact of transport for the wine
     * @param retailImpact is a double that is the environmental impact of retail for the wine
     * All impact values are gathered from CONCITOS The Great Climate Database
     */
    public TraceabilityInformation(String wineType, double agricultureImpact, double ILUCImpact, double processingImpact, double packagingImpact, double transportImpact, double retailImpact) {
        this.wineType = wineType;
        this.agricultureImpact = agricultureImpact;
        this.ILUCImpact = ILUCImpact;
        this.processingImpact = processingImpact;
        this.packagingImpact = packagingImpact;
        this.transportImpact = transportImpact;
        this.retailImpact = retailImpact;
    }

    /**
     * Method to find the relevant TraceabilityInformation object
     * @param wineInput is an object of wine we can use to check for the type
     * Loop through an ArrayList of TraceabilityInformation objects and check if the wine type is the same as the input wineTypeInput
     * set the wineIDTrace of the tempTraceabilityInformation object to be the same ID as the wineInput
     * set index 0 of wineLinked to be true so we know there is a traceability information object for the wine
     * @return the TraceabilityInformation object that has the same wineType as the input
     * This method is part of the function component Information Retrieval where it has access to the Wine model component (by way of wineInput)
     * This method is somewhat flexible as we don't have a predetermined size of the ArrayList to loop through
     * It also provides flexibility as it does not care about the built-in TraceabilityInformation objects, so long as they exist
     * As such we don't need to change anything here, even if there are changes to the Traceability data provided by CONCITO (like adding a new type of wine with the same supply chain stages)
     */
    public static TraceabilityInformation determineTraceabilityInformation(Wine wineInput) {
        ArrayList<TraceabilityInformation> traceabilityInformationArrayList = createTraceabilityInformation();
        for (int i = 0; i < traceabilityInformationArrayList.size(); i++) {
            TraceabilityInformation tempTraceabilityInformation = traceabilityInformationArrayList.get(i);
            if (tempTraceabilityInformation.wineType == wineInput.type) {
                tempTraceabilityInformation.wineIDTrace = wineInput.wineID;
                wineInput.wineLinked[0] = true;
                return tempTraceabilityInformation;
            }
        }
        return null;
    }

    /**
     * Method to create individual TraceabilityInformation objects from CONCITOs databases values (hardcoded)
     * @return ArrayList of TraceabilityInformation objects that we can loop through
     * We create an ArrayList of TraceabilityInformation objects and add the relevant objects to this ArrayList
     * This method is separate since we should be able to run it using dynamic data retrieved from other data sources
     * We thus create the info dynamically, so it will be update together with an external's database's changes
     * This method is part of the Traceability Database component which should be outside the system
     * However as we want to first create a static implementation we hardcode these data - as thus it is conceptually part of the Traceability Database component
     */
    public static ArrayList<TraceabilityInformation> createTraceabilityInformation() {
        ArrayList<TraceabilityInformation> traceabilityInformationList = new ArrayList<TraceabilityInformation>();
        TraceabilityInformation redWine = new TraceabilityInformation("Red",0.31, 0.07, 0.40, 0.41, 0.68, 0.01);
        traceabilityInformationList.add(redWine);
        TraceabilityInformation roseWine = new TraceabilityInformation("Rosé",0.31, 0.07, 0.40, 0.41, 0.68, 0.01);
        traceabilityInformationList.add(roseWine);
        TraceabilityInformation whiteWine = new TraceabilityInformation("White", 0.31, 0.07, 0.40, 0.41, 0.68, 0.01);
        traceabilityInformationList.add(whiteWine);
        // we run into problems here - LWIN does not see sparkling as separate from the other three categories
        // this means that we will never see wines that are JUST sparkling, and as such this method
        // loses some sophistication and flexibility here
        // we dont get any new information considering the sparkling subtypes of wine
        // we keep the object here, but that is a sacrifice we make
        TraceabilityInformation sparklingWine = new TraceabilityInformation("Sparkling", 0.31, 0.07, 0.40, 0.41, 0.68, 0.01);
        traceabilityInformationList.add(sparklingWine);

        return traceabilityInformationList;
    }
}
