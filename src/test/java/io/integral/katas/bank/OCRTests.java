package io.integral.katas.bank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OCRTests {

    @Test
    void recognizesZero() {
        assertThat(OCR.convert(
            " _ \n" +
                "| |\n" +
                "|_|\n" +
                "   ")).isEqualTo("0 ERR");
    }

    @Test
    void recognizesOne() {
        assertThat(OCR.convert(
            "   \n" +
                "  |\n" +
                "  |\n" +
                "   "
        )).isEqualTo("1 ERR");
    }

    @Test
    void convertsALineOfZeros() {
        assertThat(OCR.convert(
            " _  _  _  _  _  _  _  _  _ \n" +
                "| || || || || || || || || |\n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "                           "
        )).isEqualTo("000000000");
    }

    @Test
    void canParseMultipleEntries() {
        assertThat(OCR.convert(
            "                           \n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "                           \n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" +
                "                           \n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                "                           \n" +
                "                           \n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "                           \n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                "                           \n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "                           \n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "                           \n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                "                           \n" +
                " _  _  _  _  _  _  _  _  _ \n" +
                "|_||_||_||_||_||_||_||_||_|\n" +
                " _| _| _| _| _| _| _| _| _|\n" +
                "                           "
        )).isEqualTo(
            "111111111 ERR\n" +
                "222222222 ERR\n" +
                "333333333 ERR\n" +
                "444444444 ERR\n" +
                "555555555 ERR\n" +
                "666666666 ERR\n" +
                "777777777 ERR\n" +
                "888888888 ERR\n" +
                "999999999 ERR");
    }

    @Test
    void canValidateAnAccountNumber() {
        assertThat(OCR.convert(
            " _     _  _  _  _  _  _  _ \n" +
                 " _||_||_ |_||_| _||_||_ |_ \n" +
                 " _|  | _||_||_||_ |_||_| _|\n" +
                 "                             "
        )).isEqualTo("345882865");
    }

    @Test
    void canDetectAnInvalidAccountNumber() {
        assertThat(OCR.convert(
            " _  _  _  _  _     _  _    \n" +
                 "|_||_|  ||_ |_ |_| _| _|  |\n" +
                 " _||_|  ||_| _|  | _||_   |\n" +
                 "                             "
        )).isEqualTo("987654321 ERR");
    }

    @Test
    void printsILLWhenAnAccountNumberHasAnInvalidPattern() {
        assertThat(OCR.convert(
            "    _  _  _  _  _  _     _ \n" +
                "|_||_|| || ||_   |  |  | _ \n" +
                "  | _||_||_||_|  |  |  | _|\n" +
                "                           "
        )).isEqualTo("49006771? ILL");
    }

    @Test
    void printsERRWhenAnAccountNumberIsInvalid() {
        assertThat(OCR.convert(
            "                           \n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "  |  |  |  |  |  |  |  |  |\n" +
                "                           "
        )).isEqualTo("111111111 ERR");
    }
}
