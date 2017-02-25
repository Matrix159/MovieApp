package com.matrixprogramming.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/***
 * Class to set up getters and setters.
 */
public class DiscoverModel {
    /** **/
    @SerializedName("page")
    @Expose
    private int page;
    /** **/
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    /** **/
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    /** **/
    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    /***
     * Gets page.
     * @return page
     */
    public int getPage() {
        return page;
    }

    /***
     * Sets new Page.
     * @param newPage New page to replace old one
     */
    public void setPage(final int newPage) {
        this.page = newPage;
    }

    /***
     * Gets results.
     * @return Results
     */
    public List<Result> getResults() {
        return results;
    }

    /***
     * Sets new results.
     * @param newResults New results to replace old.
     */
    public void setResults(final List<Result> newResults) {
        results = newResults;
    }

    /***
     * Gets total results.
     * @return total results
     */
    public int getTotalResults() {
        return totalResults;
    }

    /***
     * Sets new total results.
     * @param newTotalResults New total results to replace old.
     */
    public void setTotalResults(final int newTotalResults) {
        this.totalResults = newTotalResults;
    }

    /***
     * Gets total pages.
     * @return total pages.
     */
    public int getTotalPages() {
        return totalPages;
    }

    /***
     * Stes new total pages.
     * @param newTotalPages New total pages to replace old
     */
    public void setTotalPages(final int newTotalPages) {
        this.totalPages = newTotalPages;
    }

    /***
     * Turns movie object into string.
     * @return String New string form form the object passes in
     */
    @Override
    public String toString() {
        return "DiscoverModel{" + "page=" + page
                + ", results=" + results
                + ", totalResults=" + totalResults
                + ", totalPages=" + totalPages + '}';
    }
}
