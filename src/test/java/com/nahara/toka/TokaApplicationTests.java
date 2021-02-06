package com.nahara.toka;

import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.nahara.toka.model.*;
import com.nahara.toka.service.hedera.api.*;
import com.sybit.airtable.exception.AirtableException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeoutException;

@SpringBootTest
class TokaApplicationTests {
    @Test
	void contextLoads() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, AirtableException {
		//VendorService vendorService = new VendorService();
        //PublicUser user= PublicUserService.findUser("recFOKKUv6sJAGpVJ");
        //System.out.println(user.getPrivateKey());

		//PublicVendor vendor= PublicVendorService.findVendor("recEV7P1Ercp3BCY3");
         //System.out.println(vendor.getPrivateKey());

         //PublicUser user= PublicUserService.findUser("recFOKKUv6sJAGpVJ");
         //System.out.println(user.getPrivateKey());


	}

}
