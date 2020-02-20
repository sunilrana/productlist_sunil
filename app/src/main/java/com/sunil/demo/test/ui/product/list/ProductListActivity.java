package com.sunil.demo.test.ui.product.list;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sunil.demo.test.R;

public class ProductListActivity extends AppCompatActivity {



    Toolbar mTopToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);



        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);

        ActionBar bar = this.getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
         //   bar.setBackgroundDrawable(new ColorDrawable(0xff00DDED));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

/*

   @Override
    public void pause() {
        if (googleImaDAIPlayer.getIsAdsPlaying()) {
            if (googleImaDAIPlayer.getIsEnableCellularDataDialogShowing() || googleImaDAIPlayer.getIsIncomingPhoneCallShowing()) {
                googleImaDAIPlayer.pauseAd();
            }
            else {
                if (googleImaDAIPlayer.getIsThisAdPaused()) {
                    if (googleImaDAIPlayer.getIsIncomingPhoneCallShowing()) {
                        googleImaDAIPlayer.pauseAd();
                    }
                    else {
                        googleImaDAIPlayer.resumeAd();
                    }
                }
                else {
                    googleImaDAIPlayer.pauseAd();
                }
            }
        }
        else {
            qpPlaybackPlayer.pause();
        }
    }

    @Override
    public void resume() {
        if (googleImaDAIPlayer.getIsAdsPlaying()) {
            if (googleImaDAIPlayer.getIsThisAdPaused()) {
                if (!googleImaDAIPlayer.getIsJustLostNetworkConnection()) {
                    if (!networkCheckerGateway.isInternetConnection() && googleImaDAIPlayer.getIsWifiNetworkConnectionActive()) {

                            googleImaDAIPlayer.setIsJustLostNetworkConnection(false);
                            googleImaDAIPlayer.setIsWifiNetworkConnectionActive(false);
                            googleImaDAIPlayer.setIsLateLostNetworkConnectionFeedback(true);

                    }
                    googleImaDAIPlayer.resumeAd();
                }
                else {
                    googleImaDAIPlayer.setIsJustLostNetworkConnection(false);
                }
            }
            else {
                if (googleImaDAIPlayer.getIsJustLostNetworkConnection()) {
                    googleImaDAIPlayer.setIsJustLostNetworkConnection(false);
                }
                else {
                    googleImaDAIPlayer.pauseAd();
                }
            }
        }
        else {
            qpPlaybackPlayer.resume();
        }
    }*/
}
