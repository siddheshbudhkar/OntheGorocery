package com.example.onthegorocery;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Home_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    DrawerLayout navDrawer;
    DatabaseReference reference;
    ArrayList<Product> prodList;
    RecyclerView recyclerViewProd;
    productAdapter prodAdapter;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    //Image SLides
    Timer timer;
    int currentPosition = 0;
    List<Slide> slideList = new ArrayList<>();
    //Ends
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar topToolbar = findViewById(R.id.actionBar);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        navDrawer = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        setSupportActionBar(topToolbar);
//        Button button = (Button) findViewById(R.id.signout);
        mAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(Home_page.this, signin_activity.class));
                }
            }
        };
        bottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.bottomNavigationAlarmMenuId:
                                Toast.makeText(Home_page.this, "FIRST TAB CLICKED", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.bottomNavigationClockMenuId:
                                Toast.makeText(Home_page.this, "Second  TAB CLICKED", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.bottomNavigationTimerMenuId:
                                Toast.makeText(Home_page.this, "Third  TAB CLICKED", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });

        recyclerViewProd = (RecyclerView) findViewById(R.id.recycleViewGrocery);
        recyclerViewProd.setHasFixedSize(true);
        recyclerViewProd.setLayoutManager(new LinearLayoutManager(this));
        prodList = new ArrayList<Product>();
        reference = FirebaseDatabase.getInstance().getReference().child("Eggs");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Toast.makeText(Home_page.this, "Data Is there", Toast.LENGTH_SHORT).show();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Product p = dataSnapshot1.getValue(Product.class);
                        prodList.add(p);
                    }
                    prodAdapter = new productAdapter(Home_page.this, prodList);
                    recyclerViewProd.setAdapter(prodAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Home_page.this, "Eroooorrrr" + prodList, Toast.LENGTH_SHORT).show();
            }
        });


        //Images SLiding
       viewPager = (ViewPager) findViewById(R.id.viewPagerImages);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);



        }
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//
//
//            }
//        });




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int cartId=item.getItemId();
        if(cartId==R.id.myCart){
            Toast.makeText(this, "DISPLAY CART ITEMS", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_page:
                Toast.makeText(Home_page.this, "Show Home Page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.orders:
                Toast.makeText(Home_page.this, "Display Category", Toast.LENGTH_SHORT).show();
                break;

        }
        item.setChecked(true);
        navDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}