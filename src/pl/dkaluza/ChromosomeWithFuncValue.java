package pl.dkaluza;

public class ChromosomeWithFuncValue {
    private final Chromosome chromosome;
    private final double funcValue;

    public ChromosomeWithFuncValue(Chromosome chromosome, double funcValue) {
        this.chromosome = new Chromosome(chromosome.getValue());
        this.funcValue = funcValue;
    }

    public Chromosome getChromosome() {
        return chromosome;
    }

    public double getFuncValue() {
        return funcValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChromosomeWithFuncValue)) return false;

        ChromosomeWithFuncValue that = (ChromosomeWithFuncValue) o;
        return chromosome.equals(that.chromosome);
    }

    @Override
    public int hashCode() {
        return chromosome.hashCode();
    }
}
