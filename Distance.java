//psolarski25@georgefox.edu

/**
 * @file Distance.java
 * @brief A library class of distance metrics
 * @author Payton Solarski
 */

public class Distance
{
    /**
     * Calculates the Euclidean distance between two points
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double euclid(double x1,  double y1, double x2, double y2)
    {
        double part1;
        double part2;
        part1 = Math.pow(x1 - x2, 2);
        part2 = Math.pow(y1 - y2, 2);

        return Math.sqrt(part1 + part2);
    }

    /**
     * Calculates the Manhattan distance between two points
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static int manhattan(int x1, int y1, int x2, int y2)
    {
        int part1;
        int part2;
        part1 = Math.abs(x1 - x2);
        part2 = Math.abs(y1 - y2);

        return part1 + part2;
    }

    /**
     * Calculates the cosine similarity between two points
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double sim(double x1, double y1, double x2, double y2)
    {
        double norm1;
        double norm2;
        norm1 = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
        norm2 = Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2));

        return ((x1 * x2) + (y1 * y2) / (norm1 * norm2));
    }

    /**
     * Main entry into the program
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(euclid(1.0, 2.0, 3.0, 4.0));
        System.out.println(manhattan(1, 2, 3, 4));
        System.out.println(sim(1.0, 2.0, 3.0, 4.0));
    }
}
