package com.nahara.toka.service.hedera.api;

import com.hedera.hashgraph.sdk.HederaPreCheckStatusException;
import com.hedera.hashgraph.sdk.HederaReceiptStatusException;
import com.nahara.toka.model.Account;
import com.nahara.toka.model.User;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.Base;
import com.sybit.airtable.Table;
import com.sybit.airtable.exception.AirtableException;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeoutException;

public class AccountService {
    Airtable airtable = new Airtable().configure("keykefT9YD5rhkuFg");
    Base base = airtable.base("appg4L9uWpNhonYHS");
    Table<Account> accountTable = base.table("Accounts", Account.class);

    public AccountService() throws AirtableException {
    }

    public Account createAccount(Account account) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException, InvocationTargetException, AirtableException, NoSuchMethodException, IllegalAccessException {

        Account newAccount = accountTable.create(account);
        return newAccount;
    }
    public void findAccount(String username) throws AirtableException {
        //return accountTable.find(username);
    }
}