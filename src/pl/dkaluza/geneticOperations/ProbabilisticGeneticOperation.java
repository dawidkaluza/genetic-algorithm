package pl.dkaluza.geneticOperations;

import pl.dkaluza.Chromosome;

import java.util.Collection;

public interface ProbabilisticGeneticOperation extends GeneticOperation {
    @Override
    void apply(Collection<Chromosome> chromosomes, int bitsNum);

    default boolean checkChance(double chance) {
        return Math.random() > chance;
    }
}
