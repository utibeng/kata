package org.utibe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class NegativesNotAllowedException extends Exception{

    private String message;

    private static final Logger logger = LogManager.getLogger();

    public NegativesNotAllowedException(){
        this.message = "Negatives not allowed ";
        logger.info(this.message);
    }

    public NegativesNotAllowedException(List<Integer> negativeNumbers){
        //logger.info("Negatives not allowed: {}", Arrays.asList(negativeNumbers));
        this.message = "Negatives not allowed: " + negativeNumbers;
        logger.info(this.message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
