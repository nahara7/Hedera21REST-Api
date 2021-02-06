package com.nahara.toka.controller;
import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.nahara.toka.model.*;
import com.nahara.toka.service.hedera.api.*;
import com.sybit.airtable.exception.AirtableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("api/v1.0/transaction")
//@CrossOrigin(origins = "")
public class TransactionController {

    //log.info("{}","token end point..." + token.getName());

    private static Logger log = LoggerFactory.getLogger(TransactionController.class);

   /*@Autowired
    private TokenRepository tokenRepository;*/

    @Autowired
    private TransactionAsyncService transactionAsyncService;
    UserService userService = new UserService();
    VendorService vendorService = new VendorService();
    PublicUserService publicUserService= new PublicUserService();
    PublicVendorService publicVendorService=new PublicVendorService();

    public TransactionController() throws AirtableException {
    }

    private Token token;
    /*@PostMapping("/token")
    public ResponseEntity<Token> createToken(@RequestBody Token token) {

        log.info("{}","/token..." + token.getSymbol());

        //Token newToken = transactionAsyncService.createToken(
                    token.getName(),
                    token.getSymbol(),
                    token.getInitialsupply());

        log.info("{}","new token id..." + newToken.getTokenid());

        //tokenRepository.save(newToken);

        return ResponseEntity.ok().body(newToken);
    }*/
    @PostMapping("/userVendor")
    //sends back receipt
    //update sending back record

    public ResponseEntity<TransactionReceipt> transactionUserVendor(@RequestBody Transaction transaction) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, AirtableException {
           PublicUser user= publicUserService.findUser(transaction.getUserId());
           PublicVendor vendor=publicVendorService.findVendor(transaction.getVendorId());
           Long fee= transaction.getFee();

           TransactionReceipt receipt = transactionAsyncService.transactionUserVendor(user, vendor, fee);

           return ResponseEntity.ok().body(receipt);

    }
    /*@PostMapping
    public ResponseEntity<TransactionReceipt> promotion(@Valid @RequestBody Transaction.Promotion promotion) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        TransactionReceipt receipt = transactionAsyncService.vendorPromotion(promotion.getUser(), promotion.getVendor(), promotion.getPromotion(), promotion.getMemo());
        return ResponseEntity.ok().body(receipt);
    }*/


    }






