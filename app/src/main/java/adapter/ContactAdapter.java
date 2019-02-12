package adapter;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import holder.ContactViewHolder;
import holder.LoadingViewHolder;
import model.Contact;
import model.OnLoadMoreListener;
import study.com.recylerviewadvan.MainActivity;
import study.com.recylerviewadvan.R;
import study.com.recylerviewadvan.SubActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 21/03/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener{
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    OnLoadMoreListener onLoadMoreListener;

    private boolean isLoading = false;
    int visibleThrehold = 5 ;
    int lastVisibleItem;
    int totalItemCount;

    RecyclerView recyclerView;
    MainActivity mContext;
    ArrayList<Contact> mContacts;

    public OnLoadMoreListener getOnLoadMoreListener() {
        return onLoadMoreListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setLoading() {
        isLoading = false;
    }

    public ContactAdapter(MainActivity mContext, RecyclerView recyclerView, final ArrayList<Contact> mContacts) {
        this.mContext = mContext;
        this.recyclerView = recyclerView;
        this.mContacts = mContacts;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThrehold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.OnLoadMore();
                        isLoading = true;
                    }
                }
            }
        });

        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        ContactViewHolder contactViewHolder = (ContactViewHolder) view.getTag();
        if (view.getId() == contactViewHolder.txtName.getId()) {
            Toast.makeText(mContext, "Ban da click vao phan tu thu :" + contactViewHolder.txtName.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        ContactViewHolder contactViewHolder = (ContactViewHolder) view.getTag();

        if(view.getId() == contactViewHolder.txtName.getId()) {
            Log.d("abc", "onLongClick: ");
            /*mContacts.remove(contactViewHolder.getPosition());
            notifyDataSetChanged();*/
        }

    return false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View contactView = LayoutInflater.from(mContext).inflate(R.layout.contactitem, parent, false);

            ContactViewHolder contactViewHolder = new ContactViewHolder(contactView, mContext);
            contactViewHolder.txtName.setOnClickListener(ContactAdapter.this);
            contactViewHolder.txtName.setOnLongClickListener(ContactAdapter.this);

            contactViewHolder.txtName.setTag(contactViewHolder);
            contactViewHolder.cardView.setOnLongClickListener((View.OnLongClickListener) mContext);

            return contactViewHolder;
        }

        if (viewType == VIEW_TYPE_LOADING) {
            View contactView = LayoutInflater.from(mContext).inflate(R.layout.loadingitem, parent, false);
            return new LoadingViewHolder(contactView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ContactViewHolder) {
            Contact contact = mContacts.get(position);
            ContactViewHolder contactViewHolder = (ContactViewHolder) holder;

            contactViewHolder.txtName.setText(contact.getName());
            contactViewHolder.txtPhone.setText(contact.getPhone());

            if (!mContext.is_action_mode) {
                ((ContactViewHolder) holder).chk.setVisibility(View.GONE);
            } else {
                ((ContactViewHolder) holder).chk.setVisibility(View.VISIBLE);
                ((ContactViewHolder) holder).chk.setChecked(false);
            }

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.mProgressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {

        return mContacts == null ? 0 : mContacts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mContacts.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void updateAdapter (ArrayList<Contact> list) {
        for (Contact contact : list) {
            mContacts.remove(contact);
        }
        notifyDataSetChanged();
    }
}
