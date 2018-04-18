package edu.gcccd.csis;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import java.io.File;
import static junit.framework.TestCase.assertTrue;

public class LanguageTest {
    private static File tmp;
    @Before
    public void setUp() throws Exception{
        tmp = File.createTempFile("tmp",".tmp");
    }

    @After
    public void tearDown() throws Exception{
        assertTrue(tmp.delete());
    }

    @Test
    public void sizeTest(){

    }
}

