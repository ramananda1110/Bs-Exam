package com.bs.exam.view.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.bs.exam.utils.BottomTabViewPager;
import com.bs.exam.view.fragment.FullViewFragment;
import com.bs.exam.view.fragment.GalleryViewFragment;
import com.bs.test.R;
import com.bs.test.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    BottomTabViewPager bottomPager;
    private MenuItem prevMenuItem;

    public static void start(Context mContext){
        Intent intent = new Intent(mContext,MainActivity.class);

        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        setupViewPager(binding.viewpager);
        binding.viewpager.setPagingEnabled(false);


        binding.viewpager.setOffscreenPageLimit(1);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.actionGallery:
                            binding.viewpager.setCurrentItem(0);

                            break;
                        case R.id.actionList:
                            binding.viewpager.setCurrentItem(1);

                            break;

                    }
                    return false;
                });


        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    binding.bottomNavigation.getMenu().getItem(0).setChecked(false);
                }


                binding.bottomNavigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = binding.bottomNavigation.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setupViewPager(ViewPager viewPager) {
        bottomPager = new BottomTabViewPager(getSupportFragmentManager());
        bottomPager.addFragment(new GalleryViewFragment(), "Gallery");
        bottomPager.addFragment(new FullViewFragment(), "List");

        viewPager.setAdapter(bottomPager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int items = item.getItemId();

        if(items== R.id.actionAbout){
            startActivity(new Intent(this, AboutMeActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_about, menu);
        return true;
    }
}