package com.datechnologies.androidtest.animation;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
/** Custom view for managing onTouch request from ImageView and for cleaner organization and
 * improve accessibility
 */
public class AnimatedLogoView extends AppCompatImageView
{
    float x;
    float y;

    public AnimatedLogoView(Context context) {
        super(context);
    }

    public AnimatedLogoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatedLogoView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean performClick()
    {
        return super.performClick();

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        super.onTouchEvent(motionEvent);
        float dx, dy;
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN: {
                x = motionEvent.getX();
                y = motionEvent.getY();
                performClick();
                invalidate();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float lastX, lastY;
                lastX = motionEvent.getX();
                lastY = motionEvent.getY();

                dx = lastX - x;
                dy = lastY - y;

                setX(getX()+dx);
                setY(getY()+dy);

                invalidate();
                break;
            }
        }
        return true;
    }

}
