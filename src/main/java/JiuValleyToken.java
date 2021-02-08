/*import com.hedera.hashgraph.sdk.*;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashgraph.sdk.proto.Response;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
//import com.hedera.hashgraph.sdk.*;
//import com.hedera.hashgraph.sdk.MirrorClient;
//import com.hedera.hashgraph.sdk.*;
//import java.util.Objects;

import java.util.concurrent.TimeoutException;
//import java.util.concurrent.TimeoutException;

public class JiuValleyToken {
    public static void main(String[] args) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        createToken();
    }
    public static void createToken() throws HederaReceiptStatusException,
            TimeoutException, HederaPreCheckStatusException {

        //Grab your Hedera testnet account ID and private key
        AccountId myAccountId = AccountId.fromString("0.0.215019");
        PrivateKey myPrivateKey = PrivateKey.fromString("302e020100300506032b657004220420718a3675360329446195eb7ae3009c3d477bebc91237659ea62afccc7aed5148");

        //Create your Hedera testnet client
        Client client = Client.forTestnet();
        client.setOperator(myAccountId, myPrivateKey);
        //client.setMaxTransactionFee(new Hbar(5));

        // Generate a new key pair
        PrivateKey adminKey = PrivateKey.generate();

        //Create the transaction
       /* TokenCreateTransaction transaction = new TokenCreateTransaction()
                .setName("Jiu Valley Token")
                .setSymbol("JVT")
                .setInitialSupply(50000000)
                .setTreasury(myAccountId)
                .setDecimals(2)
                //.setInitialSupply(5000)
                .setMaxTransactionFee(new Hbar(12))
                .setAdminKey(adminKey.getPublicKey());

        //Build the unsigned transaction, sign with admin private key of the token,
        // sign with the token treasury private key, submit the transaction to a Hedera network
        TransactionResponse txResponse = transaction.freezeWith(client).sign(adminKey).sign(myPrivateKey).execute(client);
        OkHttpClient client2 = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.dragonglass.me/hedera/api/transactions/{transactionID}")
                .method("GET", null)
                .addHeader("ad418e056319da3959b8dd68116bf526d8819680", "e224b0e9-b721-3ccb-b24b-8c2659e3fb33")
                .build();
        Response response = client2.newCall(request).execute();
        //Request the receipt of the transaction
        TransactionReceipt receipt = txResponse.getReceipt(client);
        System.out.println(receipt);
        //Get the token ID from the receipt
       //account Id??
      /*  AccountId tokenId = receipt.accountId;
        System.out.println("The new token ID is " + tokenId);
        System.out.println("Name:"+  transaction.getName());
        System.out.println("Symbol" + transaction.getSymbol());
        System.out.println("Decimals" + transaction.getDecimals());
        System.out.println("Initial Supply" + transaction.getInitialSupply());
        //System.out.println(transaction.getDecimals());
        System.out.println("treasury:    {");
                System.out.println(transaction.getTreasury().num);
        System.out.println(transaction.getTreasury().shard);
        System.out.println(transaction.getTreasury().realm);

        //transaction.getTokenSymbol(),
        //transaction.getDecimals());//transaction.getInitialSupply());*/

