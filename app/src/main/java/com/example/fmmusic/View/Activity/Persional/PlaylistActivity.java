package com.example.fmmusic.View.Activity.Persional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fmmusic.Adapter.PlaylistAdapter;
import com.example.fmmusic.Model.Songs.Playlist;
import com.example.fmmusic.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {
    private ScrollView scrollView2;
    private TextInputLayout tilFindPlaylist;
    private TextView tvtPlaylist;
    private RecyclerView rcvPlaylist;
    private CardView cvBottomPlayBars;
    private CardView cvThumbnail;
    private ImageView imgThumbnail;
    private ImageView imgPlay;
    private ImageView imgPrevious;
    private ImageView imgNext;
    private ImageView imgPause;
    private TextView tvNameSong;

    private PlaylistAdapter playlistAdapter;
    private List<Playlist> playlistList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        scrollView2 = (ScrollView) findViewById(R.id.scrollView2);
        tilFindPlaylist = (TextInputLayout) findViewById(R.id.tilFindPlaylist);
        tvtPlaylist = (TextView) findViewById(R.id.tvtPlaylist);
        rcvPlaylist = (RecyclerView) findViewById(R.id.rcvPlaylist);
        cvBottomPlayBars = (CardView) findViewById(R.id.cvBottomPlayBars);
        cvThumbnail = (CardView) findViewById(R.id.cvThumbnail);
        imgThumbnail = (ImageView) findViewById(R.id.imgThumbnail);
        imgPlay = (ImageView) findViewById(R.id.imgPlay);
        imgPrevious = (ImageView) findViewById(R.id.imgPrevious);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        imgPause = (ImageView) findViewById(R.id.imgPause);
        tvNameSong = (TextView) findViewById(R.id.tvNameSong);

        setListData();

        playlistAdapter = new PlaylistAdapter(playlistList);
        rcvPlaylist.setAdapter(playlistAdapter);
        rcvPlaylist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    void setListData(){
        String url1="https://pimobfptedu.github.io/img/";
        playlistList = new ArrayList<>();
        playlistList.add(new Playlist(url1 + "img_nhac_tre.png","Nhạc trẻ"));
        playlistList.add(new Playlist(url1 + "img_nhac_tru_tinh.png","nhạc trữ tình"));
        playlistList.add(new Playlist(url1 + "nhac_dong_que.png","nhạc đồng quê"));
        playlistList.add(new Playlist(url1 + "nhac_vang.png","nhạc vàng"));
        playlistList.add(new Playlist(url1 + "nhac_bolero.png","nhạc bolero"));
        playlistList.add(new Playlist(url1 + "nhac_hai_ngoai.png","nhạc hải ngoại"));
        playlistList.add(new Playlist(url1 + "nhac_que_huong.png","nhạc quê hương"));
        playlistList.add(new Playlist(url1 + "nhac_trinh-cong-son.png","nhạc trịnh"));
        playlistList.add(new Playlist(url1 + "nhac_hoa_tau.png","Nhạc Hòa tấu"));
        playlistList.add(new Playlist(url1 + "nhac-khong-loi.png","nhạc không lời"));
        playlistList.add(new Playlist(url1 + "nhac_thien.png","Nhạc Thiền"));
        playlistList.add(new Playlist(url1 + "nhac_indie.png","nhạc indie"));
        playlistList.add(new Playlist(url1 + "nhac_hiphop.png","nhạc hip hop"));
        playlistList.add(new Playlist(url1 + "nhac_dance.png","nhạc dance"));
        playlistList.add(new Playlist(url1 + "nhac_remix.png","nhạc remix"));
        playlistList.add(new Playlist(url1 + "nhac_san.png","nhạc sàn"));
        playlistList.add(new Playlist(url1 + "nhac-edm.png","Nhạc EDM"));
        playlistList.add(new Playlist(url1 + "nhac_pop.png","Nhạc POP"));
        playlistList.add(new Playlist(url1 + "nhac_rock.png","Nhạc Rock"));
        playlistList.add(new Playlist(url1 + "nhac_karaoke.jpg","nhạc karaoke"));
        playlistList.add(new Playlist(url1 + "nhac_dj_nonstop.png","nhạc DJ Nonstop"));
        playlistList.add(new Playlist(url1 + "nhac_thieu_nhi.png","nhạc thiếu nhi"));
        playlistList.add(new Playlist(url1 + "nhac_rb.png","nhạc r&b"));
        playlistList.add(new Playlist(url1 + "nhac_blue.png","nhạc blue"));
        playlistList.add(new Playlist(url1 + "nhac-jazz.png","nhạc jazz"));
        playlistList.add(new Playlist(url1 + "nhac_latin.png","nhạc latin"));
        playlistList.add(new Playlist(url1 + "hoatran.jpg","Nhạc Việt Nam"));
        playlistList.add(new Playlist(url1 + "nhac_usuk.png","nhạc âu Mỹ"));
        playlistList.add(new Playlist(url1 + "nhac_tieng_anh.jpg","Nhạc Tiếng anh"));
        playlistList.add(new Playlist(url1 + "nhac_nhat.png","Nhạc Nhật"));
        playlistList.add(new Playlist(url1 + "nhac_hoa.png","Nhạc Hoa"));
        playlistList.add(new Playlist(url1 + "nhac_han.png","nhạc Hàn"));
        playlistList.add(new Playlist(url1 + "nhac_thai.png","nhạc Thái"));
        playlistList.add(new Playlist(url1 + "nhac_gum.png","nhạc tập gym"));
        playlistList.add(new Playlist(url1 + "nhac_tam_trang.png","nhạc tâm trạng"));
        playlistList.add(new Playlist(url1 + "xom-cafe.png","nhạc quán cà phê"));
        playlistList.add(new Playlist(url1 + "nhac_phim.png","nhạc phim"));
        playlistList.add(new Playlist(url1 + "maxresdefault.jpg","Nhạc tình yêu"));
        playlistList.add(new Playlist(url1 + "Nhac_piano.png","Nhạc piano"));
        playlistList.add(new Playlist(url1 + "nhac_edm.png","nhạc game"));
        playlistList.add(new Playlist(url1 + "nhac_acoustic.png","nhạc acoustic"));
        playlistList.add(new Playlist(url1 + "nhac_rap.png","Nhạc rap"));
        playlistList.add(new Playlist(url1 + "nhac_xuan.png","nhạc xuân"));

    }
}