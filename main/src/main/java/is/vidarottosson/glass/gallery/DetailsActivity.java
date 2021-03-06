package is.vidarottosson.glass.gallery;
//  Created by Viddi on 2/22/14.

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import is.vidarottosson.glass.gallery.util.Utility;

public class DetailsActivity extends Activity {

    public static final String TAG = DetailsActivity.class.getSimpleName();

    public static final String KEY_INTENT_EXTRA_PATH = "filePath";
    public static final int INTENT_DETAILS = 400;
    
    private TextView mTxtTitle, mTxtTime, mTxtSize, mTxtPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        
        mTxtTitle = (TextView) findViewById(R.id.details_tvTitle);
        mTxtTime = (TextView) findViewById(R.id.details_tvTime);
        mTxtSize = (TextView) findViewById(R.id.details_tvSize);
        mTxtPath = (TextView) findViewById(R.id.details_tvPath);
        
        mTxtTitle.setTypeface(typeface);
        mTxtTime.setTypeface(typeface);
        mTxtSize.setTypeface(typeface);
        mTxtPath.setTypeface(typeface);
        
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            String filePath = intent.getExtras().getString(KEY_INTENT_EXTRA_PATH);
            setDetails(filePath);
        }
    }
    
    public void setDetails(String filePath) {
        File file = new File(filePath);

        mTxtTitle.setText(getResources().getString(R.string.details_title) + ": " + getName(file.getName()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy H:MM");
        mTxtTime.setText(getResources().getString(R.string.details_time) + ": " + dateFormat.format(new Date(file.lastModified())));

        mTxtSize.setText(getResources().getString(R.string.details_file_size) + ": " + Utility.readableFileSize(file.length()));

        mTxtPath.setText(getResources().getString(R.string.details_path) + ": " + file.getAbsolutePath());
    }

    public String getName(String fileName) {
        String extension = Utility.getExtension(fileName);

        return fileName.replace(extension, "");
    }
}
