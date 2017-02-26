import com.matrixprogramming.controller.MovieAPI;
import com.matrixprogramming.model.DiscoverModel;
import org.junit.*;
import org.junit.Assert;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Eldridge on 2/24/2017.
 */
public class APITest
{
    static MovieAPI movieAPI;
    @BeforeClass
    public static void setup()
    {
        movieAPI = new MovieAPI();
    }
    @Test
    public void apiTest()
    {
        Assert.assertNotNull(movieAPI.getController());
        LocalDate now = LocalDate.now();
        LocalDate twoWeeksAgo = now.minusWeeks(2);
        LocalDate twoWeeksAhead = now.plusWeeks(2);
        Call<DiscoverModel> call;
        try
        {
            call = movieAPI.getController().discover("en-us", "popularity.desc",
                    "3|2", "US", "en", true,
                    1, twoWeeksAgo.toString(),
                    twoWeeksAhead.toString());
            DiscoverModel discoverModel = call.execute().body();
            Assert.assertNotNull(discoverModel.getResults());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
