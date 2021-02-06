package com.nahara.toka.controller;

import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.nahara.toka.model.User;
import com.nahara.toka.model.Vendor;
import com.nahara.toka.service.TransactionService;
import org.junit.Test;

import java.util.concurrent.TimeoutException;

public class TransactionServiceTest {

    @Test
    public static void main(String[] args) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        User user = new User();
        Vendor vendor = new Vendor();

        TransactionService trS = new TransactionService(user, vendor, 10);
    }
}
