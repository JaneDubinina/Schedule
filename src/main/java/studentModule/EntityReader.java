package studentModule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface EntityReader<T> {
    public List<T> read() throws IOException;
}
