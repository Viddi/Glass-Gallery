package is.vidarottosson.glass.gallery;

//  Created by Viddi on 2/15/14.

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import is.vidarottosson.glass.gallery.models.FileItem;
import is.vidarottosson.glass.gallery.widget.SliderView;

public class DeleteActivity extends Activity {

	public static final String TAG = DeleteActivity.class.getSimpleName();

    public static final String KEY_INTENT_EXTRA_PATH = "filePath";
    public static final int PROGRESS_MAX = 100;

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

		if (getIntent().getExtras() != null) {

		}

        mSliderView.startProgress(2500);

	}

}
