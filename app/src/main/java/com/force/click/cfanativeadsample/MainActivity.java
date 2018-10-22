package com.force.click.cfanativeadsample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clickforce.ad.CFNativeAd;
import com.clickforce.ad.Listener.AdNativeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CFNativeAd nativeAd;
    private LinearLayout adView;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nativeAd = new CFNativeAd(this);
        nativeAd.setAdID("5229");
        nativeAd.setOnNativeListener(new AdNativeListener() {
            @Override
            public void onNativeAdResult(CFNativeAd cfNativeAd) {

                LinearLayout adContainer = (LinearLayout)findViewById(R.id.native_ad_container);
                LayoutInflater inflater = LayoutInflater.from(context);
                adView = (LinearLayout)inflater.inflate(R.layout.native_ad_layout,adContainer,false);
                adContainer.addView(adView);

                TextView nativeAdTitle = (TextView)adView.findViewById(R.id.native_ad_title);
                TextView nativeAdContent = (TextView)adView.findViewById(R.id.native_ad_content);
                TextView nativeAdvertiser = (TextView)adView.findViewById(R.id.native_ad_advertiser);
                Button nativeAdButtonText = (Button)adView.findViewById(R.id.native_ad_buttonText);
                ImageView nativeImage = (ImageView)adView.findViewById(R.id.native_ad_coverimage);

                nativeAdTitle.setText(nativeAd.getAdTitle());
                nativeAdContent.setText(nativeAd.getAdContent());
                nativeAdvertiser.setText(nativeAd.getAdvertiser());
                nativeAdButtonText.setText(nativeAd.getAdButtonText());

                nativeAd.downloadAndDisplayImage(nativeAd.getAdCoverImage(),nativeImage);

                List<View> clickListView = new ArrayList<>();
                clickListView.add(nativeAdTitle);
                clickListView.add(nativeAdButtonText);
                cfNativeAd.registerViewForInteraction(clickListView);

                cfNativeAd.setActiveViewLog(adContainer);
            }

            @Override
            public void onNativeAdClick() {

            }

            @Override
            public void onNativeAdOnFailed() {

            }
        });
    }
}
