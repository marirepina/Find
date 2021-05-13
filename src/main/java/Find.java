package main.java;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Find {
    private final boolean depth;
    private final File directoryName;
    private final File fileName;

    public Find(boolean depth, File directoryName, File fileName) {
        this.depth = depth;
        if (directoryName != null)
            this.directoryName = directoryName;
        else this.directoryName = new File (System.getProperty("user.dir"));
        this.fileName = fileName;
    }

    public List<String> main() throws IOException {
        return output(directoryName);
    }

    private List<String> output(File directoryName) {
        if (!directoryName.exists()) return Collections.singletonList("The specified file / directory is invalid");
        File[] directory = directoryName.isDirectory() ? directoryName.listFiles() : new File[]{directoryName};
        assert directory != null;
        List<String> list = new ArrayList<>(find(directory, false));
        if (depth) {
            File[] subdirectory;
            for (File file : directory) {
                if (file.isDirectory()) {
                    subdirectory = file.listFiles();
                    assert subdirectory != null;
                    list.addAll(find(subdirectory, true));
                }
            }
        }
        return list;
    }

    private List<String> find(File[] directoryName, boolean subDir) {
        List<String> find = new ArrayList<>();
        for (File file : directoryName){
            if (file.getName().equals(fileName.getName()))
                if (subDir) {
                    find.add(Paths.get(this.directoryName.getPath()).relativize(Paths.get(file.getParent())) +"\\"+ file.getName());
                } else {
                    find.add(file.getName());
                }
        }
        return find;
    }
}
