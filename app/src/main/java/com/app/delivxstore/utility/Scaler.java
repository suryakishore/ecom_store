package com.app.delivxstore.utility;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


/**
 * This utility is not intended for enlarging text size or view size
 * It assumes you are developing your app using 480X800 hdpi device
 * Do not follow what you see in Graphical layout it may be altered during runtime
 * So better to check on ANY Device and adjust your app according to that
 * <p>
 * For complete app scaling declare id for your main layout in XML and
 * declare object of Scaler class and pass View to it.
 * <p>
 * Do this for each Activity
 *
 * @since 3 April, 2014
 * <p>
 * Scaler obj = new Scaler();
 * rl = (RelativeLayout)findViewById(R.id.rlmain);
 * rl = (RelativeLayout)obj.scaler(context,rl);
 * OR
 * view = (view)findViewById(R.id.ID_OF_VIEW);
 * view = (view)obj.scaler(context,view);
 */

public class Scaler {

    private static double xWidth = 480f;
    private static double yHeight = 800f;

    private static View viewToEdit;
    private static ViewGroup.LayoutParams lpToGet;
    private static ViewGroup.MarginLayoutParams marginParams;
    private static double scalingFactor_Width, scalingFactor_Height;
    private static double leftMargin, topMargin, rightMargin, bottomMargin;
    private static DisplayMetrics dm;

    private static View realScaler(View view) {

        viewToEdit = view;
        lpToGet = viewToEdit.getLayoutParams();
        marginParams = (ViewGroup.MarginLayoutParams) lpToGet;

        intialize();
        dpConverter();

        marginParams.setMargins((int) Math.round(leftMargin), (int) Math.round(topMargin), (int) Math.round(rightMargin), (int) Math.round(bottomMargin));
        viewToEdit.setLayoutParams(lpToGet);

        paddingSetter();

        return viewToEdit;

    }

    private static void intialize() {


        if (lpToGet instanceof ViewGroup.MarginLayoutParams) {
            leftMargin = (marginParams.leftMargin) * scalingFactor_Width;
            topMargin = (marginParams.topMargin) * scalingFactor_Height;
            rightMargin = (marginParams.rightMargin) * scalingFactor_Width;
            bottomMargin = (marginParams.bottomMargin) * scalingFactor_Height;

        }
    }

    private static void dpConverter() {

        //dm.density= (dpi of device)/160


        leftMargin = leftMargin / (dm.xdpi / DisplayMetrics.DENSITY_DEFAULT);
        topMargin = topMargin / (dm.ydpi / DisplayMetrics.DENSITY_DEFAULT);
        rightMargin = rightMargin / (dm.xdpi / DisplayMetrics.DENSITY_DEFAULT);
        bottomMargin = bottomMargin / (dm.ydpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private static void calScalingFactor() {

        scalingFactor_Width = dm.widthPixels / xWidth;
        scalingFactor_Height = dm.heightPixels / yHeight;
    }

    public static double[] getScalingFactor(Context context) {


        double[] scale = new double[2];

        dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        windowManager.getDefaultDisplay().getMetrics(dm);

        scale[0] = dm.widthPixels / xWidth;
        scale[1] = dm.heightPixels / yHeight;

        return scale;

    }

    private static void paddingSetter() {

        viewToEdit.setPadding(
                (int) Math.round(viewToEdit.getPaddingLeft() * scalingFactor_Width),
                (int) Math.round(viewToEdit.getPaddingTop() * scalingFactor_Height),
                (int) Math.round(viewToEdit.getPaddingRight() * scalingFactor_Width),
                (int) Math.round(viewToEdit.getPaddingBottom() * scalingFactor_Height));

    }

    public View scaler(Context context, View view) {

        dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        windowManager.getDefaultDisplay().getMetrics(dm);
        calScalingFactor();

        if (view instanceof ViewGroup) {
            ViewGroup groupView = (ViewGroup) view;
            View viewChild;

            for (int cnt = 0; cnt < groupView.getChildCount(); ++cnt) {

                viewChild = realScaler(groupView.getChildAt(cnt));

                groupView.removeViewAt(cnt);
                groupView.addView(viewChild, cnt);
            }

        } else {
            view = realScaler(view);
        }


        return view;

    }

}
