package com.example.appmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Songs {

@SerializedName("id_song")
@Expose
private String idSong;
@SerializedName("name_song")
@Expose
private String nameSong;
@SerializedName("image_song")
@Expose
private String imageSong;
@SerializedName("singer")
@Expose
private String singer;
@SerializedName("link_song")
@Expose
private String linkSong;
@SerializedName("feedback")
@Expose
private String feedback;

public String getIdSong() {
return idSong;
}

public void setIdSong(String idSong) {
this.idSong = idSong;
}

public String getNameSong() {
return nameSong;
}

public void setNameSong(String nameSong) {
this.nameSong = nameSong;
}

public String getImageSong() {
return imageSong;
}

public void setImageSong(String imageSong) {
this.imageSong = imageSong;
}

public String getSinger() {
return singer;
}

public void setSinger(String singer) {
this.singer = singer;
}

public String getLinkSong() {
return linkSong;
}

public void setLinkSong(String linkSong) {
this.linkSong = linkSong;
}

public String getFeedback() {
return feedback;
}

public void setFeedback(String feedback) {
this.feedback = feedback;
}

}