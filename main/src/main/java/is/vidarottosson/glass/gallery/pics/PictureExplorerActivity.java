package is.vidarottosson.glass.gallery.pics;

//  Created by Viddi on 12/6/13.

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.glass.widget.CardScrollView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import is.vidarottosson.glass.gallery.models.FileItem;
import is.vidarottosson.glass.gallery.models.PictureItem;
import is.vidarottosson.glass.gallery.util.Utility;

public class PictureExplorerActivity extends Activity {

	public static final String TAG = PictureExplorerActivity.class.getSimpleName();

	private CardScrollView mView;
	private PicturesScrollAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mAdapter = new PicturesScrollAdapter(this, queryImages());
		mView = new CardScrollView(this);
		mView.setAdapter(mAdapter);

		setContentView(mView);
	}

    @Override
    public void onResume() {
        super.onResume();
        mView.activate();
    }

    @Override
    public void onPause() {
        super.onPause();
        mView.deactivate();
    }

	private List<PictureItem> queryImages() {
		List<PictureItem> pathList = new ArrayList<PictureItem>();

		File f = new File(Utility.MEDIA_FOLDER_SOURCE);
		File[] files = f.listFiles();

        Log.i(TAG, "Querying images..");

		for (File file : files) {

            if(isImage(file.getName())) {
                PictureItem item = new PictureItem(file.getAbsolutePath(), file.getName());
                item.setFileType(FileItem.Type.PICTURE.ordinal());

                pathList.add(item);
                Log.i(TAG, "Added an image: " + file.getAbsolutePath());
            }

		}

        Log.i(TAG, "Done querying images..");

		return pathList;
	}

    private boolean isImage(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.'));

        if(extension.equals(FileItem.EXTENSION_JPG)) {
            return true;
        }
        else if(extension.equals(FileItem.EXTENSION_PNG)) {
            return true;
        }
        else if (extension.equals(FileItem.EXTENSION_JPEG)) {
            return true;
        }
        else if(extension.equals(FileItem.EXTENSION_BMP)) {
            return true;
        }

        return false;
    }

}
