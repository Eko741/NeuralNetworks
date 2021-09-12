public class NueralNetwork {
	public static void main(String args[]) {
		long startTime = System.currentTimeMillis();
		double inputs[][] = { { 1, 2, 3, 2.5 }, { 2, 5, -1, 2 }, { -1.5, 2.7, 3.3, -0.8 } };

		double[][] test1 = { { 20, 20, 20 },{ 1, 1, 1 }, { 20, 20, 20 }, { 1, 1, 1 } };
		double[][] test2 = { { 1, 1 }, { 0, 1 }, { 0, 1 }, { 0, 1 } };
		double[][] test3 = { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } };
		MatrixMaths m = new MatrixMaths();

		Network network = new Network(3, 2, 2, 4);
		
		printMatrix(network.runNetwork(test1));
		
		System.out.println(m.batchLoss_CCE(test2, test3));
		
		//System.out.println(network.trainNetwork(test1, test2));

		System.out.println("Computation time: " + (System.currentTimeMillis() - startTime) + " ms");
		// .length gives number of rows
		// [0].length gives number of columns

	}

	public static void printMatrix(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printVector(double[] a) {
		for (int j = 0; j < a.length; j++) {
			System.out.print(a[j] + " ");
		}
		System.out.println();
	}

}
