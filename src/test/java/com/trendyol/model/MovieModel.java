package com.trendyol.model;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.util.*;

public class MovieModel extends BaseModel {

    final String MOVIE_NAME="Harry Potter and the Sorcerer's Stone";
    String year = null;
    String imdbId = null;
    String released = null;
    String title = null;

    public String getMovieImdbId() {
        Response response = super.DoRequest();
        List<HashMap<String, String>> movies = response.jsonPath().getList("Search");

        for (int i = 0; i < movies.size() - 1; i++) {
            HashMap<String, String> movieInfo = movies.get(i);
            title = movieInfo.get("Title");
            if (title.equals(MOVIE_NAME)) {
                imdbId = movieInfo.get("imdbID");
            }
        }
        if (imdbId.equals(null))   return "invalidId";
        return imdbId;
    }

    public Dictionary<String , String> searchMovieById(String movieId) {
        RequestSpecification request = SetRequest();

        String searchById = "?apikey=".concat(API_KEY).concat("&i=").concat(movieId);
        Response response = request
                .when()
                .get(searchById)
                .then()
                .statusCode(200).extract().response();

        released = response.jsonPath().getString("Released");
        year = response.jsonPath().getString("Year");
        title = response.jsonPath().getString("Title");

        Dictionary<String , String> movieInfo = new Hashtable<>();
        movieInfo.put("Released" ,released);
        movieInfo.put("Year" , year);
        movieInfo.put("Title" , title);

        return  movieInfo;
    }
}
