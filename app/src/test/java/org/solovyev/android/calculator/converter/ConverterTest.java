package org.solovyev.android.calculator.converter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    @Test(expected = NumberFormatException.class)
    public void testShouldNotParseInvalidDecNumber() throws Exception {
        Converter.parse("1A", 10);
    }

    @Test
    public void testShouldParseValidHexNumber() throws Exception {
        assertEquals(26, Converter.parse("1A", 16).toLong());
    }

    @Test
    public void testDigitalStorageConversion() throws Exception {
        assertEquals("8", DigitalStorageUnit.BYTE.convert(DigitalStorageUnit.BIT, "1"));
        assertEquals("1", DigitalStorageUnit.BIT.convert(DigitalStorageUnit.BYTE, "8"));
        assertEquals("1000", DigitalStorageUnit.KILOBYTE.convert(DigitalStorageUnit.BYTE, "1"));
        assertEquals("1024", DigitalStorageUnit.KIBIBYTE.convert(DigitalStorageUnit.BYTE, "1"));
        assertEquals("1", DigitalStorageUnit.BYTE.convert(DigitalStorageUnit.KIBIBYTE, "1024"));
        assertEquals("1000", DigitalStorageUnit.GIGABYTE.convert(DigitalStorageUnit.MEGABYTE, "1"));
        assertEquals("1024", DigitalStorageUnit.GIBIBYTE.convert(DigitalStorageUnit.MEBIBYTE, "1"));
        assertEquals("2000", DigitalStorageUnit.TERABYTE.convert(DigitalStorageUnit.GIGABYTE, "2"));
    }
}