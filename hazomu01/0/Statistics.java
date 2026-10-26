
import java.util.Arrays;

public class Statistics {

    public static double getMin(double[] numbers) {
        double smallest = numbers[0];
        for (int index = 0; index < numbers.length; index++) {
            if (numbers[index] < smallest)
                smallest = numbers[index];
        }
        return smallest;
    }

    public static double getMax(double[] numbers) {
        double largest = numbers[0];
        for (int index = 0; index < numbers.length; index++) {
            if (numbers[index] > largest)
                largest = numbers[index];
        }
        return largest;
    }

    public static double getSum(double[] numbers) {
        double total = 0;
        for (int index = 0; index < numbers.length; index++) {
            total += numbers[index];
        }
        return total;
    }

    public static double getMean(double[] numbers) {
        return getSum(numbers) / numbers.length;
    }

    public static double getStdDev(double[] numbers) {
        double average = getMean(numbers);
        double total = 0;

        for (int index = 0; index < numbers.length; index++) {
            total += (numbers[index] - average)
                    * (numbers[index] - average);
        }

        return Math.sqrt(total / numbers.length);
    }

    public static double[] getResample(double[] numbers) {
        double[] sample = new double[numbers.length];

        for (int index = 0; index < numbers.length; index++) {
            int randomIndex =
                    (int) (Math.random() * numbers.length);
            sample[index] = numbers[randomIndex];
        }

        return sample;
    }

    public static double[] getSortedResampleMeans(
            double[] numbers, int sampleCount) {
        double[] averages = new double[sampleCount];

        for (int index = 0; index < sampleCount; index++) {
            averages[index] = getMean(getResample(numbers));
        }

        Arrays.sort(averages);
        return averages;
    }

    public static double getPercentile(
            double[] numbers, double percentile) {
        double exactIndex = percentile * numbers.length / 100.0;
        int index = ((Math.abs(exactIndex - Math.round(exactIndex)) <= 1e-14)
                ? (int) Math.round(exactIndex)
                : (int) Math.floor(
                        percentile * (numbers.length + 1) / 100.0) - 1);

        index = (index < 0) ? 0
                : (index >= numbers.length) ? numbers.length - 1 : index;

        return numbers[index];
    }

    public static double[] getResampleMeanPercentiles(
            double[] numbers, int sampleCount, double... percentiles) {
        double[] averages =
                getSortedResampleMeans(numbers, sampleCount);
        double[] results = new double[percentiles.length];

        for (int index = 0; index < percentiles.length; index++) {
            results[index] = getPercentile(averages, percentiles[index]);
        }

        return results;
    }

    public static void main(String[] args) {
        double[] numbers = new double[100];
        Arrays.fill(numbers, 0, 55, 1);

        System.out.println(Arrays.toString(getResampleMeanPercentiles(numbers, 1000000, 5, 95)));
    }
}
