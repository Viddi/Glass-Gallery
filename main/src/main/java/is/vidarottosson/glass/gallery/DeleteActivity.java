package is.vidarottosson.glass.gallery;

//  Created by Viddi on 2/15/14.

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.glass.media.Sounds;
import com.google.glass.widget.SliderView;

import is.vidarottosson.glass.gallery.models.FileItem;

public class DeleteActivity extends Activity implements SliderView.OnAnimateListener {

	public static final String TAG = DeleteActivity.class.getSimpleName();

	public static final String KEY_INTENT_EXTRA_PATH = "filePath";
	public static final int PROGRESS_MILLIS = 2000;
	public static final int WAIT_MILLIS = 1000;

    public static final int RESULT_DELETED = 301;
    public static final int INTENT_DELETE = 302;

    private TextView mTextView;
    private ImageView mImageView;
	private SliderView mSliderView;

	private FileItem mFileItem;
    private Handler mHandler = new Handler();
    private boolean isAnimating = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);

		Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");

		((TextView) findViewById(R.id.delete_textView)).setTypeface(typeface);

        mImageView = (ImageView) findViewById(R.id.delete_imageView);
        mTextView = (TextView) findViewById(R.id.delete_textView);
		mSliderView = (SliderView) findViewById(R.id.delete_progressBar);

		Intent intent = getIntent();
		if (intent.getExtras() != null) {
			String filePath = intent.getExtras().getString(KEY_INTENT_EXTRA_PATH);
			mFileItem = new FileItem(filePath);
		}

        mSliderView.setOnAnimateListener(this);
        mSliderView.startProgress(PROGRESS_MILLIS);
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
            mImageView.setImageResource(R.drawable.ic_done);
            mTextView.setText(getResources().getString(R.string.deleted));

            AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audio.playSoundEffect(Sounds.SUCCESS);

            mHandler.postDelayed(mRunner, WAIT_MILLIS);
        }
        else {
            mImageView.setImageResource(R.drawable.ic_no);
            mTextView.setText(getResources().getString(R.string.error));

            AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audio.playSoundEffect(Sounds.ERROR);

            mHandler.postDelayed(mRunner, WAIT_MILLIS);
        }
    }

    @Override
    public void onAnimateCancelledListener() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private Runnable mRunner = new Runnable() {
        @Override
        public void run() {
            setResult(RESULT_OK);
            finish();
        }
    };

}
