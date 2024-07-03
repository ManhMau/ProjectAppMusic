package com.example.appmusic.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Songs implements Parcelable {

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

    protected Songs(Parcel in) {
        idSong = in.readString();
        nameSong = in.readString();
        imageSong = in.readString();
        singer = in.readString();
        linkSong = in.readString();
        feedback = in.readString();
    }

    public static final Creator<Songs> CREATOR = new Creator<Songs>() {
        @Override
        public Songs createFromParcel(Parcel in) {
            return new Songs(in);
        }

        @Override
        public Songs[] newArray(int size) {
            return new Songs[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idSong);
        dest.writeString(nameSong);
        dest.writeString(imageSong);
        dest.writeString(singer);
        dest.writeString(linkSong);
        dest.writeString(feedback);
    }
}