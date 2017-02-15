package com.umpay.huafubao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.umf.pay.sdk.UmfPay;
import com.umf.pay.sdk.UmfRequest;


public class MainActivity extends Activity implements View.OnClickListener {

    private EditText merView;
    private EditText merCustView;
    private EditText tradeNoView;
    private EditText amountView;
    private TextView logView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        merView = (EditText) findViewById(R.id.merView);
        merCustView = (EditText) findViewById(R.id.merCustView);
        tradeNoView = (EditText) findViewById(R.id.tradeNoView);
        amountView = (EditText) findViewById(R.id.amountView);

        logView = (TextView) findViewById(R.id.logView);
        logView.setMovementMethod(ScrollingMovementMethod.getInstance());

        findViewById(R.id.sendView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendView) {
            doPayOrder();
        }
    }


    //订单支付
    public void doPayOrder() {
        UmfRequest request = new UmfRequest();
        request.merId = merView.getText().toString();
        request.merCustId = merCustView.getText().toString();
        request.tradeNo = tradeNoView.getText().toString();
        request.amount = amountView.getText().toString();

        UmfPay.startPay(this, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UmfPay.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(UmfPay.RESULT_MSG);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            } else {
                String msg = data.getStringExtra(UmfPay.RESULT_MSG);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        }

        if (data != null) {
            String payCode = data.getStringExtra(UmfPay.RESULT_CODE);
            String payMsg = data.getStringExtra(UmfPay.RESULT_MSG);
            logView.setText(String.format("payResult:%s\npayMsg:%s", payCode, payMsg));
        }
    }
}
