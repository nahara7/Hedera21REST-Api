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
class DemoApplicationTests {
    @Test
	void contextLoads() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, AirtableException {
		VendorService vendorService = new VendorService();
		PublicVendor vendor= PublicVendorService.findVendor("recTYr6hllBIJaf73");
         System.out.println(vendor.getPrivateKey());

         PublicUser user= PublicUserService.findUser("recFOKKUv6sJAGpVJ");
         System.out.println(user.getPrivateKey());

         UserService userService= new UserService();
         System.out.println(user.getAccountid());
         System.out.println(vendor.getAccountid());
         //Transaction transaction= new Transaction(user.getUserId(), vendor.getVendorId(), 10);
         TransactionAsyncService transactionAsyncService= new TransactionAsyncService();
         TransactionReceipt receipt=transactionAsyncService.transactionUserVendor(user, vendor, 10);
         //System.out.println(receipt.toString());
         //System.out.println(userService.findUser("recLU2MSO8i7zefkg").getPrivateKey());
		//TransactionAsyncService service= new TransactionAsyncService();
		//TransactionReceipt receipt= service.transactionUserVendor(user, vendor, fee);

		System.out.println(receipt.status);
	}

}
