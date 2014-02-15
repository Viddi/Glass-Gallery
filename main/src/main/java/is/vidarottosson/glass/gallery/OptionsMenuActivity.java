package is.vidarottosson.glass.gallery;
//  Created by Viddi on 2/14/14.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OptionsMenuActivity extends Activity {

    public static final String TAG = OptionsMenuActivity.class.getSimpleName();

    public static final String KEY_INTENT_EXTRA = "fileItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getExtras() != null) {

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
                // TODO: delete file
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        finish();
    }
}
