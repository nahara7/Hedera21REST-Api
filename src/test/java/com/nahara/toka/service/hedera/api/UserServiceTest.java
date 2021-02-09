package com.nahara.toka.service.hedera.api;
import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.model.PublicUser;
import com.nahara.toka.model.User;
import com.nahara.toka.model.Vendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;

class UserServiceTest {

    PublicUserService publicUserService = new PublicUserService();
    private static Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    private static final String ADMINACCOUNTID = "" + System.getenv("NAHARA_ACCOUNT_ID");
    private static final String ADMINPRIVATEKEY = "" + System.getenv("NAHARA_PRIVATE_KEY");
    private static final String JVT=""+ System.getenv("JVT_TOKEN_ID");

    UserServiceTest() throws AirtableException {
    }

    @Test
    void createUser() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, AirtableException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.info("getting admin account info");

        AdminAsyncService adminAsyncService = new AdminAsyncService();
        AccountAsyncService accountAsyncService = new AccountAsyncService();
        System.out.println(accountAsyncService.getUserHederaAccountInfo("recAJZUgme5APnmSt"));

        TransactionAsyncService transactionAsyncService= new TransactionAsyncService();
        PublicUser publicUser=publicUserService.findUser("recPCBBdTmPgj2XEX");
        TransactionReceipt receipt= transactionAsyncService.AssociatingToken("recAJZUgme5APnmSt",JVT );

    }
}