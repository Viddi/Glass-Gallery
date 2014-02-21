package is.vidarottosson.glass.gallery;
//  Created by Viddi on 2/14/14.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import is.vidarottosson.glass.gallery.models.FileItem;
import is.vidarottosson.glass.gallery.models.PictureItem;
import is.vidarottosson.glass.gallery.models.VideoItem;

public class OptionsMenuActivity extends Activity {

    public static final String TAG = OptionsMenuActivity.class.getSimpleName();
    
    public static final String KEY_INTENT_EXTRA_PICTURE = "pictureItem";
    public static final String KEY_INTENT_EXTRA_VIDEO = "videoItem";

    public static final int RESULT_DELETED = 301;
    public static final int INTENT_DELETE = 302;

    private FileItem mFileItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getParcelable(KEY_INTENT_EXTRA_PICTURE) != null) {
            mFileItem = (PictureItem) bundle.getParcelable(KEY_INTENT_EXTRA_PICTURE);
        }
        else if(bundle.getParcelable(KEY_INTENT_EXTRA_VIDEO) != null) {
            mFileItem = (VideoItem) bundle.getParcelable(KEY_INTENT_EXTRA_VIDEO);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent i = new Intent(this, ShareMenuActivity.class);
                startActivity(i);
                return true;
            case R.id.details:
                // TODO: show various details
                return true;
            case R.id.delete:
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                deleteIntent.putExtra(DeleteActivity.KEY_INTENT_EXTRA_PATH, mFileItem.getPath());
                startActivityForResult(deleteIntent, INTENT_DELETE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == INTENT_DELETE && resultCode == RESULT_OK) {
            setResult(RESULT_DELETED);
            finish();
        }
    }
}
