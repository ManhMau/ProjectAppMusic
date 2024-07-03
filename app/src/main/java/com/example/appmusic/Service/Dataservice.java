package com.example.appmusic.Service;

import com.example.appmusic.Model.Album;
import com.example.appmusic.Model.Songs;
import com.example.appmusic.Model.Banner;
import com.example.appmusic.Model.ChuDeVaTheLoai;
import com.example.appmusic.Model.Playlist;
import com.example.appmusic.Model.Songs;
import com.example.appmusic.Model.Theme;
import com.example.appmusic.Model.Type;
import com.example.appmusic.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.http.Query;


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
    Call<List<Songs>> GetDanhsachbaihattheoquangcao(@Field("idbanner") String idbanner);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Songs>> GetDanhsachbaihattheoplaylist (@Field("idplaylist") String idplaylist );

    @GET("danhsachcacplaylistAdmin.php")
    Call<List<Playlist>> GetDanhsachplaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Songs>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("tatcacacchude.php")
    Call<List<Theme>> GetAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<Type>> GetTheloaitheochude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Songs>> GetDanhsanhbaihattheoalbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich")String luotthich, @Field("idbaihat") String idbaihat);

    @GET("profileuser.php")
    Call<User> getUserInfo(@Query("id_user") int id_user);

    @FormUrlEncoded
    @POST("updateUserInfo.php")
    Call<User> updateUserInfo(
            @Field("id_user") int id_user,
            @Field("displayname") String displayname,
            @Field("mail") String mail,
            @Field("phone") String phone,
            @Field("avatar") String avatar
    );

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Songs>> GetSearchBaihat(@Field("tukhoa") String tukhoa);


}

