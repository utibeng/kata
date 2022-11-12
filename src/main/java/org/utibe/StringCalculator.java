package org.utibe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringCalculator {

    private static final Logger logger = LogManager.getLogger();

    public int add(String number){

        // empty string, returns 0
        if (number.trim().length() == 0){
            return 0;
        }

        //single number returns the number, i.e no comma
        if (number.trim().indexOf(',') < 0)
            return Integer.parseInt(number);

        //two numbers
        else if (number.trim().indexOf(',') >= 0){
            int firstNumber = Integer.parseInt(number.split(",")[0]);
            int secondNumber = Integer.parseInt(number.split(",")[1]);

            logger.info("number 1 is {} and number 2 is {}", firstNumber, secondNumber);



            return (firstNumber + secondNumber );
        }

        return 0;
    }





    public static void main(String[] args) {
        logger.info("ok222");
    }
}