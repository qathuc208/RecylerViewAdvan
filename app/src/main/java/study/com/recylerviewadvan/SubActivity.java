package study.com.recylerviewadvan;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 25/03/2018.
 */

public class SubActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    GridView gridView;
    TextView txtMsg;
    TextView txtSolo;
    Integer []mThumb;

    // Adapter for gridview
    MyGridAdapter adapter = null;
    MyGridAdapter _adapter =null;

    // Don't need create new Activity so get ID from view_details.xml
    ImageView imgSolo;
    Button btnBack;

    //Lưu Bundle backup cho MainActivity
    Bundle myBackupBundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        //super.onCreate(savedInstanceState);
        myBackupBundle = savedInstanceState;
        setContentView(R.layout.grid_advance);

        txtMsg = (TextView) findViewById(R.id.txtMsg);

        mThumb = new Integer[]{
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6,
                R.drawable.ic_launcher_background,
                R.drawable.lifecycle
        };


        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new MyGridAdapter(SubActivity.this, mThumb);
        _adapter = new MyGridAdapter(SubActivity.this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);
        super.onCreate(savedInstanceState);
    }

    public void showdetail(int pos) {
        // Set new activity
        setContentView(R.layout.view_image);

        txtSolo = (TextView) findViewById(R.id.txtSolo);
        txtSolo.setText(" " +pos);

        imgSolo = (ImageView) findViewById(R.id.imgView);
        imgSolo.setImageResource(mThumb[pos]);

        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myBackupBundle = new Bundle();
                finish();
                onCreate(myBackupBundle);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
/*        Intent i = new Intent(SubActivity.this, ViewImageDetails.class);
        i.putExtra("id", pos);
        startActivity(i);*/
        showdetail(pos);
    }
}
