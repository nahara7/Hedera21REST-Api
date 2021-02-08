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
    Airtable airtable = new Airtable().configure("keykefT9YD5rhkuFg");
    Base base = airtable.base("appg4L9uWpNhonYHS");
    Table<PublicUser> userPublicTable = base.table("Users", PublicUser.class);

    public PublicUserService() throws AirtableException {
    }


    public void deleteUser(String Id) throws AirtableException {
             userPublicTable.destroy(Id);
    }

    //entity wrap
    public PublicUser findUser(String Id) throws AirtableException {
        return userPublicTable.find(Id);
    }
}