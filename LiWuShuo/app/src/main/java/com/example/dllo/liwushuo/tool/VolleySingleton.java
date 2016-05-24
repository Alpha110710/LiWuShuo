package com.example.dllo.liwushuo.tool;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.dllo.liwushuo.net.MemoryCache;

/**
 * Created by dllo on 16/5/23.
 */
public class VolleySingleton {
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private static VolleySingleton ourInstance = new VolleySingleton();

    public static VolleySingleton getInstance() {
        return ourInstance;
    }

    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(App.context);
        imageLoader = new ImageLoader(requestQueue, new MemoryCache());

    }
}
