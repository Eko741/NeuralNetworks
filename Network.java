
public class Network {
	int NOI; // number of inputs
	int NOO; // number of outputs
	int NOHL; // Number of hidden layers
	int HLS; // Number of neurons in the hidden layers

	Layer[] layers;

	public Network(int numberOfInputs, int numberOfOutputs, int numberOfHiddenLayers, int hiddenLayerSize) {

		if (numberOfInputs < 1) {
			System.out.println("There must be at least 1 input node");
		} else if (numberOfHiddenLayers < 0) {
			System.out.println("There can't be a negative amount of layers");
		} else if (numberOfOutputs < 1) {
			System.out.println("There must be at least 1 output node");
		} else if (hiddenLayerSize < 1) {
			System.out.println("The hidden layer has to have at least 1 node");
		}

		NOI = numberOfInputs;
		NOO = numberOfOutputs;
		NOHL = numberOfHiddenLayers;
		HLS = hiddenLayerSize;

		layers = new Layer[1 + numberOfHiddenLayers];

		if (numberOfHiddenLayers == 0) {

			layers[0] = new OutputLayer(numberOfInputs, numberOfOutputs, ActivationFunction.SoftMax);
		} else {

			layers[numberOfHiddenLayers] = new OutputLayer(hiddenLayerSize, numberOfOutputs,
					ActivationFunction.SoftMax);
			layers[0] = new InputLayer(numberOfInputs, hiddenLayerSize, ActivationFunction.ReLU);

			for (int i = 1; i < numberOfHiddenLayers; i++) {
				layers[i] = new Layer(hiddenLayerSize, hiddenLayerSize, ActivationFunction.ReLU);
			}
		}
	}

	double[][] runNetwork(double[][] inputs) {
		double[][] a = layers[0].forward(inputs);
		for (int i = 1; i < NOHL + 1; i++) {
			a = layers[i].forward(a);
		}
		return a;
	}

	double trainNetwork(double[][] inputs, double[][] t) {
		MatrixMaths m = new MatrixMaths();
		double[][] a = layers[0].forward(inputs);
		for (int i = 1; i < NOHL + 1; i++) {
			a = layers[i].forward(a);
		}
		double l = m.batchLoss_CCE(a, t);
		/*
		 * Train Network Here
		 */

		return l;
	}
}
