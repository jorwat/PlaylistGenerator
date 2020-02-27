package persistence;


import java.io.PrintWriter;

// Represents data that can be saved to file
// NOTE: code from Teller File after studying how it works. Since all implemented through java.io
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes the saved state to printWriter
    void save(PrintWriter printWriter);
}
