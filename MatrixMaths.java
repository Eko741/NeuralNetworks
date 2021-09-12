
public class MatrixMaths {
	public double[][] matrixMultiplication(double[][] a, double[][] b) {
		if (a[0].length != b.length) {
			System.out.println(a[0].length + " !=" + b.length);
			System.out.println("matrixMultiplication. Shape missmatch");
			return null;
		}

		double[][] c = new double[a.length][b[0].length];
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				c[i][j] = dotProduct(a[i], transpose(b)[j]);
			}

		}
		return c;
	}

	public double dotProduct(double[] a, double[] b) {
		if (!(a.length == b.length)) {
			System.out.println("dotMultiplication. Shape missmatch");
			return 0;
		}
		double s = 0;
		for (int i = 0; i < a.length; i++) {
			s += a[i] * b[i];
		}

		return s;
	}

	public double[][] transpose(double[][] a) {
		double[][] b = new double[a[0].length][a.length];
		for (int i = 0; i < a[0].length; i++) {
			double[] c = new double[a.length];
			for (int j = 0; j < a.length; j++) {
				c[j] = a[j][i];
			}
			b[i] = c;
		}
		return b;
	}

	public double[][] addRowVector(double[][] a, double[] b) {
		if (a[0].length != b.length) {
			System.out.println(a[0].length + "!=" + b.length);
			System.out.println("addRowVector. Shape missmatch");
			return null;
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				a[i][j] += b[j];
			}
		}
		return a;
	}

	public double[][] activation(double[][] a, ActivationFunction f) {
		if (f == ActivationFunction.SoftMax) {
			for (int i = 0; i < a.length; i++) {

				double[] exp_values = new double[a[i].length];
				double norm_base = 0;
				double max = a[0][0];

				for (int j = 0; j < a[i].length; j++) {
					if (max < a[i][j]) {
						max = a[i][j];
					}
				}

				for (int j = 0; j < a[i].length; j++) {

					exp_values[j] = Math.pow(Math.E, a[i][j] - max);
					norm_base += exp_values[j];
				}

				for (int j = 0; j < a[i].length; j++) {
					a[i][j] = exp_values[j] / norm_base;
				}

			}

		} else if (f == ActivationFunction.ReLU) {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[0].length; j++) {
					if (a[i][j] < 0)
						a[i][j] = 0;
				}
			}

		}

		return a;
	}

	public double batchLoss_CCE(double[][] a, double[][] t) {
		if (a.length != t.length || a[0].length != t[0].length) {
			System.out.println(a.length + "!=" + t.length);
			System.out.println(a[0].length + "!=" + t[0].length);
			System.out.println("batchLoss_CCE. Shape missmatch");
			return 0;
		}
		double l = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (a[i][j] == 0) {
					a[i][j] += 1e-7;
				}
				l += t[i][j] * Math.log(a[i][j]);
			}
		}
		return -(l / (a.length * a[0].length));
	}

	public double MSE(double[][] a, double[][] t) {
		if (a.length != t.length || a[0].length != t[0].length) {
			System.out.println(a.length + "!=" + t.length);
			System.out.println(a[0].length + "!=" + t[0].length);
			System.out.println("MSE. Shape missmatch");
			return 0;
		}
		double l = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				l += Math.pow((a[i][j] - t[i][j]), 2);
			}
		}
		
		l /= a.length * a[0].length;

		return l;
	}

}
