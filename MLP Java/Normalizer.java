public class Normalizer {
    public static double[][] normalize(double[][] data, int nFeatures) {
        double[] mean = new double[nFeatures];
        double[] stdDev = new double[nFeatures];

        // Calculate mean and standard deviation
        for (double[] row : data) {
            for (int i = 0; i < nFeatures; i++) {
                mean[i] += row[i];
            }
        }
        for (int i = 0; i < nFeatures; i++) {
            mean[i] /= data.length;
        }
        for (double[] row : data) {
            for (int i = 0; i < nFeatures; i++) {
                stdDev[i] += Math.pow(row[i] - mean[i], 2);
            }
        }
        for (int i = 0; i < nFeatures; i++) {
            stdDev[i] = Math.sqrt(stdDev[i] / data.length);
        }

        // Normalize data
        for (double[] row : data) {
            for (int i = 0; i < nFeatures; i++) {
                row[i] = (row[i] - mean[i]) / stdDev[i];
            }
        }
        return data;
    }
}
