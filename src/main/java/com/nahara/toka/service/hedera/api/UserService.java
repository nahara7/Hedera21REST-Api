package com.nahara.toka.service.hedera.api;


import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.nahara.toka.model.User;
import com.nahara.toka.model.Vendor;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;


import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;

public class UserService {
    Airtable airtable= new Airtable().configure("keykefT9YD5rhkuFg");
    Base base = airtable.base("appg4L9uWpNhonYHS");
    Table<User> userTable = base.table("Users", User.class);

    public UserService() throws AirtableException {
    }

    public User createUser(User user) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {

        User newUser= userTable.create(user);
        return newUser;
    }

    //entity wrap
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
