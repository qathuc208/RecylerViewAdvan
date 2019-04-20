package holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.TextView;

import study.com.recylerviewadvan.MainActivity;
import study.com.recylerviewadvan.R;

/**
 * Created by Administrator on 22/03/2018.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtName, txtPhone;
    public CheckBox chk;
    public CardView cardView;
    public MainActivity mainActivity;

    public ContactViewHolder(View itemView, MainActivity mainActivity) {
        super(itemView);

        this.mainActivity = mainActivity;
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);
        chk = (CheckBox) itemView.findViewById(R.id.checkBox);
        cardView = (CardView) itemView.findViewById(R.id.cardView);

        chk.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        mainActivity.prepareSelection(view, getAdapterPosition());
    }
}
