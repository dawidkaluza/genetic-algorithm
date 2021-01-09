package pl.dkaluza.selection;

import pl.dkaluza.Chromosome;
import pl.dkaluza.fitnessFunction.FitnessFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RouletteWheelSelection implements ChromosomesSelection {
    @Override
    public List<Chromosome> doSelection(List<Chromosome> chromosomes, FitnessFunction function) {
        List<Double> functionValues = chromosomes
            .stream()
            .map(chromosome -> function.calc(chromosome.getValue()))
            .collect(Collectors.toList());

        double functionValuesSum = functionValues
            .stream()
            .mapToDouble(Double::doubleValue)
            .sum();

        List<Double> slices = functionValues
            .stream()
            .map(value -> value / functionValuesSum * 100)
            .collect(Collectors.toList());

        List<Chromosome> selectedChromosomes = new ArrayList<>();
        for (int i = 0; i < chromosomes.size(); i++) {
            selectedChromosomes.add(
                chromosomes.get(drawChromosomeId(slices))
            );
        }
        return selectedChromosomes;
    }

    private int drawChromosomeId(List<Double> slices) {
        double drawnNumber = Math.random() * 100 + 1;
        double curNumber = 0;
        int lastId = slices.size() - 1;
        for (int j = 0; j < lastId; j++) {
            curNumber += slices.get(j);
            if (drawnNumber <= curNumber) {
                return j;
            }
        }

        return lastId;
    }
}
