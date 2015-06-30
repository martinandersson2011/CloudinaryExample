package com.martinandersson.cloudinaryexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private static final int THUMB_SIZE = 200;

    @Bind(R.id.imageview)
    ImageView mImageView;

    private Cloudinary cloudinary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Map config = new HashMap();
        config.put("cloud_name", "dio9eqyyz");
        config.put("api_key", "737832328971749");
        config.put("api_secret", "vmPUhntEjg4ak0-YNNlDdIjvxW0");
        cloudinary = new Cloudinary(config);

    }

    @OnClick(R.id.thumb_1)
    public void loadThumb1() {
        String path = cloudinary.url().transformation(new Transformation().width(THUMB_SIZE).height(THUMB_SIZE).crop("fill")).generate("sample1.png");
        Log.d(TAG, "Loading: " + path);
        Picasso.with(this).load(path).into(mImageView);
    }

    @OnClick(R.id.thumb_2)
    public void loadThumb2() {
        String path = cloudinary.url().transformation(new Transformation().width(THUMB_SIZE).height(THUMB_SIZE).crop("fill")).generate("sample2.png");
        Log.d(TAG, "Loading: " + path);
        Picasso.with(this).load(path).into(mImageView);
    }

    @OnClick(R.id.thumb_3)
    public void loadThumb3() {
        String path = cloudinary.url().transformation(new Transformation().width(THUMB_SIZE).height(THUMB_SIZE).crop("fill")).generate("sample3.png");
        Log.d(TAG, "Loading: " + path);
        Picasso.with(this).load(path).into(mImageView);
    }

    @OnClick(R.id.full_1)
    public void loadFull1() {
        String path = cloudinary.url().generate("sample1.png");
        Log.d(TAG, "Loading: " + path);
        Picasso.with(this).load(path).into(mImageView);
    }

    @OnClick(R.id.full_2)
    public void loadFull2() {
        String path = cloudinary.url().generate("sample2.png");
        Log.d(TAG, "Loading: " + path);
        Picasso.with(this).load(path).into(mImageView);
    }

    @OnClick(R.id.full_3)
    public void loadFull3() {
        String path = cloudinary.url().generate("sample3.png");
        Log.d(TAG, "Loading: " + path);
        Picasso.with(this).load(path).into(mImageView);
    }

}
