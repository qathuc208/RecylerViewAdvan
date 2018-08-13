package holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import study.com.recylerviewadvan.R;

/**
 * Created by Administrator on 22/03/2018.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder {
    public TextView txtName, txtPhone;

    public ContactViewHolder(View itemView) {
        super(itemView);

        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
    }

}
