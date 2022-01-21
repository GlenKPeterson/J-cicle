package org.organicdesign.fp.function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class Fn0Test {
    @Test(expected = RuntimeException.class)
    public void applyIOException() {
        //noinspection Convert2Lambda
        new Fn0<Integer>() {
            @Override public Integer applyEx() throws Exception {
                throw new IOException("test exception");
            }
        }.apply();
    }

    @Test(expected = IllegalStateException.class)
    public void applyIllegalStateException() {
        ((Fn0<Integer>) () -> {
            throw new IllegalStateException("test exception");
        }).apply();
    }

    @Test public void constantFunction() throws Exception {
        Fn0<Integer> f = () -> 7;
        assertEquals(Integer.valueOf(7), f.apply());
        assertEquals(Integer.valueOf(7), f.applyEx());
        assertEquals(Integer.valueOf(7), f.get());
        assertEquals(Integer.valueOf(7), f.call());
//        assertEquals(f.hashCode(), Fn0.constantFunction(Integer.valueOf(7)).hashCode());
//        assertTrue(f.equals(Fn0.constantFunction(Integer.valueOf(7))));
//
//        assertEquals("() -> 7", f.toString());
//
//        equalsDistinctHashCode(Fn0.constantFunction(7),
//                               Fn0.constantFunction(7),
//                               Fn0.constantFunction(7),
//                               Fn0.constantFunction(8));
//
//        assertEquals(0, Fn0.constantFunction(null).hashCode());
//
//        assertNotEquals(Fn0.constantFunction(null), null);
//
//        assertFalse(Fn0.constantFunction(35).equals((Callable<Integer>) () -> 35));
    }

    @Test(expected = IllegalStateException.class)
    public void testCall() throws Exception {
        ((Fn0<Integer>) () -> {
            throw new IllegalStateException("test exception");
        }).call();
    }
}
