package com.ahay.ambacsi.ui.outsides;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ahay.ambacsi.ConnectivityReceiver;
import com.ahay.ambacsi.R;
import com.ahay.ambacsi.ui.medicals.MainActivity;

public class SplashActivity extends AppCompatActivity {
    boolean isLoggedIn = false;
    boolean isOnline = false;
    // DailyActivityItem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /**
         * Showing splash screen while making network calls to download necessary
         * data before launching the app Will use AsyncTask to make http call
         */
        new PrefetchData().execute();
    }

    /**
     * Async Task to make http call
     */
    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Before making http calls
            isOnline = ConnectivityReceiver.isConnected();
            isLoggedIn = checkLoggedIn();
        }

        private boolean checkLoggedIn() {
            // TODO check logged in offline and online
            return false;
        }

        @Override
        protected Void doInBackground(Void... params) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */
            if (isOnline && isLoggedIn) {
                loadNewOnlineData();
            }
            return null;
        }

        private void loadNewOnlineData() {
            // TODO load new online data
            // new notification
            // new event request
            // etc.
            // save to SQLite DB
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // After completing http call
            // will close this activity and launch corresponding activity
            if (isLoggedIn) {
                // Notify user if user is offline
                if (!isOnline) {
                    Toast.makeText(SplashActivity.this, R.string.splash_offline, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SplashActivity.this, R.string.splash_welcome, Toast.LENGTH_LONG).show();
                }

                // TODO put extra for main calendar
                // Go to main activity (main activity)
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else if(isOnline) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            } else {
                Toast.makeText(SplashActivity.this, R.string.splash_network_error, Toast.LENGTH_LONG).show();
            }

            // Close this activity
            finish();
        }
    }
}
