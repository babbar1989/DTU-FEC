package com.abhishekbabbar1989.dtufec;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class bsnNotes extends AppCompatActivity {

    FirebaseStorage firebaseStorage;
    public static final String TAG ="BTN";
    StorageReference storageReference;
    // adapter- mechanism of showing data from string to data on android screen as a list
    StorageReference ref;
    ListView lvbsnnotes;
    FirebaseDatabase db;
    DatabaseReference myref;
    ProgressBar spinner;
    TextView tvprogressbar;
    ArrayList<String> material=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsn_notes);
        lvbsnnotes=findViewById(R.id.lvbsnnotes);
        spinner=findViewById(R.id.load_progress);
        tvprogressbar=findViewById(R.id.tvproglgpaper);
        db=FirebaseDatabase.getInstance();
        myref=db.getReference("study");
        spinner.setVisibility(View.VISIBLE);

        final myAdapter myAdapter= new myAdapter();
        lvbsnnotes.setAdapter(myAdapter);
        lvbsnnotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=100)
                {
                    Toast.makeText(bsnNotes.this,"your download will begin shortly ",
                            Toast.LENGTH_SHORT).show();
                    download(material.get(position));
                }
            }
        });
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value= dataSnapshot.getValue(String.class);
                if(value.charAt(0)=='B'&&value.charAt(1)=='S'&&value.charAt(2)=='N'&&value.charAt(3)=='P')
                    material.add(value);
                spinner.setVisibility(View.GONE);
                tvprogressbar.setVisibility(View.GONE);
                //  arrayAdapter.notifyDataSetChanged();
                myAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged( DataSnapshot dataSnapshot,  String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public  void downloadfiles(Context context, String filename, String fileextension, String destinationdirectoy, String url)
    {
        DownloadManager downloadManager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(url);
        DownloadManager.Request request= new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationdirectoy,filename+fileextension);
        downloadManager.enqueue(request);
    }


    class myAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return material.size();
        }

        @Override
        public Object getItem(int position) {
            return material.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemview= getLayoutInflater().inflate(   //taking an xml file and converting it in a view is called inflating
                    R.layout.row_firebase,
                    parent,
                    false

            );
            TextView tvname= itemview.findViewById(R.id.tvtitlefb);
            tvname.setText(getItem(position).toString());
            ImageView imagefb=itemview.findViewById(R.id.imagefb);
            imagefb.setImageResource(R.drawable.pdflogo1);
            TextView tvsub=itemview.findViewById(R.id.tvsubtitlefb);
            tvsub.setText("click here to download");

            return itemview;

        }
    }

    void download(final String name)
    {
        storageReference=firebaseStorage.getInstance().getReference();

        ref=storageReference.child(name+".pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();

                downloadfiles(bsnNotes.this,name,".pdf",DIRECTORY_DOWNLOADS,url);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }
}
