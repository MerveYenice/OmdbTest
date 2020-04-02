package com.trendyol.test;

import com.trendyol.model.MovieModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Dictionary;

public class MovieTest {

    // expected results for movie test assertions
    private final String EXPECTED_IMDB_ID = "tt0241527";
    private final String EXPECTED_RELEASED = "16 Nov 2001";
    private final String EXPECTED_TITLE = "Harry Potter and the Sorcerer's Stone";
    private final String EXPECTED_YEAR = "2001";

    @Test
    public void validateMovieByIdSearch() {
        MovieModel movieModel = new MovieModel();

        //get movieId by title under the "By Search" session
        String movieId = movieModel.getMovieImdbId();
        Assert.assertEquals(movieId , EXPECTED_IMDB_ID);

        //search movie by imdbId and get info under the "By ID or Title" session
        Dictionary<String,String> movieInfo = movieModel.searchMovieById(movieId);

        //check released date
        String released = movieInfo.get("Released");
        Assert.assertEquals(released, EXPECTED_RELEASED);

        //check movie year
        String year = movieInfo.get("Year");
        Assert.assertEquals(year, EXPECTED_YEAR);

        //check movie title
        String title = movieInfo.get("Title");
        Assert.assertEquals(title , EXPECTED_TITLE);
    }
}
