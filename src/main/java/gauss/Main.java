package gauss;

import reader.ArrayFileReader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Дисциплина: численные методы. Лабораторная работа 1. Яровой Денис. Вариант 20
 * <p>
 * Требования к проекту:
 * Реализовать олгоритм решения линейных уравнений методом Гаусса.
 * Входные данные находятся в файле (файл src/main/resources/data.txt)
 * <p>
 * Структура файла с данными:
 * 1 строка = 1 значение.
 * Разрешены десятичные разделители "." и ","
 * Разрешены пустые строки
 *
 * @author Den Yarovoy
 * @version 1.0 2022-02-17
 **/

public class Main {

    public static void main(String[] args) {

        try {

            File file = new File("src/main/resources/data.txt");
            List<Double> inputData = new ArrayFileReader(file).getDoubleList();
            GaussAlgorithmSolver gaussAlgorithmSolver = new GaussAlgorithmSolver(inputData);

            try {
                if (inputData.size() != 12) throw new IllegalArgumentException();
                System.out.print("Результат: ");
                Arrays.stream(gaussAlgorithmSolver.getResult()).
                        forEach(x -> System.out.printf("\n%.3f", x));
            } catch (IllegalArgumentException e) {
                System.err.println("Невалидное количество входных данных. Необходимо 12 елементов");
            }

        } catch (IOException e) {
            System.err.println("Невалидные значения в файле. Необходимая структура: 1 значение = 1 строка.");
        }

    }
}
