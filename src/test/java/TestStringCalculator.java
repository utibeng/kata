
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.utibe.NegativesNotAllowedException;
import org.utibe.StringCalculator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestStringCalculator {
    private static final Logger logger = LogManager.getLogger();
    private static StringCalculator stringCalculator;

    @BeforeAll
    public static void setup() {
        stringCalculator = new StringCalculator();
        logger.info("@BeforeAll executing ...");
    }

    @Test
    public void emptyStringReturnsZero()throws NegativesNotAllowedException{
        assertEquals(0, stringCalculator.add(" "));
    }
    @Test
    public void singleNumberReturnsTheNumber()throws NegativesNotAllowedException{
        assertEquals(7, stringCalculator.add("7"));
    }
    @Test
    public void twoNumbersReturnsTheirSum()throws NegativesNotAllowedException{
        assertEquals(5, stringCalculator.add("3,2"));
    }
    @Test
    public void unknownNumberOfNumbersReturnsTheirSum() throws NegativesNotAllowedException{
        assertEquals(30, stringCalculator.add("10,12,8"));
        assertEquals(50, stringCalculator.add("5,10,15,20"));
    }
    @Test
    public void allowNewlinesInNumbersReturnsTheirSum() throws NegativesNotAllowedException{
        assertEquals(6, stringCalculator.add("1\n2,3"));
        assertEquals(22, stringCalculator.add("5,10\n7"));
    }

    @Test
    public void testGetDelimter(){
        assertEquals(";", stringCalculator.getDelimiter("//;\n1;2,3"));
        assertEquals("-", stringCalculator.getDelimiter("//-\nad1;2,3"));
    }
    @Test
    public void testGetDelimterDefaultDelimiter() {
        assertEquals(",|\n", stringCalculator.getDelimiter("a//;\n1;2,3"));
        assertEquals(",|\n", stringCalculator.getDelimiter(",//-\nad1;2,3"));
    }

    @Test
    public void useDifferentDelimitersInNumbers() throws NegativesNotAllowedException{
        assertEquals(15, stringCalculator.add("//-\n2-2-4-7"));
        assertEquals(6, stringCalculator.add("//!\n1!2!3"));
        assertEquals(3, stringCalculator.add("//;\n1;2"));
    }

    @Test
    public void checkNegativesNotAllowedException() throws NegativesNotAllowedException{

        assertThrows(
                NegativesNotAllowedException.class, () ->
                {stringCalculator.add("2,-4,3,-5"); }
        );

        assertThrows(
                NegativesNotAllowedException.class, () ->
                    {stringCalculator.add("//!\n1!-2!3!-6"); }
        );
    }

    @Test
    public void listAllNegativesTest() throws NegativesNotAllowedException{

        NegativesNotAllowedException negativesNotAllowedException = assertThrows(
                NegativesNotAllowedException.class, () ->
                {stringCalculator.listAllNegativeNumbers(new String [] {"6", "-1", "-2", "2"}); }
        );

        assertEquals("Negatives not allowed: [-1, -2]", negativesNotAllowedException.getMessage());

    }

    @Test
    public void ignoreNumbersGreaterThan1000() throws NegativesNotAllowedException{
        assertEquals(15, stringCalculator.add("//,\n2,2,4,1783,7"));
        assertEquals(2, stringCalculator.add("1001,2"));
        //assertEquals(3, stringCalculator.add("//;\n1;2"));
    }



}
