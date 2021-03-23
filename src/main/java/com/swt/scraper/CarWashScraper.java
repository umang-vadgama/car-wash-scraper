package com.swt.scraper;

import com.swt.scraper.helper.GetStoreLocatorInfo;
import com.swt.scraper.model.Details;

import java.util.List;

public class CarWashScraper {

    public static void main(String[] args) {


        List<Details> details = new GetStoreLocatorInfo().getStoreDetails(2);

        System.out.println("DETAILS : \n"+details);
    }
}
