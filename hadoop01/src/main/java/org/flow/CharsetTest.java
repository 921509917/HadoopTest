package org.flow;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.SortedMap;

public class CharsetTest {
    public static void main(String[] args) {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet()){
            System.out.println(map.get(alias));
        }

    }
}
