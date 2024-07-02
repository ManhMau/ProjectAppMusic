package com.example.appmusic.Service;

import com.example.appmusic.Model.Album;
import com.example.appmusic.Model.Songs;
import com.example.appmusic.Model.Banner;
import com.example.appmusic.Model.ChuDeVaTheLoai;
import com.example.appmusic.Model.Playlist;
import com.example.appmusic.Model.Songs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Songs>> GetDanhsachbaihattheobanner(@Field("idbanner") String idbanner);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Songs>> GetSearchBaihat(@Field("tukhoa") String tukhoa);

}

