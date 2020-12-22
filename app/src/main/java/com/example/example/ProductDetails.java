package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetails extends AppCompatActivity {

    LinearLayout _electronicsLayout,_homeAppliancesLayout,_clothingLayout,_jewelleryLayout,_cosmeticsLayout,_furnitureLayout,_householdLayout,_stationeryLayout;
    static int elecCount=0,homeApplCount=0,clothingCount=0,jewCount=0,cosCount=0,furCount=0,householdCount=0,stationeryCount=0;
    View ElecView,HomeApplView,ClothingView,jewView,CosView,furView,householdView,StationeryView;
    LinearLayout _productLayout;
    TextView _productHeadTitle;


    Animation left,right,top,bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        _electronicsLayout = findViewById(R.id.electronicsLayout);
        _homeAppliancesLayout = findViewById(R.id.homeAppliancesLayout);
        _clothingLayout = findViewById(R.id.clothingLayout);
        _jewelleryLayout = findViewById(R.id.jewelleryLayout);
        _cosmeticsLayout = findViewById(R.id.CosmeticsLayout);
        _furnitureLayout = findViewById(R.id.FurnitureLayout);
        _householdLayout = findViewById(R.id.HouseholdItemsLayout);
        _stationeryLayout = findViewById(R.id.StationeryLayout);

        _productLayout = findViewById(R.id.ProductLayout);
        _productHeadTitle = findViewById(R.id.ProductHeadTitle);

        left = AnimationUtils.loadAnimation(this,R.anim.anim_left);
        right = AnimationUtils.loadAnimation(this,R.anim.anim_right);
        top = AnimationUtils.loadAnimation(this,R.anim.anim_top);
        bottom = AnimationUtils.loadAnimation(this,R.anim.anim_bottom);

        _productLayout.setAnimation(top);

    }

    public void onElectronics(View view)
    {
        if(elecCount <= 0) {
             ElecView = getLayoutInflater().inflate(R.layout.electronics_list, null, false);
            _electronicsLayout.addView(ElecView);
            _homeAppliancesLayout.removeView(HomeApplView);
            _clothingLayout.removeView(ClothingView);
            _jewelleryLayout.removeView(jewView);
            _cosmeticsLayout.removeView(CosView);
            _furnitureLayout.removeView(furView);
            _householdLayout.removeView(householdView);
            _stationeryLayout.removeView(StationeryView);
            homeApplCount=0;
            clothingCount=0;
            jewCount=0;
            cosCount=0;
            furCount=0;
            householdCount=0;
            stationeryCount=0;
            elecCount++;
        }
    }

    public void onHomeAppliances(View view)
    {
        if(homeApplCount <= 0)
        {
            HomeApplView = getLayoutInflater().inflate(R.layout.home_appliances_list,null,false);
            _homeAppliancesLayout.addView(HomeApplView);
            _electronicsLayout.removeView(ElecView);
            _clothingLayout.removeView(ClothingView);
            _jewelleryLayout.removeView(jewView);
            _cosmeticsLayout.removeView(CosView);
            _furnitureLayout.removeView(furView);
            _householdLayout.removeView(householdView);
            _stationeryLayout.removeView(StationeryView);
            elecCount=0;
            clothingCount=0;
            jewCount=0;
            cosCount=0;
            furCount=0;
            householdCount=0;
            stationeryCount=0;
            homeApplCount++;
        }
    }

    public void onClothing(View view)
    {
        if(clothingCount <= 0)
        {
            ClothingView = getLayoutInflater().inflate(R.layout.clothing_list,null,false);
            _clothingLayout.addView(ClothingView);
            _electronicsLayout.removeView(ElecView);
            _homeAppliancesLayout.removeView(HomeApplView);
            _jewelleryLayout.removeView(jewView);
            _cosmeticsLayout.removeView(CosView);
            _furnitureLayout.removeView(furView);
            _householdLayout.removeView(householdView);
            _stationeryLayout.removeView(StationeryView);
            elecCount=0;
            homeApplCount=0;
            jewCount=0;
            cosCount=0;
            furCount=0;
            householdCount=0;
            stationeryCount=0;
            clothingCount++;
        }

    }

    public void onJewellery(View view)
    {
        if(jewCount <= 0)
        {
            jewView = getLayoutInflater().inflate(R.layout.jewellery_list,null,false);
            _jewelleryLayout.addView(jewView);
            _homeAppliancesLayout.removeView(HomeApplView);
            _electronicsLayout.removeView(ElecView);
            _clothingLayout.removeView(ClothingView);
            _cosmeticsLayout.removeView(CosView);
            _furnitureLayout.removeView(furView);
            _householdLayout.removeView(householdView);
            _stationeryLayout.removeView(StationeryView);
            elecCount=0;
            homeApplCount=0;
            clothingCount=0;
            cosCount=0;
            furCount=0;
            householdCount=0;
            stationeryCount=0;
            jewCount++;
        }
    }

    public void onCosmetics(View view)
    {
        if(cosCount <= 0)
        {
            CosView = getLayoutInflater().inflate(R.layout.cosmetics_list,null,false);
            _cosmeticsLayout.addView(CosView);
            _electronicsLayout.removeView(ElecView);
            _homeAppliancesLayout.removeView(HomeApplView);
            _jewelleryLayout.removeView(jewView);
            _clothingLayout.removeView(ClothingView);
            _furnitureLayout.removeView(furView);
            _householdLayout.removeView(householdView);
            _stationeryLayout.removeView(StationeryView);
            elecCount=0;
            homeApplCount=0;
            clothingCount=0;
            jewCount=0;
            furCount=0;
            householdCount=0;
            stationeryCount=0;
            cosCount++;
        }
    }

    public void onFurniture(View view)
    {
        if(furCount <= 0)
        {
            furView = getLayoutInflater().inflate(R.layout.furniture_list,null,false);
            _furnitureLayout.addView(furView);
            _electronicsLayout.removeView(ElecView);
            _homeAppliancesLayout.removeView(HomeApplView);
            _jewelleryLayout.removeView(jewView);
            _clothingLayout.removeView(ClothingView);
            _cosmeticsLayout.removeView(CosView);
            _householdLayout.removeView(householdView);
            _stationeryLayout.removeView(StationeryView);
            elecCount=0;
            homeApplCount=0;
            clothingCount=0;
            jewCount=0;
            cosCount=0;
            householdCount=0;
            stationeryCount=0;
            furCount++;

        }
    }

    public void onHousehold(View view)
    {
        if(householdCount <= 0)
        {
            householdView = getLayoutInflater().inflate(R.layout.household_list,null,false);
            _householdLayout.addView(householdView);
            _electronicsLayout.removeView(ElecView);
            _homeAppliancesLayout.removeView(HomeApplView);
            _jewelleryLayout.removeView(jewView);
            _clothingLayout.removeView(ClothingView);
            _cosmeticsLayout.removeView(CosView);
            _furnitureLayout.removeView(furView);
            _stationeryLayout.removeView(StationeryView);
            elecCount=0;
            homeApplCount=0;
            clothingCount=0;
            jewCount=0;
            cosCount=0;
            furCount=0;
            stationeryCount=0;
            householdCount++;

        }
    }

    public void onStationery(View view)
    {
        if (stationeryCount <= 0)
        {
            StationeryView = getLayoutInflater().inflate(R.layout.stationery_list,null,false);
            _stationeryLayout.addView(StationeryView);
            _electronicsLayout.removeView(ElecView);
            _homeAppliancesLayout.removeView(HomeApplView);
            _jewelleryLayout.removeView(jewView);
            _clothingLayout.removeView(ClothingView);
            _cosmeticsLayout.removeView(CosView);
            _furnitureLayout.removeView(furView);
            _householdLayout.removeView(householdView);
            elecCount=0;
            homeApplCount=0;
            clothingCount=0;
            jewCount=0;
            cosCount=0;
            furCount=0;
            householdCount=0;
            stationeryCount++;

        }
    }

    public void onGroceries(View view)
    {
       _electronicsLayout.removeView(ElecView);
       _homeAppliancesLayout.removeView(HomeApplView);
       _clothingLayout.removeView(ClothingView);
       _jewelleryLayout.removeView(jewView);
       _cosmeticsLayout.removeView(CosView);
       _furnitureLayout.removeView(furView);
       _householdLayout.removeView(householdView);
       _stationeryLayout.removeView(StationeryView);

       elecCount=0;
       homeApplCount=0;
       clothingCount=0;
       jewCount=0;
       furCount=0;
       householdCount=0;
       stationeryCount=0;
    }
}