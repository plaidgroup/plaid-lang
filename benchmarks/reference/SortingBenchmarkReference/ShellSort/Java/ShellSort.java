package SortingBenchmarkReference.ShellSort.Java;

import java.util.Random;


public class ShellSort {
	public static void ShellSort(int[] data, int[] gaps)
    {
        for (int gap : gaps)
        {
            for (int i = gap; i < data.length; i++)
            {
                int temp = data[i];
                int j = i;
                for (; j >= gap && data[j - gap] > temp; j -= gap)
                {
                    data[j] = data[j - gap];
                }
                data[j] = temp;
            }
        }
    }
}
