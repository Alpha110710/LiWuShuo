package com.example.dllo.liwushuo.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/5/28.
 */
public class ImgThread extends Thread {
    private Handler handler;
    private String imgPath;


    public ImgThread(Handler handler) {
        super();
        this.handler = handler;

    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        try {
            URL u = new URL(imgPath.toString());
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                Message message = new Message();
                message.what = 13;
                message.obj = bitmap;
                handler.sendMessage(message);
                is.close();
                connection.disconnect();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//
//
//
//        try {
//            Bitmap bitmap = Picasso.with(App.context).load(imgPath).get();
//            if (bitmap != null) {
//                Message message = new Message();
//                message.what = 13;
//                message.obj = bitmap;
//                handler.sendMessage(message);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
