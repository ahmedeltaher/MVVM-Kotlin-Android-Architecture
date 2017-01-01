
package com.task.data.remote.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsItem implements Parcelable {

    @SerializedName("section")
    private String section;

    @SerializedName("subsection")
    private String subsection;

    @SerializedName("title")
    private String title;

    @SerializedName("abstract")
    private String _abstract;

    @SerializedName("url")
    private String url;

    @SerializedName("byline")
    private String byline;

    @SerializedName("item_type")
    private String itemType;

    @SerializedName("updated_date")
    private String updatedDate;

    @SerializedName("created_date")
    private String createdDate;

    @SerializedName("published_date")
    private String publishedDate;

    @SerializedName("material_type_facet")
    private String materialTypeFacet;

    @SerializedName("kicker")
    private String kicker;

    @SerializedName("des_facet")
    private List<String> desFacet = null;

    @SerializedName("org_facet")
    private List<String> orgFacet = null;

    @SerializedName("per_facet")
    private List<String> perFacet = null;

    @SerializedName("geo_facet")
    private List<String> geoFacet = null;

    @SerializedName("multimedia")
    private List<Multimedium> multimedia = null;

    @SerializedName("short_url")
    private String shortUrl;

    /**
     * @return The section
     */
    public String getSection() {
        return section;
    }

    /**
     * @param section The section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * @return The subsection
     */
    public String getSubsection() {
        return subsection;
    }

    /**
     * @param subsection The subsection
     */
    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The _abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * @param _abstract The abstract
     */
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

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
     * @return The byline
     */
    public String getByline() {
        return byline;
    }

    /**
     * @param byline The byline
     */
    public void setByline(String byline) {
        this.byline = byline;
    }

    /**
     * @return The itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType The item_type
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return The updatedDate
     */
    public String getUpdatedDate() {
        return updatedDate;
    }

    /**
     * @param updatedDate The updated_date
     */
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * @return The createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate The created_date
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return The publishedDate
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * @param publishedDate The published_date
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * @return The materialTypeFacet
     */
    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    /**
     * @param materialTypeFacet The material_type_facet
     */
    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    /**
     * @return The kicker
     */
    public String getKicker() {
        return kicker;
    }

    /**
     * @param kicker The kicker
     */
    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    /**
     * @return The desFacet
     */
    public List<String> getDesFacet() {
        return desFacet;
    }

    /**
     * @param desFacet The des_facet
     */
    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    /**
     * @return The orgFacet
     */
    public List<String> getOrgFacet() {
        return orgFacet;
    }

    /**
     * @param orgFacet The org_facet
     */
    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    /**
     * @return The perFacet
     */
    public List<String> getPerFacet() {
        return perFacet;
    }

    /**
     * @param perFacet The per_facet
     */
    public void setPerFacet(List<String> perFacet) {
        this.perFacet = perFacet;
    }

    /**
     * @return The geoFacet
     */
    public List<String> getGeoFacet() {
        return geoFacet;
    }

    /**
     * @param geoFacet The geo_facet
     */
    public void setGeoFacet(List<String> geoFacet) {
        this.geoFacet = geoFacet;
    }

    /**
     * @return The multimedia
     */
    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    /**
     * @param multimedia The multimedia
     */
    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    /**
     * @return The shortUrl
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * @param shortUrl The short_url
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.section);
        dest.writeString(this.subsection);
        dest.writeString(this.title);
        dest.writeString(this._abstract);
        dest.writeString(this.url);
        dest.writeString(this.byline);
        dest.writeString(this.itemType);
        dest.writeString(this.updatedDate);
        dest.writeString(this.createdDate);
        dest.writeString(this.publishedDate);
        dest.writeString(this.materialTypeFacet);
        dest.writeString(this.kicker);
        dest.writeStringList(this.desFacet);
        dest.writeStringList(this.orgFacet);
        dest.writeStringList(this.perFacet);
        dest.writeStringList(this.geoFacet);
        dest.writeList(this.multimedia);
        dest.writeString(this.shortUrl);
    }

    public NewsItem() {
    }

    protected NewsItem(Parcel in) {
        this.section = in.readString();
        this.subsection = in.readString();
        this.title = in.readString();
        this._abstract = in.readString();
        this.url = in.readString();
        this.byline = in.readString();
        this.itemType = in.readString();
        this.updatedDate = in.readString();
        this.createdDate = in.readString();
        this.publishedDate = in.readString();
        this.materialTypeFacet = in.readString();
        this.kicker = in.readString();
        this.desFacet = in.createStringArrayList();
        this.orgFacet = in.createStringArrayList();
        this.perFacet = in.createStringArrayList();
        this.geoFacet = in.createStringArrayList();
        this.multimedia = new ArrayList<Multimedium>();
        in.readList(this.multimedia, Multimedium.class.getClassLoader());
        this.shortUrl = in.readString();
    }

    public static final Parcelable.Creator<NewsItem> CREATOR = new Parcelable.Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel source) {
            return new NewsItem(source);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };
}
