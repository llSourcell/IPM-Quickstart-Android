package com.twilio.ipmquickstart_android;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.twilio.ipmessaging.Channel;
import com.twilio.ipmessaging.IPMessagingClientListener;
import com.twilio.ipmessaging.TwilioIPMessagingClient;
import com.twilio.ipmessaging.TwilioIPMessagingSDK;
import com.twilio.ipmessaging.Constants.InitListener;

public class MainActivity extends AppCompatActivity implements IPMessagingClientListener {


    private TwilioIPMessagingClient ipMessagingClient;
    private ProgressDialog progressDialog;
    private String capabilityToken = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // new GetCapabilityTokenAsyncTask().execute("https://twilio-ip-messaging-token.herokuapp.com/token?device=7364790c744e8fe3");
    }

    //[1] IPM Client Methods

    @Override
    public void onChannelHistoryLoaded(Channel channel) {

    }

    @Override
    public void onAttributesChange(String string) {

    }

    @Override
    public void onError(int code, String message) {

    }

    @Override
    public void onChannelAdd(Channel channel) {

    }

    @Override
    public void onChannelChange(Channel channel) {

    }

    @Override
    public void onChannelDelete(Channel channel) {

    }


    //[2] AUthentication methods

    private class GetCapabilityTokenAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ipMessagingClient = TwilioIPMessagingSDK.createIPMessagingClientWithToken(capabilityToken, MainActivity.this);
            if(ipMessagingClient != null) {
                Log.v("Test", "test");

            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity.this.progressDialog = ProgressDialog.show(MainActivity.this, "",
                    "Connecting to #general channel", true);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                     //   capabilityToken = HttpHelper.httpGet(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return capabilityToken;
        }
    }


}

