package interview.company.amazon;

public class Minimum_time_required_to_produce_m_items {
    /**
     * Examples:
     *
     * Input : arr[] = {1, 2, 3}, m = 11
     * Output : 6
     * In 6 sec, machine 1 produces 6 items, machine 2 produces 3 items,
     * and machine 3 produces 2 items. So to produce 11 items minimum
     * 6 sec are required.
     *
     * Input : arr[] = {5, 6}, m = 11
     * Output : 30
     */

    //The idea is to use Binary Search. The maximum possible time required to produce m items will be the maximum time in the array, amax,
    // multiplied by m i.e amax * m. So, use binary search between 1 to amax * m and find the minimum time which produces m items.
    // Efficient Java program to find
// minimum time required to
// produce m items.

    public class GFG{

        // Return the number of items can
// be produced in temp sec.
        static int findItems(int []arr, int n,
                             int temp)
        {
            int ans = 0;
            for (int i = 0; i < n; i++)
                ans += (temp / arr[i]);
            return ans;
        }

        // Binary search to find minimum time
// required to produce M items.
        static int bs(int []arr, int n,
                      int m, int high)

        {
            int low = 1;

            // Doing binary search to
            // find minimum time.
            while (low < high)
            {
                // Finding the middle value.
                int mid = (low + high) >> 1;

                // Calculate number of items to
                // be produce in mid sec.
                int itm = findItems(arr, n, mid);

                // If items produce is less than
                // required, set low = mid + 1.
                if (itm < m)
                    low = mid + 1;

                    // Else set high = mid.
                else
                    high = mid;
            }

            return high;
        }

        // Return the minimum time required to
// produce m items with given machine.
        static int minTime(int []arr, int n,
                           int m)
        {
            int maxval = Integer.MIN_VALUE;

            // Finding the maximum time in the array.
            for (int i = 0; i < n; i++)
                maxval = Math.max(maxval, arr[i]);

            return bs(arr, n, m, maxval * m);
        }

        // Driven Program
        static public void main (String[] args)
        {
            int []arr = {1, 2, 3};
            int n = arr.length;
            int m = 11;

            System.out.println(minTime(arr, n, m));
        }
    }

// This code is contributed by vt_m.

}
