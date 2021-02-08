package com.nahara.toka.api;

import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.components.Hedera;
import com.nahara.toka.model.PublicUser;
import com.nahara.toka.model.PublicVendor;
import com.nahara.toka.model.User;
import com.nahara.toka.model.Vendor;
import com.nahara.toka.service.hedera.api.*;
import com.sybit.airtable.exception.AirtableException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

class TransactionAsyncServiceTest {


    PublicUserService publicUserService= new PublicUserService();
    PublicVendorService publicVendorService= new PublicVendorService();
    PublicUser user= publicUserService.findUser("recAJZUgme5APnmSt");
    PublicVendor vendor= publicVendorService.findVendor("recQnwG6y90coD8ix");
    TokenAsyncService tokenAsyncService= new TokenAsyncService();
    TokenId tokenId=TokenId.fromString("0.0.309579");
    TransactionAsyncService transactionAsyncService= new TransactionAsyncService();
    Client client = Client.forTestnet();

    TransactionAsyncServiceTest() throws AirtableException {
    }

    @Test
    void tokenTest() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, AirtableException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

    }


    @Test
    void tokenAssociate() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        TransactionReceipt receiptUser=transactionAsyncService.AssociatingToken(user, tokenId.toString());
        System.out.println(receiptUser.toString());

        TransactionReceipt receiptVendor=transactionAsyncService.AssociatingToken(vendor, tokenId.toString());
        System.out.println(receiptVendor.toString());
    }

    @Test
    //token
    void tokenAssociateInitalise() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {

        /*String privateKey=tokenInfo.adminKey.toString();
        //client.setOperator(AccountId.fromString(Objects.requireNonNull(System.getenv("NAHARA_ACCOUNT_ID"))),
                //PrivateKey.fromString(Objects.requireNonNull(System.getenv("NAHARA PRIVATE KEY"))));

        TransferTransaction transaction = new TransferTransaction()
                .addTokenTransfer
                        (tokenId, AccountId.fromString(tokenInfo.treasuryAccountId.toString()), -2)

                .addTokenTransfer
                        (tokenId, AccountId.fromString(user.getAccountid()),2);

        TransactionResponse response= transaction.freezeWith(client).sign(PrivateKey.fromString(tokenInfo.adminKey.toString())).sign(PrivateKey.fromString(tokenInfo.supplyKey.toString())).execute(client);
        //TransactionResponse response=transaction.execute(client);

        TransactionReceipt receipt=response.getReceipt(client);
        System.out.println(receipt.toString());*/

    }
    @Test
        //token
    void transactionUserVendor() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {

    }
    @Test
    void tokenInfoQuery() throws TimeoutException, HederaPreCheckStatusException {
        client.setOperator(AccountId.fromString(Objects.requireNonNull(System.getenv("NAHARA_ACCOUNT_ID"))),
                PrivateKey.fromString(Objects.requireNonNull(System.getenv("NAHARA_PRIVATE_KEY"))));
        TokenInfo tokenInfo;
        TokenInfoQuery tokenQuery= new TokenInfoQuery()
                .setTokenId(tokenId);
        tokenInfo= tokenQuery.execute(client);
        System.out.println(tokenInfo.toString());

    }
    @Test
    //for now demo users don't have enough funds for the queries
    //update initial balance when stop using my testnet account
    void accountInfoQuery() throws TimeoutException, HederaPreCheckStatusException {
        client.setOperator(AccountId.fromString(Objects.requireNonNull(System.getenv("NAHARA_ACCOUNT_ID"))),
                PrivateKey.fromString(Objects.requireNonNull(System.getenv("NAHARA_PRIVATE_KEY"))));
        AccountInfo accountInfo;
        AccountInfoQuery query = new AccountInfoQuery()
                .setAccountId(AccountId.fromString(user.getAccountid()));

        accountInfo = query.execute(client);
        System.out.println(accountInfo.toString());

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


        vendor.setPublickey();
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
        vendor.setPublickey();
        vendor.setAccountid();

        TransactionAsyncService service = new TransactionAsyncService();
        TransactionReceipt receipt = service.cashBack(user, vendor, 5, (int) 10);
        System.out.println(receipt.status);
    }
}