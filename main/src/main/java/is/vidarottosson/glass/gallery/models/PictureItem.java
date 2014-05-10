package is.vidarottosson.glass.gallery.models;

//  Created by Viddi on 12/8/13.

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;

public class PictureItem extends FileItem implements Parcelable {

	public static final String TAG = PictureItem.class.getSimpleName();

    public static final String EXTENSION_PNG = ".png";
    public static final String EXTENSION_JPG = ".jpg";
    public static final String EXTENSION_JPEG = ".jpeg";
    public static final String EXTENSION_BMP = ".bmp";

	public static final String EXTENSIONS_PICTURE[] = {EXTENSION_PNG, EXTENSION_JPG, EXTENSION_JPEG, EXTENSION_BMP};

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getPath());
        out.writeString(getName());
    }

    public static final Parcelable.Creator<PictureItem> CREATOR = new Parcelable.Creator<PictureItem>() {
        public PictureItem createFromParcel(Parcel in) {
            return new PictureItem(in);
        }

        public PictureItem[] newArray(int size) {
            return new PictureItem[size];
        }
    };

    private PictureItem(Parcel in) {
        super(in.readString());
    }

	public PictureItem(File file) {
		super(file);
	}

	public Bitmap createBitmap(Context context) {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		int width = size.x;
		int height = size.y;

		Bitmap tempBitmap = BitmapFactory.decodeFile(getPath());

		// Screen dimensions are 640x360

		Bitmap bitmap = Bitmap.createScaledBitmap(tempBitmap, width, height, false);
		tempBitmap.recycle();

		Log.i(TAG, "Created Bitmap");

        return bitmap;
	}

}
