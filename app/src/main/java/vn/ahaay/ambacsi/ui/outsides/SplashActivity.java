package vn.ahaay.ambacsi.ui.outsides;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAccount;
import vn.ahaay.ambacsi.api.ambacsi.auth.AmBacSiAuth;
import vn.ahaay.ambacsi.api.sharedpreference.UserDataManager;
import vn.ahaay.ambacsi.helper.ConnectivityReceiver;
import vn.ahaay.ambacsi.ui.medicals.HomeActivity;
import vn.ahaay.ambacsi.ui.profiles.CreateProfileActivity;

public class SplashActivity extends AppCompatActivity {
    boolean isLoggedIn = false;
    boolean isOnline = false;
    // DailyActivityItem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vn.ahaay.ambacsi.R.layout.activity_splash);

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

            isLoggedIn = new UserDataManager(SplashActivity.this).isLoggedIn();
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
                updateData();
            }
            return null;
        }

        private void updateData() {
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
                // get login account to AmBacSiAuth
                UserDataManager __userDataManager = new UserDataManager(SplashActivity.this);
                UserDataManager.LoggedInAccount __account = __userDataManager.getLoggedInAccount();
                if (__account != null) {
                    AmBacSiAuth.setAccount(new AmBacSiAccount(
                            __account.getUId(),
                            __account.getUserName(),
                            __account.getEmail(),
                            __account.getToken()
                    ));
                }

                if (new UserDataManager(SplashActivity.this).isHaveProfile()) {
                    // Notify user if user is offline
                    if (!isOnline) {
                        Toast.makeText(SplashActivity.this, vn.ahaay.ambacsi.R.string.splash_offline, Toast.LENGTH_LONG).show();
                    }

                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                } else {
                    // Notify user if user is offline
                    if (!isOnline) {
                        // TODO start setting for result
                        Toast.makeText(SplashActivity.this, vn.ahaay.ambacsi.R.string.splash_network_error, Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(new Intent(SplashActivity.this, CreateProfileActivity.class));
                    }
                }
            } else if(isOnline) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            } else {
                // TODO start setting for result
                Toast.makeText(SplashActivity.this, vn.ahaay.ambacsi.R.string.splash_network_error, Toast.LENGTH_LONG).show();
            }

            // Close this activity
            finish();
        }
    }
}
