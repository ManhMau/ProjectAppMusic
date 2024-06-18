package com.example.appmusic.Service;

import com.example.appmusic.Model.Album;
import com.example.appmusic.Model.Songs;
import com.example.appmusic.Model.Banner;
import com.example.appmusic.Model.ChuDeVaTheLoai;
import com.example.appmusic.Model.Playlist;
import com.example.appmusic.Model.Songs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {
     @GET("songbanner.php")
    Call<List<Banner>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlayListCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChuDeVaTheLoai> GetDataChuDeVaTheLoai();

    @GET("albumhot.php")
        Call<List<Album>> GetAlbumHot();

    @GET("baihatyeuthich.php")
    Call<List<Songs>> GetBaiHatHot();

}

