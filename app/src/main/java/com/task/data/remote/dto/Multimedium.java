
package com.task.data.remote.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Multimedium implements Parcelable {

    @SerializedName("url")
    private String url;

    @SerializedName("format")
    private String format;

    @SerializedName("height")
    private Long height;

    @SerializedName("width")
    private Long width;

    @SerializedName("type")
    private String type;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("caption")
    private String caption;

    @SerializedName("copyright")
    private String copyright;

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return The height
     */
    public Long getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(Long height) {
        this.height = height;
    }

    /**
     * @return The width
     */
    public Long getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    public void setWidth(Long width) {
        this.width = width;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * @param subtype The subtype
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     * @return The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return The copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * @param copyright The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.format);
        dest.writeValue(this.height);
        dest.writeValue(this.width);
        dest.writeString(this.type);
        dest.writeString(this.subtype);
        dest.writeString(this.caption);
        dest.writeString(this.copyright);
    }

    public Multimedium() {
    }

    protected Multimedium(Parcel in) {
        this.url = in.readString();
        this.format = in.readString();
        this.height = (Long) in.readValue(Long.class.getClassLoader());
        this.width = (Long) in.readValue(Long.class.getClassLoader());
        this.type = in.readString();
        this.subtype = in.readString();
        this.caption = in.readString();
        this.copyright = in.readString();
    }

    public static final Parcelable.Creator<Multimedium> CREATOR = new Parcelable.Creator<Multimedium>() {
        @Override
        public Multimedium createFromParcel(Parcel source) {
            return new Multimedium(source);
        }

        @Override
        public Multimedium[] newArray(int size) {
            return new Multimedium[size];
        }
    };
}
