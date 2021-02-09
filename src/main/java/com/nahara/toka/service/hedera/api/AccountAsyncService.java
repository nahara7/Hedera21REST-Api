package com.nahara.toka.service.hedera.api;

import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.model.*;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

public class AccountAsyncService {
    Airtable airtable = new Airtable().configure("keykefT9YD5rhkuFg");
    private static final String ADMINACCOUNTID= ""+System.getenv("NAHARA_ACCOUNT_ID");
    private static final String ADMINPRIVATEKEY= ""+System.getenv("NAHARA_PRIVATE_KEY");
    Base base = airtable.base("appg4L9uWpNhonYHS");
    Table<Account> accountTable = base.table("Accounts", Account.class);
    Table<PublicUser> userTable = base.table("Users", PublicUser.class);
    Table<PublicVendor> vendorTable = base.table("Vendors", PublicVendor.class);

    public AccountAsyncService() throws AirtableException {
    }

    private static Logger log = LoggerFactory.getLogger(AccountAsyncService.class);

    public Account createAccount(Account account) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {

        Account newAccount = accountTable.create(account);
        return newAccount;
    }

    public Account findAccount(String Id) throws AirtableException {
        return accountTable.find(Id);
    }

    @Async
    //not a string !
    public AccountBalance getUserAccountBalance(String userId) throws AirtableException {
       PublicUser user=userTable.find(userId);
        Client client = Client.forTestnet();
        AccountBalance accountBalance;

        log.info("{}", "getting user account balance...");
        try {
            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));

            AccountBalanceQuery query = new AccountBalanceQuery()
                    .setAccountId(AccountId.fromString(user.getAccountid()));
            accountBalance = query.execute(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(accountBalance)
                .getNow(null);
    }

    @Async
    //not a string !
    public AccountBalance getVendorAccountBalance(String vendorId) throws AirtableException {
        PublicVendor vendor=vendorTable.find(vendorId);
        Client client = Client.forTestnet();
        AccountBalance accountBalance;

        log.info("{}", "getting  vendor account balance...");
        try {
            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));

            AccountBalanceQuery query = new AccountBalanceQuery()
                    .setAccountId(AccountId.fromString(vendor.getAccountid()));
            accountBalance = query.execute(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(accountBalance)
                .getNow(null);
    }

    @Async
    //not a string !
    public AccountInfo getUserHederaAccountInfo(String userId) throws AirtableException {
        PublicUser user= userTable.find(userId);
        Client client = Client.forTestnet();
        AccountInfo accountInfo;
        log.info("{}", "user account information");
        try {
            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));

            AccountInfoQuery query = new AccountInfoQuery()
                    .setAccountId(AccountId.fromString(user.getAccountid()));

            accountInfo = query.execute(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(accountInfo)
                .getNow(null);
    }
    public AccountInfo getVendorHederaAccountInfo(String vendorId) throws AirtableException {
        PublicVendor vendor=vendorTable.find(vendorId);

        Client client = Client.forTestnet();
        AccountInfo accountInfo;
        log.info("{}", "user account information");
        try {
            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));

            AccountInfoQuery query = new AccountInfoQuery()
                    .setAccountId(AccountId.fromString(vendor.getAccountid()));

            accountInfo = query.execute(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(accountInfo)
                .getNow(null);
    }
}