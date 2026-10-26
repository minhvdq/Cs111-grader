import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class StatisticsJUnitTest {

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
	
    public static final double EPSILON = 1e-4;
    public static boolean approxEquals(double d1, double d2) {
            return Math.abs(1 - d1/d2) <= EPSILON;
    }

	public static double[] getRandomUniformData(int size) {
		double[] data = new double[size];
		for (int i = 0; i < size; i++)
			data[i] = Math.random() * size;
		return data;
	}
	
	@Test
	public void testMin() {
		for (int i = 0; i < 5; i++) {
			double[] data = getRandomUniformData((int) (Math.random() * 100 + 1));
			if (Statistics.getMin(data) != getMin(data))
				fail("getMin failed to return the minimum data value.");
		}
	}
	
	@Test
	public void testMax() {
		for (int i = 0; i < 5; i++) {
			double[] data = getRandomUniformData((int) (Math.random() * 100 + 1));
			if (Statistics.getMax(data) != getMax(data))
				fail("getMax failed to return the maximum data value.");
		}
	}

	@Test
	public void testSum() {
		for (int i = 0; i < 5; i++) {
			double[] data = getRandomUniformData((int) (Math.random() * 100 + 10));
			if (!approxEquals(Statistics.getSum(data), getSum(data)))
				fail("getSum failed to return the correct data sum.");
		}
	}

	@Test
	public void testMean() {
		for (int i = 0; i < 5; i++) {
			double[] data = getRandomUniformData((int) (Math.random() * 100 + 10));
			if (!approxEquals(Statistics.getMean(data), getMean(data)))
				fail("getMean failed to return the correct data mean (average).");
		}
	}
	
	@Test
	public void testStdDev() {
		for (int i = 0; i < 5; i++) {
			double[] data = getRandomUniformData((int) (Math.random() * 100 + 10));
//			System.out.printf("data length %d your stdDev %f correct stdDev %f\n", data.length, Statistics.getStdDev(data), getStdDev(data));
			if (!approxEquals(Statistics.getStdDev(data), getStdDev(data)))
				fail("getStdDev failed to return the correct data population standard deviation.");
		}
	}
	
	@Test
	public void testResample() {
		for (int i = 0; i < 5; i++) {
			double[] data = getRandomUniformData((int) (Math.random() * 100 + 10));
			double[] dataSorted = data;
			Arrays.sort(dataSorted);
			double[] resample = Statistics.getResample(data);
			if (resample.length != data.length)
				fail("getResample resample length is not the same as the data length.");
			for (double x : resample)
				if (Arrays.binarySearch(data, x) < 0)
					fail("getResample resample contains value not in the data array.");
		}
	}
	
	@Test
	public void testSortedResampleMeans() {
		double[] data = new double[100];
		Arrays.fill(data, 0, 55, 1);
		double[] resampleMeans = Statistics.getSortedResampleMeans(data, 10000);
		double[] meansSorted = resampleMeans.clone();
		Arrays.sort(meansSorted);
		if (!Arrays.equals(resampleMeans, meansSorted))
			fail("getSortedResampleMeans returns an unsorted array.");
//		double min = Double.POSITIVE_INFINITY;
//		double max = Double.NEGATIVE_INFINITY;
//		for (int i = 0; i < 1000; i++) {
//			resampleMeans = getSortedResampleMeans(data, 10000);
//			min = (min > resampleMeans[0]) ? resampleMeans[0] : min;
//			max = (max < resampleMeans[9999]) ? resampleMeans[9999] : max;
//		}
//		System.out.println(min + " " + max); // 0.29 0.78
		if (resampleMeans[0] < 0.27 || resampleMeans[9999] > .80)
			fail("getSortedResampleMeans returns highly improbable extreme mean values.");
	}
	
	@Test
	public void testPercentile() {
		for (int i = 0; i < 5; i++) {
			int size = 90 + (int) (Math.random() * 20);
			double[] data = new double[size];
			for (int j = 0; j < data.length; j++)
				data[j] = j;
			for (int pct = 0; pct <= 100; pct+=20)
				if (Math.abs(Statistics.getPercentile(data, pct) - getPercentile(data, pct)) > 1)
					fail("getPercentile percentile approximation error is too great.");
			System.out.println();
		}
	}
	
	@Test
	public void testResampleMeanPercentiles() {
		double[] data = new double[100];
		Arrays.fill(data, 0, 55, 1);
		double[] pcts = {5, 50, 95};
//		double[] min = new double[3];
//		Arrays.fill(min, Double.POSITIVE_INFINITY);
//		double[] max = new double[3];
//		Arrays.fill(max, Double.NEGATIVE_INFINITY);
//		for (int i = 0; i < 1000; i++) {
//			double[] pctMeans = getResampleMeanPercentiles(data, 10000, pcts[0], pcts[1], pcts[2]);
//			if (pctMeans.length != pcts.length)
//				fail("getResampleMeanPercentiles returned an incorrect number of percentile values.");
//			for (int j = 0; j < pcts.length; j++) {
//				min[j] = (min[j] > pctMeans[j]) ? pctMeans[j] : min[j];
//				max[j] = (max[j] < pctMeans[j]) ? pctMeans[j] : max[j];
//			}
//		}
//		System.out.println(Arrays.toString(min));
//		System.out.println(Arrays.toString(max));
//		[0.47, 0.55, 0.63]
//		[0.47, 0.55, 0.63]
		double[] pctMeans = Statistics.getResampleMeanPercentiles(data, 10000, pcts[0], pcts[1], pcts[2]);
		if (!approxEquals(pctMeans[0], 0.47) || !approxEquals(pctMeans[1], 0.55) || !approxEquals(pctMeans[2], 0.63))
			fail("getResampleMeanPercentiles returned a highly unlikely resample mean percentile value.");
	}
	
}
