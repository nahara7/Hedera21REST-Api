package com.nahara.toka.controller;


import com.hedera.hashgraph.sdk.AccountBalance;
import com.hedera.hashgraph.sdk.AccountInfo;
import com.nahara.toka.model.Account;
//import com.nahara.toka.model.Contact;
import com.nahara.toka.model.Id;
import com.nahara.toka.service.hedera.api.*;
import com.sybit.airtable.exception.AirtableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/account")
public class AccountController {
    VendorService vendorService= new VendorService();

    UserService userService= new UserService();

    PublicVendorService publicVendorService= new PublicVendorService();

    PublicUserService publicUserService=new PublicUserService();

    AccountAsyncService accountAsyncService=new AccountAsyncService();

    TransactionAsyncService transactionAsyncService=new TransactionAsyncService();

    //ContactService contactService= new ContactService();
    private static Logger log = LoggerFactory.getLogger(AccountController.class);

    public AccountController() throws AirtableException {
    }

    @GetMapping("/hedera/user")
    public AccountInfo getUserHederaAccountInfo(@RequestBody Id userId) throws AirtableException {
        log.info("{}", "findingUser");
        return accountAsyncService.getUserHederaAccountInfo(userId.getBaseId());
    }
    @GetMapping("/hedera/vendor")
    public ResponseEntity<AccountInfo> getVendorHederaAccountInfo(@RequestBody Id userId) throws AirtableException {
       AccountInfo accountInfo=accountAsyncService.getVendorHederaAccountInfo(userId.getBaseId());

       return ResponseEntity.ok().body(accountInfo);
    }
    @GetMapping("/userBalance")
    public ResponseEntity<AccountBalance> userBalance(@RequestBody Id userId) throws AirtableException {

        log.info("{}", "getting user account balance...");
        AccountBalance accountBalance=accountAsyncService.getUserAccountBalance(userId.getBaseId());
        return ResponseEntity.ok().body(accountBalance);

    }


    //update vendor
    //
    @GetMapping("/vendorBalance")
    public ResponseEntity<AccountBalance> getVendorBalance(@RequestBody Id vendorId) throws AirtableException {

        AccountBalance accountBalance = accountAsyncService.getVendorAccountBalance(vendorId.getBaseId());
        return ResponseEntity.ok().body(accountBalance);
    }
    @GetMapping("/tokaAccount")
    public Account getTokaAccountInfo(@RequestBody Id tokaAccountId) throws AirtableException {
        log.info("{}", "getting account of"+ tokaAccountId.getBaseId());
        return accountAsyncService.findAccount(tokaAccountId.getBaseId());
    }

    //add contact

}
