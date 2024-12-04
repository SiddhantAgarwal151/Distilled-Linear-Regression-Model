import org.tensorflow.*;

public class TensorFlowTest {
    public static void main(String[] args) {
        // Initialize the TensorFlow session
        try (Graph graph = new Graph()) {
            // Create a tensor (constant)
            try (Tensor<Integer> t = Tensor.create(3)) {
                // Add an operation to the graph (this is a simple constant)
                Operation op = graph.opBuilder("Const", "MyConst")
                        .setAttr("dtype", DataType.DT_INT32)
                        .setAttr("value", t)
                        .build();

                // Create a session to run the graph
                try (Session session = new Session(graph)) {
                    // Run the graph and fetch the result
                    Tensor<?> result = session.runner().fetch("MyConst").run().get(0);
                    System.out.println("TensorFlow constant value: " + result.intValue());
                }
            }
        }
    }
}
