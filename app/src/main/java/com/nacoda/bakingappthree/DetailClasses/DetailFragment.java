package com.nacoda.bakingappthree.DetailClasses;


import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.nacoda.bakingappthree.R;
import com.nacoda.bakingappthree.Utilities.Fonts;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @InjectView(R.id.tvStepsDescription)
    TextView tvStepsDescription;
    @InjectView(R.id.video_view)
    SimpleExoPlayerView playerView;
    @InjectView(R.id.thumbnail_view)
    ImageView thumbnail_view;
    String stepsDescription;
    String videoURL;
    String thumbnailURL;

    static final String PLAY_WHEN_READY_KEY = "PLAY_WHEN_READY";
    boolean playWhenReady = true;

    static final String CURRENT_WINDOW_KEY = "CURRENT_WINDOW";
    int currentWindow;

    static final String PLAY_BACK_POSITION_KEY = "PLAY_BACK_POSITION";
    long playbackPosition;

    SimpleExoPlayer player;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.inject(this, v);


        Fonts.RobotoMedium(getActivity(), tvStepsDescription);
        tvStepsDescription.setText(stepsDescription);
        return v;
    }

    public void setStepsDescription(String stepsDescription) {
        this.stepsDescription = stepsDescription;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        if (videoURL == null){
            playerView.setVisibility(View.GONE);
            if (thumbnailURL == null){
                thumbnail_view.setVisibility(View.GONE);
            } else {
                Glide.with(getActivity()).load(thumbnailURL).into(thumbnail_view);
            }
        } else {
            Uri uri = Uri.parse(videoURL);
            MediaSource mediaSource = buildMediaSource(uri);
            player.prepare(mediaSource,true,false);
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            playWhenReady = savedInstanceState.getBoolean(PLAY_WHEN_READY_KEY);
            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW_KEY);
            playbackPosition = savedInstanceState.getByte(PLAY_BACK_POSITION_KEY);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(PLAY_WHEN_READY_KEY, playWhenReady);
        outState.putInt(CURRENT_WINDOW_KEY, currentWindow);
        outState.putLong(PLAY_BACK_POSITION_KEY, playbackPosition);
    }
}
