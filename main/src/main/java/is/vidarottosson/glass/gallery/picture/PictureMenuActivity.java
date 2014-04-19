package is.vidarottosson.glass.gallery.picture;

//  Created by Viddi on 2/14/14.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import is.vidarottosson.glass.gallery.DeleteActivity;
import is.vidarottosson.glass.gallery.DetailsActivity;
import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.FileItem;
import is.vidarottosson.glass.gallery.models.PictureItem;
import is.vidarottosson.glass.gallery.models.VideoItem;

public class PictureMenuActivity extends Activity {

    public static final String TAG = PictureMenuActivity.class.getSimpleName();

    public static final String KEY_INTENT_EXTRA_PICTURE = "pictureItem";

    private FileItem mFileItem;
    private boolean isWaiting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getParcelable(KEY_INTENT_EXTRA_PICTURE) != null) {
            mFileItem = (PictureItem) bundle.getParcelable(KEY_INTENT_EXTRA_PICTURE);
        }
        else if(bundle.getParcelable(VideoItem.KEY_FOR_INTENT_EXTRA) != null) {
            mFileItem = (VideoItem) bundle.getParcelable(VideoItem.KEY_FOR_INTENT_EXTRA);
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.picture, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.share:
//                Intent i = new Intent(this, ShareMenuActivity.class);
//                startActivity(i);
//                isWaiting = true;
//                return true;
            case R.id.details:
                Intent detailsIntent = new Intent(this, DetailsActivity.class);
                detailsIntent.putExtra(DetailsActivity.KEY_INTENT_EXTRA_PATH, mFileItem.getPath());
                startActivityForResult(detailsIntent, DetailsActivity.INTENT_DETAILS);
                isWaiting = true;
                return true;
            case R.id.delete:
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                deleteIntent.putExtra(DeleteActivity.KEY_INTENT_EXTRA_PATH, mFileItem.getPath());
                startActivityForResult(deleteIntent, DeleteActivity.INTENT_DELETE);
                isWaiting = true;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        if (!isWaiting) {
            finish();
        }
        isWaiting = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        openOptionsMenu();

        if(requestCode == DeleteActivity.INTENT_DELETE && resultCode == RESULT_OK) {
            setResult(DeleteActivity.RESULT_DELETED);
            finish();
        }
    }
}
