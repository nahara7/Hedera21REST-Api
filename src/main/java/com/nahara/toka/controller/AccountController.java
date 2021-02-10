package com.nahara.toka.controller;


import com.hedera.hashgraph.sdk.AccountBalance;
import com.hedera.hashgraph.sdk.AccountInfo;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.nahara.toka.model.*;
import com.nahara.toka.service.hedera.api.*;
import com.sybit.airtable.exception.AirtableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/account")
public class AccountController {

    VendorService vendorService = new VendorService();
    UserService userService = new UserService();
    PublicVendorService publicVendorService = new PublicVendorService();
    PublicUserService publicUserService = new PublicUserService();
    AccountAsyncService accountAsyncService = new AccountAsyncService();
    TransactionAsyncService transactionAsyncService = new TransactionAsyncService();
    AdminAsyncService adminAsyncService= new AdminAsyncService();

    private static final String JVT = "" + System.getenv("JVT_TOKEN_ID");

    private static Logger log = LoggerFactory.getLogger(AccountController.class);

    public AccountController() throws AirtableException {
    }

    @GetMapping("/hedera/user")
    public AccountInfo getUserHederaAccountInfo(@RequestBody Id userId) throws AirtableException {
        log.info("{}", "finding User");
        System.out.println(userId.getBaseId());
        return accountAsyncService.getUserHederaAccountInfo(userId.getBaseId());
    }

    @GetMapping("/hedera/vendor")
    public ResponseEntity<AccountInfo> getVendorHederaAccountInfo(@RequestBody Id userId) throws AirtableException {
        log.info("{}", "finding Vendor");
        AccountInfo accountInfo = accountAsyncService.getVendorHederaAccountInfo(userId.getBaseId());

        return ResponseEntity.ok().body(accountInfo);
    }

    @GetMapping("/userBalance")
    public ResponseEntity<AccountBalance> userBalance(@RequestBody Id userId) throws AirtableException {

        log.info("{}", "getting user account balance...");
        AccountBalance accountBalance = accountAsyncService.getUserAccountBalance(userId.getBaseId());
        return ResponseEntity.ok().body(accountBalance);

    }

    @GetMapping("/vendorBalance")
    public ResponseEntity<AccountBalance> getVendorBalance(@RequestBody Id vendorId) throws AirtableException {

        AccountBalance accountBalance = accountAsyncService.getVendorAccountBalance(vendorId.getBaseId());
        return ResponseEntity.ok().body(accountBalance);
    }

    @GetMapping("/tokaAccount")
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
    @GetMapping("userPrivateKey")
    public String getUserPrivateKey(@RequestBody Id userId) throws AirtableException {
        PublicUser user=publicUserService.findUser(userId.getBaseId());
        return user.getPrivateKey().toString();
    }
    @GetMapping("userPublicKey")
    public String getUserPublicKey(@RequestBody Id userId) throws AirtableException {
        PublicUser user= publicUserService.findUser(userId.getBaseId());
        log.info("{}", "public key+" + user.getPublicKey());
        return user.getPublicKey();
    }
    @GetMapping("userAccountId")
    public String getUserAccountId(@RequestBody Id userId) throws AirtableException {
        User user= userService.findUser(userId.getBaseId());
        return user.getAccountid();
    }
    @GetMapping("vendorPrivateKey")
    public String getVendorPrivateKey(@RequestBody Id vendorId) throws AirtableException {
        PublicVendor vendor= publicVendorService.findVendor(vendorId.getBaseId());
        return vendor.getPrivateKey();

    }
    @GetMapping("vendorPublicKey")
    public String getVendorPublicKey(@RequestBody Id vendorId) throws AirtableException {
          //Vendor vendor=vendorService.findVendor(vendorId.getBaseId());
          PublicVendor vendor= publicVendorService.findVendor(vendorId.getBaseId());
          return vendor.getPublicKey();
    }
    @GetMapping("vendorAccountId")
    public String getVendorAccountId(@RequestBody Id vendorId) throws AirtableException {
     Vendor vendor= vendorService.findVendor(vendorId.getBaseId());
     return vendor.getAccountid();
    }
    @GetMapping("adminBalance")
    public ResponseEntity<AccountBalance> getAdminBalance(){
        AccountBalance accountBalance=adminAsyncService.adminAccountBalance();
        return ResponseEntity.ok().body(accountBalance);
    }

}


