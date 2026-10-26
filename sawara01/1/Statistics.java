import java.util.Arrays;
import java.util.Random;

public class Statistics {

	private static final Random RANDOM = new Random();
	
public static double getMin(double[] data) {
		double min = data[0];
		for (double d : data) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}
	
	
public static double getMax(double[] data) {
double max = data[0];
for (double d : data)
	if (d > max) {
		max = d;
	}
return max;
	}
	
	
public static double getSum(double[] data) {
double sum = 0.0; 
for (double d : data) {
	sum += d;
}
return sum;
	}
	
	
public static double getMean(double[] data) {
		return getSum(data) / data.length;
	}
	
	
public static double getStdDev(double[] data) {
		double mean = getMean(data);
		double sumSquares = 0.0;
		for (double d : data) {
			sumSquares += (d - mean) * (d - mean);
		}
		return Math.sqrt(sumSquares / data.length);
	}	
	
	
public static double[] getResample(double[] data) {
double[] resample = new double[data.length];
for (int i = 0; i < resample.length; i++) {
	resample[i] = data[RANDOM.nextInt(data.length)];
}
return resample;
	}
	

	public static double[] getSortedResampleMeans(double[] data, int numResamples) {
double[] means = new double[numResamples];
		for (int i = 0; i < means.length; i++) {
			means[i] = getMean(getResample(data));
		}
		Arrays.sort(means);
		return means;
	}
	
	
	public static double getPercentile(double[] data, double percentile) {
		double exactIndex = percentile * data.length / 100.0;
		int index = ((Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14) ? (int) Math.round(exactIndex) : (int) Math.floor(percentile * (data.length + 1) / 100.0) - 1);
//		index = (index < 0) ? 0 : (index >= data.length) ? data.length - 1 : index;
		index = Math.max(0, Math.min(index, data.length - 1));
		return data[index];
	}
	

	public static double[] getResampleMeanPercentiles(double[] data, int numResamples, double... percentiles) {
double[] sortedMeans = getSortedResampleMeans(data, numResamples);
double[] values = new double[percentiles.length];
for (int i = 0; i < percentiles.length; i++) {
	values[i] = getPercentile(sortedMeans, percentiles[i]); 
}
return values;
	}
	
	public static void main(String[] args) {
		double[] data = new double[100];
		Arrays.fill(data, 0, 55, 1);
		System.out.println(Arrays.toString(getResampleMeanPercentiles(data, 1000000, 5, 95)));
	}

}
