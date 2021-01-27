package com.newjerseysoftware.hederaDemo;

import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.newjerseysoftware.hederaDemo.model.User;
import com.newjerseysoftware.hederaDemo.model.Vendor;
import com.newjerseysoftware.hederaDemo.service.hedera.api.TransactionAsyncService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeoutException;

@SpringBootTest
class DemoApplicationTests {
    @Test
	void contextLoads() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
		User user=new User();
		user.setPrivateKey();
		user.setPublickey();
		user.setAccountid();

		Vendor vendor=new Vendor();
		vendor.setPrivateKey();
		vendor.setPublicKey();
		vendor.setAccountid();

		Long fee= Long.valueOf(10);

		TransactionAsyncService service= new TransactionAsyncService();
		TransactionReceipt receipt= service.transactionUserVendor(user, vendor, fee);
		System.out.println(receipt.status);
	}

}
