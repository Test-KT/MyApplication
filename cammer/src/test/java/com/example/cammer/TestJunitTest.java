package com.example.cammer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lsl on 17-10-12.
 */
public class TestJunitTest {
    private TestJunit testJunit;

    @Before
    public void setUp() throws Exception {
        testJunit = new TestJunit();
    }

    @Test
    public void add() throws Exception {
        assertEquals(6, testJunit.add(1, 5), 0);
    }

    @Test
    public void remove() throws Exception {
        assertEquals(4, testJunit.remove(8, 4), 0);
    }

    @Test
    public void clear() throws Exception {
        assertEquals(0, testJunit.clear(1, 5), 0);
    }

    @Test
    public void del() throws Exception {
        assertEquals(-4, testJunit.del(1, 5), 0);
    }

}