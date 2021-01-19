package com.anadolstudio.hotelstest.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class GoogleMaps {

    public static void openMaps(Context context, double lat, double lon, String address) {
        Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lon + "?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }
    }
}
