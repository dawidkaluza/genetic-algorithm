package pl.dkaluza.geneticOperations;

import pl.dkaluza.Chromosome;

import java.util.Collection;
import java.util.Iterator;

public class Crossover implements ProbabilisticGeneticOperation {
    private final double chance;

    public Crossover(double chance) {
        this.chance = chance;
    }

    @Override
    public void apply(Collection<Chromosome> chromosomes, int bitsNum) {
        Iterator<Chromosome> iterator = chromosomes.iterator();
        while (iterator.hasNext()) {
            Chromosome first = iterator.next();
            if (!iterator.hasNext()) {
                break;
            }

            Chromosome second = iterator.next();
            if (!checkChance(chance)) {
                continue;
            }

            int locus = drawLocus(bitsNum);
            for (int i = locus; i < bitsNum; i++) {
                int firstBit = first.getBit(i);
                int secondBit = second.getBit(i);

                first.setBit(i, secondBit);
                second.setBit(i, firstBit);
            }
        }
    }
}
