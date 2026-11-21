package org.solovyev.android.calculator.buttons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CppSpecialButtonTest {

    @Test
    public void testShouldReturnButtonByGlyph() throws Exception {
        assertEquals(CppSpecialButton.copy, CppSpecialButton.getByGlyph(CppSpecialButton.copy.glyph));
        assertEquals(CppSpecialButton.paste, CppSpecialButton.getByGlyph(CppSpecialButton.paste.glyph));
    }

    @Test
    public void testShouldReturnNullForButtonWithoutGlyph() throws Exception {
        assertNull(CppSpecialButton.getByGlyph(CppSpecialButton.brackets_wrap.glyph));
    }

    @Test
    public void testShouldReturnButtonByAction() throws Exception {
        assertEquals(CppSpecialButton.memory_clear, CppSpecialButton.getByAction("MC"));
        assertEquals(CppSpecialButton.memory_store, CppSpecialButton.getByAction("MS"));
        assertEquals(CppSpecialButton.memory_plus, CppSpecialButton.getByAction("M+"));
        assertEquals(CppSpecialButton.memory_minus, CppSpecialButton.getByAction("M-"));
    }
}