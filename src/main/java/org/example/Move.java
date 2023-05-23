package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Move {

    public boolean move() throws IOException {
        Properties prop = new Properties();

        prop.load(Move.class.getClassLoader().getResourceAsStream("application.properties"));

        String getSrc = (String) prop.get("src");

        System.out.println("src: " + getSrc);
        String getTarget = (String) prop.get("target");
        System.out.println("target: " + getTarget);

        List<File> sourceFileList = getFileListFromDirectoryAndFilterByDate(getSrc);

        for (File sourceFile : sourceFileList) {
            Path target = new File(getTarget + "\\" +  sourceFile.getName()).toPath();
            System.out.println("File abs path and name: " + sourceFile.getAbsolutePath());
            System.out.println("File path and name: " + sourceFile.getName());
            Files.copy(sourceFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
        }
        return true;
    }

    public static List<File> getFileListFromDirectoryAndFilterByDate(String directoryPath) {
        List<File> files = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path path : paths) {
                files.add(new File(path.toUri()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return files;
    }

}
