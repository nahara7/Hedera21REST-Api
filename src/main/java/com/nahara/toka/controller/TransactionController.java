package com.nahara.toka.controller;

import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.nahara.toka.model.Test;
import com.nahara.toka.model.Transaction;
import com.nahara.toka.model.User;
import com.nahara.toka.model.Vendor;
import com.nahara.toka.service.hedera.api.TransactionAsyncService;

//import com.newjerseysoftware.hederaDemo.service.hedera.api.TransactionAsyncService;
import com.nahara.toka.service.hedera.api.UserService;
import com.nahara.toka.service.hedera.api.VendorService;
import com.sybit.airtable.exception.AirtableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("api/v1.0/transaction")
//@CrossOrigin(origins = "")
public class TransactionController {

    //log.info("{}","token end point..." + token.getName());

    private static Logger log = LoggerFactory.getLogger(TransactionController.class);

   /* @Autowired
    private TokenRepository tokenRepository;*/

    @Autowired
    private TransactionAsyncService transactionAsyncService;
    UserService userService = new UserService();
    VendorService vendorService = new VendorService();

    public TransactionController() throws AirtableException {
    }

    /*private Token token;
    @PostMapping("/token")
    public ResponseEntity<Token> createToken(@Valid @RequestBody Token token) {

        log.info("{}","/token..." + token.getSymbol());

        Token newToken = transactionAsyncService.createToken(
                    token.getName(),
                    token.getSymbol(),
                    token.getInitialsupply());

        log.info("{}","new token id..." + newToken.getTokenid());

        tokenRepository.save(newToken);

        return ResponseEntity.ok().body(newToken);
    }*/
    @PostMapping
    public void transactionUserVendor(@RequestBody Transaction transaction) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, AirtableException {

       /* //User user=userService.findUser(transaction.getUserId());
        Vendor vendor=vendorService.findVendor(transaction.getVendorId());
        //String key=vendorService.getPrivKey(vendorService.findVendor(transaction.getVendorId()));
        //System.out.println(key);
        System.out.println(vendor.getVendorId());
        System.out.println(user.getUserId());
        //System.out.println(userService.getPrivKey(user.getUserId()));
        System.out.println(user.getUsername());

        System.out.println(vendor.getUsername());

        //System.out.println(user.getPrivateKey());
        //System.out.println(vendorService.findVendor(transaction.getVendorId()).getPrivateKey());
        //System.out.println(user.getUsername());
        //TransactionReceipt receipt = transactionAsyncService.transactionUserVendor(user, vendor, (long) transaction.getFee());
        //System.out.println(receipt.toString());
        //return ResponseEntity.ok().body(receipt);

    }
    /*@PostMapping
    public ResponseEntity<TransactionReceipt> promotion(@Valid @RequestBody Transaction.Promotion promotion) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        TransactionReceipt receipt = transactionAsyncService.vendorPromotion(promotion.getUser(), promotion.getVendor(), promotion.getPromotion(), promotion.getMemo());
        return ResponseEntity.ok().body(receipt);
    }*/


    }
}





