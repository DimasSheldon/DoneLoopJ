package sheldon.com.android.doneloopj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnLoopjListener {

    EditText mURL, mUsername, mPassword;
    Button mButtonGET, mButtonPOST;
    TextView mResult;

    MyLoopjTask myLoopjTask;
    String url, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mURL = (EditText) findViewById(R.id.url);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mButtonGET = (Button) findViewById(R.id.btnGET);
        mButtonPOST = (Button) findViewById(R.id.btnPOST);
        mResult = (TextView) findViewById(R.id.results);

        myLoopjTask = new MyLoopjTask(this, this);
        mURL.setHint(myLoopjTask.getURL());
    }

    public void doGET(View view) {
        url = mURL.getText().toString();
        username = mUsername.getText().toString();
        password = mPassword.getText().toString();

        if (username.isEmpty() && password.isEmpty()) {
            myLoopjTask.executeLoopjGET(url);
        } else {
            myLoopjTask.executeLoopjPOST(url, username, password);
        }
    }

    public void doPOST(View view) {
        url = mURL.getText().toString();
        username = mUsername.getText().toString();
        password = mPassword.getText().toString();

        if (username.isEmpty() && password.isEmpty()) {
            myLoopjTask.executeLoopjPOST(url);
        } else {
            myLoopjTask.executeLoopjPOST(url, username, password);
        }
    }

    @Override
    public void getResponse(String responses) {
        mResult.setText(responses);
    }
}