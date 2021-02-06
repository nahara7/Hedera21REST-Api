package com.nahara.toka.service.hedera.api;

import com.hedera.hashgraph.sdk.*;
import com.hedera.hashgraph.sdk.proto.Transaction;
import com.nahara.toka.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    //for Token
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
    public TransactionReceipt transactionUserVendor(PublicUser user, PublicVendor vendor, long fee) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        Client client = Client.forTestnet();
        TransactionReceipt receipt;
        //Token token = new Token();
        log.info("{}", "starting transaction...");
        try {
             //Attribute JVT
            TokenId TokenId = com.hedera.hashgraph.sdk.TokenId.fromString("0.0.275240");

            client.setOperator(AccountId.fromString(user.getAccountid()),
                           //make take in the feee
                    PrivateKey.fromString(user.getPrivateKey()));
                            //(Objects.requireNonNull(System.getenv("my_private_key"))));
            TransferTransaction transaction = new TransferTransaction()
                    .addHbarTransfer
                            (AccountId.fromString(user.getAccountid()), new Hbar(-2))

                    .addHbarTransfer
                            (AccountId.fromString(vendor.getAccountid()), new Hbar(2));
            TransferTransaction txId = transaction.
            freezeWith(client).sign( PrivateKey.fromString
            (user.getPrivateKey()));
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
    //update client
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
    //Update Client
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
    @Async()
    public AccountId AccountCreateTransaction(String publickey) throws TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        log.info("{}", "account create...");
        //my env variables
        TransactionReceipt receipt;
        try {
            Client client = Client.forTestnet();

            AccountId envId = AccountId.
                    fromString(Objects.requireNonNull((System.getenv("nahara_account_id"))));
            PrivateKey envPriv = PrivateKey.
                    fromString(Objects.requireNonNull((System.getenv("nahara_private_key"))));
            client.setOperator(envId, envPriv);
            AccountCreateTransaction transaction = new AccountCreateTransaction()
                    .setKey(PublicKey.fromString(publickey))
                    .setInitialBalance(new Hbar(700));
            TransactionResponse txId = transaction.execute(client);
            receipt = txId.getReceipt(client);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return CompletableFuture.completedFuture(receipt.accountId)
                .getNow(null);
    }

}

