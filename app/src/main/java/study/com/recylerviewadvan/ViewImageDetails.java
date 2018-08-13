package study.com.recylerviewadvan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 25/03/2018.
 */

public class ViewImageDetails extends AppCompatActivity {

    TextView txtSolo;
    ImageView imgSolo;
    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image);

        Intent i = getIntent();

        int pos = i.getExtras().getInt("id");
        MyGridAdapter myGridAdapter = new MyGridAdapter(this);

        txtSolo = (TextView) findViewById(R.id.txtSolo);
        txtSolo.setText(" "+pos);

        imgSolo = (ImageView) findViewById(R.id.imgView);
        imgSolo.setImageResource((Integer) myGridAdapter.getItem(pos));

        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
