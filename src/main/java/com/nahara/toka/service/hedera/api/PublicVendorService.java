package com.nahara.toka.service.hedera.api;


import com.nahara.toka.model.PublicUser;
import com.nahara.toka.model.PublicVendor;
import com.nahara.toka.model.Vendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.springframework.stereotype.Service;

@Service
public class PublicVendorService {

    private static final String TOKAAIR = ""+System.getenv("TOKAAIR");
    private static final String TOKABASE = ""+System.getenv("TOKABASE");
    private static final String JVT=""+ System.getenv("JVT_TOKEN_ID");

    Airtable airtable = new Airtable().configure(TOKAAIR);
    Base base = airtable.base(TOKABASE);
    public Table<PublicVendor> vendorPublicTable = base.table("Vendors", PublicVendor.class);



    public PublicVendorService() throws AirtableException {
    }


    public void deleteVendor(String Id) throws AirtableException {
        //vendorPublicTable.destroy(Id);
    }

    public  PublicVendor findVendor(String Id) throws AirtableException {

        return vendorPublicTable.find(Id);
    }
}