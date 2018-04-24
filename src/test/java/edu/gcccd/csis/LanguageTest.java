package edu.gcccd.csis;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class LanguageTest {
    private static File tmp;

    final String testString = "//synchronized Occurences: 0\n" +
            "//do Occurences: 0\n" +
            "//float Occurences: 0\n" +
            "//while Occurences: 1\n" +
            "//protected Occurences: 0\n" +
            "//else Occurences: 0\n" +
            "//continue Occurences: 0\n" +
            "//catch Occurences: 3\n" +
            "//if Occurences: 1\n" +
            "//case Occurences: 0\n" +
            "//new Occurences: 4\n" +
            "//package Occurences: 1\n" +
            "//static Occurences: 1\n" +
            "//void Occurences: 3\n" +
            "//byte Occurences: 0\n" +
            "//double Occurences: 0\n" +
            "//finally Occurences: 0\n" +
            "//this Occurences: 1\n" +
            "//strictfp Occurences: 0\n" +
            "//throws Occurences: 0\n" +
            "//enum Occurences: 0\n" +
            "//extends Occurences: 0\n" +
            "//null Occurences: 0\n" +
            "//transient Occurences: 0\n" +
            "//final Occurences: 1\n" +
            "//true Occurences: 1\n" +
            "//try Occurences: 2\n" +
            "//implements Occurences: 1\n" +
            "//private Occurences: 2\n" +
            "//const Occurences: 0\n" +
            "//import Occurences: 12\n" +
            "//for Occurences: 3\n" +
            "//interface Occurences: 0\n" +
            "//long Occurences: 0\n" +
            "//switch Occurences: 0\n" +
            "//default Occurences: 0\n" +
            "//goto Occurences: 0\n" +
            "//public Occurences: 6\n" +
            "//native Occurences: 0\n" +
            "//assert Occurences: 0\n" +
            "//class Occurences: 2\n" +
            "//break Occurences: 0\n" +
            "//false Occurences: 0\n" +
            "//volatile Occurences: 0\n" +
            "//abstract Occurences: 0\n" +
            "//int Occurences: 1\n" +
            "//instanceof Occurences: 0\n" +
            "//super Occurences: 0\n" +
            "//boolean Occurences: 0\n" +
            "//throw Occurences: 0\n" +
            "//char Occurences: 0\n" +
            "//short Occurences: 0\n" +
            "//return Occurences: 1\n" +
            "//Total Occurences: 47";

    final String nullFile = "//synchronized Occurences: 0\n"+
            "//do Occurences: 0\n"+
            "//float Occurences: 0\n"+
            "//while Occurences: 0\n"+
            "//protected Occurences: 0\n"+
            "//else Occurences: 0\n"+
            "//continue Occurences: 0\n"+
            "//catch Occurences: 0\n"+
            "//if Occurences: 0\n"+
            "//case Occurences: 0\n"+
            "//new Occurences: 0\n"+
            "//package Occurences: 0\n"+
            "//static Occurences: 0\n"+
            "//void Occurences: 0\n"+
            "//byte Occurences: 0\n"+
            "//double Occurences: 0\n"+
            "//finally Occurences: 0\n"+
            "//this Occurences: 0\n"+
            "//strictfp Occurences: 0\n"+
            "//throws Occurences: 0\n"+
            "//enum Occurences: 0\n"+
            "//extends Occurences: 0\n"+
            "//null Occurences: 0\n"+
            "//transient Occurences: 0\n"+
            "//final Occurences: 0\n"+
            "//true Occurences: 0\n"+
            "//try Occurences: 0\n"+
            "//implements Occurences: 0\n"+
            "//private Occurences: 0\n"+
            "//const Occurences: 0\n"+
            "//import Occurences: 0\n"+
            "//for Occurences: 0\n"+
            "//interface Occurences: 0\n"+
            "//long Occurences: 0\n"+
            "//switch Occurences: 0\n"+
            "//default Occurences: 0\n"+
            "//goto Occurences: 0\n"+
            "//public Occurences: 0\n"+
            "//native Occurences: 0\n"+
            "//assert Occurences: 0\n"+
            "//class Occurences: 0\n"+
            "//break Occurences: 0\n"+
            "//false Occurences: 0\n"+
            "//volatile Occurences: 0\n"+
            "//abstract Occurences: 0\n"+
            "//int Occurences: 0\n"+
            "//instanceof Occurences: 0\n"+
            "//super Occurences: 0\n"+
            "//boolean Occurences: 0\n"+
            "//throw Occurences: 0\n"+
            "//char Occurences: 0\n"+
            "//short Occurences: 0\n"+
            "//return Occurences: 0\n"+
            "//Total Occurences: 0";


    // Create tmp file .. runs before each and every test methods
    @Before
    public void setUp() throws Exception {
        tmp = File.createTempFile("tmp", ".tmp");
    }

    // Delete tmp file .. runs after each and every test methods
    @After
    public void tearDown() throws Exception {
        assertTrue(tmp.delete());
    }

    // Test that Strings provided in the StringArray ReservedWords are found and correctly counted in a given String
    @Test
    public void occurrences() throws Exception {
        final LanguageImpl s0 = new LanguageImpl();
        s0.occurrences(tmp.getAbsolutePath());
        for(String s: s0.getHash().keySet()){
            assertEquals((int)s0.getHash().get(s),0);
        }

        final LanguageImpl s1 = new LanguageImpl();
        s1.append(tmp.getAbsolutePath());
        s1.occurrences(tmp.getAbsolutePath());
        for(String s: s1.getHash().keySet()){
            assertEquals((int)s1.getHash().get(s),1);
        }

        final LanguageImpl s2 = new LanguageImpl();
        s2.append(tmp.getAbsolutePath());
        s2.occurrences(tmp.getAbsolutePath());
        for(String s: s2.getHash().keySet()){
            assertEquals((int)s2.getHash().get(s),2);
        }

        final LanguageImpl s3 = new LanguageImpl();
        s3.append(tmp.getAbsolutePath());
        s3.occurrences(tmp.getAbsolutePath());
        for(String s: s3.getHash().keySet()){
            assertEquals((int)s3.getHash().get(s),3);
        }



    }

    // Test that append successfully and correctly adds a given string to a file
    @Test
    public void append() throws Exception {
        assertNotNull(tmp);
        final LanguageImpl sa = new LanguageImpl();
        final long size0 = Files.size(Paths.get(tmp.toURI()));
        final String s = "// Hello World";
        sa.append(tmp.getAbsolutePath());
        final long size1 = Files.size(Paths.get(tmp.toURI()));
        assertEquals(size0 + nullFile.length(), size1);
    }

    @Test
    public void appendStringTest() throws Exception {
        String tester = "";
        final LanguageImpl sa = new LanguageImpl();
        // Appends to a separate text file, puts it into a string then tests it against a defined test string
        final String SOURCEFILE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + LanguageImpl.class.getName().replace(".", File.separator) + ".java";
        sa.occurrences(SOURCEFILE);
        sa.append(tmp.getAbsolutePath());
        BufferedReader charInput = new BufferedReader(new InputStreamReader(new FileInputStream(tmp)));
        int charByChar = 0;
        while ((charByChar = charInput.read()) != -1){
            tester += (char)charByChar;
        }
        assertTrue(tester.equals(testString));


    }
}

