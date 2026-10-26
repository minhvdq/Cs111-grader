import java.util.Arrays;
import java.util.Random;

public class Statistics {
	
	public static double getMin(double[] data) {
		double min = data[0];
		
		for(int i = 1; i < data.length; i++) {
			if (data[i] < min) {
				min = data[i];
			}
		}
			
			return min;
	}
	
	public static double getMax(double[] data) {
		double max = data[0];
		for(int i = 1; i < data.length; i++) {
			if (data[i] > max) {
				max = data[i];
		
	}
			}
		return max;
		}
	
	public static double getSum(double[] data) {
		double sum = 0;
		for(int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	public static double getMean(double[] data) {
		return getSum(data) / data.length;
	}
	
	public static double getStdDev(double[] data) {
		double mean = getMean(data);
		double sum = 0;
		
		for(int i = 0; i < data.length; i++) {
			double difference = data[i] - mean;
			sum+= difference * difference;
		}
		return Math.sqrt(sum / data.length);
	}
		
	public static double[] getResample(double[] data) {
		Random random = new Random();
		double[] resample = new double[data.length];
		
		for(int i = 0; i < data.length; i++) {
			resample[i] = data[random.nextInt(data.length)];
		}
		return resample;
	}
	
	public static double[] getSortedResampleMeans(double[] data, int numResamples) {
		double[] means = new double[numResamples];
		
		for(int i = 0; i < means.length; i++) {
			means[i] = getMean(getResample(data));
		}
		Arrays.sort(means);
		return means;	
	}
	
	public static double getPercentile(double[] data, double percentile) {
		double exactIndex = percentile * data.length / 100.0;
		int index = ((Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14) ? (int) Math.round(exactIndex)
				: (int) Math.floor(percentile * (data.length + 1) / 100.0) - 1);
		index = (index < 0) ? 0 : (index >= data.length) ? data.length - 1: index;
		
		return data[index];
	}
	
	public static double[] getResampleMeanPercentiles(double[] data, int numResamples, double...percentiles) {
		double[] sortedMeans = getSortedResampleMeans(data, numResamples);
		double[] result = new double[percentiles.length];
		
		for(int i = 0; i < percentiles.length; i++) {
			result[i] = getPercentile(sortedMeans, percentiles[i]);
		}
		return result;
	}
	
	
			

	public static void main(String[] args) {
		
		double[] data = new double[100];
		Arrays.fill(data, 0, 55, 1);
		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 5, 95)));
		

	}

}
