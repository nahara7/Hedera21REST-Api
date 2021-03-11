package com.nahara.toka.service.hedera.api;


import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.nahara.toka.model.PublicVendor;
import com.nahara.toka.model.Vendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.springframework.stereotype.Service;


import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;
@Service
public class VendorService {
    private static final String TOKAAIR = ""+System.getenv("TOKAAIR");
    private static final String TOKABASE = ""+System.getenv("TOKABASE");
    private static final String JVT=""+ System.getenv("JVT_TOKEN_ID");

    Airtable airtable = new Airtable().configure(TOKAAIR);
    Base base = airtable.base(TOKABASE);
    public Table<Vendor> vendorTable = base.table("Vendors", Vendor.class);


    public VendorService() throws AirtableException {
    }

    public Vendor createVendor(Vendor vendor) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {

         Vendor newVendor= vendorTable.create(vendor);
         return newVendor;
    }
    public void deleteVendor(String Id) throws AirtableException {
        vendorTable.destroy(Id);
    }
    public Vendor findVendor(String Id) throws AirtableException {
        return vendorTable.find(Id);
    }
    public Vendor update(Vendor vendor) throws InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {
        return vendorTable.update(vendor);

    }
}
