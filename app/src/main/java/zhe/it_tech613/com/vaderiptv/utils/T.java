package zhe.it_tech613.com.vaderiptv.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import zhe.it_tech613.com.vaderiptv.R;


/**
 * Created by Tony on 25.06.2017.
 */

public class T {

    /**
     * Show Text
     * @param context
     * @param content
     */
    public static void show(Context context, String content) {
        View v = View.inflate(context.getApplicationContext(), R.layout.layout_toast, null);
        ((TextView)v.findViewById(R.id.text)).setText(content);
        Toast t = new Toast(context.getApplicationContext());
        t.setView(v);
        t.setGravity(Gravity.TOP,0,0);
        t.show();
    }
    public static void showError(Context context, String content) {
        View v = View.inflate(context.getApplicationContext(), R.layout.layout_toast, null);
        TextView errorTxt = (TextView)v.findViewById(R.id.text);
        errorTxt.setBackgroundColor(context.getResources().getColor(R.color.colorError));
        errorTxt.setText(content);
        Toast t = new Toast(context.getApplicationContext());
        t.setView(v);
        t.setGravity(Gravity.TOP,0,0);
        t.show();
    }

    public static void showInfo(Context context, String content) {
        View v = View.inflate(context.getApplicationContext(), R.layout.layout_toast, null);
        TextView errorTxt = (TextView)v.findViewById(R.id.text);
        errorTxt.setBackgroundColor(context.getResources().getColor(R.color.colorInfo));
        errorTxt.setText(content);
        Toast t = new Toast(context.getApplicationContext());
        t.setView(v);
        t.setGravity(Gravity.TOP,0,0);
        t.show();
    }

    public static void showError(Context context, int content) {
        showError(context, context.getResources().getString(content));
    }

    public static void showSuccess(Context context, int id){
        showSuccess(context,context.getResources().getString(id));
    }

    public static void showSuccess(Context context, String content) {
        View v = View.inflate(context.getApplicationContext(), R.layout.layout_toast, null);
        TextView errorTxt = (TextView)v.findViewById(R.id.text);
        errorTxt.setBackgroundColor(context.getResources().getColor(R.color.colorSuccess));
        errorTxt.setText(content);
        Toast t = new Toast(context.getApplicationContext());
        t.setView(v);
        t.setGravity(Gravity.TOP,0,0);
        t.show();
    }
    public static void showWarning(Context context, String content) {
        View v = View.inflate(context.getApplicationContext(), R.layout.layout_toast, null);
        TextView errorTxt = (TextView)v.findViewById(R.id.text);
        errorTxt.setBackgroundColor(context.getResources().getColor(R.color.colorWarning));
        errorTxt.setText(content);
        Toast t = new Toast(context.getApplicationContext());
        t.setView(v);
        t.setGravity(Gravity.TOP,0,0);
        t.show();
    }

    public static void show(Context context, int content) {

        show(context,context.getString(content));
    }

    /**
     * Long Display
     * @param context
     * @param content
     */
    public static void showL(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }
}
