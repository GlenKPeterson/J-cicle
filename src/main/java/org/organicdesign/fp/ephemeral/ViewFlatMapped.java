// Copyright 2014-02-15 PlanBase Inc. & Glen Peterson
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

package org.organicdesign.fp.ephemeral;

import org.organicdesign.fp.Sentinal;
import org.organicdesign.fp.function.Function;

public class ViewFlatMapped<T,U> extends ViewAbstract<U> {
    private final View<T> outerView;

    private View<U> innerView = emptyView();

    private final Function<T,View<U>> func;

    ViewFlatMapped(View<T> v, Function<T,View<U>> f) { outerView = v; func = f; }

    @SuppressWarnings("unchecked")
    public static <T,U> View<U> of(View<T> v, Function<T,View<U>> f) {
        // You can put nulls in, but you don't get nulls out.
        if (f == null) { return emptyView(); }
        // TODO: Is this comparison possible?
//        if (f == FunctionUtils.IDENTITY) { return (View<U>) v; }
        if ( (v == null) || (v == EMPTY_VIEW) ) { return emptyView(); }
        return new ViewFlatMapped<>(v, f);
    }

    @Override
    public U next() {
        if (innerView == EMPTY_VIEW) {
            T item = outerView.next();
            if (item == Sentinal.USED_UP) { return usedUp(); }
            innerView = func.apply_(item);
        }
        U innerNext = innerView.next();
        if (innerNext == Sentinal.USED_UP) {
            innerView = emptyView();
            next();
        }
        return innerNext;
    }

    @SuppressWarnings("unchecked")
    public static <T,U> View<U> emptyView() {
        return (View<U>) EMPTY_VIEW;
    }

    @SuppressWarnings("unchecked")
    public U usedUp() { return (U) Sentinal.USED_UP; }
}
