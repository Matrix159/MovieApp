
package com.matrixprogramming.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/***
 * POGO to get every/anything on a movie.
 */
public class Result {

    /** Poster path of movie. **/
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    /** Boolean if the movie is adult only. **/
    @SerializedName("adult")
    @Expose
    private boolean adult;
    /** String that is the overview of the movie. **/
    @SerializedName("overview")
    @Expose
    private String overview;
    /** String that is the release date of the movie. **/
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    /** List of integers that is the genre IDs of the movie. **/
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    /** Int that is the id of the movie. **/
    @SerializedName("id")
    @Expose
    private int id;
    /** String that is the original title of the movie. **/
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    /** String that is the original language of the movie. **/
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    /** String that is the title of the movie. **/
    @SerializedName("title")
    @Expose
    private String title;
    /** String that is the backdrop path of the movie. **/
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    /** Double expressing the popularity of the movie. **/
    @SerializedName("popularity")
    @Expose
    private double popularity;
    /** Int that is teh vote count of the movie. **/
    @SerializedName("vote_count")
    @Expose
    private int voteCount;
    /** Boolean if the movie has a video. **/
    @SerializedName("video")
    @Expose
    private boolean video;
    /** Double expresing the voting average of the movie. **/
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    /***
     * Gets poster path.
     * @return Poster path
     */
    public String getPosterPath() {
        return posterPath;
    }

    /***
     * Sets new poster path.
     * @param newPosterPath New poster path to replace old one
     */
    public void setPosterPath(final String newPosterPath) {
        this.posterPath = newPosterPath;
    }

    /***
     * Checks if the movie is adult only (true/false).
     * @return Boolean if movie is adult only
     */
    public boolean isAdult() {
        return adult;
    }

    /***
     * Sets if a movie is adult only (true/false).
     * @param newAdult New boolean to replace old value
     */
    public void setAdult(final boolean newAdult) {
        this.adult = newAdult;
    }

    /***
     * Gets overview of movie.
     * @return Movie overview
     */
    public String getOverview() {
        return overview;
    }

    /***
     * Sets new overview description.
     * @param newOverview New overview to replace old one
     */
    public void setOverview(final String newOverview) {
        this.overview = newOverview;
    }

    /***
     * Gets release date of movie.
     * @return Movie release date.
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /***
     * Sets new release date for movie.
     * @param newReleaseDate New release date to replace old one
     */
    public void setReleaseDate(final String newReleaseDate) {
        this.releaseDate = newReleaseDate;
    }

    /***
     * Gets genre IDs.
     * @return Genre IDs
     */
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    /***
     * Sets new genre IDs.
     * @param newGenreIds New genre ids to replace old one(s)
     */
    public void setGenreIds(final List<Integer> newGenreIds) {
        this.genreIds = newGenreIds;
    }

    /***
     * Gets movie ID.
     * @return ID
     */
    public int getId() {
        return id;
    }

    /***
     * Sets a new movie ID.
     * @param newId New id to replace old one
     */
    public void setId(final int newId) {
        this.id = newId;
    }

    /***
     * Gets original title.
     * @return Original title
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /***
     * Sets new original title.
     * @param newOriginalTitle New original title to replce old one.
     */
    public void setOriginalTitle(final String newOriginalTitle) {
        this.originalTitle = newOriginalTitle;
    }

    /***
     * Gets original language.
     * @return Original language
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /***
     * Sets new original language.
     * @param newOriginalLanguage New original language to replace old one
     */
    public void setOriginalLanguage(final String newOriginalLanguage) {
        this.originalLanguage = newOriginalLanguage;
    }

    /***
     * Gets title.
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /***
     * Sets the title to a new value.
     * @param newTitle New title to replace the old one
     */
    public void setTitle(final String newTitle) {
        this.title = newTitle;
    }

    /***
     * Gets the backdrop path.
     * @return Backdrop path
     */
    public Object getBackdropPath() {
        return backdropPath;
    }

    /***
     * Sets teh new backdrop path.
     * @param newBackdropPath New backdrop path to replace the old one
     */
    public void setBackdropPath(final String newBackdropPath) {
        this.backdropPath = newBackdropPath;
    }

    /***
     * Gets the popularity.
     * @return Popularity to be obtained
     */
    public double getPopularity() {
        return popularity;
    }

    /***
     * Sets the new popularity.
     * @param newPopularity New popularity to replace old one
     */
    public void setPopularity(final double newPopularity) {
        this.popularity = newPopularity;
    }

    /***
     * Gets the vote count.
     * @return Vote count
     */
    public int getVoteCount() {
        return voteCount;
    }

    /***
     * Sets the vote count.
     * @param newVoteCount New vote count.
     */
    public void setVoteCount(final int newVoteCount) {
        this.voteCount = newVoteCount;
    }

    /***
     * Returns if the video is true/false.
     * @return True/false based on video
     */
    public boolean isVideo() {
        return video;
    }

    /***
     * Sets video to input value (true/false).
     * @param newVideo New video that is to replace the old one
     */
    public void setVideo(final boolean newVideo) {
        this.video = newVideo;
    }

    /***
     * Gets the voting average of the movie.
     * @return VoteAverage
     */
    public double getVoteAverage() {
        return voteAverage;
    }

    /***
     * Sets a new voting average to input.
     * @param neeVoteAverage New voting average
     */
    public void setVoteAverage(final double neeVoteAverage) {
        this.voteAverage = neeVoteAverage;
    }
    /***
     * Turns object into string.
     * @return String New string that was formed form the passed object
     */
    @Override
    public String toString() {
        return "Result{"
                + "posterPath='" + posterPath + '\''
                + ", adult=" + adult
                + ", overview='" + overview + '\''
                + ", releaseDate='" + releaseDate + '\''
                + ", genreIds=" + genreIds + ", id=" + id
                + ", originalTitle='" + originalTitle + '\''
                + ", originalLanguage='" + originalLanguage + '\''
                + ", title='" + title + '\''
                + ", backdropPath='" + backdropPath + '\''
                + ", popularity=" + popularity + ", voteCount=" + voteCount
                + ", video=" + video + ", voteAverage=" + voteAverage + '}';
    }
}
