package com.github.ccmagic.mlhtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author kxmc
 * 2019/3/13 11:47
 */
public class MaxLinesHalfTextView extends AppCompatTextView {


    /**
     * 最多显示多少行，最后一行显示一半，并添加“...”
     */
    private int maxLine;

    public MaxLinesHalfTextView(Context context) {
        this(context, null);
    }

    public MaxLinesHalfTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public MaxLinesHalfTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MaxLinesHalfTextView);
        maxLine = ta.getInteger(R.styleable.MaxLinesHalfTextView_max_lines_half_tv_line, 2);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getLineCount() > maxLine) {
            CharSequence content = getText();
            if (content != null) {
                String str = content.toString();
                float width = getPaint().measureText(str);
                //如果需要精准控制，可以换成控件宽度
                float tvWidth = getMeasuredWidth();
                float lines = width / tvWidth;
                if (lines > (maxLine - 0.5)) {
                    setText((str.substring(0, (int) (content.length() / lines * (maxLine - 0.5))) + "..."));
                }
            }
        }
    }


}
