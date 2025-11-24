package use_case.search;

import entity.Song;

import java.util.List;

public interface SearchUserDataAccessInterface {
    List<Song> search(String query) throws Exception;

    List<String> getInfo(int songID) throws Exception;
}
