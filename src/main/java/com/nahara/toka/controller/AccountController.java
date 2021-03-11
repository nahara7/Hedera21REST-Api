package com.nahara.toka.controller;


import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.model.*;
import com.nahara.toka.service.hedera.api.*;
import com.sybit.airtable.exception.AirtableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1.0/account")
public class AccountController {

    @Autowired
    TransactionAsyncService transactionAsyncService;
    @Autowired
    UserService userService;
    @Autowired
    VendorService vendorService;
    @Autowired
    PublicUserService publicUserService;
    @Autowired
    PublicVendorService publicVendorService;
    @Autowired
    AdminAsyncService adminAsyncService;
    @Autowired
    AccountAsyncService accountAsyncService;


    private static final String JVT = "" + System.getenv("JVT_TOKEN_ID");

    private static Logger log = LoggerFactory.getLogger(AccountController.class);

    public AccountController() throws AirtableException {
    }

    @PostMapping("/hedera/userTokenRelationships")
    public ResponseEntity<Map<TokenId, TokenRelationship>> getUserTokenRelationships(@RequestBody Id userId) throws AirtableException {
        log.info("{}", "finding User");
        System.out.println(userId.getBaseId());
        return ResponseEntity.ok().body( accountAsyncService.getUserHederaAccountInfo(userId.getBaseId()).tokenRelationships);
    }

    @PostMapping("/hedera/vendorTokenRelationships")
    public ResponseEntity<Map<TokenId, TokenRelationship>> getVendorTokenRelationships(@RequestBody Id vendorId) throws AirtableException {
        log.info("{}", "finding Vendor");
        return ResponseEntity.ok().body(accountAsyncService.getVendorHederaAccountInfo(vendorId.getBaseId()).tokenRelationships);
    }

    @PostMapping("/userBalance")
    public ResponseEntity<AccountBalance> userBalance(@RequestBody Id userId) throws AirtableException {

        log.info("{}", "getting user account balance...");
        AccountBalance accountBalance = accountAsyncService.getUserAccountBalance(userId.getBaseId());
        return ResponseEntity.ok().body(accountBalance);

    }

    @PostMapping("/vendorBalance")
    public ResponseEntity<AccountBalance> getVendorBalance(@RequestBody Id vendorId) throws AirtableException {

        AccountBalance accountBalance = accountAsyncService.getVendorAccountBalance(vendorId.getBaseId());
        return ResponseEntity.ok().body(accountBalance);
    }

    @PostMapping("/tokaAccount")
    public Account getTokaAccountInfo(@RequestBody Id tokaAccountId) throws AirtableException {
        log.info("{}", "getting account of" + tokaAccountId.getBaseId());
        return accountAsyncService.findAccount(tokaAccountId.getBaseId());
    }

    @PostMapping("userTokenAssociate")
    public ResponseEntity<TransactionReceipt> userTokenAssociate(@RequestBody Id userId) throws AirtableException {
        TransactionReceipt receipt = transactionAsyncService.userAssociatingToken(userId.getBaseId(), JVT);
        return ResponseEntity.ok().body(receipt);
    }

    @PostMapping("vendorTokenAssociate")
    public ResponseEntity<TransactionReceipt> vendorTokenAssociate(@RequestBody Id vendorId) throws AirtableException {
        TransactionReceipt receipt = transactionAsyncService.vendorAssociatingToken(vendorId.getBaseId(), JVT);
        return ResponseEntity.ok().body(receipt);
    }
    @PostMapping("userPrivateKey")
    public String getUserPrivateKey(@RequestBody Id userId) throws AirtableException {
        PublicUser user=publicUserService.findUser(userId.getBaseId());
        return user.getPrivateKey().toString();
    }
    @PostMapping("userFirstName")
    public String getUserFirstName(@RequestBody Id userId) throws AirtableException{
        User user= userService.findUser(userId.getBaseId());
        return user.getFirstname();
    }
    @PostMapping("userPublicKey")
    public String getUserPublicKey(@RequestBody Id userId) throws AirtableException {
        PublicUser user= publicUserService.findUser(userId.getBaseId());
        log.info("{}", "public key+" + user.getPublicKey());
        return user.getPublicKey();
    }
    @PostMapping("userAccountId")
    public String getUserAccountId(@RequestBody Id userId) throws AirtableException {
        User user= userService.findUser(userId.getBaseId());
        return user.getAccountid();
    }
    @PostMapping("vendorPrivateKey")
    public String getVendorPrivateKey(@RequestBody Id vendorId) throws AirtableException {
        PublicVendor vendor= publicVendorService.findVendor(vendorId.getBaseId());
        return vendor.getPrivateKey();

    }
    @PostMapping("vendorPublicKey")
    public String getVendorPublicKey(@RequestBody Id vendorId) throws AirtableException {

        PublicVendor vendor= publicVendorService.findVendor(vendorId.getBaseId());
          return vendor.getPublicKey();
    }
    @PostMapping("vendorShopName")
    public String getVendorShopName(@RequestBody Id vendorId) throws AirtableException{
        Vendor vendor= vendorService.findVendor(vendorId.getBaseId());
        return vendor.getVendorShopName();
    }
    @PostMapping("vendorAccountId")
    public String getVendorAccountId(@RequestBody Id vendorId) throws AirtableException {
     Vendor vendor= vendorService.findVendor(vendorId.getBaseId());
     return vendor.getAccountid();
    }
    @PostMapping("adminBalance")
    public ResponseEntity<AccountBalance> getAdminBalance(){
        AccountBalance accountBalance=adminAsyncService.adminAccountBalance();
        return ResponseEntity.ok().body(accountBalance);
    }

}


