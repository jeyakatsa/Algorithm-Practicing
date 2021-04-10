import java.util.*;

class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
};

class MergeIntervals {



  //MergeLikely Intervals
  public static List<Interval> merge(List<Interval> intervals) {
    if (intervals.size() < 2)
      return intervals;

    // sort the intervals by start time
    Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

    List<Interval> mergedIntervals = new LinkedList<Interval>();
    Iterator<Interval> intervalItr = intervals.iterator();
    Interval interval = intervalItr.next();
    int start = interval.start;
    int end = interval.end;

    while (intervalItr.hasNext()) {
      interval = intervalItr.next();
      if (interval.start <= end) { // overlapping intervals, adjust the 'end'
        end = Math.max(interval.end, end);
      } else { // non-overlapping interval, add the previous interval and reset
        mergedIntervals.add(new Interval(start, end));
        start = interval.start;
        end = interval.end;
      }
    }
    // add the last interval
    mergedIntervals.add(new Interval(start, end));

    return mergedIntervals;
  }




  //Insert Interval
  public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    if (intervals == null || intervals.isEmpty())
      return Arrays.asList(newInterval);

    List<Interval> mergedIntervals = new ArrayList<>();

    int i = 0;
    // skip (and add to output) all intervals that come before the 'newInterval'
    while (i < intervals.size() && intervals.get(i).end < newInterval.start)
      mergedIntervals.add(intervals.get(i++));

    // merge all intervals that overlap with 'newInterval'
    while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
      newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
      newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
      i++;
    }

    // insert the newInterval
    mergedIntervals.add(newInterval);

    // add all the remaining intervals to the output
    while (i < intervals.size())
      mergedIntervals.add(intervals.get(i++));

    return mergedIntervals;
  }



  //Intervals Intersection
  //Given two lists of intervals, find intersection of two lists.
  public static Interval[] merge2(Interval[] arr1, Interval[] arr2) {
    List<Interval> result = new ArrayList<Interval>();
    int i = 0, j = 0;
    while (i < arr1.length && j < arr2.length) {
      // check if the interval arr[i] intersects with arr2[j]
      // check if one of the interval's start time lies within the other interval
      if ((arr1[i].start >= arr2[j].start && arr1[i].start <= arr2[j].end)
          || (arr2[j].start >= arr1[i].start && arr2[j].start <= arr1[i].end)) {
        // store the intersection part
        result.add(new Interval(Math.max(arr1[i].start, arr2[j].start), Math.min(arr1[i].end, arr2[j].end)));
      }

      // move next from the interval which is finishing first
      if (arr1[i].end < arr2[j].end)
        i++;
      else
        j++;
    }

    return result.toArray(new Interval[result.size()]); 
  }

  //Conflicting Appointments
  public static boolean canAttend(Interval[] intervals) {
    if (intervals == null || intervals.length == 0) {
      return false;
    }
    Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

    // find any overlapping appointment
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i].start < intervals[i - 1].end) {
        // please note the comparison above, it is "<" and not "<="
        // while merging we needed "<=" comparison, as we will be merging the two
        // intervals having condition "intervals[i].start == intervals[i - 1].end" but
        // such intervals don't represent conflicting appointments as one starts right
        // after the other
        return false;
      }
    }
    return true;  
  }


  public static void main(String[] args) {


    Interval[] input = new Interval[] { new Interval(1, 3), new Interval(5, 7), new Interval(9, 12) };
    boolean result = canAttend(input);
    System.out.print("Can attend? " + result);

    // Interval[] input1 = new Interval[] { new Interval(1, 3), new Interval(5, 6), new Interval(7, 9) };
    // Interval[] input2 = new Interval[] { new Interval(2, 3), new Interval(5, 7) };
    // Interval[] result = merge2(input1, input2);
    // System.out.print("Intervals Intersection: ");
    // for (Interval interval : result)
    //   System.out.print("[" + interval.start + "," + interval.end + "] ");
    // System.out.println();




    // List<Interval> input = new ArrayList<Interval>();
    // input.add(new Interval(1, 4));
    // input.add(new Interval(2, 5));
    // input.add(new Interval(7, 9));
    // System.out.print("Merged intervals: ");
    // for (Interval interval : MergeIntervals.merge(input))
    //   System.out.print("[" + interval.start + "," + interval.end + "] ");
    // System.out.println();

    // input = new ArrayList<Interval>();
    // input.add(new Interval(6, 7));
    // input.add(new Interval(2, 4));
    // input.add(new Interval(5, 9));
    // System.out.print("Merged intervals: ");
    // for (Interval interval : MergeIntervals.merge(input))
    //   System.out.print("[" + interval.start + "," + interval.end + "] ");
    // System.out.println();

    // input = new ArrayList<Interval>();
    // input.add(new Interval(1, 4));
    // input.add(new Interval(2, 6));
    // input.add(new Interval(3, 5));
    // System.out.print("Intervals aftr inserting new interval: ");
    // for (Interval interval : MergeIntervals.insert(input, new Interval(2, 9)))
    //   System.out.print("[" + interval.start + "," + interval.end + "] ");
    // System.out.println();
  }


}