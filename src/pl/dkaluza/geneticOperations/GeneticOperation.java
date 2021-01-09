package pl.dkaluza.geneticOperations;

import pl.dkaluza.Chromosome;

import java.util.Collection;

public interface GeneticOperation {
    void apply(Collection<Chromosome> chromosomes, int bitsNum);

    default int drawLocus(int bitsNum) {
        return (int) (Math.random() * bitsNum);
    }
}
