package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Move {


    public boolean move() throws IOException {
        Properties prop = new Properties();


        prop.load(Move.class.getClassLoader().getResourceAsStream("application.properties"));


        String getSrc = (String) prop.get("src");
        String getTarget = (String) prop.get("target");




        Path src = new File(getSrc).toPath();

        File target = new File(getTarget);


        Files.move(src, target.toPath());




        return true;
    }
}






