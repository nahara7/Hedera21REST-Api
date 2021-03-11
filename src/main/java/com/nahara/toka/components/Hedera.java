package com.nahara.toka.components;

import com.hedera.hashgraph.sdk.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

@Component("Hedera")
public class Hedera {
//creates any token

    private static Logger log = LoggerFactory.getLogger(Hedera.class);

    @Autowired
    private Environment env;


    private static final String HEDERA_API_ACCOUNT_ID = ""+System.getenv("ACCOUNT_ID");
    private static final String HEDERA_API_PRIVATE_KEY = ""+System.getenv("PRIVATE_KEY");

    public Hedera() { }

    public TokenId createHederaToken(String tokenName, String tokenSymbol,
                                     String initialSupply) throws TimeoutException, HederaPreCheckStatusException,
            HederaReceiptStatusException {

        log.info("{}","createHederaToken()");

        AccountId myAccountId = AccountId.fromString(HEDERA_API_ACCOUNT_ID);
        PrivateKey myPrivateKey = PrivateKey.fromString(HEDERA_API_PRIVATE_KEY);

        Client client = Client.forTestnet();
        client.setOperator(myAccountId, myPrivateKey);

        PrivateKey adminKey = PrivateKey.generate();
        PrivateKey supplyKey= PrivateKey.generate();

        TokenCreateTransaction transaction = new TokenCreateTransaction()
                .setTokenName(tokenName)
                .setTokenSymbol(tokenSymbol)
                .setTreasuryAccountId(myAccountId)
                .setInitialSupply(Long.parseLong(initialSupply))
                .setMaxTransactionFee(new Hbar(12))
                .setAdminKey(adminKey.getPublicKey())
                .setSupplyKey(supplyKey.getPublicKey());

        TransactionResponse txResponse =
                transaction
                        .freezeWith(client)
                        .sign(adminKey)
                        .sign(myPrivateKey)
                        .execute(client);

        TransactionReceipt receipt = txResponse.getReceipt(client);
        TokenId tokenId = receipt.tokenId;

        return receipt.tokenId;
    }
}
