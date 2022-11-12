package org.utibe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final Logger logger = LogManager.getLogger();

    public int add(String numbersString){

        int sum = 0;
        String delimiter = this.getDelimiter(numbersString);
        String [] numbers = this.stripDelimiter(numbersString).split(delimiter);
        for(String numString:numbers){
            try{
                sum += Integer.parseInt(numString);
            }
            catch (NumberFormatException numberFormatException){
                logger.info("could not parse {}", numString);
            }
        }
        return sum;
    }

    public String getDelimiter(String numbers){
        String defaultDelimiter = ",|\n";
        Matcher matchedNumber = this.getMatcherForString(numbers, "//(\\S)\n(\\S*)");
        if (matchedNumber.matches()) {
            return  matchedNumber.group(1);
        }
        return defaultDelimiter;
    }

    public String stripDelimiter(String numbers){

        Matcher matchedNumber = this.getMatcherForString(numbers, "//(\\S)\n(\\S*)");
        if (matchedNumber.matches()) {
            return  matchedNumber.group(2);
        }
        return numbers;
    }

    public Matcher getMatcherForString(String inputString, String regexString){

        //Pattern delimiterRegex = Pattern.compile("//(\\S)\n(\\S*)" );
        Pattern delimiterRegex = Pattern.compile(regexString );
        return delimiterRegex.matcher(inputString);

    }

    /*public static void main(String [] args){
        //logger.info(new StringCalculator().getDelimiter("//;\n1;2,3"));
        //logger.info(new StringCalculator().getDelimiter("//;\n1;2,3"));
        //new StringCalculator().getDelimiter("//$\n1;");
        //logger.info();
        //new StringCalculator().getDelimiter("//$\n1;");
        //logger.info(new StringCalculator().stripDelimiter("//$\n67,7,9"));
        logger.info(Arrays.asList("2$2$4$7".split("\\$")));

    }*/

}