package com.twilio.ipmquickstart_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.twilio.ipmessaging.Channel;
import com.twilio.ipmessaging.IPMessagingClientListener;
import com.twilio.ipmessaging.TwilioIPMessagingClient;
import com.twilio.ipmessaging.TwilioIPMessagingSDK;
import com.twilio.ipmessaging.Constants.InitListener;
import android.app.PendingIntent;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements IPMessagingClientListener {


    private TwilioIPMessagingClient ipMessagingClient;
    private ProgressDialog progressDialog;
    private String capabilityToken = null;
    private Context context;


    //[0] System Methods
    public MainActivity() {
        super();
        Log.v("Fyu", "other is called at some point");

    }


    public MainActivity(Context context) {
        super();
        Log.v("Fyu", "This is called at some point");
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("Log:", "App has launched");
       new GetCapabilityTokenAsyncTask().execute("https://twilio-ip-messaging-token.herokuapp.com/token?device=7364790c744e8fe3");
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


    //[2] Authentication methods


    public interface LoginListener {
        public void onLoginStarted();

        public void onLoginFinished();

        public void onLoginError(String errorMessage);

        public void onLogoutFinished();
    }


    private class GetCapabilityTokenAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
              Log.v("Retrieved token:", capabilityToken);
            TwilioIPMessagingSDK.initializeSDK(getApplicationContext(), new InitListener() {

                @Override
                public void onInitialized() {
                    ipMessagingClient = TwilioIPMessagingSDK.createIPMessagingClientWithToken(capabilityToken, MainActivity.this);
                    if (ipMessagingClient != null) {
                        Log.v("Test", "Dude you init'd the client!");
                    } else {
                        Log.v("nu", "jdshufuisdf");
                    }
                }

                @Override
                public void onError(Exception error) {
                    Log.v("YO", "YOU errror IT" + error);

                }
            });
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
                capabilityToken = HttpHelper.httpGet(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return capabilityToken;
        }
    }


}

