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

        // Create labels for training (same as in the DataGenerator)
        int[] labels = new int[trainData.length];
        for (int i = 0; i < trainData.length; i++) {
            labels[i] = trainData[i][0] + trainData[i][1] > 0 ? 1 : 0; // Simple boundary
        }

        // Train decision tree
        DecisionTree tree = new DecisionTree();
        tree.fit(trainData, labels);

        // Visualize
        GraphPlot.setData(testData, tree); // Pass data and trained tree model to the visualization
        javafx.application.Application.launch(GraphPlot.class); // Launch JavaFX
    }
}
