import java.util.Random;

public class DataGenerator {
    public static double[][] generateData(int nSamples, int nFeatures) {
        double[][] data = new double[nSamples][nFeatures + 1]; // Last column for labels
        Random rand = new Random();
        for (int i = 0; i < nSamples; i++) {
            double x1 = rand.nextDouble() * 10 - 5; // Feature 1
            double x2 = rand.nextDouble() * 10 - 5; // Feature 2
            int label = x1 + x2 > 0 ? 1 : 0; // Simple linear boundary
            data[i][0] = x1;
            data[i][1] = x2;
            data[i][2] = label; // Label
        }
        return data;
    }
}
