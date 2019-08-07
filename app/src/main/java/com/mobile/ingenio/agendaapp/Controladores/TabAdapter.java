package com.mobile.ingenio.agendaapp.Controladores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    private  final List<Fragment> mfragment = new ArrayList<>();
    private  final List<String> mtitulo = new ArrayList<>();

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mfragment.get(i);
    }

    @Override
    public int getCount() {
        return mfragment.size();
    }

    public  void addFragment(Fragment fragment, String titulo){
        mfragment.add(fragment);
        mtitulo.add(titulo);
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return mtitulo.get(position);
    }
}
