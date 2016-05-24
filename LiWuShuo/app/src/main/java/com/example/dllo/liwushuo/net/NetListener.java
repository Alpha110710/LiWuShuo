package com.example.dllo.liwushuo.net;

import com.android.volley.VolleyError;

/**
 * Created by dllo on 16/5/23.
 */
public interface NetListener {
    void onSuccessed(String response);
    void onFailed(VolleyError error);
}
