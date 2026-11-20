package org.solovyev.android.calculator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * AdView placeholder retained for XML layout compatibility.
 * Network permissions and advertising SDKs have been completely removed.
 */
public class AdView extends FrameLayout {

    public AdView(Context context) {
        super(context);
        init();
    }

    public AdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setVisibility(GONE);
    }

    public void destroy() {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void show() {
    }

    public void hide() {
    }
}
