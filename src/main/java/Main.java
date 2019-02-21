import java.util.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class Main {

    private static String latin = "abcdefghijklmnopqrstuvwxyz";
    private static String greek = "\u03B1\u03B2\u03B3\u03B4\u03B5\u03B6\u03B7\u03B8\u03B9\u03BA\u03BB\u03BC\u03BD\u03BE\u03BF\u03C0\u03C1\u03C2\u03C3\u03C4\u03C5\u03C6\u03C7\u03C9\u03CA\u03CB\u03CC\u03CD\u03CE\u03CF";

    public static void main(String[] args) {
        //Scanner scn = new Scanner(System.in);
        //System.out.println("Matrix size n X n : ");
        int matrixLength = Integer.parseInt(args[0]);
       // System.out.println("Solution to display: ");
        int solutionToDisplay = Integer.parseInt(args[1]);
        //System.out.print("greek or latin? ");
        String symbolTypes = args[2];
        String symbolTypesToUse = switchSymbolsTypes(symbolTypes);

        int solutionCount = 1;

        StringBuilder randomNLetters = generateRandomNLetters(symbolTypesToUse, matrixLength);

        List<char[][]> matrixList = new ArrayList<char[][]>();

        Random random = new Random();
        long execTime = 0;
        long startTime = System.nanoTime();
        label:
        while (solutionCount <= solutionToDisplay) {

            char[][] matrix = generateMatrix(randomNLetters,solutionCount, execTime, matrixLength, random);
            if(matrix!=null){
                if (matrixList.size() == 0) {
                    matrixList.add(matrix);
                    printMatrix(solutionCount, execTime,matrix);

                    solutionCount++;
                } else {
                    for (char[][] matrices : matrixList) {
                        if (Arrays.deepEquals(matrices,matrix)) {
                            continue label;
                        }
                    }
                    matrixList.add(matrix);
                    printMatrix(solutionCount,execTime,matrix);
                    solutionCount++;

                }
            }
            execTime = System.nanoTime() - startTime;
        }



    }

    private static StringBuilder generateRandomNLetters(String letters, int matrixLength) {
        StringBuilder randomNLetters = new StringBuilder();
        Random random = new Random();
        randomNLetters.append(letters.charAt(random.nextInt(letters.length())));
        label:
        while (randomNLetters.length() < matrixLength) {
            char character = letters.charAt(random.nextInt(letters.length()));
            for (int i = 0; i < randomNLetters.length(); i++) {
                if (randomNLetters.charAt(i) == character) {
                    continue label;
                }
            }
            randomNLetters.append(character);
        }
        return randomNLetters;
    }

    private static char[][] generateMatrix(StringBuilder randomNLetters, int count, long execTime, int matrixLength, Random random) {

        try {
            char[][] matrix = new char[matrixLength][matrixLength];
            String randomStringLetters = shuffleString(randomNLetters.toString());
            StringBuilder remainingLetters;

            for (int i = 0; i < matrixLength; i++) {
                for (int j = 0; j < matrixLength; j++) {
                    if (i == 0) {
                        matrix[i][j] = randomStringLetters.charAt(j);
                    } else {
                        remainingLetters = new StringBuilder(randomStringLetters);
                        for (int k = 0; k < i; k++) {
                            remainingLetters.deleteCharAt(remainingLetters.toString().indexOf(matrix[k][j]));
                        }
                        for (int m = 0; m < j; m++) {
                            if (remainingLetters.toString().indexOf(matrix[i][m]) != -1) {
                                remainingLetters.deleteCharAt(remainingLetters.toString().indexOf(matrix[i][m]));
                            }
                        }
                        matrix[i][j] = remainingLetters.toString().charAt(random.nextInt(remainingLetters.length()));
                    }
                }
            }
            return matrix;
        } catch (Exception e) {
            //nothing to do
        }
        return null;

    }

    private static void printMatrix(int count,long execTime ,char[][] matrice) {
        System.out.println("Solution " + count + " || Application running time : " + NANOSECONDS.toMillis(execTime) +" ms");
        System.out.println("------------------------------------");

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("------------------------------------");
    }

    private static String shuffleString(String string) {
        List<Character> characters = new ArrayList<Character>();

        for (char c : string.toCharArray()) {
            characters.add(c);
        }

        StringBuilder output = new StringBuilder(string.length());

        while (characters.size() != 0) {
            int rand = (int) (Math.random() * characters.size());
            output.append(characters.remove(rand));
        }
        return output.toString();
    }

    public static String switchSymbolsTypes(String symbols){
        switch (symbols) {
            case "latin":
                symbols = latin;
                break;
            case "greek":
                symbols = greek;
                break;

             default:
                 symbols=latin;
        }
        return symbols;
    }

}
