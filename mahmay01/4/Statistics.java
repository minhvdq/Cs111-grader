import java.util.Arrays;
import java.util.Random;

public class Statistics {
	/**
	 * Return the minimum value from the given data array.
	 * @param data data array
	 * @return the minimum value in the data array
	 */
	public static double getMin(double[] data) {
		double min = data[0];
		for (int i =1; i < data.length; i++)
			if (data[i] < min )
				min = data[i];
		return min;
	}
	
	/**
	 * Return the maximum value from the given data array.
	 * @param data data array
	 * @return the maximum value in the data array
	 */
	public static double getMax(double[] data) {
		double max = data[0];
		for (int i =1; i < data.length; i++)
			if (data[i] > max )
				max = data[i];
		return max;
	}
	
	/**
	 * Get the sum of the values in the given data array.
	 * @param data data array
	 * @return the sum of the values in the given data array
	 */
	public static double getSum(double[] data) {
		double sum =0;
		for (int i =0; i<data.length;i++)
			sum += data[i];
		return sum;
	}
	
	/**
	 * Get the mean (average) of the values in the given data array.
	 * @param data data array
	 * @return the mean of the values in the given data array
	 */
	public static double getMean(double[] data) {
		double sum = getSum(data);
		double mean = sum / data.length;
		return mean;
	}
	
	/**
	 * Return the population standard deviation of the data. (See <a href="https://en.wikipedia.org/wiki/Standard_deviation">https://en.wikipedia.org/wiki/Standard_deviation</a>.)
	 * @param data given data array
	 * @return population standard deviation of the data.
	 */
	public static double getStdDev(double[] data) {
		double m = getMean(data);
		double sum =0;
		
		for (int i =0; i < data.length; i++)
			sum+= Math.pow(data[i]-m, 2);
		
		return Math.sqrt(sum/data.length);
	}	
	
	/**
	 * Return a new single array of data resampled from the given data array. The resampled array is of the same length as the given data array.  
	 * Each resampled element is taken from a random index of the original array, so repetition/omission in resampling is expected.
	 * @param data given data array
	 * @return a new resampled data array with random elements chosen from the given data array (with potential repetitions/omissions) and having the same length
	 */
	public static double[] getResample(double[] data) {
		double [] res = new double [data.length];
		Random rand = new Random();
		 int index;
		for (int i =0; i< data.length; i++) {
			index = rand.nextInt(data.length);
			res[i] = data[index]; 
		}
		
		return res;
	}
	
	/**
	 * Compute the given number of resamples from the given data array, and return a sorted list of the means of each resample.
	 * @param data given data array
	 * @param numResamples number of resamples computed from the given data array
	 * @return an array of length numResamples containing a sorted list of means from each of the resamples
	 */
	public static double[] getSortedResampleMeans(double[] data, int numResamples) {
		double [] newArr = new double[numResamples];
		for  (int i =0; i < numResamples; i++) {
			newArr [i]= getMean(getResample(data));
		}
		Arrays.sort(newArr);
		return newArr;
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
		double [] means = getSortedResampleMeans(data, numResamples);
		double [] perArr = new double[percentiles.length];
		for (int i =0; i < percentiles.length;i++) {
			perArr [i] = getPercentile(means, percentiles[i]);
		}
		return perArr;
	}
	
	public static void main(String[] args) {
		double[] data = new double[100];
		Arrays.fill(data, 0, 55, 1);
		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 5, 95)));
	}

}

	


