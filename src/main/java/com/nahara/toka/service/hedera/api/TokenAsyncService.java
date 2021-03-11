package com.nahara.toka.service.hedera.api;
import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.components.Hedera;
import com.nahara.toka.model.Account;
import com.nahara.toka.model.PublicUser;
import com.nahara.toka.model.User;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
@Service
public class TokenAsyncService {
    private static final String ADMINACCOUNTID= ""+System.getenv("ACCOUNT_ID");
    private static final String ADMINPRIVATEKEY= ""+System.getenv("PRIVATE_KEY");
    private static final String TOKAAIR = ""+System.getenv("TOKAAIR");
    private static final String TOKABASE = ""+System.getenv("TOKABASE");
    private static final String JVT=""+ System.getenv("JVT_TOKEN_ID");


    Hedera hedera;

    Airtable airtable = new Airtable().configure(TOKAAIR.toString());
    Base base = airtable.base(TOKABASE.toString());
    Table<Account> accountTable = base.table("Tokens", Account.class);

    private static Logger log = LoggerFactory.getLogger(AccountAsyncService.class);

    public TokenAsyncService() throws AirtableException {
    }

    public TokenId createToken(String tokenName, String tokenSymbol,
                               String initialSupply) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {
    return hedera.createHederaToken(tokenName, tokenSymbol, initialSupply);

    }
    //add deleting tokens
    //add minting
    //filtering will be done by front end if user is attributed to more then one token
    //only tokenId's and User or Vendor Id's get sent back and corresponding model is found
    //default token is JVT
}
