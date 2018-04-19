package edu.gcccd.csis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageImpl implements Language{
    private int totalCount;
    private HashMap<String,Integer> occurencesTracker = new HashMap<String, Integer>(ReservedWords.length);
    public LanguageImpl(){
        totalCount = 0;
        for(String s: ReservedWords){
            occurencesTracker.put(s,0);
        }
    }

    public HashMap<String, Integer> getHash(){ //Need for testclass
        return this.occurencesTracker;
    }

    @Override
    public void occurrences(String sourcefile) {
        try {
            String s;
            Path path = Paths.get(sourcefile);
            Pattern wordsOnly = Pattern.compile("(\\b|\\s)*(\\b|\\s)");
            Scanner input = new Scanner(path).useDelimiter(wordsOnly);
            while (input.hasNext()) {
                s = input.next();
                if (occurencesTracker.containsKey(s)) {
                    occurencesTracker.put(s, occurencesTracker.get(s) + 1);
                    totalCount++;
                }
            }
        }
        catch(InvalidPathException e){
            System.err.println("Bad file input");
        }
        catch(IOException IOException){
            System.err.println("Bad IO");
        }
    }

    @Override
    public void append(String sourcefile){
        try{
            Path path = Paths.get(sourcefile);
            FileWriter toFile = new FileWriter(sourcefile,true);
            for(String s: occurencesTracker.keySet()){
                toFile.write("//" + s + " Occurences: "  + occurencesTracker.get(s) + "\n");
            }
            toFile.write("//Total Occurences: " + totalCount);
            toFile.close();
        }
        catch(IOException IOException){
            System.err.println("IO Exception is present");
        }

    }

    public static void main(String args[]){
        final String SOURCEFILE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + LanguageImpl.class.getName().replace(".", File.separator) + ".java";
        LanguageImpl k = new LanguageImpl();
        k.occurrences(SOURCEFILE);
        k.append(SOURCEFILE);

    }

}