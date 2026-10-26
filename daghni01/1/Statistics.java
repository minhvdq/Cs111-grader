import java.util.Arrays;
import java.util.Random;

public class Statistics {

    public static double getMin(double[] data) {
        double min = data[0];
        for (double val : data) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    public static double getMax(double[] data) {
        double max = data[0];
        for (double val : data) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    public static double getSum(double[] data) {
        double sum = 0;
        for (double val : data) {
            sum += val;
        }
        return sum;
    }

    public static double getMean(double[] data) {
        if (data.length == 0) return 0.0;
        return getSum(data) / data.length;
    }

    public static double getStdDev(double[] data) {
        if (data.length == 0) return 0.0;
        double mean = getMean(data);
        double sumSquaredDiff = 0;
        for (double val : data) {
            sumSquaredDiff += Math.pow(val - mean, 2);
        }
        return Math.sqrt(sumSquaredDiff / data.length);
    }

    public static double[] getResample(double[] data) {
        Random random = new Random();
        double[] resample = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            resample[i] = data[random.nextInt(data.length)];
        }
        return resample;
    }

    public static double[] getSortedResampleMeans(double[] data, int numResamples) {
        double[] resampleMeans = new double[numResamples];
        for (int i = 0; i < numResamples; i++) {
            double[] resample = getResample(data);
            resampleMeans[i] = getMean(resample);
        }
        Arrays.sort(resampleMeans);
        return resampleMeans;
    }

    public static double getPercentile(double[] data, double percentile) {
        int index = (int) (percentile * data.length);
        if (index >= data.length) {
            index = data.length - 1;
        }
        if (index < 0) {
            index = 0;
        }
        return data[index];
    }

    public static double[] getResampleMeanPercentiles(double[] data, int numResamples, double... percentiles) {
        double[] sortedMeans = getSortedResampleMeans(data, numResamples);
        double[] percentileValues = new double[percentiles.length];
        for (int i = 0; i < percentiles.length; i++) {
            percentileValues[i] = getPercentile(sortedMeans, percentiles[i]);
        }
        return percentileValues;
    }
}