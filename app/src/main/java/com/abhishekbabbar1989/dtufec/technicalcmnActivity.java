package com.abhishekbabbar1989.dtufec;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class technicalcmnActivity extends AppCompatActivity {

    ListView lvtechcmn;
    String ntitle[]={"Notes","Books","Question Papers"};
    String nsubtitle[]={"click here","click here","click here"};
    int images[]={R.drawable.noteslogo1,R.drawable.bookslogo1,R.drawable.questionpaperlogo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technicalcmn);
        lvtechcmn=findViewById(R.id.lvtechcmn);
        myadapter adapter=new myadapter(this,ntitle,nsubtitle,images);
        lvtechcmn.setAdapter(adapter);
        lvtechcmn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                if(position==0)
                {
                    i= new Intent(technicalcmnActivity.this,techcmnNotes.class);
                    startActivity(i);
                }
                if(position==1)
                {
                    i= new Intent(technicalcmnActivity.this,techcmnBooks.class);
                    startActivity(i);

                }
                if(position==2)
                {

                    i= new Intent(technicalcmnActivity.this,techcmnPaper.class);
                    startActivity(i);
                }
            }
        });
    }
    class myadapter extends ArrayAdapter<String> {

        Context context;
        String rtitle[];
        String rsubtitle[];
        int rimgs[];
        myadapter(Context c,String title[],String subtitle[],int imgs[])
        {
            super(c,R.layout.row,R.id.tvmaintitle,title);
            this.context=c;
            this.rtitle=title;
            this.rsubtitle=subtitle;
            this.rimgs=imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images= row.findViewById(R.id.image);
            TextView mytitle=row.findViewById(R.id.tvmaintitle);
            TextView mysubtitle=row.findViewById(R.id.tvsubtitle);
            images.setImageResource(rimgs[position]);
            mytitle.setText(rtitle[position]);
            mysubtitle.setText(rsubtitle[position]);






            return row;

        }
    }
}
