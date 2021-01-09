package pl.dkaluza;

public class Chromosome {
    private int value;

    public Chromosome(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setBit(int index, int bit) {
        switch (bit) {
            case 0: {
                this.value &= ~(1 << index);
                break;
            }

            case 1: {
                this.value |= 1 << index;
                break;
            }

            default: {
                throw new IllegalArgumentException("Bit must be 0 or 1");
            }
        }
    }

    public int getBit(int index) {
        return (value >> index) & 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chromosome)) return false;

        Chromosome that = (Chromosome) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "Chromosome{" +
            "value=" + value +
            '}';
    }
}
