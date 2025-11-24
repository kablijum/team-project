package use_case.view_song;

import java.util.List;

public interface ViewSongNewDataAccessInterface {

    List<String> getInfo(int songID) throws Exception;
}
