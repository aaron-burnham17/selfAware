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
//synchronized Occurences: 0
//do Occurences: 0
//float Occurences: 0
//while Occurences: 1
//protected Occurences: 0
//else Occurences: 0
//continue Occurences: 0
//catch Occurences: 3
//if Occurences: 1
//case Occurences: 0
//new Occurences: 4
//package Occurences: 1
//static Occurences: 1
//void Occurences: 3
//byte Occurences: 0
//double Occurences: 0
//finally Occurences: 0
//this Occurences: 0
//strictfp Occurences: 0
//throws Occurences: 0
//enum Occurences: 0
//extends Occurences: 0
//null Occurences: 0
//transient Occurences: 0
//final Occurences: 1
//true Occurences: 1
//try Occurences: 2
//implements Occurences: 1
//private Occurences: 2
//const Occurences: 0
//import Occurences: 12
//for Occurences: 2
//interface Occurences: 0
//long Occurences: 0
//switch Occurences: 0
//default Occurences: 0
//goto Occurences: 0
//public Occurences: 5
//native Occurences: 0
//assert Occurences: 0
//class Occurences: 2
//break Occurences: 0
//false Occurences: 0
//volatile Occurences: 0
//abstract Occurences: 0
//int Occurences: 1
//instanceof Occurences: 0
//super Occurences: 0
//boolean Occurences: 0
//throw Occurences: 0
//char Occurences: 0
//short Occurences: 0
//return Occurences: 0
//Total Occurences: 43