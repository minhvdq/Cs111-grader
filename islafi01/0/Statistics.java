import java.util.Arrays;
import java.util.Random;

public class Statistics {

	/**
	 * Return the minimum value from the given data array.
	 */
	public static double getMin(double[] data) {
		double min = data[0];

		for (int i = 1; i < data.length; i++) {
			if (data[i] < min) {
				min = data[i];
			}
		}

		return min;
	}

	/**
	 * Return the maximum value from the given data array.
	 */
	public static double getMax(double[] data) {
		double max = data[0];

		for (int i = 1; i < data.length; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}

		return max;
	}

	/**
	 * Get the sum of the values in the given data array.
	 */
	public static double getSum(double[] data) {
		double sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum = sum + data[i];
		}

		return sum;
	}

	/**
	 * Get the mean (average) of the values.
	 */
	public static double getMean(double[] data) {
		return getSum(data) / data.length;
	}

	/**
	 * Return the population standard deviation.
	 */
	public static double getStdDev(double[] data) {
		double mean = getMean(data);
		double sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum = sum + Math.pow(data[i] - mean, 2);
		}

		return Math.sqrt(sum / data.length);
	}

	/**
	 * Return a new resampled array.
	 */
	public static double[] getResample(double[] data) {
		Random rand = new Random();
		double[] resample = new double[data.length];

		for (int i = 0; i < data.length; i++) {
			resample[i] = data[rand.nextInt(data.length)];
		}

		return resample;
	}

	/**
	 * Return sorted means of the resamples.
	 */
	public static double[] getSortedResampleMeans(double[] data, int numResamples) {

		double[] means = new double[numResamples];

		for (int i = 0; i < numResamples; i++) {
			means[i] = getMean(getResample(data));
		}

		Arrays.sort(means);

		return means;
	}

	/**
	 * Return the approximate percentile value.
	 */
	public static double getPercentile(double[] data, double percentile) {

		double exactIndex = percentile * data.length / 100.0;
		int index = ((Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14) ? (int) Math.round(exactIndex)
				: (int) Math.floor(percentile * (data.length + 1) / 100.0) - 1);
		index = (index < 0) ? 0 : (index >= data.length) ? data.length - 1 : index;
		return data[index];
	}

	/**
	 * Return requested percentiles of resample means.
	 */
	public static double[] getResampleMeanPercentiles(double[] data, int numResamples, double... percentiles) {

		double[] means = getSortedResampleMeans(data, numResamples);
		double[] results = new double[percentiles.length];

		for (int i = 0; i < percentiles.length; i++) {
			results[i] = getPercentile(means, percentiles[i]);
		}

		return results;
	}

	public static void main(String[] args) {
		double[] data = new double[100];
		Arrays.fill(data, 0, 55, 1);
		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 5, 95)));
	}
}
