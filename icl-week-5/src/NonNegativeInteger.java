public class NonNegativeInteger implements Comparable<NonNegativeInteger> {
    public NonNegativeInteger() {
        v = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NonNegativeInteger)) return false;
        NonNegativeInteger nni = (NonNegativeInteger)obj;
        return v == nni.get();
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(v);
        return 31 * result;
    }

    public int compareTo(NonNegativeInteger nni) {
        return Integer.compare(v, nni.v);
    }

    public void set(int v) throws IllegalArgumentException {
        if (v < 0) {
            throw new IllegalArgumentException("" +
                    "You cannot pass a negative integer to this method.");
        }
        this.v = v;
    }

    public int get() {
        return v;
    }

    private int v;
}
