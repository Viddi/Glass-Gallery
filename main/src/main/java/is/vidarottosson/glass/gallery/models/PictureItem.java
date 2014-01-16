package is.vidarottosson.glass.gallery.models;
//  Created by Viddi on 12/8/13.

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class PictureItem extends FileItem {
    public static final String TAG = PictureItem.class.getSimpleName();

    private Bitmap bitmap;

    public PictureItem(String path, String name) {
        super(Type.PICTURE.ordinal(), path, name);
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

        Log.i(TAG, "Created Bitmap");
    }

    public void destroyBitmap() {
        bitmap.recycle();
        bitmap = null;
        Log.i(TAG, "Recycled Bitmap");
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

}
