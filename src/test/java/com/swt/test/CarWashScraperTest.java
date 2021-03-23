package com.swt.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import com.swt.scraper.helper.GetStoreLocatorInfo;
import com.swt.scraper.model.Details;
import org.junit.Test;

import java.util.List;

public class CarWashScraperTest {

    @Test
    public void getDetails(){

        List<Details> details = new GetStoreLocatorInfo().getStoreDetails(1);

        Details data = details.get(0);

        assertThat(data.getCity(), is ("Ahmedabad"));
        assertThat(data.getStore(), is ("BODAKDEV"));
        assertThat(data.getTollFreeNumber(), is ("1800-300-22-300"));
    }
}
