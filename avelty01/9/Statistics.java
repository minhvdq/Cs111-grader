import java.util.Arrays;

public class Statistics {

    public static double getMin(double[] data) {
        double min = data[0];
        for (int i = 1; i < data.length; i = i + 1) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }

    public static double getMax(double[] data) {
        double max = data[0];
        for (int i = 1; i < data.length; i = i + 1) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    public static double getSum(double[] data) {
        double total = 0.0;
        for (int i = 0; i < data.length; i = i + 1) {
            total = total + data[i];
        }
        return total;
    }

    public static double getMean(double[] data) {
        if (data.length == 0) return 0.0;
        return getSum(data) / data.length;
    }

    public static double getStdDev(double[] data) {
        if (data.length == 0) return 0.0;
        double avg = getMean(data);
        double varianceSum = 0.0;
        
        for (int i = 0; i < data.length; i = i + 1) {
            double difference = data[i] - avg;
            varianceSum = varianceSum + (difference * difference);
        }
        
        return Math.sqrt(varianceSum / data.length);
    }

    public static double[] getResample(double[] data) {
        double[] resample = new double[data.length];
        for (int i = 0; i < resample.length; i = i + 1) {
            int randomIndex = (int) (Math.random() * data.length);
            resample[i] = data[randomIndex];
        }
        return resample;
    }

    public static double[] getSortedResampleMeans(double[] data, int numResamples) {
        double[] resampleMeans = new double[numResamples];
        for (int i = 0; i < numResamples; i = i + 1) {
            resampleMeans[i] = getMean(getResample(data));
        }
        Arrays.sort(resampleMeans);
        return resampleMeans;
    }

    public static double getPercentile(double[] data, double percentile) {
        if (data == null || data.length == 0) return 0.0;
        double index = percentile * (data.length - 1);
        int lower = (int) Math.floor(index);
        int upper = (int) Math.ceil(index);
        if (lower == upper) return data[lower];
        return data[lower] + (index - lower) * (data[upper] - data[lower]);
    }

    public static double[] getResampleMeanPercentiles(double[] data, int numResamples, double... percentiles) {
        double[] sortedMeans = getSortedResampleMeans(data, numResamples);
        double[] results = new double[percentiles.length];
        for (int i = 0; i < percentiles.length; i = i + 1) {
            results[i] = getPercentile(sortedMeans, percentiles[i]);
        }
        return results;
    }
}

