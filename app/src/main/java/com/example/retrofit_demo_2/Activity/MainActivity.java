package com.example.retrofit_demo_2.Activity;

import static com.example.retrofit_demo_2.Activity.SplashScreen_Activity.editor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.retrofit_demo_2.R;
import com.example.retrofit_demo_2.ui.AddProductFragment;
import com.example.retrofit_demo_2.ui.ShowAllProductFragment;
import com.example.retrofit_demo_2.ui.ViewProductFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    AppBarLayout appBarLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarLayout = findViewById(R.id.appBar);
        navigationView = findViewById(R.id.navview);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.nav_drawer);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.menu_add_product)
                {
                    addFragment(new AddProductFragment());
                }
                if (item.getItemId() == R.id.menu_view_product)
                {
                    addFragment(new ViewProductFragment());
                }
                if (item.getItemId() == R.id.menu_view_all_product)
                {
                    addFragment(new ShowAllProductFragment());
                }
                if (item.getItemId() == R.id.menu_logout)
                {

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    editor.putBoolean("isLogin",false);
                    editor.commit();
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });

    }
    private void addFragment(Fragment fragment)
    {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }

}