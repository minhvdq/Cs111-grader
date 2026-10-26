import java.util.Arrays;
import java.util.Random;

public class Statistics {

		public static Random rand = new Random();
		
		public static double getMin(double[] data) {
			double min = data[0];
			for (double value : data) {
				if (value < min) {
					min = value;
				}
			}
			return min;
		}
		
		public static double getMax(double[] data) {
			double max = data[0];
			for (double value : data) {
				if (value > max) {
					max = value;
				}
			}
			return max;
		}
		
		public static double getSum(double[] data) {
			double sum = 0;
			for (double value : data) {
				sum += value;
			}
			return sum;
		}
		
		public static double getMean(double[] data) {
			return getSum(data) / data.length;
		}
		
		public static double getStdDev(double[] data) {
			double mean = getMean(data);
			double sumSquareDiffs = 0;
			for (double value : data) {
				sumSquareDiffs += Math.pow(value - mean, 2);
			}
			return Math.sqrt(sumSquareDiffs / data.length);
		}
		
		
		public static double getPercentile(double[] data, double percentile) {
			int index = (int) Math.round(percentile * (data.length - 1));
			return data[index];
		}
		
		public static double[] getResample(double[] data) {
			double[] resample = new double[data.length];
			for ( int i = 0; i < resample.length; i++) {
				resample[i] = data[rand.nextInt(data.length)];
			}
			return resample;
		}
		
		public static double[] getSortedResampleMeans(double[] data, int numResamples) {
			double[] means = new double[numResamples];
			for (int i = 0; i < numResamples; i++) {
				double[] resample = getResample(data);
				means[i] = getMean(resample);
			}
			Arrays.sort(means);
			return means;
		}
		
		public static double[] getResampleMeanPercentiles(double[] data, int numResamples, double[] percentiles) {
			double[] sortedMeans = getSortedResampleMeans(data, numResamples);
			double[] resultPercentiles = new double[percentiles.length];
			
			for (int i = 0; i < percentiles.length; i++) {
				resultPercentiles[i] = getPercentile(sortedMeans, percentiles[i]);
			}
			return resultPercentiles;
		}
}

