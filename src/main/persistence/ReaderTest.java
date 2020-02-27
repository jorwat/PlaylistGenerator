package persistence;

import model.Library;
import model.Playlist;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReaderTest {
    @Test
    void testParsePlaylistsTestFile() {
        try {
            List<Playlist> playlists = Reader.readPlaylists(new File("./data/testPlaylist.txt"));
            Playlist p1 = playlists.get(0);
            assertEquals("Jordan",p1.getTag());
            assertEquals("Hood Beats", p1.getPlaylistName());
            assertEquals("Rap", p1.getPlaylistGenre());
            assertEquals(2, p1.getTotalSongs());
            assertEquals(500, p1.getTotalRuntime());

            Playlist p2 = playlists.get(1);
            assertEquals("Jordan",p2.getTag());
            assertEquals("Country Thunder", p2.getPlaylistName());
            assertEquals("Country", p2.getPlaylistGenre());
            assertEquals(1, p2.getTotalSongs());
            assertEquals(250, p2.getTotalRuntime());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile2() {
        try {
            List<Playlist> playlists = Reader.readPlaylists(new File("./data/testPlaylist2.txt"));
            Playlist p1 = playlists.get(0);
            assertEquals("Nick",p1.getTag());
            assertEquals("OoogaBooga", p1.getPlaylistName());
            assertEquals("Alternative", p1.getPlaylistGenre());
            assertEquals(4, p1.getTotalSongs());
            assertEquals(400, p1.getTotalRuntime());

            Playlist p2 = playlists.get(1);
            assertEquals("Nick",p2.getTag());
            assertEquals("FamilyTime",p2.getPlaylistName());
            assertEquals("Children",p2.getPlaylistGenre());
            assertEquals(6, p2.getTotalSongs());
            assertEquals(595,p2.getTotalRuntime());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readPlaylists(new File("./data/testPlaylist.txt"));
        } catch (IOException e)  {
            //expected
        }
    }

}
