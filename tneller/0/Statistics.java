import java.util.Arrays;

public class Statistics {

	/**
	 * Return the minimum value from the given data array.
	 * @param data data array
	 * @return the minimum value in the data array
	 */
	public static double getMin(double[] data) {
		double min = data[0];
		for (double datum: data)
			min = (datum < min) ? datum : min;
		return min;
	}
	
	/**
	 * Return the maximum value from the given data array.
	 * @param data data array
	 * @return the maximum value in the data array
	 */
	public static double getMax(double[] data) {
		double max = data[0];
		for (double datum: data)
			max = (datum > max) ? datum : max;
		return max;
	}
	
	/**
	 * Get the sum of the values in the given data array.
	 * @param data data array
	 * @return the sum of the values in the given data array
	 */
	public static double getSum(double[] data) {
		double sum = 0;
		for (double datum : data)
			sum += datum;
		return sum;
	}
	
	/**
	 * Get the mean (average) of the values in the given data array.
	 * @param data data array
	 * @return the mean of the values in the given data array
	 */
	public static double getMean(double[] data) {
		return getSum(data) / data.length;
	}
	
	/**
	 * Return the population standard deviation of the data. (See <a href="https://en.wikipedia.org/wiki/Standard_deviation">https://en.wikipedia.org/wiki/Standard_deviation</a>.)
	 * @param data given data array
	 * @return population standard deviation of the data.
	 */
	public static double getStdDev(double[] data) {
		double mean = getMean(data);
		double sumOfSquareDiffs = 0;
		for (double datum : data)
			sumOfSquareDiffs += (datum - mean) * (datum - mean);
		return Math.sqrt(sumOfSquareDiffs / data.length);
	}	
	
	/**
	 * Return a new single array of data resampled from the given data array. The resampled array is of the same length as the given data array.  
	 * Each resampled element is taken from a random index of the original array, so repetition/omission in resampling is expected.
	 * @param data given data array
	 * @return a new resampled data array with random elements chosen from the given data array (with potential repetitions/omissions) and having the same length
	 */
	public static double[] getResample(double[] data) {
		double[] resampledData = new double[data.length];
		for (int i = 0; i < data.length; i++)
			resampledData[i] = data[(int) (Math.random() * data.length)];
		return resampledData;
	}
	
	/**
	 * Compute the given number of resamples from the given data array, and return a sorted list of the means of each resample.
	 * @param data given data array
	 * @param numResamples number of resamples computed from the given data array
	 * @return an array of length numResamples containing a sorted list of means from each of the resamples
	 */
	public static double[] getSortedResampleMeans(double[] data, int numResamples) {
		double[] means = new double[numResamples];
		for (int i = 0; i < numResamples; i++)
			means[i] = getMean(getResample(data));
		Arrays.sort(means);
		return means;
	}
	
	/**
	 * Given a sorted data array and a percentile, return the approximate percentile value from that sorted array.
	 * @param data sorted data array
	 * @param percentile percentile requested from data array 
	 * @return data array entry that approximates the requested percentile value
	 */
	public static double getPercentile(double[] data, double percentile) {
		double exactIndex = percentile * data.length / 100.0;
		int index = ((Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14) ? (int) Math.round(exactIndex) : (int) Math.floor(percentile * (data.length + 1) / 100.0) - 1);
		index = (index < 0) ? 0 : (index >= data.length) ? data.length - 1 : index;
		return data[index];
	}
	
	/**
	 * Given a data array and a specified number of resamples, return the given percentiles values for the resample means.
	 * @param data source data array
	 * @param numResamples number of times to resample the array and compute the means
	 * @param percentiles given percentiles to compute from the sorted means
	 * @return an array of specified percentile values for the resample means
	 */
	public static double[] getResampleMeanPercentiles(double[] data, int numResamples, double... percentiles) {
		double[] sortedMeans = getSortedResampleMeans(data, numResamples);
		double[] pctMeans = new double[percentiles.length];
		for (int i = 0; i < percentiles.length; i++)
			pctMeans[i] = getPercentile(sortedMeans, percentiles[i]);
		return pctMeans;
	}
	
	public static void main(String[] args) {
		int p0Wins = 50, p1Wins = 48, draws = 1, totalGames = p0Wins + p1Wins + draws;
		double[] data = new double[totalGames];
		Arrays.fill(data, 0, p0Wins, 0);
		Arrays.fill(data, p0Wins, p0Wins + p1Wins, 1);
		Arrays.fill(data, p0Wins + p1Wins, totalGames, 0.5);
		int high = 99, low = 1; // assumed including, not including 0.5 respectively
		// Looking for highest confidence interval not including 0.5:
		while (high - low > 1) {
			int mid = (high + low) / 2;
			double[] pcts = getResampleMeanPercentiles(data, 1000000, 50 - mid/2.0, 50 + mid/2.0);
			System.out.printf("%d%% confidence: %s\n", mid, Arrays.toString(pcts));
			boolean containsHalf = pcts[0] <= 0.5 && pcts[1] >= 0.5;
			if (containsHalf) {
				high = mid;
			}
			else {
				low = mid;
			}
		}
		System.out.printf("Player 0 is better with %d%% confidence.\n", low);
		
//		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 5, 95))); // 90% confidence interval
//		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 45, 55))); // 10% confidence interval

		
//		double[] data = new double[10];
//		Arrays.fill(data, 0, 7, 1);
//		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 5, 95)));
		
//		for (int i = data.length - 1; i > 0; i--) {
//			int j = (int) (Math.random() * (i + 1));
//			double tmp = data[j];
//			data[j] = data[i];
//			data[i] = tmp;
//		}
//		System.out.println(Arrays.toString(data));
//		// [1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0]
//		System.out.println(Arrays.toString(getResample(data)));		
//		System.out.println(Arrays.toString(getResample(data)));
//		System.out.println(Arrays.toString(getResample(data)));
//		System.out.println(Arrays.toString(getResample(data)));
//		
////		System.out.println(Arrays.toString(getSortedResampleMeans(data, 1000000)));
//		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 5, 95)));
//		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 10, 90)));
//		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 15, 85)));

	}

}
