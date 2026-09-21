
public class ExtendedString implements Comparable<ExtendedString>, Iterable<Character> {

    private final String value;

    public ExtendedString(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(ExtendedString other) {
        return Integer.compare(this.value.length(), other.value.length());
    }

    @Override
    public java.util.Iterator<Character> iterator() {
        return new java.util.Iterator<Character>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < value.length();
            }

            @Override
            public Character next() {
                return value.charAt(index++);
            }
        };
    }

    @Override
    public String toString() {
        return value;
    }
}
