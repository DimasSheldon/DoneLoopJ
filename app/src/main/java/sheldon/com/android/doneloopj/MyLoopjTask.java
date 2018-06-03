package sheldon.com.android.doneloopj;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class MyLoopjTask {
    private static final String TAG = "RESPONSE";
    private static final String BASE_URL = "http://192.168.88.9:8080/MyLoopjServer/MyLoopjServlet";
    //    String BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    //    String BASE_URL = "http://103.27.207.134/umon/api/user/posttest/";
    private Context context;
    private OnLoopjListener loopjListener;

    AsyncHttpClient client;

    public MyLoopjTask(Context context, OnLoopjListener loopjListener) {
        client = new AsyncHttpClient();
        this.context = context;
        this.loopjListener = loopjListener;
    }

    public void executeLoopjGET() {

        client.get(BASE_URL, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                String jsonResponse = response.toString();

                //send response to interface
                loopjListener.getResponse(jsonResponse);

                Toast.makeText(context, jsonResponse, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onSuccess: " + jsonResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(context, errorResponse.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + errorResponse);
            }
        });
    }

    public void executeLoopjPOST() {

        client.post(BASE_URL, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                String jsonResponse = response.toString();

                //send response to interface
                loopjListener.getResponse(jsonResponse);

                Toast.makeText(context, jsonResponse, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onSuccess: " + jsonResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(context, errorResponse.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + errorResponse);
            }
        });
    }

    public String getURL() {
        return BASE_URL;
    }
}