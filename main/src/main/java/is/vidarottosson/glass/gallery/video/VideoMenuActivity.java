package is.vidarottosson.glass.gallery.video;

//  Created by jonstaff on 1/30/14.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.ShareMenuActivity;

public class VideoMenuActivity extends Activity {
	public static final String TAG = VideoMenuActivity.class.getSimpleName();

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
		inflater.inflate(R.menu.video, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.share:
                Intent i = new Intent(this, ShareMenuActivity.class);
                startActivity(i);
				return true;
			case R.id.delete:
				// TODO: delete that video
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

	}
}