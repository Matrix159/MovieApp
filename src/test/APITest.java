

import main.com.matrixprogramming.controller.MovieAPI;
import main.com.matrixprogramming.model.DiscoverModel;
import main.com.matrixprogramming.model.Result;
import org.junit.*;
import org.junit.Assert;
import retrofit2.Call;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/***
 * Test cases
 */
public class APITest
{
    private static MovieAPI movieAPI;


    @BeforeClass
    public static void setup() throws IOException
    {
        movieAPI = new MovieAPI();
    }

    @Test
    public void apiTest() throws IOException
    {
        Assert.assertNotNull(movieAPI.getController());
        LocalDate now = LocalDate.now();
        LocalDate twoWeeksAgo = now.minusWeeks(2);
        LocalDate twoWeeksAhead = now.plusWeeks(2);
        Call<DiscoverModel> call;
        call = movieAPI.getController().discover("en-us", "popularity.desc",
                "3|2", "US", "en", false,
                1, twoWeeksAgo.toString(),
                twoWeeksAhead.toString());
        DiscoverModel discoverModel = call.execute().body();
        Assert.assertNotNull(discoverModel.getResults());
        List<Result> results = discoverModel.getResults();
        Assert.assertTrue(discoverModel.getPage() >= 1);
        Assert.assertTrue(discoverModel.getTotalResults() >= 0);
        Assert.assertTrue(discoverModel.getTotalPages() >= 1);
        Assert.assertNotNull(discoverModel.toString());
        for(Result result : results)
        {
            Assert.assertTrue(!result.isAdult());
            Assert.assertTrue(result.getOverview() != null && result.getOverview().length() >= 1);
            Assert.assertTrue(result.getReleaseDate() != null && result.getReleaseDate().length() == 10);
            Assert.assertNotNull(result.getGenreIds());
            Assert.assertTrue(result.getId() >= 0);
            Assert.assertTrue(result.getOriginalTitle() != null
                    && result.getOriginalTitle().length() >= 0);
            Assert.assertTrue(result.getOriginalLanguage() != null
                    && result.getOriginalLanguage().length() >= 0);
            Assert.assertTrue(result.getTitle() != null
                    && result.getTitle().length() >= 0);
            if(result.getPosterPath() != null)
            Assert.assertTrue(result.getPosterPath().length() >= 6);
            if(result.getBackdropPath() != null)
                Assert.assertTrue(result.getBackdropPath().length() >= 6);
            Assert.assertTrue(result.getPopularity() >= 0.0);
            Assert.assertTrue(result.getVoteCount() >= 0);
            Assert.assertTrue(!result.isVideo());
            Assert.assertTrue(result.getVoteAverage() >= 0.0);
            Assert.assertNotNull(result.toString());
        }

    }
}
