package study.com.recylerviewadvan;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Random;

import adapter.ContactAdapter;
import holder.ContactViewHolder;
import model.Contact;
import model.OnLoadMoreListener;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    RecyclerView recyclerView;
    ArrayList<Contact> mContacts = new ArrayList<Contact>();
    ArrayList<Contact> selection_list = new ArrayList<Contact>();
    int counter = 0;

    ContactAdapter contactAdapter;
    Random random = new Random();
    Button btnNext;
    android.support.v7.widget.Toolbar toolbar;
    public boolean is_action_mode = false;
    TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addControls();
        addEvents();
    }

    private void addEvents() {
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });

        contactAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void OnLoadMore() {
                Log.d("abc", "Them phan tu vao list");
                mContacts.add(null);

                contactAdapter.notifyItemInserted(mContacts.size() - 1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("abc", "Remove phan tu old");
                        mContacts.remove(mContacts.size() - 1);

                        contactAdapter.notifyItemRemoved(mContacts.size());
                        int index = mContacts.size();
                        int end = index + 20;

                        for (int i = index; i < end; i++) {
                            Contact c = new Contact();
                            c.setName("Ten" + i);
                            String phone = "098";
                            int x = random.nextInt(3);

                            if (x == 1) phone = "090";
                            else if (x == 2) phone = "094";

                            for (int p = 0; p < 7; p++) {
                                phone += random.nextInt(10);
                            }

                            c.setPhone(phone);
                            mContacts.add(c);
                        }

                        contactAdapter.notifyDataSetChanged();
                        contactAdapter.setLoading();
                    }
                }, 5000);
            }
        });
    }


    private void addControls() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        count = (TextView) findViewById(R.id.textCount);
        count.setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContacts = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Contact c = new Contact();
            c.setName("Ten" + i);
            String phone = "098";
            int x = random.nextInt(3);

            if (x == 1) phone = "090";
            else if (x == 2) phone = "094";

            for (int p = 0; p < 7; p++) {
                phone += random.nextInt(10);
            }

            c.setPhone(phone);
            mContacts.add(c);
        }

        contactAdapter = new ContactAdapter(this, recyclerView, mContacts);
        recyclerView.setAdapter(contactAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_delete) {
            is_action_mode = false;
/*            ContactAdapter contactAdapterRestore = (ContactAdapter) contactAdapter;
            contactAdapterRestore.updateAdapter(selection_list);*/
            contactAdapter.updateAdapter(selection_list);
            clearActionMode();
        } else if (item.getItemId() == R.id.item_selected) {
            Log.d("abc","aaaaaaaa");
        } else if (item.getItemId() == android.R.id.home) {
            clearActionMode();
            contactAdapter.notifyDataSetChanged();
        }
        return true;
    }

    public void clearActionMode() {
        is_action_mode = false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        count.setVisibility(View.GONE);
        count.setText("0 item selected");
        counter = 0;
        selection_list.clear();
    }

    @Override
    public void onBackPressed() {
        if (is_action_mode) {
            clearActionMode();
            contactAdapter.notifyDataSetChanged();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        count.setVisibility(View.VISIBLE);
        is_action_mode = true;
        contactAdapter.notifyDataSetChanged();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void prepareSelection(View v, int pos) {
        if (((CheckBox) v).isChecked()) {
            selection_list.add(mContacts.get(pos));
            counter = counter + 1;
            updateCounter(counter);
        } else {
            selection_list.remove(mContacts.get(pos));
            counter = counter - 1;
            updateCounter(counter);
        }
    }

    public void checkedAll(View v) {
        ((CheckBox) v).setChecked(true);
    }

    public void updateCounter(int counter) {
        if (counter == 0) {
            count.setText("0 item selected");
        } else {
            count.setText(counter + " items selected");
        }
    }
}
