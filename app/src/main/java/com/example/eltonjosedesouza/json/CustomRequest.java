package com.example.eltonjosedesouza.json;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class CustomRequest extends Request {

    private Response.Listener listener; // the response listener

    public CustomRequest(int requestMethod, String url,
                         Response.Listener responseListener,
                         Response.ErrorListener errorListener) {

        super(requestMethod, url, errorListener); // Call parent constructor
        this.listener = responseListener;
    }

    // Same as JsonObjectRequest#parseNetworkResponse
    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            if (response.data.length > 10000)
                setShouldCache(false);

            String jsonString = new String(response.data, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonString);
            return Response.success(jsonObject,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(Object response) {
        listener.onResponse(response); // Call response listener
    }

    @Override
    public int compareTo(Object o) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
