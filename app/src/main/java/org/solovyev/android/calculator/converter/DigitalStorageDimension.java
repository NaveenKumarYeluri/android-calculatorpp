package org.solovyev.android.calculator.converter;

import android.content.Context;
import androidx.annotation.NonNull;

import org.solovyev.android.calculator.Named;
import org.solovyev.android.calculator.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DigitalStorageDimension implements ConvertibleDimension {

    @NonNull
    private static final DigitalStorageDimension INSTANCE = new DigitalStorageDimension();
    @NonNull
    private final List<Convertible> units = new ArrayList<Convertible>(Arrays.asList(DigitalStorageUnit.values()));

    private DigitalStorageDimension() {
    }

    @NonNull
    public static ConvertibleDimension get() {
        return INSTANCE;
    }

    @NonNull
    @Override
    public Named<ConvertibleDimension> named(@NonNull Context context) {
        return Named.<ConvertibleDimension>create(this, R.string.cpp_converter_digital_storage, context);
    }

    @NonNull
    @Override
    public List<Convertible> getUnits() {
        return units;
    }
}
