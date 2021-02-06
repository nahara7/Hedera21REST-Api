package com.nahara.toka.service.hedera.api;


import com.nahara.toka.model.PublicVendor;
import com.nahara.toka.model.Vendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;



public class PublicVendorService {


    public PublicVendorService() throws AirtableException {
    }


    public void deleteVendor(String Id) throws AirtableException {
        //vendorPublicTable.destroy(Id);
    }

    //entity wrap
    public static PublicVendor findVendor(String Id) throws AirtableException {
        Airtable airtable = new Airtable().configure("keykefT9YD5rhkuFg");
        Base base = airtable.base("appg4L9uWpNhonYHS");
        Table<PublicVendor> vendorPublicTable = base.table("Vendors", PublicVendor.class);
        return vendorPublicTable.find(Id);
    }
}