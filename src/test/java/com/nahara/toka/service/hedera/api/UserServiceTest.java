package com.nahara.toka.service.hedera.api;
import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.model.User;
import com.nahara.toka.model.Vendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeoutException;

class UserServiceTest {
    private static Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    @Test
    void createUser() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, AirtableException {
        log.info("testing userservice");
     UserService userService= new UserService();
     VendorService vendorService=new VendorService();
     Airtable airtable= new Airtable().configure("keykefT9YD5rhkuFg");
     Base base = airtable.base("appg4L9uWpNhonYHS");
     Table<Vendor> vendorTable = base.table("Vendors", Vendor.class);

     Vendor vendor=vendorService.findVendor("recEV7P1Ercp3BCY3");
     System.out.println(vendor.getVendorId());

     try {

        }catch (Exception e){
         e.printStackTrace();
     }
    }
   }