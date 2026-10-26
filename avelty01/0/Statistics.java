import java.util.Arrays;

public class Statistics {

    public static double minimum(double[] data) {
        double min = data[0];
        for (int i = 0; i < data.length; i = i + 1) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }
    public static double maximum(double[] data) {
        double max = data[0];
        for (int i = 0; i < data.length; i = i + 1) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }
    public static double sum(double[] data) {
        double total = 0.0;
        for (int i = 0; i < data.length; i = i + 1) {
            total = total + data[i];
        }
        return total;
    }

    public static double mean(double[] data) {
        double totalSum = sum(data);
        double totalCount = data.length;
        double average = totalSum / totalCount;
        return average;
    }

    public static double populationStandardDeviation(double[] data) {
        double avg = mean(data);
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
            double calculatedMean = mean(oneResampleArray);
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
    public static void main(String[] args) {
        double[] initialData = {1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0};

        System.out.println("--- Core Stats ---");
        System.out.println("Minimum: " + minimum(initialData));
        System.out.println("Maximum: " + maximum(initialData));
        System.out.println("Sum: " + sum(initialData));
        System.out.println("Mean: " + mean(initialData));
        System.out.println("Pop Std Dev: " + populationStandardDeviation(initialData) + "\n");

        double[] confidenceInterval90 = getResampleMeanPercentiles(initialData, 1000000, 0.05, 0.95);
        System.out.println("5th Percentile: " + confidenceInterval90[0]);
        System.out.println("95th Percentile: " + confidenceInterval90[1]);
        double[] confidenceInterval70 = getResampleMeanPercentiles(initialData, 1000000, 0.15, 0.85);
        System.out.println("15th Percentile: " + confidenceInterval70[0]);
        System.out.println("85th Percentile: " + confidenceInterval70[1]);
    }
}
