package com.nahara.toka.service.hedera.api;


import com.google.gson.annotations.SerializedName;
import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.nahara.toka.model.*;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;
@Service
public class UserService {
    private static final String TOKAAIR = ""+System.getenv("TOKAAIR");
    private static final String TOKABASE = ""+System.getenv("TOKABASE");
    private static final String JVT=""+ System.getenv("JVT_TOKEN_ID");

    Airtable airtable = new Airtable().configure(TOKAAIR);
    Base base = airtable.base(TOKABASE);
    Table<User> userTable = base.table("Users", User.class);

    public UserService() throws AirtableException {
    }

    public User createUser(User user) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {

        User newUser= userTable.create(user);
        return newUser;
    }
    public User findUser(String Id) throws AirtableException {
        return userTable.find(Id);
    }
    public void deleteUser(String Id) throws AirtableException {
        userTable.destroy(Id);
    }

    public User updateUser(User user) throws InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {
        return userTable.update(user);
    }

}
