package app.divyanshu.navigationdrawer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.Toast;

import app.divyanshu.navigationdrawer.Fragment.Tools;
import app.divyanshu.navigationdrawer.Fragment.ViewFrag;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    public int  MY_REQUEST_CAMERA = 1888;

    EditText name;
    Button submit;
    Fragment newFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((FrameLayout)findViewById(R.id.content_frame)).setVisibility(View.VISIBLE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        name = findViewById(R.id.name);
        submit = findViewById(R.id.sbmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                ((FrameLayout)findViewById(R.id.content_frame)).removeAllViews();

                String name1 = name.getText().toString();

                Bundle bundle  = new Bundle();
                bundle.putString("name",name1);

                newFragment = new Tools();
                newFragment.setArguments(bundle);

                showFragment();



            }
        });
    }

    public void showFragment()
    {
        FragmentTransaction  transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_main_drawer,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id =item.getItemId();

        if (id == R.id.action_settings)
        {
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id =  menuItem.getItemId();


        if (id ==   R.id.nav_home)
        {
              Intent a = new Intent(getApplicationContext(), MainActivity.class);
              startActivity(a);
        }
        else if (id == R.id.nav_gallery)
        {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_REQUEST_CAMERA);

                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/");
                    startActivityForResult(intent, 0);
                }
            }
        }

        else if (id == R.id.nav_tools)
        {

           ((FrameLayout)findViewById(R.id.content_frame)).removeAllViews();
            FragmentTransaction  transaction1 = getSupportFragmentManager().beginTransaction();
            newFragment = new Tools();
            transaction1.replace(R.id.content_frame, newFragment);
            transaction1.addToBackStack(null);
            transaction1.commit();
        }
        else if(id ==  R.id.nav_slideshow)
        {
            ((FrameLayout)findViewById(R.id.content_frame)).removeAllViews();
            FragmentTransaction  transaction2 = getSupportFragmentManager().beginTransaction();
            newFragment = new ViewFrag();
            transaction2.replace(R.id.content_frame, newFragment);
            transaction2.addToBackStack(null);
            transaction2.commit();

        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_REQUEST_CAMERA)
        {
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
//                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(camera,MY_REQUEST_CAMERA);


                Intent intent  = new Intent(Intent.ACTION_PICK);
                intent.setType("image/");
                startActivityForResult(intent,0);
            } }
    }

}
