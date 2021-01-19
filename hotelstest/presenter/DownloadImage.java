package com.anadolstudio.hotelstest.presenter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.anadolstudio.hotelstest.model.Hotel;
import com.anadolstudio.hotelstest.model.Server;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class DownloadImage {
    private static final int COLOR_BLUE_DARK = 0xFF004064;
    private static final int COLOR_BLUE_LIGHT = 0xFF0080E1;
    public static final int[] PLACEHOLDER_COLORS = new int[]{Color.BLACK, COLOR_BLUE_DARK, COLOR_BLUE_LIGHT};

    public static void download(Hotel hotel, ImageView imageView) {
        Drawable placeholder = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, PLACEHOLDER_COLORS);
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView.setImageBitmap(DownloadImage.deleteFrame(bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                imageView.setImageDrawable(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                imageView.setImageDrawable(placeHolderDrawable);
            }
        };
        imageView.setTag(target);
        Picasso.get()
                .load(Uri.parse(Server.BASE_IMAGE_URl + hotel.getImage()))
                .error(placeholder)
                .placeholder(placeholder)
                .into(target);
    }


    public static Bitmap deleteFrame(Bitmap oldBitmap) {
        int w = oldBitmap.getWidth() - 2;
        int h = oldBitmap.getHeight() - 2;
        int[] pixels = new int[w * h];
        oldBitmap.getPixels(pixels, 0, w, 1, 1, w, h);

        Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        newBitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return newBitmap;
    }
}
