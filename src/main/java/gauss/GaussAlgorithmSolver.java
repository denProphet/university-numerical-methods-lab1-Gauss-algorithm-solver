package gauss;

import java.util.Arrays;
import java.util.List;

public class GaussAlgorithmSolver {

    public GaussAlgorithmSolver(List<Double> inputData) {
        this.inputData = inputData;
    }

    private List<Double> inputData;
    private double[][] mainMatrix;

    private final int n1 = 3; //Размерность начальной матрицы (строки)
    private final int n2 = 4; //Размерность начальной матрицы (строки)

    /**
     * Инициализация значений
     */

    private void initialize() {
        mainMatrix = new double[n1][n2]; //Матрица-дублер
        int listIndex = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                mainMatrix[i][j] = inputData.get(listIndex);
                listIndex++;
            }

        }
    }

    /**
     * Запуск алгоритма
     */

    public double[] getResult() {
        initialize();
       return getResultsFromMatrix(reverseWay(straightWay(mainMatrix)));
    }

    /**
     * Метод отделения ответов (массив ответов) от общей матрицы
     */
    private double[] getResultsFromMatrix(double[][] matrix) {
        double[] results = new double[n1];
        for (int i = 0; i < n1; i++) {
            results[i] = matrix[i][n1];
        }
        return results;
    }

    /**
     * Метод обратного пути
     */
    private double[][] reverseWay(double[][] matrix) {
        for (int k = n1 - 1; k > -1; k--) { //k-номер строки

            for (int i = n1; i > -1; i--) {//i-номер столбца
                matrix[k][i] = matrix[k][i] / matrix[k][k];
            }
            for (int i = k - 1; i > -1; i--) { //i-номер следующей строки после k

                double K = matrix[i][k] / matrix[k][k];
                for (int j = n1; j > -1; j--) { //j-номер столбца следующей строки после k
                    matrix[i][j] = matrix[i][j] - matrix[k][j] * K;
                }
            }
        }

        return matrix;
    }

    /**
     * Метод прямого пути
     */

    private double[][] straightWay(double[][] matrix) {
        double[][] matrixClone = new double[n1][n1 + 1]; //Матрица-дублер
        for (int i = 0; i < n1; i++)
            System.arraycopy(matrix[i], 0, matrixClone[i], 0, n1 + 1);

        //Прямой ход (Зануление нижнего левого угла)
        for (int k = 0; k < n1; k++) //k-номер строки
        {
            for (int i = 0; i < n1 + 1; i++) //i-номер столбца
                matrixClone[k][i] = matrixClone[k][i] / matrix[k][k]; //Деление k-строки на первый
            // член !=0 для преобразования его в единицу
            for (int i = k + 1; i < n1; i++) { //i-номер следующей строки после k

                double l = matrixClone[i][k] / matrixClone[k][k]; //Коэффициент
                for (int j = 0; j < n1 + 1; j++) //j-номер столбца следующей строки после k
                    matrixClone[i][j] = matrixClone[i][j] - matrixClone[k][j] * l;
                //Зануление элементов матрицы ниже первого члена, преобразованного в единицу
            }
            for (int i = 0; i < n1; i++) {//Обновление, внесение изменений в начальную матрицу
                System.arraycopy(matrixClone[i], 0, matrix[i], 0, n1 + 1);
            }
        }
        return matrixClone;
    }

}







