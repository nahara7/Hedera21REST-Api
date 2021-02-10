package com.nahara.toka.controller;

import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.nahara.toka.model.*;
import com.nahara.toka.service.hedera.api.*;
import com.sybit.airtable.exception.AirtableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping("api/v1.0/")
//@CrossOrigin(origins = "")
//when accessing the Public User and Public Vendor you just need
//to send over the userId or vendorID
//remember to delete and update accounts as well !
public class BaseController {

    private VendorService vendorService= new VendorService();
    private UserService userService=new UserService();
    private PublicVendorService publicVendorService= new PublicVendorService();
    private PublicUserService publicUserService= new PublicUserService();
    private AccountAsyncService accountAsyncService = new AccountAsyncService();

    public BaseController() throws AirtableException {
    }

    @PostMapping("/createUser")
    public ResponseEntity<PublicUser> createUser(@RequestBody User user) throws AirtableException, InvocationTargetException, IllegalAccessException, TimeoutException, NoSuchMethodException, HederaReceiptStatusException, HederaPreCheckStatusException {
        user.setPrivateKey();
        user.setPublickey();
        user.setAccountid();
        User newUser= userService.createUser(user);
        //creates account subsequently
        //will only be used at registration
        Account account= new Account();
        account.setUsername(user.getUsername());
        account.setPassword(user.getPassword());
        Account newAccount= accountAsyncService.createAccount(account);

        return ResponseEntity.ok().body(publicUserService.findUser(newUser.getUserId()));
    }
    @PostMapping("/createVendor")
    public ResponseEntity<PublicVendor> createVendor(@RequestBody Vendor vendor) throws AirtableException, InvocationTargetException, IllegalAccessException, TimeoutException, NoSuchMethodException, HederaReceiptStatusException, HederaPreCheckStatusException {

        vendor.setPrivateKey();
        vendor.setPublickey();
        vendor.setAccountid();
        Vendor newVendor=vendorService.createVendor(vendor);

        //creates account subsequently
        //will only be used at registration
        Account account= new Account();
        account.setUsername(vendor.getUsername());
        account.setVendorId(vendor.getVendorId());
        account.setEmail(vendor.getEmail());
        account.setPassword(vendor.getPassword());

        Account newAccount= accountAsyncService.createAccount(account);
        return ResponseEntity.ok().body(publicVendorService.findVendor(newVendor.getVendorId()));

    }
    @PutMapping ("/updateUser")
    public ResponseEntity<PublicUser> updateUser(@RequestBody User user) throws AirtableException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User updatedUser=userService.updateUser(user);
        return ResponseEntity.ok().body(publicUserService.findUser(updatedUser.getUserId()));

    }

   @DeleteMapping("/user/{id}")
   public String deleteUser(String Id){
    try{

        userService.deleteUser(Id);

    } catch (AirtableException e) {
        e.printStackTrace();
        return "user does not exist";
    }
       return "User has been successfully deleted";
   }

    @DeleteMapping("/vendor/{id}")
    public String deleteVendor(String Id){
        try{
            vendorService.deleteVendor(Id);

        } catch (AirtableException e) {
            e.printStackTrace();
            return "vendor does not exist";
        }
        return "Vendor has been successfully deleted";
    }
}
