package edu.gcccd.csis;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class LanguageTest {
    private static File tmp;

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
        final LanguageImpl sa = new LanguageImpl();
        sa.occurrences(tmp.getAbsolutePath());
        for(String s: sa.getHash().keySet()){
            assertEquals((int)sa.getHash().get(s),0);
        }

        sa.append(tmp.getAbsolutePath());
        sa.occurrences(tmp.getAbsolutePath());
        for(String s: sa.getHash().keySet()){
            assertEquals((int)sa.getHash().get(s),1);
        }

        sa.append(tmp.getAbsolutePath());
        sa.occurrences(tmp.getAbsolutePath());
        for(String s: sa.getHash().keySet()){
            assertEquals((int)sa.getHash().get(s),2);
        }

        sa.append(tmp.getAbsolutePath());
        sa.occurrences(tmp.getAbsolutePath());
        for(String s: sa.getHash().keySet()){
            assertEquals((int)sa.getHash().get(s),3);
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
        assertEquals(size0 + s.length(), size1);

        // now verify that the correct string was appended to the file
        // ...
    }
}

