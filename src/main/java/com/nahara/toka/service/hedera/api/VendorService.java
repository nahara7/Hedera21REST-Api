package com.nahara.toka.service.hedera.api;


import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.nahara.toka.model.Vendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;


import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;

public class VendorService {
    Airtable airtable= new Airtable().configure("keykefT9YD5rhkuFg");
    Base base = airtable.base("appg4L9uWpNhonYHS");
    Table<Vendor> vendorTable = base.table("Vendors", Vendor.class);

    public VendorService() throws AirtableException {
    }

    public Vendor createVendor(Vendor vendor) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {

         Vendor newVendor= vendorTable.create(vendor);
         return newVendor;
    }
    public void deleteVendor(String Id) throws AirtableException {
        vendorTable.destroy(Id);
    }
    //entity wrap
    public Vendor findVendor(String Id) throws AirtableException {
        return vendorTable.find(Id);
    }
    public Vendor update(Vendor vendor) throws InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {
        return vendorTable.update(vendor);

    }
    public Vendor findVendorByAccountId(String accountId) throws AirtableException {
        //inaccurate

        return vendorTable.find(accountId);
    }

    public Vendor findVendorByEmail(Vendor vendor) throws AirtableException {
        return vendorTable.find(vendor.getEmail());
    }

}
