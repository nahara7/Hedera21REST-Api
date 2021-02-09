package com.nahara.toka.model;

import com.nahara.toka.service.hedera.api.TransactionAsyncService;
import com.nahara.toka.service.hedera.api.UserService;
import com.nahara.toka.service.hedera.api.VendorService;
import com.sybit.airtable.exception.AirtableException;

public class CashBack {
    private String userId;
    private String vendorId;
    private long total;

    public CashBack(){}

    public CashBack(String userId, String vendorId, long total) throws AirtableException {
        this.userId=userId;
        this.vendorId=vendorId;
        this.total=total;


    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
