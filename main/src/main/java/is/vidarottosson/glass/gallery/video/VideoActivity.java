package is.vidarottosson.glass.gallery.video;

//  Created by jonstaff on 1/17/14.

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.TextureView;

import com.google.android.glass.widget.CardScrollView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.FileItem;
import is.vidarottosson.glass.gallery.models.VideoItem;
import is.vidarottosson.glass.gallery.util.Utility;

public class VideoActivity extends Activity {
	public static final String TAG = VideoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);
    }
}