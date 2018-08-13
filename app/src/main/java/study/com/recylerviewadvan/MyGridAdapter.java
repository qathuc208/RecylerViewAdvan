package study.com.recylerviewadvan;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Administrator on 25/03/2018.
 */

public class MyGridAdapter extends BaseAdapter {
    private Context mContext;
    private Integer []mThumbIds = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.ic_launcher_background,
            R.drawable.lifecycle
    };

    public MyGridAdapter(Context c) {
        mContext = c;
    }

    public MyGridAdapter(Context c, Integer []mThum) {
        mContext = c;
        mThumbIds = mThum;
    }


    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return mThumbIds[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView ingView;

        if(view == null) {
            ingView = new ImageView(mContext);
            // Edit anh
            ingView.setLayoutParams(new GridView.LayoutParams(85, 85));
            ingView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ingView.setPadding(8, 8, 8, 8);
        }else{
            ingView=(ImageView) view;
        }

        ingView.setImageResource(mThumbIds[i]);
        return ingView;
    }
}
