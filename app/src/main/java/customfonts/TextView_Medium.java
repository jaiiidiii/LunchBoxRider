package customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class TextView_Medium extends android.support.v7.widget.AppCompatTextView {
    public TextView_Medium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextView_Medium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextView_Medium(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "myfonts/Roboto-TextView_Medium");
            setTypeface(tf);
        }
    }

}