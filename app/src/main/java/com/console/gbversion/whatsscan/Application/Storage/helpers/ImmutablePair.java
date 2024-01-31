package com.console.gbversion.whatsscan.Application.Storage.helpers;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class ImmutablePair<T, S> implements Serializable {
    private static final long serialVersionUID = 40;
    public final T element1;
    public final S element2;

    public ImmutablePair() {
        this.element1 = null;
        this.element2 = null;
    }

    public ImmutablePair(T t, S s) {
        this.element1 = t;
        this.element2 = s;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (!(obj instanceof ImmutablePair)) {
            return false;
        }
        ImmutablePair immutablePair = (ImmutablePair) obj;
        T t = (T) immutablePair.element1;
        S s = (S) immutablePair.element2;
        if (!this.element1.equals(t) || !this.element2.equals(s)) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return this.element1.hashCode() << (this.element2.hashCode() + 16);
    }
}
