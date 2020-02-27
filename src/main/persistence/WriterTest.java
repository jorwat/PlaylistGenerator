package persistence;

import model.Library;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE = "./data/testPlaylist.txt";
    private Writer testWriter;
    private Library l1;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        l1 = new Library("Jordan");
        Playlist p1 = new Playlist("Hood Beats", "Rap");
        Playlist p2 = new Playlist("Country Thunder", "Country");
        l1.addPlaylist(p1);
        l1.addPlaylist(p2);
        Song s1 = new Song("Monster", "Kanye West", "Rap", 200);
        Song s2 = new Song("Space Cowboy", "Kasey Musgraves", "Country", 250);
        Song s3 = new Song("Bonfire", "Childish Gambino", "Rap", 300);
        p1.addSong(s1);
        p2.addSong(s2);
        p1.addSong(s3);
    }

    @Test
    void testWriteLibrary() {
        testWriter.write(l1);
        testWriter.close();

        try {
            List<Playlist> playlists = Reader.readPlaylists(new File(TEST_FILE));
            Playlist rap = playlists.get(0);
            assertEquals("Jordan",rap.getTag());
            assertEquals("Hood Beats",rap.getPlaylistName());
            assertEquals("Rap", rap.getPlaylistGenre());
            assertEquals(2, rap.getTotalSongs());
            assertEquals(500, rap.getTotalRuntime());

            Playlist country = playlists.get(1);
            assertEquals("Jordan", country.getTag());
            assertEquals("Country Thunder",country.getPlaylistName());
            assertEquals("Country", country.getPlaylistGenre());
            assertEquals(1, country.getTotalSongs());
            assertEquals(250, country.getTotalRuntime());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

}
