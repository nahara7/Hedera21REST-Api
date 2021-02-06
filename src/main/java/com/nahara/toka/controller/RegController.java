package com.nahara.toka.controller;

import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.nahara.toka.model.PublicUser;
import com.nahara.toka.model.User;
import com.nahara.toka.model.Vendor;
import com.nahara.toka.service.hedera.api.PublicUserService;
import com.nahara.toka.service.hedera.api.PublicVendorService;
import com.nahara.toka.service.hedera.api.UserService;
import com.nahara.toka.service.hedera.api.VendorService;
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
public class RegController {


    private VendorService vendorService= new VendorService();
    private UserService userService=new UserService();

    private PublicVendorService publicVendorService= new PublicVendorService();

    private PublicUserService publicUserService= new PublicUserService();
    public RegController() throws AirtableException {
    }

    @PostMapping("/createUser")
    public ResponseEntity<PublicUser> createUser(@RequestBody User user) throws AirtableException, InvocationTargetException, IllegalAccessException, TimeoutException, NoSuchMethodException, HederaReceiptStatusException, HederaPreCheckStatusException {
        user.setPrivateKey();
        user.setPublickey();
        user.setAccountid();
        User newUser= userService.createUser(user);
        System.out.println(publicUserService.findUser(newUser.getUserId()).getPrivateKey());
        return ResponseEntity.ok().body(publicUserService.findUser(newUser.getUserId()));
    }
    @PostMapping("/createVendor")
    public void createVendor(@RequestBody Vendor vendor) throws AirtableException, InvocationTargetException, IllegalAccessException, TimeoutException, NoSuchMethodException, HederaReceiptStatusException, HederaPreCheckStatusException {
        vendor.setPrivateKey();
        vendor.setPublickey();
        vendor.setAccountid();
        Vendor newVendor=vendorService.createVendor(vendor);
        System.out.println(publicVendorService.findVendor(newVendor.getVendorId()).getPrivateKey());

    }

   @DeleteMapping("/user/{id}")
   public String deleteUser(String Id){
    try{
         //inaccurate
        userService.findUser(Id);

    } catch (AirtableException e) {
        e.printStackTrace();
        return "user does not exist";
    }
       return "successfully deleted";
   }

    @DeleteMapping("/vendor/{id}")
    public String deleteVendor(String Id){
        try{
            vendorService.deleteVendor(Id);

        } catch (AirtableException e) {
            e.printStackTrace();
            return "vendor does not exist";
        }
        return "successfully deleted";
    }
}
