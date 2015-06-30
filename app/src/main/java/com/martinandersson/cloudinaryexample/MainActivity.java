package com.martinandersson.cloudinaryexample;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private static final int THUMB_SIZE = 200;
    private static final int PHOTO_MAX_WIDTH = 450;
    private static final int VIDEO_MAX_WIDTH = 320;

    @Bind(R.id.thumbnail)
    ImageView mThumbnail;
    @Bind(R.id.imageview)
    ImageView mImageView;
    @Bind(R.id.videoview)
    VideoView mVideoView;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

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
        getThumbnailPath("sample1.png");
    }

    @OnClick(R.id.thumb_2)
    public void loadThumb2() {
        getThumbnailPath("sample2.png");
    }

    @OnClick(R.id.thumb_3)
    public void loadThumb3() {
        getThumbnailPath("sample3.png");
    }

    @OnClick(R.id.full_1)
    public void loadFull1() {
        getPhotoPath("sample1.png");
    }

    @OnClick(R.id.full_2)
    public void loadFull2() {
        getPhotoPath("sample2.png");
    }

    @OnClick(R.id.full_3)
    public void loadFull3() {
        getPhotoPath("sample3.png");
    }

    @OnClick(R.id.video_1)
    public void loadVideo1() {
        getVideoPath("video1.mp4");
    }

    @OnClick(R.id.video_2)
    public void loadVideo2() {
        getVideoPath("video2.mp4");
    }

    private void getThumbnailPath(String filename) {
        String path = cloudinary.url().transformation(getThumbnailTransformation()).generate(filename);
        showThumbnail(path);
    }

    private void getPhotoPath(String filename) {
        String path = cloudinary.url().transformation(getPhotoTransformation()).generate(filename);
        showPhoto(path);
    }

    private void getVideoPath(String filename) {
        String path = cloudinary.url().resourceType("video").transformation(getVideoTransformation()).generate(filename);
        showVideo(path);
    }

    private Transformation getThumbnailTransformation() {
        return new Transformation().width(THUMB_SIZE).height(THUMB_SIZE).crop("fill");
    }

    private Transformation getPhotoTransformation() {
        return new Transformation().width(getPhotoWidth()).height(getPhotoHeight()).crop("fill");
    }

    private Transformation getVideoTransformation() {
        return new Transformation().width(getVideoWidth()).height(getVideoHeight()).crop("fill");
    }

    private int getPhotoWidth() {
        return PHOTO_MAX_WIDTH;
    }

    private int getPhotoHeight() {
        return PHOTO_MAX_WIDTH * mImageView.getHeight() / mImageView.getWidth();
    }

    private int getVideoWidth() {
        return VIDEO_MAX_WIDTH;
    }

    private int getVideoHeight() {
        return VIDEO_MAX_WIDTH * mVideoView.getHeight() / mVideoView.getWidth();
    }

    private void showThumbnail(String path) {
        mThumbnail.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.GONE);
        mVideoView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);

        Log.d(TAG, "showThumbnail: " + path);
        Picasso.with(this).load(path).into(mThumbnail, new Callback() {
            @Override
            public void onSuccess() {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showPhoto(String path) {
        mThumbnail.setVisibility(View.GONE);
        mImageView.setVisibility(View.VISIBLE);
        mVideoView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);

        Log.d(TAG, "showPhoto: " + path);
        Picasso.with(this).load(path).into(mImageView, new Callback() {
            @Override
            public void onSuccess() {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showVideo(String path) {
        mVideoView.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);

        Log.d(TAG, "showVideo: " + path);
        mVideoView.setVideoPath(path);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "Starting video: " + mp.getVideoWidth() + ", " + mp.getVideoHeight());
                mVideoView.start();
                mProgressBar.setVisibility(View.GONE);
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "Video finished", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
