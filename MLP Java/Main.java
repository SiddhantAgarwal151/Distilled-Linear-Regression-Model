public class Main {
    public static void main(String[] args) {
        // Generate data
        double[][] data = DataGenerator.generateData(1000, 2);

        // Normalize data
        double[][] normalizedData = Normalizer.normalize(data, 2);

        // Split into training and test sets (80/20 split)
        int splitIndex = (int) (normalizedData.length * 0.8);
        double[][] trainData = new double[splitIndex][];
        double[][] testData = new double[normalizedData.length - splitIndex][];
        System.arraycopy(normalizedData, 0, trainData, 0, splitIndex);
        System.arraycopy(normalizedData, splitIndex, testData, 0, testData.length);

        // Train MLP
        MLP mlp = new MLP(2, 4, 2); // 2 input features, 4 hidden neurons, 2 outputs
        for (double[] sample : trainData) {
            double[] input = {sample[0], sample[1]};
            double[] output = mlp.forward(input);
            // Training logic to be added
        }

        // Visualize
        GraphPlot.setData(testData, mlp); // Pass data and trained model to the visualization
        javafx.application.Application.launch(GraphPlot.class); // Launch JavaFX
    }
}
