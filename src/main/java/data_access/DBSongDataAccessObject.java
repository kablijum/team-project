package data_access;

import entity.Review;
import entity.Song;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The DAO for song data.
 * Song data structure: {"username": "songdata", "password": "1234", "info": [{"id": , "name": "", "artist": "", "rating": , "reviews": []}]}
 */
public class DBSongDataAccessObject {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";
    private static final String ADMIN = "songdata";
    private static final String ADMIN_PASSWORD = "1234";
    private static final String INFO = "info";

    private final Song song;

    public DBSongDataAccessObject(Song song) {
        this.song = song;
    }

    public boolean songExists(String songId) {
        List<Song> songDB = getSongDatabase();
        for (Song songDBItem : songDB) {
            if (songId.equals(songDBItem.getId())) {
                return true;
            }
        }
        return false;
    }

    public void saveSong(Song song) {
        if (!adminExists()) {
            createAdmin();
        }
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, ADMIN);
        requestBody.put(PASSWORD, ADMIN_PASSWORD);

        List<Song> songDB = getSongDatabase();
        songDB.add(song);

        JSONArray songDBJSON = new JSONArray();
        for (Song songDBItem : songDB) {
            SongMapper songMapper = new SongMapper(songDBItem);
            songDBJSON.put(songMapper.mapSongtoJSON());
        }
        requestBody.put(INFO, songDBJSON);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }


    public List<Song> getSongDatabase() {
        if (!adminExists()) {
            createAdmin();
            return new ArrayList<>();
        }

        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", ADMIN))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject adminJSONObject = responseBody.getJSONObject("user");
                final JSONArray songsJSONArray = adminJSONObject.getJSONArray(INFO);
                final List<Song> songs = new ArrayList<>();

                for (int i = 0; i < songsJSONArray.length(); i++) {
                    final JSONObject songJSONObject = songsJSONArray.getJSONObject(i);
                    SongMapper songMapper = new SongMapper(songJSONObject);
                    Song mappedSong = songMapper.mapJSONtoSong();
                    songs.add(mappedSong);
                }
                return songs;
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean adminExists() {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", ADMIN))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createAdmin() {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, ADMIN);
        requestBody.put(PASSWORD, ADMIN_PASSWORD);
        requestBody.put(INFO, new JSONArray());

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
}
