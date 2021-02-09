package com.nahara.toka.controller;
import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.model.*;
import com.nahara.toka.model.Transaction;
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



    TransactionAsyncService transactionAsyncService= new TransactionAsyncService();
    UserService userService = new UserService();
    VendorService vendorService = new VendorService();
    PublicUserService publicUserService= new PublicUserService();
    PublicVendorService publicVendorService=new PublicVendorService();
    AdminAsyncService adminAsyncService= new AdminAsyncService();
    AccountAsyncService accountAsyncService= new AccountAsyncService();

    public String JVT=""+ System.getenv("JVT_TOKEN_ID");
    public TransactionController() throws AirtableException {
    }

    private Token token;
    @PostMapping("/token")
    public ResponseEntity<Token> createToken(@RequestBody Token token) {

        log.info("{}","/token..." + token.getSymbol());

        Token newToken = transactionAsyncService.createToken(
                    token.getName(),
                    token.getSymbol(),
                    token.getInitialsupply());

        log.info("{}","new token id..." + newToken.getTokenid());

        //tokenRepository.save(newToken);

        return ResponseEntity.ok().body(newToken);
    }
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
    @PostMapping("/initialize")
    public String initialize(@RequestBody Id userId) throws AirtableException {
        System.out.println(userId.getBaseId());
        System.out.println(JVT);
        String baseId=userId.getBaseId();
        TransactionReceipt receipt= transactionAsyncService.AssociatingToken(baseId, JVT);
        adminAsyncService.adminDeposit(userId.getBaseId());
        AccountBalance accountBalance= accountAsyncService.getUserAccountBalance(userId.getBaseId());

        return accountBalance.token.toString();

    }
    @PostMapping("user/adminDeposit")
    public  ResponseEntity<AccountBalance> userAdminDeposit(@RequestBody Id userId) throws AirtableException {
            TransactionReceipt receipt= adminAsyncService.adminDeposit(userId.getBaseId());
            AccountBalance accountBalance= accountAsyncService.getUserAccountBalance(userId.getBaseId());

            return ResponseEntity.ok().body(accountBalance);
        }
    @PostMapping("vendor/adminDeposit")
    public  ResponseEntity<AccountBalance> vendorAdminDeposit(@RequestBody Id vendorId) throws AirtableException {
        TransactionReceipt receipt= adminAsyncService.adminDeposit(vendorId.getBaseId());
        AccountBalance accountBalance= accountAsyncService.getUserAccountBalance(vendorId.getBaseId());

        return ResponseEntity.ok().body(accountBalance);
    }


    @PostMapping("/cashback)")
    public ResponseEntity<TransactionReceipt> cashBack( @RequestBody CashBack cashBack ) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, AirtableException {
        PublicUser user= publicUserService.findUser(cashBack.getUserId());
        PublicVendor vendor=publicVendorService.findVendor(cashBack.getVendorId());
        TransactionReceipt receipt=transactionAsyncService.cashBack(user, vendor,cashBack.getTotal());
        return ResponseEntity.ok().body(receipt);
    }


}






