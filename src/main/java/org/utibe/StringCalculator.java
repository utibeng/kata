package org.utibe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class StringCalculator {

    private static final Logger logger = LogManager.getLogger();

    public int add(String number){

        int sum = 0;

        //Step 2 - variable number of numbers
        //String [] numbers = number.split(",");
        String [] numbers = number.split(",|\n");
        logger.info("parsed string is {}", Arrays.asList(numbers));
        for(String numString:numbers){
            try{
                sum += Integer.parseInt(numString);
            }
            catch (NumberFormatException numberFormatException){
                logger.info("error parsing {}", numString);
            }
        }
        return sum;
    }

}