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
    private Library l;
    private Playlist p1;
    private Playlist p2;
    private Song s1;
    private Song s2;
    private Song s3;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        l = new Library("JordanW");
        p1 = new Playlist("Hood Beats", "Rap");
        p2 = new Playlist("Country Thunder", "Country");
        l.addPlaylist(p1);
        l.addPlaylist(p2);
        s1 = new Song("Monster","Kanye West","Rap",200);
        s2 = new Song("Space Cowboy","Kasey Musgraves","Country", 250);
        s3 = new Song("Bonfire","Childish Gambino","Rap", 300);
        p1.addSong(s1);
        p2.addSong(s2);
        p1.addSong(s3);
    }

    @Test
    void testWriteLibrary() {
        testWriter.write(l);
        testWriter.close();

        try {
            List<Playlist> playlists = Reader.readPlaylists(new File(TEST_FILE));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
