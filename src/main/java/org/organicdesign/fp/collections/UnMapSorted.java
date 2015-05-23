// Copyright 2015-04-13 PlanBase Inc. & Glen Peterson
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package org.organicdesign.fp.collections;

import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;

/** An unmodifiable SortedMap. */
public interface UnMapSorted<K,V> extends UnMap<K,V>, SortedMap<K,V> {

    // ==================================================== Static ====================================================
    UnMapSorted<Object,Object> EMPTY = new UnMapSorted<Object,Object>() {
        @Override public UnSet<Map.Entry<Object,Object>> entrySet() { return UnSet.empty(); }
        @Override public UnSet<Object> keySet() { return UnSet.empty(); }
        @Override public Comparator<? super Object> comparator() { return null; }
        @Override public UnMapSorted<Object,Object> subMap(Object fromKey, Object toKey) { return this; }
        @Override public UnMapSorted<Object,Object> tailMap(Object fromKey) { return this; }
        @Override public Object firstKey() { throw new NoSuchElementException("empty map"); }
        @Override public Object lastKey() { throw new NoSuchElementException("empty map"); }
        @Override public UnCollection<Object> values() { return UnSet.empty(); }
        @Override public int size() { return 0; }
        @Override public boolean isEmpty() { return true; }
        @Override public UnIterator<UnEntry<Object,Object>> iterator() { return UnIterator.empty(); }
        @Override public boolean containsKey(Object key) { return false; }
        @Override public boolean containsValue(Object value) { return false; }
        @Override public Object get(Object key) { return null; }
    };
    @SuppressWarnings("unchecked")
    static <T,U> UnMapSorted<T,U> empty() { return (UnMapSorted<T,U>) EMPTY; }

    // =================================================== Instance ===================================================

// public Comparator<? super K>	comparator()

    /**
     * Returns a view of the mappings contained in this map.  The set should actually contain UnMap.Entry items, but
     * that return signature is illegal in Java, so you'll just have to remember. */
    @Override UnSet<Map.Entry<K,V>> entrySet();

// public  K	firstKey()

    /** {@inheritDoc} */
    @Override default UnMapSorted<K,V> headMap(K toKey) { return subMap(firstKey(), toKey); }

    /** Returns a view of the keys contained in this map. */
    @Override UnSet<K> keySet();

// public  K	lastKey()

    /** {@inheritDoc} */
    @Override UnMapSorted<K,V> subMap(K fromKey, K toKey);

    /** {@inheritDoc} */
    @Override UnMapSorted<K,V> tailMap(K fromKey);

    /** {@inheritDoc} */
    @Override UnCollection<V> values();

// Methods inherited from interface java.util.Map
// clear, compute, computeIfAbsent, computeIfPresent, containsKey, containsValue, equals, forEach, get, getOrDefault, hashCode, isEmpty, merge, put, putAll, putIfAbsent, remove, remove, replace, replace, replaceAll, size
}