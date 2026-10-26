import java.util.Arrays;

public class Statistics {

    public static double getMin(double[] data) {
        double min = data[0];
        for (int i = 0; i < data.length; i = i + 1) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }

    public static double getMax(double[] data) {
        double max = data[0];
        for (int i = 0; i < data.length; i = i + 1) {
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
        double totalSum = getSum(data);
        double totalCount = data.length;
        double average = totalSum / totalCount;
        return average;
    }

    public static double getStdDev(double[] data) {
        double avg = getMean(data);
        double varianceSum = 0.0;
        
        for (int i = 0; i < data.length; i = i + 1) {
            double difference = data[i] - avg;
            double squaredDifference = difference * difference; 
            varianceSum = varianceSum + squaredDifference;
        }
        
        double variance = varianceSum / data.length;
        double standardDeviation = Math.sqrt(variance);
        return standardDeviation;
    }

    public static double[] getResample(double[] data) {
        int length = data.length;
        double[] resample = new double[length];
        
        for (int i = 0; i < resample.length; i = i + 1) {
            double randomFraction = Math.random();
            int randomIndex = (int) (randomFraction * data.length);
            resample[i] = data[randomIndex];
        }
        return resample;
    }

    public static double[] getSortedResampleMeans(double[] data, int numResamples) {
        double[] resampleMeans = new double[numResamples];
        
        for (int i = 0; i < numResamples; i = i + 1) {
            double[] oneResampleArray = getResample(data);
            double calculatedMean = getMean(oneResampleArray);
            resampleMeans[i] = calculatedMean;
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
        int arrayLength = percentiles.length;
        double[] results = new double[arrayLength];
        
        for (int i = 0; i < percentiles.length; i = i + 1) {
            double targetPercentile = percentiles[i];
            double foundValue = getPercentile(sortedMeans, targetPercentile);
            results[i] = foundValue;
        }
        return results;
    }
}


