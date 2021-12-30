package org.organicdesign.fp.collections;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.organicdesign.fp.FunctionUtils.ordinal;
import static org.organicdesign.fp.oneOf.Option.none;
import static org.organicdesign.fp.oneOf.Option.some;

/**
 Created by gpeterso on 9/14/16.
 */
public class MutListTest {
    static class TestList<E> implements MutList<E> {
        private final @NotNull List<E> inner;

        TestList(@NotNull List<E> ls) { inner = ls; }

        @Override public @NotNull MutList<E> append(E val) {
            inner.add(val);
            return this;
        }

        @Override public ImList<E> immutable() {
            return PersistentVector.ofIter(inner);
        }

        @Override public @NotNull MutList<E> replace(int idx, E e) {
            inner.set(idx, e);
            return this;
        }

        @Override public int size() { return inner.size(); }

        @Override public E get(int i) { return inner.get(i); }
    }

    @Test public void testStuff() {
        List<String> control = new ArrayList<>();
        MutList<String> test = new TestList<>(new ArrayList<>());

        for (int i = 0; i < 32; i++) {
            String ord = ordinal(i);
            control.add(ord);
            test.append(ord);
            assertEquals(control.size(), test.size());
            assertEquals(control, test);
        }

        List<String> moreStuff = Arrays.asList("this", "is", "more", "stuff");
        control.addAll(moreStuff);
        test.concat(moreStuff);

        assertEquals(control.size(), test.size());
        assertEquals(control, test);

        test = test.appendSome(() -> none());
        assertEquals(control.size(), test.size());
        assertEquals(control, test);

        test = test.appendSome(() -> some("hello"));
        control.add("hello");
        assertEquals(control.size(), test.size());
        assertEquals(control, test);
    }

    @Test public void testOriginalMethods() {
        List<String> control = new ArrayList<>();
        MutList<String> test = new TestList<>(new ArrayList<>());

        for (int i = 0; i < 32; i++) {
            String ord = ordinal(i);
            assertTrue(control.add(ord));
            assertTrue(test.add(ord));
            assertEquals(control.size(), test.size());
            assertEquals(control, test);
        }

        List<String> moreStuff = Arrays.asList("this", "is", "more", "stuff");
        assertTrue(control.addAll(moreStuff));
        assertTrue(test.addAll(moreStuff));

        assertEquals(control.size(), test.size());
        assertEquals(control, test);
    }

}