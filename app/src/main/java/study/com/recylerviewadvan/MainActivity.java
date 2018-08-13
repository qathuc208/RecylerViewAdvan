package study.com.recylerviewadvan;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

import adapter.ContactAdapter;
import model.Contact;
import model.OnLoadMoreListener;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Contact> mContacts = new ArrayList<Contact>();
    ContactAdapter contactAdapter;
    Random random = new Random();
    Button btnNext;

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
                        mContacts.remove(mContacts.size() -1);

                        contactAdapter.notifyItemRemoved(mContacts.size());
                        int index = mContacts.size();
                        int end = index + 20;

                        for (int i = index ;i < end;i++) {
                            Contact c = new Contact();
                            c.setName("Ten" + i);
                            String phone = "098";
                            int x = random.nextInt(3);

                            if(x == 1) phone = "090";
                            else if (x == 2 ) phone = "094";

                            for(int p =0; p< 7;p++) {
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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContacts = new ArrayList<>();

        for (int i=0; i< 20; i++) {
            Contact c = new Contact();
            c.setName("Ten" + i);
            String phone = "098";
            int x = random.nextInt(3);

            if(x == 1) phone = "090";
            else if (x == 2 ) phone = "094";

            for(int p =0; p< 7;p++) {
                phone += random.nextInt(10);
            }

            c.setPhone(phone);
            mContacts.add(c);
        }

        contactAdapter = new ContactAdapter(this, recyclerView, mContacts);
        recyclerView.setAdapter(contactAdapter);

    }


}
