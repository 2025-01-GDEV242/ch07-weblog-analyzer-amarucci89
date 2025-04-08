/** Chapter 7 Lab - Weblog Analyzer
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Alessandro Marucci
 * @version 2025.04.07
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     * @param filename The file to be analyzed.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Return the number of accesses recorded in the log file.
     * @return The number of accesses.
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for (int hourCount : hourCounts)
        {
            total = total + hourCount;
        }
        return total;
    }
    
    /**
     * Return the busiest hour of the day.
     * @return The busiest hour.
     */
    public int busiestHour()
    {
        int busiestHour = 0;
        for (int hour = 1; hour < hourCounts.length; hour++)
        {
            if (hourCounts[hour] > hourCounts[busiestHour])
            {
                busiestHour = hour;
            }
        }
        return busiestHour;       
    }
    
    /**
     * Return the busiest two hour period of the day.
     * @return The busiest two hour period.
     */
    public int busiestTwoHourPeriod()
    {
        int busiestPeriod = 0;
        int busiestPeriodCount = 0;
        for (int hour = 0; hour < hourCounts.length - 1; hour++)
        {
            int periodCount = hourCounts[hour] + hourCounts[hour + 1];
            if (periodCount > busiestPeriodCount)
            {
                busiestPeriod = hour;
                busiestPeriodCount = periodCount;
            }
        }
        return busiestPeriod;       
    }
    
    /**
     * Return the quietest hour of the day.
     * @return The quiestest hour.
     */
    public int quietestHour()
    {
        int quietestHour = 0;
        for (int hour = 1; hour < hourCounts.length; hour++)
        {
            if (hourCounts[hour] < hourCounts[quietestHour])
            {
                quietestHour = hour;
            }
        }
        return quietestHour;        
    }
}
