package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        int base = 1000;
        for (int i = 0; i < 8; i ++) {
            Ns.addLast(base);
            base = base * 2;
        }

        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int M = 10000;

        for (int i = 0; i < Ns.size(); i ++) {
            // create SLList
            int items = Ns.get(i);
            SLList<Integer> sl = new SLList<>();
            for (int j = 0; j < items; j ++) {
                sl.addLast(j);
            }

            // timing
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < M; j ++) {
                sl.getLast();
            }
            opCounts.addLast(M);
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }

        printTimingTable(Ns, times, opCounts);
    }

}
