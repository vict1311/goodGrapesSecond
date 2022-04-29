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

    public static ArrayList<ProductInformation> createProductInformation() {
        ArrayList<ProductInformation> productInformationList = new ArrayList<ProductInformation>();
        ProductInformation hugel = new ProductInformation("0", "France", "Alsace", "Altenberg de Bergheim", "Hugel");
        productInformationList.add(hugel);
        ProductInformation brownBrothers = new ProductInformation( "1", "Australia", "Victoria", "Wimmera", "Brown Brohters");
        productInformationList.add(brownBrothers);
        ProductInformation trapet = new ProductInformation("2", "France", "Alsace", "Sonnenglanz", "Trapet");
        productInformationList.add(trapet);

        System.out.print(productInformationList);

        return productInformationList;
    }

}
