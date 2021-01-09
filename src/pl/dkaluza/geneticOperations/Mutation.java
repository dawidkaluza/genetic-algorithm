package pl.dkaluza.geneticOperations;

import pl.dkaluza.Chromosome;

import java.util.Collection;

public class Mutation implements ProbabilisticGeneticOperation {
    private final double chance;

    public Mutation(double chance) {
        this.chance = chance;
    }

    @Override
    public void apply(Collection<Chromosome> chromosomes, int bitsNum) {
        for (Chromosome chromosome : chromosomes) {
            if (!checkChance(chance)) {
                continue;
            }

            int locus = drawLocus(bitsNum);
            chromosome.setBit(locus, chromosome.getBit(locus) == 1 ? 0 : 1);
        }
    }
}
