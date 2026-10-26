import java.util.Arrays;
import java.util.Random;

public class Statistics {

	public static void main(String[] args) {
		
		
		
		System.out.println(getMean(new double[] {-5,3,4}));
		System.out.println(getStdDev(new double[] {2, 4, 6}));
		System.out.println(Arrays.toString(getResample(new double[] {10, 20, 30})));
		double[] data = {10, 20, 30};
		System.out.println(Arrays.toString(getSortedResampleMeans(data, 3)));
		double[] data2 = {1, 0, 1, 1, 1, 0, 1, 0, 1, 1};
		System.out.println(Arrays.toString(getResampleMeanPercentiles(data2, 10000, 5, 95)));
	}
	public static double getMin(double[] data) {
		double min = data[0];
		for (int i = 1; i < data.length; i++) {
            if (data[i] <min) {
                min = data[i];
            }	
		}
		return min;
		}
	public static double getMax(double[] data) {
		double max = data[0];
		for (int i=1; i<data.length; i++) {
			if (data[i]>max) {
				max = data[i];
			}
		}
		return max;
	}
	public static double getSum(double[] data) {
		double sum = data[0];
		for (int i=1; i<data.length; i++) {
			sum+=data[i];
		}
		return sum;
	}
	public static double getMean(double[] data) {
		double mean = getSum(data);
		mean/=data.length;
		return mean;
	}
	public static double getStdDev(double[] data) {
		double mean = getMean(data);
		double sum = 0;
		for (int i=0; i<data.length; i++) {
			sum+=Math.pow(data[i] - mean, 2);
		}
		
		return Math.sqrt(sum/data.length);
	}
	public static double[] getResample(double[] data) {
		Random random = new Random();
		double[] newdata = new double[data.length];
		for (int i=0; i<data.length; i++) {
			int roll = random.nextInt(data.length);
			newdata[i] = data[roll];
		}
		return newdata;
	}
	public static double[] getSortedResampleMeans(double[] data, int numResamples) {
		double[] mix =new double[numResamples];
		for (int i=0; i<numResamples; i++) {
			mix[i] = getMean(getResample(data));
		}
		Arrays.sort(mix);
		return mix;
	}
	
	public static double[] getResampleMeanPercentiles(double[] data, int numResamples, double... percentiles){
		
		double[] means = getSortedResampleMeans(data, numResamples);
		double[] results = new double[percentiles.length];
		for (int i=0; i<percentiles.length; i++) {
			 results[i] = getPercentile(means, percentiles[i]);
		}
		return results;

	}
	public static double getPercentile(double[] data, double percentile) {
	    double exactIndex = percentile * data.length / 100.0;
	    int index = ((Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14)
	            ? (int) Math.round(exactIndex)
	            : (int) Math.floor(percentile * (data.length + 1) / 100.0) - 1);
	    index = (index < 0) ? 0 : (index >= data.length) ? data.length - 1 : index;
	    return data[index];
	}
}