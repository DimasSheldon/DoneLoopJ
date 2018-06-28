package sheldon.com.android.doneloopj;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class MyLoopjTask {
    private static final String TAG = "RESPONSE";
    //    private static final String BASE_URL = "http://103.27.207.134/umon/api/user/posttest/";
    private static final String BASE_URL = "http://103.27.207.134/umon/api/user/submitLogin/";

    private String url;
    private Context context;
    private OnLoopjListener loopjListener;
    private AsyncHttpClient client;
    private String jsonResponse;
    private HashMap<String, String> params;
    private RequestParams requestParams;

    public MyLoopjTask(Context context, OnLoopjListener loopjListener) {
        this.context = context;
        this.loopjListener = loopjListener;
        client = new AsyncHttpClient();
    }

    public void executeLoopjGET(String url) {
        requestParams = new RequestParams();
        requestParams.put("msgsukses", "dimassheldon");

        checkURL(url);
        client.get(this.url, requestParams, new JsonHttpResponseHandler() {
            //        client.get(this.url, null, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
                loopjListener.getResponse("Error Bro:(");
                Log.d(TAG, "onFailure#1: " + errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
                loopjListener.getResponse("Error Bro:(");
                Log.d(TAG, "onFailure#2: " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
                loopjListener.getResponse(jsonResponse);
                Toast.makeText(context, jsonResponse, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onSuccess: " + jsonResponse);
            }
        });
    }

    public void executeLoopjPOST(String url) {
        requestParams = new RequestParams();
        requestParams.put("username", "POSTUsername");
        requestParams.put("hashpassword", "POSTPassword");

        checkURL(url);
        client.post(this.url, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
                loopjListener.getResponse("Error Bro:(");
                Log.d(TAG, "onFailure#1: " + errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
                loopjListener.getResponse(responseString);
                Log.d(TAG, "onFailure#2: " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
                loopjListener.getResponse(jsonResponse);
                Toast.makeText(context, jsonResponse, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onSuccess: " + jsonResponse);
            }
        });
    }

    public void executeLoopjPOST(String url, String uname, String pass) {
        String md5Password = convertPassMd5(pass);
        requestParams = new RequestParams();
        requestParams.put("username", uname);
        requestParams.put("password", pass); //plain password
        requestParams.put("hashpassword", md5Password); // password dlm bentuk hash: md5

        checkURL(url);
        client.post(this.url, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
                loopjListener.getResponse("Error Bro:(");
                Log.d(TAG, "onFailure#1: " + errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
                loopjListener.getResponse(responseString);
                Log.d(TAG, "onFailure#2: " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);

//                try {
//                    jsonResponse = response.getString("username");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                jsonResponse = response.toString();
                loopjListener.getResponse(jsonResponse);
                Toast.makeText(context, jsonResponse, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onSuccess: " + jsonResponse);
            }
        });
    }

    private void checkURL(String url) {
        if (!(url.equals(BASE_URL)) && !(url.isEmpty())) {
            this.url = url;
        } else {
            this.url = BASE_URL;
        }
    }

    public String getURL() {
        return BASE_URL;
    }

    private static String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }
}