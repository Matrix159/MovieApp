
package main.com.matrixprogramming.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/***
 * POJO to get every/anything on a movie.
 */
public class Result {

    /**
     * Poster path of movie.
     **/
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    /**
     * Boolean if the movie is adult only.
     **/
    @SerializedName("adult")
    @Expose
    private boolean adult;
    /**
     * String that is the overview of the movie.
     **/
    @SerializedName("overview")
    @Expose
    private String overview;
    /**
     * String that is the release date of the movie.
     **/
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    /**
     * List of integers that is the genre IDs of the movie.
     **/
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    /**
     * Int that is the id of the movie.
     **/
    @SerializedName("id")
    @Expose
    private int id;
    /**
     * String that is the original title of the movie.
     **/
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    /**
     * String that is the original language of the movie.
     **/
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    /**
     * String that is the title of the movie.
     **/
    @SerializedName("title")
    @Expose
    private String title;
    /**
     * String that is the backdrop path of the movie.
     **/
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    /**
     * Double expressing the popularity of the movie.
     **/
    @SerializedName("popularity")
    @Expose
    private double popularity;
    /**
     * Int that is teh vote count of the movie.
     **/
    @SerializedName("vote_count")
    @Expose
    private int voteCount;
    /**
     * Boolean if the movie has a video.
     **/
    @SerializedName("video")
    @Expose
    private boolean video;
    /**
     * Double expresing the voting average of the movie.
     **/
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
     * Checks if the movie is adult only (true/false).
     * @return Boolean if movie is adult only
     */
    public boolean isAdult() {
        return adult;
    }


    /***
     * Gets overview of movie.
     * @return Movie overview
     */
    public String getOverview() {
        return overview;
    }


    /***
     * Gets release date of movie.
     * @return Movie release date.
     */
    public String getReleaseDate() {
        return releaseDate;
    }


    /***
     * Gets genre IDs.
     * @return Genre IDs
     */
    public List<Integer> getGenreIds() {
        return genreIds;
    }


    /***
     * Gets movie ID.
     * @return ID
     */
    public int getId() {
        return id;
    }


    /***
     * Gets original title.
     * @return Original title
     */
    public String getOriginalTitle() {
        return originalTitle;
    }


    /***
     * Gets original language.
     * @return Original language
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }


    /***
     * Gets title.
     * @return Title
     */
    public String getTitle() {
        return title;
    }


    /***
     * Gets the backdrop path.
     * @return Backdrop path
     */
    public String getBackdropPath() {
        return backdropPath;
    }


    /***
     * Gets the popularity.
     * @return Popularity to be obtained
     */
    public double getPopularity() {
        return popularity;
    }


    /***
     * Gets the vote count.
     * @return Vote count
     */
    public int getVoteCount() {
        return voteCount;
    }


    /***
     * Returns if the video is true/false.
     * @return True/false based on video
     */
    public boolean isVideo() {
        return video;
    }


    /***
     * Gets the voting average of the movie.
     * @return VoteAverage
     */
    public double getVoteAverage() {
        return voteAverage;
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
