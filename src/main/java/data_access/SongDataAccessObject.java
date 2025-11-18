package data_access;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Song;
import use_case.search.SearchUserDataAccessInterface;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SongDataAccessObject implements SearchUserDataAccessInterface {
    private final String token;

    public SongDataAccessObject(String token) {
        this.token = token;
    }

    @Override
    public List<Song> search(String query) throws Exception {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String endpoint = "https://api.genius.com/search?q=" + encodedQuery;

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + token);

        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

        JsonArray hits = json
                .getAsJsonObject("response")
                .getAsJsonArray("hits");

        List<Song> results = new ArrayList<>();

        for (var hit : hits) {
            JsonObject result = hit.getAsJsonObject()
                    .getAsJsonObject("result");

            int id = result.get("id").getAsInt();
            String title = result.get("title").getAsString();
            String artist = result.getAsJsonObject("primary_artist").get("name").getAsString();

            results.add(new Song(id, title, artist));
        }

        return results;
    }
}

