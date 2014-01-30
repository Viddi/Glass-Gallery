package is.vidarottosson.glass.gallery;

//  Created by Viddi on 12/5/13.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import is.vidarottosson.glass.gallery.pics.PictureActivity;
import is.vidarottosson.glass.gallery.video.VideoActivity;
import is.vidarottosson.glass.gallery.video.VideoListActivity;

public class MenuActivity extends Activity {
	public static final String TAG = MenuActivity.class.getSimpleName();

	public static final int INTENT_PICTURES = 100;
	public static final int INTENT_VIDEOS = 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void onResume() {
		super.onResume();
		openOptionsMenu();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.pictures:
				Intent picIntent = new Intent(this, PictureActivity.class);
				startActivityForResult(picIntent, INTENT_PICTURES);
				return true;
			case R.id.videos:
				Intent videoIntent = new Intent(this, VideoListActivity.class);
				startActivityForResult(videoIntent, INTENT_VIDEOS);
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
		if (resultCode == RESULT_OK && requestCode == INTENT_PICTURES) {

		}
	}
}
