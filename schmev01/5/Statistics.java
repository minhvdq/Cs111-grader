import java.util.Arrays;
import java.util.Random;

/**
 * Statistics
 */
public class Statistics {

    public static double getMax(double[] data) {
        double max = Double.MIN_VALUE;
        for (double value : data) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static double getMin(double[] data) {
        double min = Double.MAX_VALUE;
        for (double value : data) {
            if (value < min) {
                min = value;
            }
        }
        return min;
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
        double stdDev = 0;
        for (double value : data) {
            stdDev += Math.pow(value - mean, 2);
        }
        return Math.sqrt(stdDev / data.length);
    }

    public static double[] getResample(double[] data) {
        Random rand = new Random();
        double[] resample = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            resample[i] = data[rand.nextInt(data.length)];
        }
        return resample;
    }

    public static double[] getSortedResampleMeans(
        double[] data,
        int numResamples
    ) {
        double[] resampleMeans = new double[numResamples];
        for (int i = 0; i < numResamples; i++) {
            resampleMeans[i] = getMean(getResample(data));
        }
        Arrays.sort(resampleMeans);
        return resampleMeans;
    }

    public static double getPercentile(double[] data, double percentile) {
        double exactIndex = (percentile * data.length) / 100.0;
        int index =
            Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14
                ? (int) Math.round(exactIndex)
                : (int) Math.floor((percentile * (data.length + 1)) / 100.0) -
                  1;
        index = index < 0 ? 0 : index >= data.length ? data.length - 1 : index;
        return data[index];
    }

    public static double[] getResampleMeanPercentiles(
        double[] data,
        int numResamples,
        double... percentiles
    ) {
        double[] resampleMeans = getSortedResampleMeans(data, numResamples);
        double[] percentileValues = new double[percentiles.length];
        for (int i = 0; i < percentileValues.length; i++) {
            percentileValues[i] = getPercentile(resampleMeans, percentiles[i]);
        }
        return percentileValues;
    }

    public static void main(String[] args) {
        double[] data = { -1, 5, 2, 4, 9, 3, 7 };
        System.out.println("Max: " + getMax(data));
        System.out.println("Mean: " + getMean(data));
        System.out.println("Sum: " + getSum(data));
        System.out.println("Min: " + getMin(data));
        System.out.println("Standard deviation: " + getStdDev(data));
        System.out.println("Resample: " + Arrays.toString(getResample(data)));
        System.out.println(
            "Sorted resample means: " +
                Arrays.toString(getSortedResampleMeans(data, 10))
        );
        System.out.println(
            "Resample mean percentiles: " +
                Arrays.toString(getResampleMeanPercentiles(data, 80, 3, 80, 1))
        );
    }
}
