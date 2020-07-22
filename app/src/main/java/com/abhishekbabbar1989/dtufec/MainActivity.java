package com.abhishekbabbar1989.dtufec;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle ntoggle;
   CardView evs,logical,communication,german,french,japanese,buisnesscmn,financial,engexp,techcmn,language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout =findViewById(R.id.drawerlayout);
        ntoggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(ntoggle);
        ntoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        engexp=findViewById(R.id.engexploration);
        techcmn=findViewById(R.id.technicalcmn);
        evs=findViewById(R.id.evs);
        logical=findViewById(R.id.logical);
        communication=findViewById(R.id.communication);
       buisnesscmn =findViewById(R.id.buisnesscmn);
        financial=findViewById(R.id.financial);
        language=findViewById(R.id.language);
        drawerLayout =findViewById(R.id.drawerlayout);
        ntoggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(ntoggle);
        NavigationView nvdrawer=findViewById(R.id.nvdrawer);

        ntoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nvdrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.contactme:
                        Uri uri=Uri.parse("tel:"+"7289829772");
                        i=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(i);
                        break;
                    case R.id.syllabus:
                        i = new Intent(MainActivity.this, syllabusActivity.class);
                        startActivity(i);
                        break;

                    case R.id.website:
                         uri=Uri.parse("https://dtufec.herokuapp.com");
                        i=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(i);
                        break;

                    case R.id.aboutdeveloper:
                        i = new Intent(MainActivity.this, aboutDeveloper.class);
                        startActivity(i);
                        break;

                    default:
                        i = new Intent(MainActivity.this, frenchPaper.class);
                        startActivity(i);
                }

                return false;
            }
        });
        evs.setOnClickListener(this);
        logical.setOnClickListener(this);
        communication.setOnClickListener(this);
        buisnesscmn.setOnClickListener(this);
        financial.setOnClickListener(this);
        techcmn.setOnClickListener(this);
        engexp.setOnClickListener(this);
        language.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId())
        {
            case R.id.evs : i=new Intent(this,Evs.class);startActivity(i); break;
            case R.id.logical : i=new Intent(this,logicalActivity.class); startActivity(i);break;
            case R.id.communication : i=new Intent(this,communicationActivity.class);startActivity(i); break;
            case R.id.buisnesscmn : i=new Intent(this,buisnesscmnActivity.class);startActivity(i); break;
            case R.id.financial : i=new Intent(this,financialActivity.class);startActivity(i); break;
            case R.id.language : i=new Intent(this,languageActivity.class);startActivity(i); break;
            case R.id.technicalcmn: i=new Intent(this,technicalcmnActivity.class);startActivity(i); break;
            case R.id.engexploration: i=new Intent(this,engineeringexpActivity.class);startActivity(i); break;

            default:break;
        }






    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(ntoggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);

    }
}
