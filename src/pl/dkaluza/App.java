package pl.dkaluza;

import pl.dkaluza.fitnessFunction.CubicPolynomialFunction;
import pl.dkaluza.fitnessFunction.FitnessFunction;
import pl.dkaluza.selection.ChromosomesSelection;
import pl.dkaluza.selection.RouletteWheelSelection;

import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Podaj wartość a dla funkcji przystosowania: ");
        int a = scanner.nextInt();

        System.out.println("Podaj wartość b dla funkcji przystosowania: ");
        int b = scanner.nextInt();

        System.out.println("Podaj wartość c dla funkcji przystosowania: ");
        int c = scanner.nextInt();

        System.out.println("Podaj wartość d dla funkcji przystosowania: ");
        int d = scanner.nextInt();

        FitnessFunction function = new CubicPolynomialFunction(a, b, c, d);
        ChromosomesSelection chromosomesSelection = new RouletteWheelSelection();
        GeneticAlgorithm algorithm = new GeneticAlgorithm(function, chromosomesSelection);

        System.out.println("Podaj liczbe chromosomow: ");
        int chromosomesNum = scanner.nextInt();
        if (chromosomesNum < 0) {
            throw new IllegalArgumentException("Nieprawidłowa liczba chromosomów");
        }

        System.out.println("Podaj szanse na  krzyżowane (od 0 do 1): ");
        double chanceToCrossover = scanner.nextDouble();
        if (chanceToCrossover < 0 || chanceToCrossover > 1) {
            throw new IllegalArgumentException("Nieprawidłowa szansa na krzyżowanie");
        }

        System.out.println("Podaj szane na mutacje (od 0 do 1): ");
        double chanceToMutation = scanner.nextDouble();
        if (chanceToCrossover < 0 || chanceToCrossover > 1) {
            throw new IllegalArgumentException("Nieprawidłowa szansa na mutacje");
        }

        System.out.println("Podaj limit braku zmiany najlepszego chromosomu (kryterium zatrzymania): ");
        int noChangesLimit = scanner.nextInt();
        if (noChangesLimit <= 0) {
            throw new IllegalArgumentException("Nieprawidłowy limit");
        }

        algorithm.exec(chromosomesNum, chanceToCrossover, chanceToMutation, noChangesLimit);
    }


}
