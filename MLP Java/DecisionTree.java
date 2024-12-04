import java.util.*;

public class DecisionTree {
    private int featureIndex;
    private double threshold;
    private DecisionTree left, right;
    private int label;

    public DecisionTree() {
        this.left = null;
        this.right = null;
    }

    public void fit(double[][] data, int[] labels) {
        // Basic logic for building a decision tree (this is just a stub for illustration)
        if (data.length <= 1) return;  // Stop if only one data point or no data point
        
        // Choose the best feature and threshold to split on (you can refine this logic)
        featureIndex = 0; // Example feature index
        threshold = 0.0;  // Example threshold for splitting
        
        List<double[]> leftData = new ArrayList<>();
        List<Integer> leftLabels = new ArrayList<>();
        List<Integer> rightLabels = new ArrayList<>();
        List<double[]> rightData = new ArrayList<>();
    
        // Split the data based on chosen feature and threshold
        for (int i = 0; i < data.length; i++) {
            if (data[i][featureIndex] <= threshold) {
                leftData.add(data[i]);
                leftLabels.add(labels[i]);
            } else {
                rightData.add(data[i]);
                rightLabels.add(labels[i]);
            }
        }
    
        // Stop recursion if no further split is possible (leaf node condition)
        if (leftData.size() == 0 || rightData.size() == 0) {
            label = (leftLabels.size() > rightLabels.size()) ? leftLabels.get(0) : rightLabels.get(0);  // Majority class
            return;
        }
    
        // Recursively build the left and right subtrees
        left = new DecisionTree();
        right = new DecisionTree();
        left.fit(leftData.toArray(new double[0][0]), leftLabels.stream().mapToInt(Integer::intValue).toArray());
        right.fit(rightData.toArray(new double[0][0]), rightLabels.stream().mapToInt(Integer::intValue).toArray());
    }
    

    public int predict(double[] dataPoint) {
        if (left == null && right == null) {
            return label; // Leaf node
        }
        
        if (dataPoint[featureIndex] <= threshold) {
            return left.predict(dataPoint);
        } else {
            return right.predict(dataPoint);
        }
    }
}
