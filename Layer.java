import java.util.Random;

public class Layer {
	int NOI; // Number of inputs
	int NON; // Number of neurons AKA outputs
	double[][] weights;
	double[] biases;
	ActivationFunction a;

	public Layer(int numberOfInputs, int numberOfNeurons, ActivationFunction activationFunction) {
		this.NOI = numberOfInputs;
		this.NON = numberOfNeurons;

		weights = new double[NOI][NON];
		biases = new double[NON];
		a = activationFunction;

		// Initializes weights and biases
		Random r = new Random();
		for (int i = 0; i < NOI; i++) {
			for (int j = 0; j < NON; j++) {
				weights[i][j] = r.nextDouble() - 0.5;
			}
		}

		for (int i = 0; i < NON; i++) {
			biases[i] = 0;
		}
	}

	

	public double[][] forward(double[][] inputs) {
		MatrixMaths m = new MatrixMaths();
		return m.activation(m.addRowVector(m.matrixMultiplication(inputs, weights), biases), a);
	}
	
	ActivationFunction getActivationFunction() {
		return(a);
	}
}
