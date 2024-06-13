import java.util.Scanner;

public class GaussAlgorithmus {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Eingabe der Matrixgröße
        System.out.print("Geben Sie die Anzahl der Gleichungen ein: ");
        int n = scanner.nextInt();

        // Eingabe der Koeffizientenmatrix und der Konstanten
        double[][] a = new double[n][n];
        double[] b = new double[n];

        System.out.println("Geben Sie die Koeffizienten der Matrix ein:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Geben Sie die Konstanten ein:");
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextDouble();
        }

        // Gauß-Algorithmus
        double[] x = gauss(a, b);

        // Ausgabe der Lösung
        System.out.println("Die Lösungen sind:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.4f\n", i + 1, x[i]);
        }

        scanner.close();
    }

    public static double[] gauss(double[][] a, double[] b) {
        int n = b.length;

        // Vorwärtseinsetzen
        for (int i = 0; i < n; i++) {
            // Suche des Pivotelements
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(a[j][i]) > Math.abs(a[max][i])) {
                    max = j;
                }
            }

            // Zeilen vertauschen
            double[] temp = a[i];
            a[i] = a[max];
            a[max] = temp;

            double t = b[i];
            b[i] = b[max];
            b[max] = t;

            // Nullstellen unterhalb des Pivotelements
            for (int j = i + 1; j < n; j++) {
                double factor = a[j][i] / a[i][i];
                b[j] -= factor * b[i];
                for (int k = i; k < n; k++) {
                    a[j][k] -= factor * a[i][k];
                }
            }
        }

        // Rückwärtseinsetzen
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += a[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / a[i][i];
        }

        return x;
    }
}
