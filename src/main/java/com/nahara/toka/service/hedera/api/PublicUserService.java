package com.nahara.toka.service.hedera.api;



import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.nahara.toka.model.PublicUser;
import com.nahara.toka.model.PublicVendor;
import com.nahara.toka.model.Vendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;


import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;

public class PublicUserService {


    public PublicUserService() throws AirtableException {
    }


    public void deleteUser(String Id) throws AirtableException {
        //vendorPublicTable.destroy(Id);
    }

    //entity wrap
    public static PublicUser findUser(String Id) throws AirtableException {
        Airtable airtable = new Airtable().configure("keykefT9YD5rhkuFg");
        Base base = airtable.base("appg4L9uWpNhonYHS");
        Table<PublicUser> userPublicTable = base.table("Users", PublicUser.class);
        return userPublicTable.find(Id);
    }
}