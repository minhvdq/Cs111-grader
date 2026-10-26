import java.util.Arrays;

public class Statistics {
	
	public static double getMax(double[] data) {
		double max = data[0];
		for (double value : data) {
			if (value > max) {
				max = value;
			}
		}
		return max;
	}
	
	public static double getMean(double[] data) {
		double sum = 0;
		for (double value : data) {
			sum += value;
		}
		return sum / data.length;
	}
	
	public static double getMin(double[] data) {
		double min = data[0];
		for (double value : data) {
			if (value < min) {
				min = value;
			}
		}
		return min;
	}

	public static double getPercentile(double[] data, double percentile) {
		double exactIndex = percentile * data.length / 100.0;
		int index = ((Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14) ? (int) Math.round(exactIndex) : (int) Math.floor(percentile * (data.length + 1) / 100.0) - 1);
		index = (index < 0) ? 0 : (index >= data.length) ? data.length - 1 : index;
		return data[index];
	}
	
	public static double[] getResample(double[] data) {
		double[] resample = new double[data.length];
		for (int i = 0; i < resample.length; i++) {
			resample[i] = data[(int)(Math.random() * data.length)];
		}
		return resample;
	}
	
	public static double[]	getResampleMeanPercentiles(double[] data, int numResamples, double... percentiles) {
		double[] resampleMeanPercentiles = new double[percentiles.length];
		double[] resampleMeans = getSortedResampleMeans(data, numResamples);
		for (int i = 0; i < percentiles.length; i++) {
			resampleMeanPercentiles[i] = getPercentile(resampleMeans, percentiles[i]);
		}
		return resampleMeanPercentiles;
	}
	
	public static double[]	getSortedResampleMeans(double[] data, int numResamples) {
		double[] resampleMeans = new double[numResamples];
		// generate unsorted array of means
		for (int i = 0; i < numResamples; i++) {
			double[] resample = getResample(data);
			resampleMeans[i] = getMean(resample);
		}
		Arrays.sort(resampleMeans);
		return resampleMeans;
	}
	
	public static double getStdDev(double[] data) {
		double sum = 0;
		for (double value : data) {
			sum += value;
		}
		double avg = sum / data.length;
		double stDevSum = 0.0;
		for (double value : data) {
			stDevSum += Math.pow(Math.abs(value - avg), 2);
		}
		return Math.pow(stDevSum / data.length, .5);
	}
	
	public static double getSum(double[] data) {
		double sum = 0.0;
		for (double value : data) {
			sum += value;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		// test code
		double[] testArray = {1, 2, 3, 5, 10, 12, 15, 25};
		System.out.println(getMin(testArray));
		System.out.println(getMax(testArray));
		System.out.println(getMean(testArray));
		System.out.println(getSum(testArray));
		System.out.println(getStdDev(testArray));
		System.out.println(getPercentile(testArray, 70));
		System.out.println(Arrays.toString(getResample(testArray)));
		System.out.println(Arrays.toString(getSortedResampleMeans(testArray, 10)));
		System.out.println(Arrays.toString(getResampleMeanPercentiles(testArray, 100, 10, 25, 33, 65, 90)));
	}

}
