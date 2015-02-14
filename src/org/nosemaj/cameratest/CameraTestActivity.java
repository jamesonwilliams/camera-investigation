package org.nosemaj.cameratest;

import android.app.Activity;
import android.media.CamcorderProfile;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import java.util.HashMap;

public class CameraTestActivity extends Activity {
    private static final String TAG = CameraTestActivity.class.getName();
    HashMap<String, String> mBuildMap = new HashMap<String, String>();
    HashMap<Integer, String> mProfileMap = new HashMap<Integer, String>();
    private String mResult = "";

    private void setupBuildMap() {
        mBuildMap.put("BOARD", Build.BOARD);
        mBuildMap.put("BRAND", Build.BRAND);
        mBuildMap.put("CPU_ABI2", Build.CPU_ABI2);
        mBuildMap.put("CPU_ABI", Build.CPU_ABI);
        mBuildMap.put("DEVICE", Build.DEVICE);
        mBuildMap.put("DISPLAY", Build.DISPLAY);
        mBuildMap.put("FINGERPRINT", Build.FINGERPRINT);
        mBuildMap.put("HARDWARE", Build.HARDWARE);
        mBuildMap.put("HOST", Build.HOST);
        mBuildMap.put("ID", Build.ID);
        mBuildMap.put("MANUFACTURER", Build.MANUFACTURER);
        mBuildMap.put("MODEL", Build.MODEL);
        mBuildMap.put("PRODUCT", Build.PRODUCT);
        mBuildMap.put("RADIO", Build.RADIO);
        mBuildMap.put("SERIAL", Build.SERIAL);
        mBuildMap.put("TAGS", Build.TAGS);
        mBuildMap.put("TIME", "" + Build.TIME);
        mBuildMap.put("TYPE", Build.TYPE);
        mBuildMap.put("USER", Build.USER);
        mBuildMap.put("VERSION.CODENAME", Build.VERSION.CODENAME);
        mBuildMap.put("VERSION.INCREMENTAL", Build.VERSION.INCREMENTAL);
        mBuildMap.put("VERSION.RELEASE", Build.VERSION.RELEASE);
        mBuildMap.put("VERSION.SDK", Build.VERSION.SDK);
    }

    private void setupProfileMap() {
        mProfileMap.put(CamcorderProfile.QUALITY_1080P, "QUALITY_1080P");
        mProfileMap.put(CamcorderProfile.QUALITY_2160P, "QUALITY_2160P");
        mProfileMap.put(CamcorderProfile.QUALITY_480P, "QUALITY_480P");
        mProfileMap.put(CamcorderProfile.QUALITY_720P, "QUALITY_720P");
        mProfileMap.put(CamcorderProfile.QUALITY_CIF, "QUALITY_CIF");
        mProfileMap.put(CamcorderProfile.QUALITY_HIGH, "QUALITY_HIGH");
        mProfileMap.put(CamcorderProfile.QUALITY_HIGH_SPEED_1080P, "QUALITY_HIGH_SPEED_1080P");
        mProfileMap.put(CamcorderProfile.QUALITY_HIGH_SPEED_2160P, "QUALITY_HIGH_SPEED_2160P");
        mProfileMap.put(CamcorderProfile.QUALITY_HIGH_SPEED_480P, "QUALITY_HIGH_SPEED_480P");
        mProfileMap.put(CamcorderProfile.QUALITY_HIGH_SPEED_720P, "QUALITY_HIGH_SPEED_720P");
        mProfileMap.put(CamcorderProfile.QUALITY_HIGH_SPEED_HIGH, "QUALITY_HIGH_SPEED_HIGH");
        mProfileMap.put(CamcorderProfile.QUALITY_HIGH_SPEED_LOW, "QUALITY_HIGH_SPEED_LOW");
        mProfileMap.put(CamcorderProfile.QUALITY_LOW, "QUALITY_LOW");
        mProfileMap.put(CamcorderProfile.QUALITY_QCIF, "QUALITY_QCIF");
        mProfileMap.put(CamcorderProfile.QUALITY_QVGA, "QUALITY_QVGA");
        mProfileMap.put(CamcorderProfile.QUALITY_TIME_LAPSE_1080P, "QUALITY_TIME_LAPSE_1080P");
        mProfileMap.put(CamcorderProfile.QUALITY_TIME_LAPSE_2160P, "QUALITY_TIME_LAPSE_2160P");
        mProfileMap.put(CamcorderProfile.QUALITY_TIME_LAPSE_480P, "QUALITY_TIME_LAPSE_480P");
        mProfileMap.put(CamcorderProfile.QUALITY_TIME_LAPSE_720P, "QUALITY_TIME_LAPSE_720P");
        mProfileMap.put(CamcorderProfile.QUALITY_TIME_LAPSE_CIF, "QUALITY_TIME_LAPSE_CIF");
        mProfileMap.put(CamcorderProfile.QUALITY_TIME_LAPSE_HIGH, "QUALITY_TIME_LAPSE_HIGH");
        mProfileMap.put(CamcorderProfile.QUALITY_TIME_LAPSE_LOW, "QUALITY_TIME_LAPSE_LOW");
        mProfileMap.put(CamcorderProfile.QUALITY_TIME_LAPSE_QCIF, "QUALITY_TIME_LAPSE_QCIF");
        mProfileMap.put(CamcorderProfile.QUALITY_TIME_LAPSE_QVGA, "QUALITY_TIME_LAPSE_QVGA");
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupBuildMap();
        setupProfileMap();
    }

    public void onResume() {
        super.onResume();

        mResult = "Attributes:\n";

        for (String key : mBuildMap.keySet()) {
            mResult += key + ": " + mBuildMap.get(key) + "\n";
        }

        mResult += "Camcorder Profiles:\n";

        for (Integer profile : mProfileMap.keySet()) {
            mResult += mProfileMap.get(profile) + ": " + CamcorderProfile.hasProfile(profile) + "\n";
        }

        TextView mTextView = (TextView) findViewById(R.id.text_view);
        mTextView.setText(mResult);

        Log.d(TAG, mResult);
    }

    public void onButtonClick(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "jameson.h.williams@intel.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Camera Field Data");
        intent.putExtra(Intent.EXTRA_TEXT, mResult);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }
}

