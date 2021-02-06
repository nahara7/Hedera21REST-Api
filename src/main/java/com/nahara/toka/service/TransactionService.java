package com.nahara.toka.service;

import com.hedera.hashgraph.sdk.*;

import java.util.concurrent.TimeoutException;

import com.nahara.toka.model.User;
import com.nahara.toka.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
//import io.github.cdimascio.dotenv.Dotenv;


//@Service
public class TransactionService {
    //check versions and dependencies
    @Autowired
    private TransactionResponse transaction;
    private final User user;
    private final Vendor vendor;
    private final int fee;
    Client client = Client.forTestnet();
    @Autowired
    //public TransactionService(@Qualifier("demoUser") UserDao userdao, @Qualifier("demoVendor") VendorDao vendordao) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
    public TransactionService(User user, Vendor vendor, int fee) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        this.user=user;
        this.vendor=vendor;
        this.fee=fee;
    }

    public void Transaction(User user, Vendor vendor) throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {

        this.transaction=transactionUserVendor();
    }
    public TransactionResponse transactionUserVendor() throws StringIndexOutOfBoundsException, TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {

        //check order
        //configure private key in account create transaction
        //client.setOperator(user.getAccountid(), user.getPrivateKey());
        TransactionResponse transactionResponse = new TransferTransaction()
                 //return actual Id?
                .addHbarTransfer(AccountId.fromString(user.getAccountid()), Hbar.fromTinybars(-(fee)))
                .addHbarTransfer(AccountId.fromString(vendor.getAccountid()), Hbar.fromTinybars(fee))
                //add input as memo
                //.setTransactionMemo("transfer test")
                //in my local project this line gave a no such method found error
                .execute(client);

        //this.transaction=transactionResponse;
        return transactionResponse;
    }
    public TransactionReceipt returnReceipt() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        return transaction.getReceipt(client);
    }
    //change class
    public int addUser(User user) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        return 0;
    }
    public int addVendor(Vendor vendor) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException{
        return 0;
    }
           /* public List<User> getAllUsers(){
                return.user
            }*/
}
//add query balance and more !




