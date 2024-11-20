import java.util.Random;

public class MLP {
    private double[][] weights1;
    private double[] bias1;
    private double[][] weights2;
    private double[] bias2;

    public MLP(int inputSize, int hiddenSize, int outputSize) {
        Random rand = new Random();
        weights1 = new double[inputSize][hiddenSize];
        bias1 = new double[hiddenSize];
        weights2 = new double[hiddenSize][outputSize];
        bias2 = new double[outputSize];

        // Initialize weights and biases
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < hiddenSize; j++) {
                weights1[i][j] = rand.nextGaussian();
            }
        }
        for (int i = 0; i < hiddenSize; i++) {
            bias1[i] = rand.nextGaussian();
            for (int j = 0; j < outputSize; j++) {
                weights2[i][j] = rand.nextGaussian();
            }
        }
    }

    public double[] forward(double[] input) {
        double[] hidden = new double[bias1.length];
        double[] output = new double[bias2.length];

        // Hidden layer
        for (int i = 0; i < hidden.length; i++) {
            for (int j = 0; j < input.length; j++) {
                hidden[i] += input[j] * weights1[j][i];
            }
            hidden[i] = Math.max(0, hidden[i] + bias1[i]); // ReLU activation
        }

        // Output layer
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < hidden.length; j++) {
                output[i] += hidden[j] * weights2[j][i];
            }
            output[i] += bias2[i];
        }
        return softmax(output);
    }

    private double[] softmax(double[] input) {
        double sum = 0;
        for (double x : input) sum += Math.exp(x);
        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = Math.exp(input[i]) / sum;
        }
        return output;
    }
}
