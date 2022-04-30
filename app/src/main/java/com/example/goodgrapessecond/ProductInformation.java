package com.example.goodgrapessecond;

import java.util.ArrayList;

public class ProductInformation {

    public String wineIDProduct;
    public String country;
    public String region;
    public String subRegion;
    public String producerName;

    /**
     * Constructor for ProductInformation objects
     * @param wineIDProduct is an integer illustrating the ID for the wine product
     * @param country is a string that is telling which country the wine is from
     * @param region is a string that informs which region the wine is produced
     * @param subRegion is a string that informs which subregion the wine is produced
     * @param producerName is a stirng with the name of the wine producer
     */
    public ProductInformation(String wineIDProduct, String country, String region, String subRegion, String producerName) {
        this.wineIDProduct = wineIDProduct;
        this.country = country;
        this.region = region;
        this.subRegion = subRegion;
        this.producerName = producerName;
    }

    /**
     * Method to find the relevant ProductInformation object
     * @param wineInfo is an object of wine we can use to check for the information
     * Loop through an ArrayList of ProductInformation objects and check if the product ID is the same as the input wineInfo ID
     * set the wineIDProduct of the tempProductInformation object to be the same ID as the wineInfo
     * @return the ProductInformation object that has the same wineID as the input
     * This method is part of the function component Information Retrieval where it has access to the Wine model component (by way of wineInfo)
     * This method is somewhat flexible as we don't have a predetermined size of the ArrayList to loop through (just as TraceabilityInformation)
     * It also provides flexibility as it does not care about the built-in Productinformation objects, so long as they exist (just as TraceabilityInformation)
     * As such we don't need to change anything here, even if there are changes to the ProductInformation data provided by LWIN
     */
    public static ProductInformation determineProductInformation(Wine wineInfo) {
        ArrayList<ProductInformation> productInformationArrayList = createProductInformation();
        for (int i = 0; i < productInformationArrayList.size(); i++) {
            ProductInformation tempProductInformation = productInformationArrayList.get(i);
            if (tempProductInformation.wineIDProduct == wineInfo.wineID) {
                return tempProductInformation;
            }
        }
        return null;
    }

    /**
     * Method to create individual ProductInformation objects from LWINs databases values (hardcoded)
     * @return ArrayList of ProductInformation objects that we can loop through
     * We create an ArrayList of ProductInformation objects and add the relevant objects to this ArrayList
     * This method is separate since we should be able to run it using dynamic data retrieved from other data sources (Like TraceabilityInformation)
     * We thus create the info dynamically, so it will be update together with an external's database's changes (Like TraceabilityInformation)
     * This method is part of the Product Information Database component which should be outside the system (Like TraceabilityInformation)
     * However as we want to first create a static implementation we hardcode these data - as thus it is conceptually part of the Product Information Database component (Like TraceabilityInformation)
     */
    public static ArrayList<ProductInformation> createProductInformation() {
        ArrayList<ProductInformation> productInformationList = new ArrayList<ProductInformation>();
        // the first traceabilityInformation trace1 does not have a sub-region - we use the one right above it in LWIN
        ProductInformation trace1 = new ProductInformation("0", "France", "Alsace", "Altenberg de Bergheim", "Schieferkopf");
        productInformationList.add(trace1);
        ProductInformation trace2 = new ProductInformation( "1", "France", "Alsace", "N/A", "Trimbach");
        productInformationList.add(trace2);
        ProductInformation trace3 = new ProductInformation("2", "Argentina", "Mendoza", "N/A", "Alta Vista");
        productInformationList.add(trace3);
        ProductInformation trace4 = new ProductInformation( "3", "France", "Champagne", "N/A", "Deutz");
        productInformationList.add(trace4);
        ProductInformation trace5 = new ProductInformation("4", "Australia", "South Australia", "Barossa Valley", "Magpie Estate");
        productInformationList.add(trace5);

        return productInformationList;
    }

}
