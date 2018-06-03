package sheldon.com.android.doneloopj;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class MyLoopjTask {
    private static final String TAG = "RESPONSE";
//    private static final String BASE_URL = "http://192.168.88.9:8080/MyLoopjServer/MyLoopjServlet";
    private static final String BASE_URL = "http://103.27.207.134/umon/api/user/posttest";

    private static String url;
    //    String BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    //    String BASE_URL = "http://103.27.207.134/umon/api/user/posttest/";
    private Context context;
    private OnLoopjListener loopjListener;

    private AsyncHttpClient client;

    public MyLoopjTask(Context context, OnLoopjListener loopjListener) {
        client = new AsyncHttpClient();
        this.context = context;
        this.loopjListener = loopjListener;
    }

    public void executeLoopjGET(String url) {

        validateURL(url);

        client.get(this.url, null, new JsonHttpResponseHandler() {

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
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d(TAG, "onFailure1: " + errorResponse);
//                Toast.makeText(context, errorResponse.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d(TAG, "onFailure2: " + errorResponse);
//                Toast.makeText(context, errorResponse.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d(TAG, "onFailure3: " + throwable);
//                Toast.makeText(context, throwable.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void executeLoopjPOST(String url) {

        validateURL(url);

        client.post(this.url, null, new JsonHttpResponseHandler() {

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
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d(TAG, "onFailure1: " + errorResponse);
//                Toast.makeText(context, errorResponse.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d(TAG, "onFailure2: " + errorResponse);
//                Toast.makeText(context, errorResponse.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d(TAG, "onFailure3: " + throwable);
//                Toast.makeText(context, throwable.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getURL() {
        return BASE_URL;
    }

    private void validateURL(String url) {
        if (!(url.equals(BASE_URL)) && !(url.isEmpty())) {
            this.url = url;
        } else {
            this.url = BASE_URL;
        }
    }
}