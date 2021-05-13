package test.java;

import main.java.FindLauncher;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class FindTests {
    @Test
    public void test1() {
        assertEquals(Collections.singletonList("3.txt"), new FindLauncher().launch("-d","TestDirs/dir1","3.txt"));
    }

    @Test
    public void test2() {
        assertEquals(Arrays.asList("1.txt", "2\\1.txt", "3\\1.txt"), new FindLauncher().launch("-r","-d","TestDirs/dir2","1.txt"));
    }

    @Test
    public void test3() {
        assertEquals(Collections.singletonList("src"), new FindLauncher().launch("src"));
    }

    @Test
    public void test4() {
        assertEquals(Collections.singletonList("Incorrect data entry"), new FindLauncher().launch("-d","TestDirs/dir1"));
    }

    @Test
    public void test5() {
        assertEquals(Collections.singletonList("The specified file / directory is invalid"), new FindLauncher().launch("-d","TestDirs/dir15","1.txt"));
    }
}
