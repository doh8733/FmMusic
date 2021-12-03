package com.example.fmmusic.View.Activity.Persional;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fmmusic.Adapter.SongsLibAdapter;
import com.example.fmmusic.Model.Songs.AudioModel;
import com.example.fmmusic.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SongsLibActivity extends AppCompatActivity {
    private RecyclerView rcvListSongs;
    private TextInputLayout tilFindSonglib;
    private MaterialButton btnRamdoomplay;
    private TextView textView;
    private CardView cvBottomPlayBars;
    private CardView cvThumbnail;
    private ImageView imgThumbnail;
    private ImageView imgPlay;
    private ImageView imgPrevious;
    private ImageView imgNext;
    private ImageView imgPause;
    private TextView tvNameSong;

    private SongsLibAdapter songsLibAdapter;
    public static List<AudioModel> audioModelList;
    public static List<AudioModel> audioFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_lib);

        rcvListSongs = (RecyclerView) findViewById(R.id.rcvListSongs);
        tilFindSonglib = (TextInputLayout) findViewById(R.id.tilFindSonglib);
        btnRamdoomplay = (MaterialButton) findViewById(R.id.btnRamdoomplay);
        textView = (TextView) findViewById(R.id.textView);
        cvBottomPlayBars = (CardView) findViewById(R.id.cvBottomPlayBars);
        cvThumbnail = (CardView) findViewById(R.id.cvThumbnail);
        imgThumbnail = (ImageView) findViewById(R.id.imgThumbnail);
        imgPlay = (ImageView) findViewById(R.id.imgPlay);
        imgPrevious = (ImageView) findViewById(R.id.imgPrevious);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        imgPause = (ImageView) findViewById(R.id.imgPause);
        tvNameSong = (TextView) findViewById(R.id.tvNameSong);
        //end
        if (isPermissionsGranted()){
            Toast.makeText(SongsLibActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }else {
            takePermission();
        }
        //get song vao trong list
        audioModelList = new ArrayList<>();
        audioModelList = loadAudio(this);

        songsLibAdapter = new SongsLibAdapter(audioModelList);
        rcvListSongs.setAdapter(songsLibAdapter);
        rcvListSongs.setLayoutManager(new LinearLayoutManager(this));

        tilFindSonglib.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    findSong();
                    return true;
                }
                return false;
            }
        });
    }

    void findSong(){
        int pos = -1;
        for (int i = 0; i < audioModelList.size(); i++) {
            if (audioModelList.get(i).getName().contains(tilFindSonglib.getEditText().getText().toString())
                    || audioModelList.get(i).getName().equalsIgnoreCase(tilFindSonglib.getEditText().getText().toString())){
                audioFind = new ArrayList<>();
                audioFind.add(audioModelList.get(i));
                songsLibAdapter = new SongsLibAdapter(audioFind);
                rcvListSongs.setAdapter(songsLibAdapter);
                rcvListSongs.setLayoutManager(new LinearLayoutManager(this));
                pos++;
            }
        }
        if (pos<0){
            Toast.makeText(SongsLibActivity.this, "Không tìm thấy "+tilFindSonglib.getEditText().getText().toString(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(SongsLibActivity.this, "Đã tìm thấy "+tilFindSonglib.getEditText().getText().toString(), Toast.LENGTH_SHORT).show();
            audioModelList = new ArrayList<>();
            audioModelList = loadAudio(this);

            songsLibAdapter = new SongsLibAdapter(audioModelList);
            rcvListSongs.setAdapter(songsLibAdapter);
            rcvListSongs.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private List<AudioModel> loadAudio(Context context){
        List<AudioModel> tempList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.DURATION,
                MediaStore.Audio.AudioColumns.ALBUM_ID
        };
        Cursor cursor = context.getContentResolver().query(uri,projection,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                long albumID = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM_ID));
                Uri imgPath = Uri.parse("content://media/external/audio/albumart");
                Uri imgParse = ContentUris.withAppendedId(imgPath,albumID);
                tempList.add(new AudioModel(
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION)),
                        imgParse.toString()
                ));
            }
            cursor.close();
        }
        return tempList;
    }
    private boolean isPermissionsGranted(){
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R){
            return Environment.isExternalStorageManager();
        }else {
            int readEnternalStoragePermission =
                    ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE);
            return readEnternalStoragePermission == PackageManager.PERMISSION_GRANTED;
        }
    }
    private void takePermission(){
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R){
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getPackageName())));
                startActivityForResult(intent,100);
            }catch (Exception e){
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent,100);
                e.printStackTrace();
            }
        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==100){
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R){
                    if (Environment.isExternalStorageManager()){
                        Toast.makeText(SongsLibActivity.this, "Permission granted!", Toast.LENGTH_SHORT).show();
                    }else {
                        takePermission();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length>0){
            if (requestCode==101){
                boolean readExternalStorage = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                if (readExternalStorage){
                    Toast.makeText(SongsLibActivity.this, "Read permission is granted in android 10 and below", Toast.LENGTH_SHORT).show();
                }else {
                    takePermission();
                }
            }
        }
    }
}