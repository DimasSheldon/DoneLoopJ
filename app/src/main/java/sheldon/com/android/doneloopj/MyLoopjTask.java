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

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class MyLoopjTask {
    private static final String TAG = "RESPONSE";
    //    private static final String BASE_URL = "http://103.27.207.134/umon/api/user/posttest";
    private static final String BASE_URL = "http://192.168.88.10:8080/MyLoopjServlet";

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

        checkURL(url);

        client.get(this.url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                jsonResponse = response.toString();

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

                Log.d(TAG, "onFailure#1: " + errorResponse);
                jsonResponse = errorResponse.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, (CharSequence) errorResponse, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d(TAG, "onFailure#2: " + errorResponse);

                jsonResponse = errorResponse.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, errorResponse, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d(TAG, "onFailure#3: " + throwable);

                jsonResponse = throwable.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void executeLoopjPOST(String url) {

        checkURL(url);

        client.post(this.url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                jsonResponse = response.toString();

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

                Log.d(TAG, "onFailure#1: " + errorResponse);
                jsonResponse = errorResponse.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, (CharSequence) errorResponse, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d(TAG, "onFailure#2: " + errorResponse);

                jsonResponse = errorResponse.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, errorResponse, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d(TAG, "onFailure#3: " + throwable);

                jsonResponse = throwable.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void executeQueryGet(String url, final String query) {
        params = new HashMap<String, String>();
        params.put("userQuery", query);
        requestParams = new RequestParams(params);

        checkURL(url);
        client.get(this.url, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    response.put("userInput", query);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonResponse = response.toString();

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

                Log.d(TAG, "onFailure#1: " + errorResponse);
                jsonResponse = errorResponse.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, (CharSequence) errorResponse, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d(TAG, "onFailure#2: " + errorResponse);

                jsonResponse = errorResponse.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, errorResponse, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d(TAG, "onFailure#3: " + throwable);

                jsonResponse = throwable.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void executeQueryPost(String url, final String query) {
        params = new HashMap<String, String>();
        params.put("userQuery", query);
        requestParams = new RequestParams(params);

        checkURL(url);
        client.post(this.url, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);


                try {
//                    response.put("userInput", query);
                    jsonResponse = response.getString("userQuery");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                jsonResponse = response.toString();

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

                Log.d(TAG, "onFailure#1: " + errorResponse);
                jsonResponse = errorResponse.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, (CharSequence) errorResponse, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d(TAG, "onFailure#2: " + errorResponse);

                jsonResponse = errorResponse.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, errorResponse, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d(TAG, "onFailure#3: " + throwable);

                jsonResponse = throwable.toString();
                loopjListener.getResponse(jsonResponse);
//                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
}