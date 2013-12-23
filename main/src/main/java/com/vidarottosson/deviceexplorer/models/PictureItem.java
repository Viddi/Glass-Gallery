package com.vidarottosson.deviceexplorer.models;
//  Created by Viddi on 12/8/13.

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.view.Display;
import android.view.WindowManager;

public class PictureItem extends FileItem {

    private Bitmap bitmap;

    public PictureItem(String path, String name, Context context) {
        super(Type.PICTURE.ordinal(), path, name);

        // TODO: Only load an image if will be displayed
        createBitmap(context);
    }

    public void createBitmap(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        Bitmap tempBitmap = BitmapFactory.decodeFile(getPath());

        // Screen dimensions are 640x360

        bitmap = Bitmap.createScaledBitmap(tempBitmap, width, height, false);

        tempBitmap.recycle();
    }

    public void destroyBitmap() {
        bitmap.recycle();
        bitmap = null;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public boolean isLoaded() {
        if(bitmap != null) {
            return true;
        }

        return false;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private class AsyncBitmapLoader extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }

    }

    // TODO: Create callback listeners for images


}
