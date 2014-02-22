package is.vidarottosson.glass.gallery;

//  Created by Viddi on 2/15/14.

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.glass.media.Sounds;
import com.google.glass.widget.SliderView;
import is.vidarottosson.glass.gallery.models.FileItem;

public class DeleteActivity extends Activity implements SliderView.OnAnimateListener {

	public static final String TAG = DeleteActivity.class.getSimpleName();

	public static final String KEY_INTENT_EXTRA_PATH = "filePath";
	public static final int PROGRESS_SECONDS = 2000;
	public static final int WAIT_SECONDS = 250;

	private LinearLayout mDeletingLayout, mDeletedLayout;
	private SliderView mSliderView;

	private FileItem mFileItem;
    private boolean isAnimating = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);

		mDeletingLayout = (LinearLayout) findViewById(R.id.delete_layoutDeleting);
		mDeletedLayout = (LinearLayout) findViewById(R.id.delete_layoutDeleted);

		Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");

		((TextView) findViewById(R.id.delete_tvDeleting)).setTypeface(typeface);
		((TextView) findViewById(R.id.delete_tvDeleted)).setTypeface(typeface);

		mSliderView = (SliderView) findViewById(R.id.delete_progressBar);

		Intent intent = getIntent();
		if (intent.getExtras() != null) {
			String filePath = intent.getExtras().getString(KEY_INTENT_EXTRA_PATH);
			mFileItem = new FileItem(filePath);
		}

        mSliderView.setOnAnimateListener(this);
        mSliderView.startProgress(PROGRESS_SECONDS);
	}

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            isAnimating = false;

            AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audio.playSoundEffect(Sounds.DISMISSED);
            mSliderView.stopProgress();

            return true;
        }

        return false;
    }

    @Override
    public void onAnimateFinishedListener() {
        if(!isAnimating) {
            return;
        }

        if(mFileItem.deleteItem()) {
            mDeletingLayout.setVisibility(View.GONE);
            mDeletedLayout.setVisibility(View.VISIBLE);

            AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audio.playSoundEffect(Sounds.SUCCESS);

            sleep(WAIT_SECONDS);
            setResult(RESULT_OK);
        }
        else {
            AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audio.playSoundEffect(Sounds.ERROR);

            sleep(WAIT_SECONDS);
            setResult(RESULT_CANCELED);
        }

        finish();
    }

    @Override
    public void onAnimateCancelledListener() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}