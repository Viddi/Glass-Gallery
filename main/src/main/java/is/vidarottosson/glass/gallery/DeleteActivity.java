package is.vidarottosson.glass.gallery;

//  Created by Viddi on 2/15/14.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.glass.widget.SliderView;

import is.vidarottosson.glass.gallery.models.FileItem;

public class DeleteActivity extends Activity implements SliderView.OnAnimateListener {

	public static final String TAG = DeleteActivity.class.getSimpleName();

    public static final String KEY_INTENT_EXTRA_PATH = "filePath";

	private LinearLayout mDeletingLayout, mDeletedLayout;
	private SliderView mSliderView;

	private FileItem mFileItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);

		mDeletingLayout = (LinearLayout) findViewById(R.id.delete_layoutDeleting);
		mDeletedLayout = (LinearLayout) findViewById(R.id.delete_layoutDeleted);

		mSliderView = (SliderView) findViewById(R.id.delete_progressBar);

        Intent intent = getIntent();
		if (intent.getExtras() != null) {
            String filePath = intent.getExtras().getString(KEY_INTENT_EXTRA_PATH);
            mFileItem.setPath(filePath);
		}

        mSliderView.setOnAnimateListener(this);
        mSliderView.startProgress(2500);

	}

    @Override
    public void onAnimateFinishedListener() {
//        if(mFileItem.deleteItem()) {
//            mDeletingLayout.setVisibility(View.GONE);
//            mDeletedLayout.setVisibility(View.VISIBLE);
//
//            // TODO: Play sound
//
//            try {
//                Thread.sleep(1500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            setResult(RESULT_OK);
//            finish();
//        }
//        else {
//
//
//            try {
//                Thread.sleep(1500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            setResult(RESULT_CANCELED);
//            finish();
//        }
    }

    @Override
    public void onAnimateCancelledListener() {
        setResult(RESULT_CANCELED);
        finish();
    }

}
