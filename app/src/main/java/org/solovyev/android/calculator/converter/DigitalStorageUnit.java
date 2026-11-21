package org.solovyev.android.calculator.converter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import jscl.JsclMathEngine;
import jscl.NumeralBase;
import org.solovyev.android.calculator.Named;
import org.solovyev.android.calculator.R;

public enum DigitalStorageUnit implements Convertible {
    BIT(0.125d, R.string.cpp_units_storage_bits),
    BYTE(1.0d, R.string.cpp_units_storage_bytes),
    KILOBIT(125.0d, R.string.cpp_units_storage_kilobits),
    KILOBYTE(1000.0d, R.string.cpp_units_storage_kilobytes),
    KIBIBYTE(1024.0d, R.string.cpp_units_storage_kibibytes),
    MEGABIT(125000.0d, R.string.cpp_units_storage_megabits),
    MEGABYTE(1000000.0d, R.string.cpp_units_storage_megabytes),
    MEBIBYTE(1048576.0d, R.string.cpp_units_storage_mebibytes),
    GIGABIT(125000000.0d, R.string.cpp_units_storage_gigabits),
    GIGABYTE(1000000000.0d, R.string.cpp_units_storage_gigabytes),
    GIBIBYTE(1073741824.0d, R.string.cpp_units_storage_gibibytes),
    TERABYTE(1000000000000.0d, R.string.cpp_units_storage_terabytes),
    TEBIBYTE(1099511627776.0d, R.string.cpp_units_storage_tebibytes),
    PETABYTE(1000000000000000.0d, R.string.cpp_units_storage_petabytes),
    PEBIBYTE(1125899906842624.0d, R.string.cpp_units_storage_pebibytes);

    public final double bytesFactor;
    @StringRes
    public final int nameRes;

    DigitalStorageUnit(double bytesFactor, @StringRes int nameRes) {
        this.bytesFactor = bytesFactor;
        this.nameRes = nameRes;
    }

    @NonNull
    @Override
    public String convert(@NonNull Convertible to, @NonNull String value) throws NumberFormatException {
        final double from = Converter.parse(value).toDouble();
        final DigitalStorageUnit target = (DigitalStorageUnit) to;
        final double inBytes = from * this.bytesFactor;
        final double converted = inBytes / target.bytesFactor;
        return JsclMathEngine.getInstance().format(converted, NumeralBase.dec);
    }

    @NonNull
    @Override
    public Named<Convertible> named(@NonNull Context context) {
        return Named.<Convertible>create(this, nameRes, context);
    }
}
