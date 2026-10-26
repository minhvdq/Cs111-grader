
import java.util.Arrays;

public class Statistics {

    public static double getMin(double[] data) {
        if (data == null || data.length == 0) {
            return 0.0;
        }

        double min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }

    public static double getMax(double[] data) {
        if (data == null || data.length == 0) {
            return 0.0;
        }

        double max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    public static double getSum(double[] data) {
        if (data == null || data.length == 0) {
            return 0.0;
        }

        double sum = 0.0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        return sum;
    }

    public static double getMean(double[] data) {
        if (data == null || data.length == 0) {
            return 0.0;
        }

        return getSum(data) / data.length;
    }

    public static double getStdDev(double[] data) {
        if (data == null || data.length == 0) {
            return 0.0;
        }

        double mean = getMean(data);
        double varianceSum = 0.0;

        for (int i = 0; i < data.length; i++) {
            double difference = data[i] - mean;
            varianceSum += difference * difference;
        }

        double variance = varianceSum / data.length;
        return Math.sqrt(variance);
    }

    public static double[] getResample(double[] data) {
        if (data == null || data.length == 0) {
            return new double[0];
        }

        double[] resample = new double[data.length];

        for (int i = 0; i < resample.length; i++) {
            int randomIndex = (int) (Math.random() * data.length);
            resample[i] = data[randomIndex];
        }

        return resample;
    }

    public static double[] getSortedResampleMeans(
            double[] data, int numResamples) {

        if (numResamples <= 0) {
            return new double[0];
        }

        double[] resampleMeans = new double[numResamples];

        for (int i = 0; i < numResamples; i++) {
            double[] resample = getResample(data);
            resampleMeans[i] = getMean(resample);
        }

        Arrays.sort(resampleMeans);
        return resampleMeans;
    }

    public static double getPercentile(double[] data, double percentile) {
        if (data == null || data.length == 0) {
            return 0.0;
        }

        double exactIndex = percentile * data.length / 100.0;

        int index = (Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14)
                ? (int) Math.round(exactIndex)
                : (int) Math.floor(percentile * (data.length + 1) / 100.0) - 1;

        if (index < 0) {
            index = 0;
        } else if (index >= data.length) {
            index = data.length - 1;
        }

        return data[index];
    }

    public static double[] getResampleMeanPercentiles(
            double[] data, int numResamples, double... percentiles) {

        if (percentiles == null || percentiles.length == 0) {
            return new double[0];
        }

        double[] sortedMeans = getSortedResampleMeans(data, numResamples);
        double[] results = new double[percentiles.length];

        for (int i = 0; i < percentiles.length; i++) {
            results[i] = getPercentile(sortedMeans, percentiles[i]);
        }

        return results;
    }

    public static void main(String[] args) {
        double[] data = new double[100];
        Arrays.fill(data, 0, 55, 1);

        System.out.println(Arrays.toString(
                getResampleMeanPercentiles(data, 1000000, 5, 95)));
    }
}


