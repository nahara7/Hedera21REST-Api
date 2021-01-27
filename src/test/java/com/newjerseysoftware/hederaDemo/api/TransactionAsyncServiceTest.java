package com.newjerseysoftware.hederaDemo.api;

import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.newjerseysoftware.hederaDemo.model.User;
import com.newjerseysoftware.hederaDemo.model.Vendor;
import com.newjerseysoftware.hederaDemo.service.hedera.api.TransactionAsyncService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class TransactionAsyncServiceTest {

    @Test
    void createToken() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {

    }

    @Test
    void transactionUserVendor() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        User user = new User();
        user.setPrivateKey();
        user.setPublickey();
        user.setAccountid();
        System.out.println(user.getAccountid());
        Vendor vendor = new Vendor();
        vendor.setPrivateKey();
        vendor.setPublicKey();
        vendor.setAccountid();

        TransactionAsyncService service = new TransactionAsyncService();
        TransactionReceipt receipt = service.transactionUserVendor(user, vendor, (long) 10);
        System.out.println(receipt.status);
    }

    @Test
    void vendorPromotion() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        User user = new User();
        user.setPrivateKey();
        user.setPublickey();
        user.setAccountid();
        System.out.println(user.getAccountid());
        Vendor vendor = new Vendor();
        vendor.setPrivateKey();
        vendor.setPublicKey();
        vendor.setAccountid();

        TransactionAsyncService service = new TransactionAsyncService();
        TransactionReceipt receipt = service.vendorPromotion(user, vendor, (long) (10), "test transaction");
        System.out.println(receipt.status);
    }

    @Test
    void cashBack() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        User user = new User();
        user.setPrivateKey();
        user.setPublickey();
        user.setAccountid();
        System.out.println(user.getAccountid());
        Vendor vendor = new Vendor();
        vendor.setPrivateKey();
        vendor.setPublicKey();
        vendor.setAccountid();

        TransactionAsyncService service = new TransactionAsyncService();
        TransactionReceipt receipt = service.cashBack(user, vendor, 5, (int) 10);
        System.out.println(receipt.status);
    }
}