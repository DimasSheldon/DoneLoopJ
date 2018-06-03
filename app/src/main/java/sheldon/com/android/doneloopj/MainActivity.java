package sheldon.com.android.doneloopj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnLoopjListener {

    EditText mURL;
    Button mButtonGET, mButtonPOST;
    TextView mResult;

    MyLoopjTask myLoopjTask;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mURL = (EditText) findViewById(R.id.url);
        mButtonGET = (Button) findViewById(R.id.btnGET);
        mButtonPOST = (Button) findViewById(R.id.btnPOST);
        mResult = (TextView) findViewById(R.id.results);

        myLoopjTask = new MyLoopjTask(this, this);
        mURL.setHint(myLoopjTask.getURL());
    }

    public void doGET(View view) {
        url = mURL.getText().toString();
        myLoopjTask.executeLoopjGET(url);
    }

    public void doPOST(View view) {
        url = mURL.getText().toString();
        myLoopjTask.executeLoopjPOST(url);
    }

    @Override
    public void getResponse(String responses) {
        mResult.setText(responses);
    }
}