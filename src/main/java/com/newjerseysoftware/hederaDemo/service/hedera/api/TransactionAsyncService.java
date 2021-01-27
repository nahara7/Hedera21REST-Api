package com.newjerseysoftware.hederaDemo.service.hedera.api;

import com.hedera.hashgraph.sdk.*;
import com.newjerseysoftware.hederaDemo.components.Hedera;
import com.newjerseysoftware.hederaDemo.model.Token;
import com.newjerseysoftware.hederaDemo.model.User;
import com.newjerseysoftware.hederaDemo.model.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@Service
public class TransactionAsyncService {
    //private TransactionReceipt receipt;

    public TransactionAsyncService() {
    }

    /*@Autowired
    private Hedera hedera;
     @Autowired
    public TransactionAsyncService(Hedera hedera) {
        this.hedera = hedera;
    }*/

    private static Logger log = LoggerFactory.getLogger(TransactionAsyncService.class);

   /* @Async()
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

            //} catch (TimeoutException|HederaReceiptStatusException|HederaPreCheckStatusException e) {
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return CompletableFuture.completedFuture(token)
                .getNow(null);

    }*/

    @Async()
    public TransactionReceipt transactionUserVendor(User user, Vendor vendor, Long fee) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        Client client = Client.forTestnet();
        TransactionReceipt receipt;
        //Token token = new Token();
        log.info("{}", "starting transaction...");
        try {
            //TokenId TokenId= com.hedera.hashgraph.sdk.TokenId.fromString(token.getTokenid());
            TokenId TokenId = com.hedera.hashgraph.sdk.TokenId.fromString("0.0.275240");
            //account Id=Nahara
            client.setOperator(AccountId.fromString
                            (Objects.requireNonNull(System.getenv("nahara_account_id"))),
                    PrivateKey.fromString
                            (Objects.requireNonNull(System.getenv("my_private_key"))));
            TransferTransaction transfer = new TransferTransaction()
                    .addHbarTransfer
                            (AccountId.fromString(user.getAccountid()), Hbar.from((long) -(fee)))
                    .addHbarTransfer
                            (AccountId.fromString(vendor.getAccountid()), Hbar.from(BigDecimal.valueOf(fee)));
            //must attribute JVT token to user to do this transaction
       /* TransferTransaction transfer= new TransferTransaction()
                .addTokenTransfer(TokenId, AccountId.fromString(user.getAccountid()), -(fee))
                .addTokenTransfer(TokenId, AccountId.fromString(vendor.getAccountid()), fee);*/
            TransferTransaction txId = transfer.
                    freezeWith(client).sign(PrivateKey.fromString(user.getPrivateKey()));
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
    public TransactionReceipt vendorPromotion(User user, Vendor vendor, Long promo, String memo) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        TransactionReceipt receipt;
        Client client = Client.forTestnet();
        Token token = new Token();
        log.info("{}", "promotion...");
        try {
            client.setOperator(AccountId.fromString(Objects.requireNonNull(System.getenv("nahara_account_id"))),
                    PrivateKey.fromString(Objects.requireNonNull(System.getenv("my_private_key"))));
            TransferTransaction transfer = new TransferTransaction()
                    .addHbarTransfer(AccountId.fromString(vendor.getAccountid()), Hbar.fromTinybars(-(promo)))
                    .addHbarTransfer(AccountId.fromString(user.getAccountid()), Hbar.fromTinybars(promo))
                    .setTransactionMemo("promotion: " + memo);
            TransferTransaction txId = transfer.freezeWith(client).
                    sign(PrivateKey.fromString(vendor.getPrivateKey()));
            txId.execute(client);
            TransactionId tId = txId.getTransactionId();
            //this.record=tId.getRecord(client);
            receipt = tId.getReceipt(client);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt)
                .getNow(null);
    }

    @Async
    //check this !
    public TransactionReceipt cashBack(User user, Vendor vendor, int percentage, int total) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        TransactionReceipt receipt;
        Client client = Client.forTestnet();
        double fee = (double) (total * (5.0f / 100.0));
        log.info("{}", "cashback...");
        try {
            client.setOperator(AccountId.fromString(Objects.requireNonNull(System.getenv("nahara_account_id"))),
                    PrivateKey.fromString(Objects.requireNonNull(System.getenv("my_private_key"))));
            TransferTransaction transfer = new TransferTransaction()
                    .addHbarTransfer(AccountId.fromString
                            (vendor.getAccountid()), Hbar.fromTinybars((long) -(fee)))
                    .addHbarTransfer(AccountId.fromString
                            (user.getAccountid()), Hbar.fromTinybars((long) fee));
            TransferTransaction txId = transfer.freezeWith(client).
                    sign(PrivateKey.fromString(vendor.getPrivateKey()));
            txId.execute(client);
            TransactionId tId = txId.getTransactionId();
            //this.record=tId.getRecord(client);
            receipt = tId.getReceipt(client);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt)
                .getNow(null);
    }
}

