package com.nahara.toka.service.hedera.api;

import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.components.Hedera;
import com.nahara.toka.model.Account;
import com.nahara.toka.model.PublicUser;
import com.nahara.toka.model.PublicVendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
//Used for admin initial deposit,
//finding admin initial balance
//and admin account information
@Service
public class AdminAsyncService {

    private static final String ADMINACCOUNTID= ""+System.getenv("NAHARA_ACCOUNT_ID");
    private static final String ADMINPRIVATEKEY= ""+System.getenv("NAHARA_PRIVATE_KEY");
    private static final String TOKAAIR = ""+System.getenv("TOKAAIR");
    private static final String TOKABASE = ""+System.getenv("TOKABASE");
    private static final String JVT=""+ System.getenv("JVT_TOKEN_ID");


    Hedera hedera;

    private static Logger log = LoggerFactory.getLogger(AdminAsyncService.class);

    Airtable airtable = new Airtable().configure(TOKAAIR);
    Base base = airtable.base(TOKABASE);
    Table<Account> accountTable = base.table("Accounts", Account.class);
    Table<PublicUser> userTable = base.table("Users", PublicUser.class);
    Table<PublicVendor> vendorTable = base.table("Vendors", PublicVendor.class);

    public AdminAsyncService() throws AirtableException {};

    //these queries cost !
    @Async()
    public AccountBalance adminAccountBalance(){

        Client client = Client.forTestnet();
        AccountBalance accountBalance;
        log.info("{}", "getting  admin account balance...");
        try {
            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));

            AccountBalanceQuery query = new AccountBalanceQuery()
                    .setAccountId(AccountId.fromString(ADMINACCOUNTID));
            accountBalance = query.execute(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(accountBalance)
                .getNow(null);
    }
    @Async()
    public TransactionReceipt adminDeposit(String userId) throws AirtableException {
        PublicUser user= userTable.find(userId);
        Client client = Client.forTestnet();
        TransactionReceipt receipt;
        //Token token = new Token();
        log.info("{}", "starting admin initial deposit...");
        try {
            //Attribute JVT
            TokenId tokenId = TokenId.fromString(JVT);

            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));
            TransferTransaction transaction = new TransferTransaction()
                    .addTokenTransfer
                            (tokenId,(AccountId.fromString(ADMINACCOUNTID)), -25)
                    .addTokenTransfer
                            (tokenId, AccountId.fromString(user.getAccountid()),25);
            TransactionResponse response=transaction.execute(client);
            log.info("admin deposited 25 tokens");
            receipt=response.getReceipt(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt)
                .getNow(null);
    }
    @Async()
    public AccountInfo adminAccountInfo(){
        Client client=Client.forTestnet();
        System.out.println(ADMINACCOUNTID);
        AccountInfo accountInfo;
        log.info("{}", "getting admin account balance...");
        try {
            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));

            AccountInfoQuery query = new AccountInfoQuery()
                    .setAccountId(AccountId.fromString(ADMINACCOUNTID));
            accountInfo = query.execute(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(accountInfo)
                .getNow(null);
    }

    }











