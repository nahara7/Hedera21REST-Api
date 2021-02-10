package com.nahara.toka.service.hedera.api;
import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.nahara.toka.model.PublicUser;
import com.nahara.toka.model.PublicVendor;
import com.nahara.toka.model.User;
import com.nahara.toka.model.Vendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;

public class PublicUserService {

    private static final String TOKAAIR = ""+System.getenv("TOKAAIR");
    private static final String TOKABASE = ""+System.getenv("TOKABASE");
    private static final String JVT=""+ System.getenv("JVT_TOKEN_ID");

    Airtable airtable = new Airtable().configure(TOKAAIR);
    Base base = airtable.base(TOKABASE);
    public Table<PublicUser> userPublicTable = base.table("Users", PublicUser.class);

    public PublicUserService() throws AirtableException {
    }


    public void deleteUser(String Id) throws AirtableException {
             userPublicTable.destroy(Id);
    }

    public PublicUser findUser(String Id) throws AirtableException {
        return userPublicTable.find(Id);
    }
}