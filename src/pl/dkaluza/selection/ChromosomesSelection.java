package pl.dkaluza.selection;

import pl.dkaluza.Chromosome;
import pl.dkaluza.fitnessFunction.FitnessFunction;

import java.util.List;

public interface ChromosomesSelection {
    List<Chromosome> doSelection(List<Chromosome> chromosomes, FitnessFunction function);
}
