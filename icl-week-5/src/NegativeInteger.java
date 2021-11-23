public class NegativeInteger implements Comparable<NegativeInteger> {
    public NegativeInteger() {
        v = -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NegativeInteger)) return false;
        NegativeInteger ni = (NegativeInteger)obj;
        return v == ni.get();
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(v);
        return 31 * result;
    }

    public int compareTo(NegativeInteger ni) {
        return Integer.compare(v, ni.v);
    }

    public void set(int v) throws IllegalArgumentException {
        if (v >= 0) {
            throw new IllegalArgumentException("" +
                    "You cannot pass a positive integer to this method.");
        }
        this.v = v;
    }

    public int get() {
        return v;
    }

    private int v;
}
