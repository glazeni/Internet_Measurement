package internetmeasurement.android;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by glazen on 21/12/16.
 */
public class TypefaceUtil {


    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //Increase Clickable Area
    /*public static void increaseClickArea(View parent, View child) {

        // increase the click area with delegateArea, can be used in + create
        // icon
        final View chicld = child;
        parent.post(new Runnable() {
            public void run() {
                // Post in the parent's message queue to make sure the
                // parent
                // lays out its children before we call getHitRect()
                Rect delegateArea = new Rect();
                View delegate = chicld;
                delegate.getHitRect(delegateArea);
                delegateArea.top -= 600;
                delegateArea.bottom += 600;
                delegateArea.left -= 600;
                delegateArea.right += 600;
                TouchDelegate expandedArea = new TouchDelegate(delegateArea,
                        delegate);
                // give the delegate to an ancestor of the view we're
                // delegating the
                // area to
                if (View.class.isInstance(delegate.getParent())) {
                    ((View) delegate.getParent())
                            .setTouchDelegate(expandedArea);
                }
            };
        });

    }*/
}