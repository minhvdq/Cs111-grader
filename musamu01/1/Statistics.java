
import java.util.Arrays;
import java.util.Random;

public class Statistics {

    private static final Random random = new Random();

   
    public static double getMin(double[] data) {
        double min = data[0];
        for (double value : data)
            if (value < min) min = value;
        return min;
    }

 
    public static double getMax(double[] data) {
        double max = data[0];
        for (double value : data)
            if (value > max) max = value;
        return max;
    }

  
    public static double getSum(double[] data) {
        double sum = 0.0;
        for (double value : data)
            sum += value;
        return sum;
    }

  
    public static double getMean(double[] data) {
        return getSum(data) / data.length;
    }

    
    public static double getStdDev(double[] data) {
        double mean = getMean(data);
        double sum = 0.0;
        for (double value : data)
            sum += (value - mean) * (value - mean);
        return Math.sqrt(sum / data.length);
    }

  
    public static double[] getResample(double[] data) {
        double[] resample = new double[data.length];
        for (int i = 0; i < data.length; i++)
            resample[i] = data[random.nextInt(data.length)];
        return resample;
    }

    
    public static double[] getSortedResampleMeans(
            double[] data, int numResamples) {
        double[] means = new double[numResamples];
        for (int i = 0; i < numResamples; i++)
            means[i] = getMean(getResample(data));
        Arrays.sort(means);
        return means;
    }

  
    public static double getPercentile(double[] data, double percentile) {
        double exactIndex = percentile * data.length / 100.0;
        int index = ((Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14)
                ? (int) Math.round(exactIndex)
                : (int) Math.floor(percentile * (data.length + 1)
                / 100.0) - 1);
        index = (index < 0) ? 0
                : (index >= data.length) ? data.length - 1 : index;
        return data[index];
    }

  
    public static double[] getResampleMeanPercentiles(
            double[] data, int numResamples, double... percentiles) {
        double[] means = getSortedResampleMeans(data, numResamples);
        double[] values = new double[percentiles.length];
        for (int i = 0; i < percentiles.length; i++)
            values[i] = getPercentile(means, percentiles[i]);
        return values;
    }

    public static void main(String[] args) {
        double[] data = new double[100];
        Arrays.fill(data, 0, 55, 1);
        System.out.println(Arrays.toString(
                getResampleMeanPercentiles(data, 1000000, 5, 95)));
    }
}
