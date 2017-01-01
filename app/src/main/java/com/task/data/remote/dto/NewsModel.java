
package com.task.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel {

    @SerializedName("status")
    private String status;

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("section")
    private String section;

    @SerializedName("last_updated")
    private String lastUpdated;

    @SerializedName("num_results")
    private Long numResults;

    @SerializedName("results")
    private List<NewsItem> newsItems = null;

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return The lastUpdated
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated The last_updated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return The numResults
     */
    public Long getNumResults() {
        return numResults;
    }

    /**
     * @param numResults The num_results
     */
    public void setNumResults(Long numResults) {
        this.numResults = numResults;
    }

    /**
     * @return The newsItems
     */
    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    /**
     * @param newsItems The newsItems
     */
    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

}
