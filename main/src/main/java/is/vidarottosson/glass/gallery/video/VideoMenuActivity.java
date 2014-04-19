package is.vidarottosson.glass.gallery.video;

//  Created by jonstaff on 2/23/14.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import is.vidarottosson.glass.gallery.DeleteActivity;
import is.vidarottosson.glass.gallery.DetailsActivity;
import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.VideoItem;

public class VideoMenuActivity extends Activity {
	public static final String TAG = VideoMenuActivity.class.getSimpleName();

	private VideoItem mVideo;
	private boolean mIsWaiting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = getIntent().getExtras();
		if (bundle.getParcelable(VideoItem.KEY_FOR_INTENT_EXTRA) != null) {
			mVideo = bundle.getParcelable(VideoItem.KEY_FOR_INTENT_EXTRA);
		}
	}

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        openOptionsMenu();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.play:
				Intent playIntent = new Intent(this, VideoActivity.class);
				playIntent.putExtra(VideoItem.KEY_FOR_INTENT_EXTRA, mVideo);
				startActivity(playIntent);
				finish();
				return true;
			case R.id.details:
				Intent detailsIntent = new Intent(this, DetailsActivity.class);
				detailsIntent.putExtra(DetailsActivity.KEY_INTENT_EXTRA_PATH, mVideo.getPath());
				startActivityForResult(detailsIntent, DetailsActivity.INTENT_DETAILS);
				mIsWaiting = true;
				return true;
			case R.id.delete:
				Intent deleteIntent = new Intent(this, DeleteActivity.class);
				deleteIntent.putExtra(DeleteActivity.KEY_INTENT_EXTRA_PATH, mVideo.getPath());
				startActivityForResult(deleteIntent, DeleteActivity.INTENT_DELETE);
				mIsWaiting = true;
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		if (!mIsWaiting) {
			finish();
		}
		mIsWaiting = false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
        openOptionsMenu();

		if (requestCode == DeleteActivity.INTENT_DELETE && resultCode == RESULT_OK) {
			setResult(DeleteActivity.RESULT_DELETED);
			finish();
		}
	}
}