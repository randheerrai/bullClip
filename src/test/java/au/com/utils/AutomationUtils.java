package au.com.utils;

/**
 * Created by randhirrai on 6/7/17.
 */

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.math3.util.Precision;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class AutomationUtils {

    public static String getRandomString() {
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date = new Date();
        return format.format(date);
    }

    public static String getRandomNumberFromDate() {
        DateFormat format = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        return format.format(date);
    }


    public static String getDateInFormat(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);

    }

    public static String getTodaysDate(Date date) {
        return getDateInFormat(date, "dd MMM yyyy");
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
//        return getDateInFormat(cal.getTime(), "dd MMM yyyy");
        return cal.getTime();
    }


    public static Date getDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return cal.getTime();
    }



    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes); // minus number would decrement the days
        return cal.getTime();
//        return getDateInFormat(cal.getTime(), "dd MMM yyyy");
    }



    public static String copyFile(String srcFilePath, String randomizingString) {

        File srcfile = new File(srcFilePath);
        String destFileName = srcfile.getParent() + "\\" + FilenameUtils.getBaseName(srcFilePath) + randomizingString + "." + FilenameUtils.getBaseName(srcFilePath);
        File destFile = new File(destFileName);

        try {
            FileUtils.copyFile(srcfile, destFile);
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteFile(String fileToUpload) {
        FileUtils.deleteQuietly(new File(fileToUpload));
    }

    public static String getFileName(String filePath) {
        return FilenameUtils.getName(filePath);
    }

    public static int getRandomNumber(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;

    }

    public static long getCurrentEpochTime() {
        long epoch = System.currentTimeMillis();
        return epoch;
    }

    /**
     * Time should be in "MM/dd/yyyy HH:mm:ss format"
     * @param date
     * @return
     * @throws ParseException
     */
    public static long convertTimeInEpoch(String date) throws ParseException {
        long epoch = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").
                    parse(date).getTime();
         return epoch;


    }


    public static DateTime getCurrentTimeInTimeZone(TimeZone timeZone) {
        DateTime now = new DateTime(System.currentTimeMillis(), DateTimeZone.forTimeZone(timeZone));
        return now;
    }

    public String getEpochFromGivenTime(Date date) throws ParseException {
        return String.valueOf(date.getTime());

    }

    public static Double getRandomDouble(double low, double high) {
        Random r = new Random();
//        return r.nextInt(high - low) + low;
        return Precision.round((r.nextInt((int)((high-low)*10+1))+low*10) / 10.0,1);

    }




}
