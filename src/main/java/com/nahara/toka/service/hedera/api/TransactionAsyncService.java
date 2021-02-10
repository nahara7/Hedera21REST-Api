package com.nahara.toka.service.hedera.api;

import com.hedera.hashgraph.sdk.*;
import com.hedera.hashgraph.sdk.proto.Transaction;
import com.nahara.toka.components.Hedera;
import com.nahara.toka.model.*;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;


public class TransactionAsyncService {
    public TransactionAsyncService() throws AirtableException {
    }
    //for Token
    @Autowired
    private Hedera hedera;



    private static final String ADMINACCOUNTID= ""+System.getenv("NAHARA_ACCOUNT_ID");
    private static final String ADMINPRIVATEKEY= ""+System.getenv("NAHARA_PRIVATE_KEY");
    private static final String JVT=""+ System.getenv("JVT_TOKEN_ID");

    PublicUserService publicUserService= new PublicUserService();
    PublicVendorService publicVendorService= new PublicVendorService();

    public TransactionAsyncService(Hedera hedera) throws AirtableException {
        this.hedera = hedera;
    }

    private static Logger log = LoggerFactory.getLogger(TransactionAsyncService.class);

    @Async()
    public Token createToken(String tokenName, String tokenSymbol,
                             String initialSupply) {

        TokenId tokenId;
        Token token = new Token();
        log.info("{}", "creating token...");

        try {

            tokenId = hedera.createHederaToken(tokenName, tokenSymbol, initialSupply);

            token.setTokenid("" + tokenId);
            token.setName(tokenName);
            token.setSymbol(tokenSymbol);
            token.setInitialsupply(initialSupply);
            log.info("{}", "token created " + tokenId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return CompletableFuture.completedFuture(token)
                .getNow(null);

    }

    @Async()
    public TransactionReceipt transactionUserVendor(PublicUser user, PublicVendor vendor, long fee) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        Client client = Client.forTestnet();
        TransactionReceipt receipt;

        log.info("{}", "Transferring tokens...");
        try {

            TokenId tokenId = com.hedera.hashgraph.sdk.TokenId.fromString("0.0.307812");
            client.setOperator(AccountId.fromString(user.getAccountid()),
                    PrivateKey.fromString(user.getPrivateKey()));
            TransferTransaction transaction = new TransferTransaction()
                    .addTokenTransfer
                            (tokenId, AccountId.fromString(user.getAccountid()), -fee)
                    .addTokenTransfer
                            (tokenId, AccountId.fromString(vendor.getAccountid()),fee);
            TransactionResponse response=transaction.execute(client);
            receipt=response.getReceipt(client);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt)
                .getNow(null);
    }

    @Async()
    //update client
    //update token
    public TransactionReceipt vendorPromotion(User user, Vendor vendor, Long promo, String memo) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        TransactionReceipt receipt;
        Client client = Client.forTestnet();
        log.info("{}", "promotion...");
        try {
            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));
            TransferTransaction transfer = new TransferTransaction()
                    .addHbarTransfer(AccountId.fromString(vendor.getAccountid()), Hbar.fromTinybars(-(promo)))
                    .addHbarTransfer(AccountId.fromString(user.getAccountid()), Hbar.fromTinybars(promo))
                    .setTransactionMemo("promotion: " + memo);
            TransferTransaction txId = transfer.freezeWith(client).
                    sign(PrivateKey.fromString(vendor.getPrivateKey()));
            txId.execute(client);
            TransactionId tId = txId.getTransactionId();
            receipt = tId.getReceipt(client);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt)
                .getNow(null);
    }


    @Async()
    public TransactionReceipt cashBack(PublicUser user, PublicVendor vendor, long total) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        TransactionReceipt receipt;
        Client client = Client.forTestnet();
        double fee = (double) (total * (5.0f / 100.0));
        log.info("{}", "cashback...");
        try {
            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));
            TransferTransaction transfer = new TransferTransaction()
                    .addTokenTransfer(TokenId.fromString(JVT), AccountId.fromString
                            (vendor.getAccountid()), ((long) -(fee)))
                    .addTokenTransfer(TokenId.fromString(JVT), AccountId.fromString
                            (user.getAccountid()), ((long) fee));
            TransferTransaction txId = transfer.freezeWith(client).
                    sign(PrivateKey.fromString(vendor.getPrivateKey()));
            txId.execute(client);
            TransactionId tId = txId.getTransactionId();
            receipt = tId.getReceipt(client);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt)
                .getNow(null);
    }

    @Async()
    public AccountId accountCreateTransaction(String publickey) throws TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        log.info("{}", "account create...");
        TransactionReceipt receipt;
        try {
            Client client = Client.forTestnet();

            AccountId envId = AccountId.
                    fromString(ADMINACCOUNTID);
            PrivateKey envPriv = PrivateKey.
                    fromString(ADMINPRIVATEKEY);
            client.setOperator(envId, envPriv);
            AccountCreateTransaction transaction = new AccountCreateTransaction()
                    .setKey(PublicKey.fromString(publickey))
                    .setInitialBalance(new Hbar(10));
            TransactionResponse txId = transaction.execute(client);
            receipt = txId.getReceipt(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt.accountId)
                .getNow(null);
    }

    @Async()
    public TransactionReceipt userAssociatingToken(String userId, String tokenId) throws AirtableException {
        PublicUser user =publicUserService.findUser(userId);
        log.info("{}", "creating client");
        Client client = Client.forTestnet();
        AccountInfo accountInfo;
        TransactionReceipt receipt;
        log.info("{}", "associating token to user");
        try {

                client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                        PrivateKey.fromString(ADMINPRIVATEKEY));

                TokenAssociateTransaction transaction = new TokenAssociateTransaction()
                        .setAccountId(AccountId.fromString(user.getAccountid()))
                        .setTokenIds(TokenId.fromString(tokenId));

                TransactionResponse txResponse = transaction.freezeWith(client).sign
                        (PrivateKey.fromString(user.getPrivateKey())).execute(client);
                receipt = txResponse.getReceipt(client);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return CompletableFuture.completedFuture(receipt)
                    .getNow(null);
        }
    //NOTE CORRESPONDING TOKEN BALANCE MUST BE AT 0 TO DISASSOCIATE!
    @Async()
    public TransactionReceipt vendorAssociatingToken(String vendorId, String tokenId) throws AirtableException {
        PublicVendor vendor = publicVendorService.findVendor(vendorId);
        Client client = Client.forTestnet();
        AccountInfo accountInfo;
        TransactionReceipt receipt;
        log.info("{}", "associating token to vendor");
        try {

            client.setOperator(AccountId.fromString(ADMINACCOUNTID),
                    PrivateKey.fromString(ADMINPRIVATEKEY));

            TokenAssociateTransaction transaction = new TokenAssociateTransaction()
                    .setAccountId(AccountId.fromString(vendor.getAccountid()))
                    .setTokenIds(TokenId.fromString(tokenId));

            TransactionResponse txResponse = transaction.freezeWith(client).sign
                    (PrivateKey.fromString(vendor.getPrivateKey())).execute(client);
            receipt = txResponse.getReceipt(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt)
                .getNow(null);
    }
        //NOTE CORRESPONDING TOKEN BALANCE MUST BE AT 0 TO DISASSOCIATE!
        @Async()
        public TransactionReceipt userDisassociatingToken(String userId, String tokenId) throws AirtableException {
        PublicUser user =publicUserService.findUser(userId);
        Client client = Client.forTestnet();
        TransactionReceipt receipt;
        log.info("{}", "disassociating token from user");
        try {

            client.setOperator(AccountId.fromString(user.getAccountid()),
                    PrivateKey.fromString(user.getPrivateKey()));

            TokenDissociateTransaction transaction = new TokenDissociateTransaction()
                    .setAccountId(AccountId.fromString(user.getAccountid()))
                    .setTokenIds(TokenId.fromString(tokenId));

            TransactionResponse txResponse = transaction.freezeWith(client).sign
                    (PrivateKey.fromString(user.getPrivateKey())).execute(client);
            receipt = txResponse.getReceipt(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt)
                .getNow(null);
       }
    //NOTE CORRESPONDING TOKEN BALANCE MUST BE AT 0 TO DISASSOCIATE!
    @Async()
    public TransactionReceipt vendorDisassociatingToken(String vendorId, String tokenId) throws AirtableException {
        PublicVendor vendor = publicVendorService.findVendor(vendorId);
        Client client = Client.forTestnet();
        TransactionReceipt receipt;
        log.info("{}", "disassociating token from user");
        try {

            client.setOperator(AccountId.fromString(vendor.getAccountid()),
                    PrivateKey.fromString(vendor.getPrivateKey()));

            TokenDissociateTransaction transaction = new TokenDissociateTransaction()
                    .setAccountId(AccountId.fromString(vendor.getAccountid()))
                    .setTokenIds(TokenId.fromString(tokenId));

            TransactionResponse txResponse = transaction.freezeWith(client).sign
                    (PrivateKey.fromString(vendor.getPrivateKey())).execute(client);
            receipt = txResponse.getReceipt(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt)
                .getNow(null);
    }

}

