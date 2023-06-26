import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CalcTest {

    @Test
    public void arabicTest() throws Exception {
        String sum = "9 + 9";
        String minus = "9 - 9";
        String mult = "9 * 9";
        String div = "9 / 3";
        String divWithRemainder = "9 / 2";

        List<String> dataSum = Arrays.stream(sum.split(" ")).toList();
        dataSum = DataChecking.checkNumbers(dataSum);
        int resultSum = Main.arabianCalc(dataSum);
        assertThat(resultSum).isSameAs(18);

        List<String> dataMinus = Arrays.stream(minus.split(" ")).toList();
        int resultMinus = Main.arabianCalc(dataMinus);
        assertThat(resultMinus).isSameAs(0);

        List<String> dataMult = Arrays.stream(mult.split(" ")).toList();
        int resultMult = Main.arabianCalc(dataMult);
        assertThat(resultMult).isSameAs(81);

        List<String> dataDiv = Arrays.stream(div.split(" ")).toList();
        int resultDiv = Main.arabianCalc(dataDiv);
        assertThat(resultDiv).isSameAs(3);

        List<String> dataDivWithRemainder = Arrays.stream(divWithRemainder.split(" ")).toList();
        int resultDivWithRemainder = Main.arabianCalc(dataDivWithRemainder);
        assertThat(resultDivWithRemainder).isSameAs(4);

    }

    @Test
    public void romanTest() throws Exception {
        String sum = "IX + IX";
        String minus = "IX - VIII";
        String mult = "IX * IX";
        String div = "IX / III";
        String divWithRemainder = "IX / II";

        List<String> dataSum = Arrays.stream(sum.split(" ")).toList();
        dataSum = DataChecking.checkNumbers(dataSum);
        String resultSum = Main.convertToRoman(Main.arabianCalc(dataSum));
        assertThat(resultSum).isEqualTo("XVIII");

        List<String> dataMinus = Arrays.stream(minus.split(" ")).toList();
        dataMinus = DataChecking.checkNumbers(dataMinus);
        String resultMinus = Main.convertToRoman(Main.arabianCalc(dataMinus));
        assertThat(resultMinus).isEqualTo("I");

        List<String> dataMult = Arrays.stream(mult.split(" ")).toList();
        dataMult = DataChecking.checkNumbers(dataMult);
        String resultMult = Main.convertToRoman(Main.arabianCalc(dataMult));
        assertThat(resultMult).isEqualTo("LXXXI");

        List<String> dataDiv = Arrays.stream(div.split(" ")).toList();
        dataDiv = DataChecking.checkNumbers(dataDiv);
        String resultDiv = Main.convertToRoman(Main.arabianCalc(dataDiv));
        assertThat(resultDiv).isEqualTo("III");

        List<String> dataDivWithRemainder = Arrays.stream(divWithRemainder.split(" ")).toList();
        dataDivWithRemainder = DataChecking.checkNumbers(dataDivWithRemainder);
        String resultDivWithRemainder = Main.convertToRoman(Main.arabianCalc(dataDivWithRemainder));
        assertThat(resultDivWithRemainder).isEqualTo("IV");

    }
}
