package main.com.matrixprogramming.model;

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
     * Gets results.
     * @return Results
     */
    public List<Result> getResults() {
        return results;
    }



    /***
     * Gets total results.
     * @return total results
     */
    public int getTotalResults() {
        return totalResults;
    }



    /***
     * Gets total pages.
     * @return total pages.
     */
    public int getTotalPages() {
        return totalPages;
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
