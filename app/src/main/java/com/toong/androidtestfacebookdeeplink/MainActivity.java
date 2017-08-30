package com.toong.androidtestfacebookdeeplink;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Intent intent = getIntent();
            String action = intent.getAction();
            Uri data = intent.getData();

            Log.i(TAG, ""+action);
            Log.i(TAG, ""+data);

            Log.i(TAG, "scheme: "+data.getScheme());
            Log.i(TAG, "host: "+data.getHost());
            Log.i(TAG, "lastPathSegment: "+data.getLastPathSegment());

            Map<String, String> map = getQueryMap(data.getLastPathSegment());
            for (String key : map.keySet()) {
                Log.i(TAG, "key "+key);
                Log.i(TAG, "value "+map.get(key));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Map<String, String> getQueryMap(String query){
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params)
        {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
}
