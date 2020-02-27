package persistence;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// A writer that can write library data to a file
// NOTE: Writer code from Teller File after studying how it works. Since all implemented through java.io
public class Writer {
    private PrintWriter printWriter;

    // EFFECTS: constructs a writer that will write the library data to file
    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");
    }

    // MODIFIES: this
    // EFFECTS: writes save state to file
    public void write(Saveable saveable) {
        saveable.save(printWriter);
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing data
    public void close() {
        printWriter.close();
    }
}
