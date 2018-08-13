package holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import study.com.recylerviewadvan.R;

/**
 * Created by Administrator on 22/03/2018.
 */

public class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar mProgressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
    }
}
