package pl.dkaluza;

import pl.dkaluza.fitnessFunction.FitnessFunction;
import pl.dkaluza.geneticOperations.Crossover;
import pl.dkaluza.geneticOperations.Mutation;
import pl.dkaluza.selection.ChromosomesSelection;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {
    private final FitnessFunction fitnessFunction;
    private final ChromosomesSelection chromosomesSelection;

    public GeneticAlgorithm(FitnessFunction fitnessFunction, ChromosomesSelection chromosomesSelection) {
        this.fitnessFunction = fitnessFunction;
        this.chromosomesSelection = chromosomesSelection;
    }

    public void exec(int chromosomesNum, double chanceToCrossover, double chanceToMutation, int noChangesLimit) {
        System.out.println("Generuje chromosomy...");
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < chromosomesNum; i++) {
            chromosomes.add(new Chromosome(
                (int) (Math.random() * 32)
            ));
            System.out.println("Chromosom #" + (i + 1) + ": " + chromosomes.get(i).getValue());
        }

        Crossover crossover = new Crossover(chanceToCrossover);
        Mutation mutation = new Mutation(chanceToMutation);

        int iterationsNum = 0;
        int noChangesCounter = 0;
        ChromosomeWithFuncValue bestChromosome = null;
        ChromosomeWithFuncValue bestChromosomeOfIteration;
        while (noChangesCounter < noChangesLimit) {
            bestChromosomeOfIteration = execIteration(chromosomes, crossover, mutation);
            if (bestChromosome == null) {
                bestChromosome = bestChromosomeOfIteration;
                continue;
            }

            if (bestChromosome.getFuncValue() >= bestChromosomeOfIteration.getFuncValue()) {
                noChangesCounter ++;
            } else {
                bestChromosome = bestChromosomeOfIteration;
                noChangesCounter = 0;
            }

            iterationsNum ++;
        }

        if (bestChromosome == null) {
            throw new IllegalStateException("Best chromosome is null");
        }

        System.out.println("KONIEC DZIAŁANIA ALGORYTMU");
        System.out.println("Najlepszy chromosom: " + bestChromosome.getChromosome());
        System.out.println("Jego wartość funkcji przystosowania wynosi: " + bestChromosome.getFuncValue());
        System.out.println("Ilosc iteracji: " + iterationsNum);
    }

    private ChromosomeWithFuncValue execIteration(List<Chromosome> chromosomes, Crossover crossover, Mutation mutation) {
        chromosomes = chromosomesSelection.doSelection(chromosomes, fitnessFunction);
        crossover.apply(chromosomes, 5);
        mutation.apply(chromosomes, 5);

        Chromosome bestChromosome = null;
        double bestFuncValue = 0;
        for (Chromosome chromosome : chromosomes) {
            double funcValue = fitnessFunction.calc(chromosome.getValue());
            if (funcValue > bestFuncValue) {
                bestChromosome = chromosome;
                bestFuncValue = funcValue;
            }
        }

        if (bestChromosome == null) {
            throw new IllegalStateException("Best chromosome is null");
        }

        return new ChromosomeWithFuncValue(bestChromosome, bestFuncValue);
    }
}
