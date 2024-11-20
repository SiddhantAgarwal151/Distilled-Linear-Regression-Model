import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphPlot extends Application {

    private static double[][] data;
    private static MLP mlp;

    public static void setData(double[][] inputData, MLP trainedMLP) {
        data = inputData;
        mlp = trainedMLP;
    }

    @Override
    public void start(Stage stage) {
        // Set up the axes
        NumberAxis xAxis = new NumberAxis(-5, 5, 1);
        xAxis.setLabel("Feature 1");
        NumberAxis yAxis = new NumberAxis(-5, 5, 1);
        yAxis.setLabel("Feature 2");

        // Create scatter chart
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle("Data Visualization and Decision Boundary");

        // Plot data points
        XYChart.Series<Number, Number> class0Series = new XYChart.Series<>();
        class0Series.setName("Class 0");
        XYChart.Series<Number, Number> class1Series = new XYChart.Series<>();
        class1Series.setName("Class 1");

        for (double[] point : data) {
            double[] input = {point[0], point[1]};
            double[] output = mlp.forward(input);
            int predictedClass = output[0] > output[1] ? 0 : 1;

            if (predictedClass == 0) {
                class0Series.getData().add(new XYChart.Data<>(point[0], point[1]));
            } else {
                class1Series.getData().add(new XYChart.Data<>(point[0], point[1]));
            }
        }

        scatterChart.getData().addAll(class0Series, class1Series);

        // Create canvas for decision boundary
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw decision boundary
        double xMin = -5, xMax = 5, yMin = -5, yMax = 5;
        double step = 0.1;

        for (double x = xMin; x <= xMax; x += step) {
            for (double y = yMin; y <= yMax; y += step) {
                double[] input = {x, y};
                double[] output = mlp.forward(input);
                int predictedClass = output[0] > output[1] ? 0 : 1;

                if (predictedClass == 0) {
                    gc.setFill(Color.LIGHTBLUE);
                } else {
                    gc.setFill(Color.LIGHTPINK);
                }

                // Convert chart coordinates to canvas pixels
                double canvasX = ((x - xMin) / (xMax - xMin)) * canvas.getWidth();
                double canvasY = ((yMax - y) / (yMax - yMin)) * canvas.getHeight(); // Inverted Y-axis

                gc.fillRect(canvasX, canvasY, 2, 2);
            }
        }

        // Set up the scene
        StackPane root = new StackPane();
        root.getChildren().addAll(canvas, scatterChart); // Canvas is under the scatter chart

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("MLP Decision Boundary Visualization");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
