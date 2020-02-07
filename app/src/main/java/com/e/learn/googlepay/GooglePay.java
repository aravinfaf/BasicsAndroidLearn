package com.e.learn.googlepay;

import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.e.learn.R;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;

public class GooglePay extends AppCompatActivity {

    Button send;
    PaymentsClient paymentsClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_pay);

        send=findViewById(R.id.send);

        Wallet.WalletOptions walletOptions=new Wallet.WalletOptions.Builder()
                .setEnvironment(WalletConstants.ENVIRONMENT_TEST).build();

        paymentsClient=Wallet.getPaymentsClient(GooglePay.this,walletOptions);

    }
}
