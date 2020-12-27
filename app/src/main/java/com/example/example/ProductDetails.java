package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductDetails extends AppCompatActivity {

    EditText _productTitle,_productName,_productPrice,_productQuality,_productColor,_productDesc;

    LinearLayout _electronicsLayout,_homeAppliancesLayout,_clothingLayout,_jewelleryLayout,_cosmeticsLayout,_furnitureLayout,_householdLayout,_stationeryLayout;
    static int elecCount=0,homeApplCount=0,clothingCount=0,jewCount=0,cosCount=0,furCount=0,householdCount=0,stationeryCount=0;
    View ElecView,HomeApplView,ClothingView,jewView,CosView,furView,householdView,StationeryView;
    LinearLayout _productLayout;
    TextView _productHeadTitle;
    Button _submit;

    String msg="";

    Animation left,right,top,bottom;

    DatabaseReference UserDB;
    FirebaseAuth mAuth;
    Products products;

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

        _productTitle = findViewById(R.id.ProductTitle);
        _productName = findViewById(R.id.ProductName);
        _productPrice = findViewById(R.id.ProductPrice);
        _productQuality = findViewById(R.id.Quantity);
        _productColor = findViewById(R.id.Color);
        _productDesc = findViewById(R.id.ProductDescription);


        UserDB = FirebaseDatabase.getInstance("https://loca-e3bf3-default-rtdb.firebaseio.com/").getReference();
        mAuth = FirebaseAuth.getInstance();




        _submit = findViewById(R.id.ProductSubmit);

        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pt = _productTitle.getText().toString();
                String pn = _productName.getText().toString();
                String pp = _productPrice.getText().toString();
                String pq = _productQuality.getText().toString();
                String pc = _productColor.getText().toString();
                String pd = _productDesc.getText().toString();

                if (msg.equals("Mobile") || msg.equals("Tabs") || msg.equals("TV") || msg.equals("Computer") || msg.equals("Speaker") || msg.equals("ElectronicsOther")) {

                   products = new Products(pt,pn,pp,pq,pc,pd);
                   UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("Electronics").child(msg).push().setValue(products);
                   UserDB.child("Products").child("Electronics").child(msg).push().setValue(products).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           Toast.makeText(ProductDetails.this, "Success", Toast.LENGTH_SHORT).show();
                       }
                   });

                    Toast.makeText(ProductDetails.this, "Electronics: " + msg, Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("WashingMachine") || msg.equals("Refrigerator") || msg.equals("AC") || msg.equals("Chimney") || msg.equals("Fans") || msg.equals("Lights") || msg.equals("HomeOthers"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("HomeAppliances").child(msg).push().setValue(products);
                    UserDB.child("Products").child("HomeAppliances").child(msg).push().setValue(products);
                    Toast.makeText(ProductDetails.this, "Home Appliances: " + msg, Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("Earrings") || msg.equals("Necklace") || msg.equals("Bangles") || msg.equals("JewelleryOthers"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("Jewellery").child(msg).push().setValue(products);
                    UserDB.child("Products").child("Jewellery").child(msg).push().setValue(products).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ProductDetails.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Toast.makeText(ProductDetails.this, "Jewellery: " + msg, Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("Pencils") || msg.equals("Notebook") || msg.equals("Slambook") || msg.equals("Textbook") || msg.equals("Storybook") || msg.equals("StationeryOthers"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("Stationery").child(msg).push().setValue(products);
                    UserDB.child("Products").child("Stationery").child(msg).push().setValue(products);
                    Toast.makeText(ProductDetails.this, "Stationery: " + msg, Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("Tables") || msg.equals("Cupboards") || msg.equals("Bed") || msg.equals("Sofa") || msg.equals("Chairs") || msg.equals("FurnitureOthers"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("Furniture").child(msg).push().setValue(products);
                    UserDB.child("Products").child("Furniture").child(msg).push().setValue(products);
                    Toast.makeText(ProductDetails.this, "Furniture: " + msg, Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("Lipstick") || msg.equals("Nail") || msg.equals("Eyeliner") || msg.equals("FaceCreams") || msg.equals("HairSpray") || msg.equals("CosmeticsOthers"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("Cosmetics").child(msg).push().setValue(products);
                    UserDB.child("Products").child("Cosmetics").child(msg).push().setValue(products);
                    Toast.makeText(ProductDetails.this, "Cosmetics: " + msg, Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("Plates") || msg.equals("Glasses") || msg.equals("Utensils") || msg.equals("BedSheets") || msg.equals("Curtains") || msg.equals("HouseHoldOthers"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("HouseholdItems").child(msg).push().setValue(products);
                    UserDB.child("Products").child("HouseholdItems").child(msg).push().setValue(products);
                    Toast.makeText(ProductDetails.this, "HouseHoldItems: " + msg, Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("KidsJeans") || msg.equals("KidsTops") || msg.equals("KidsNightWear") || msg.equals("KidsOthers"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("Clothing").child("Kids").child(msg).push().setValue(products);
                    UserDB.child("Products").child("Clothing").child("Kids").child(msg).push().setValue(products);
                    Toast.makeText(ProductDetails.this, "Clothing: kids: " + msg, Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("WomenCasual") || msg.equals("WomenTraditional") || msg.equals("WomenWinter") || msg.equals("WomenInner") ||  msg.equals("WomenOthers"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("Clothing").child("Women").child(msg).push().setValue(products);
                    UserDB.child("Products").child("Clothing").child("Women").child(msg).push().setValue(products);
                    Toast.makeText(ProductDetails.this, "Clothing: women: " + msg, Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("MenCasual") || msg.equals("MenTraditional") || msg.equals("MenWinter") || msg.equals("MenInner") || msg.equals("MenOthers"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("Clothing").child("Men").child(msg).push().setValue(products);
                    UserDB.child("Products").child("Clothing").child("Men").child(msg).push().setValue(products);
                    Toast.makeText(ProductDetails.this, "Clothing: Men: " + msg , Toast.LENGTH_SHORT).show();
                }
                else if(msg.equals("Grocery"))
                {
                    products = new Products(pt,pn,pp,pq,pc,pd);
                    UserDB.child("Users").child(mAuth.getUid()).child("ProductDetails").child("Groceries").child(msg).push().setValue(products);
                    UserDB.child("Products").child("Groceries").child(msg).push().setValue(products);
                    Toast.makeText(ProductDetails.this, "Groceries", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ProductDetails.this, "Error Selection Option", Toast.LENGTH_SHORT).show();
                }

            }
        });

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
        msg = "Grocery";
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

    public void onMobile(View view)
    {
        msg = "Mobile";
    }

    public void onTabs(View view)
    {
        msg = "Tabs";
    }

    public void onTV(View view)
    {
        msg = "TV";
    }

    public void onComputer(View view)
    {
        msg = "Computer";
    }

    public void onSpeaker(View view)
    {
        msg = "Speaker";
    }

    public void onElectronicsOthers(View view)
    {
        msg = "ElectronicsOther";
    }

    public void onWashingMachine(View view)
    {
        msg = "WashingMachine";
    }

    public void onRefrigerator(View view)
    {
        msg = "Refrigerator";
    }

    public void onAC(View view)
    {
        msg = "AC";
    }

    public void onChimney(View view)
    {
        msg = "Chimney";
    }

    public void onFans(View view)
    {
        msg = "Fans";
    }

    public void onLights(View view)
    {
        msg = "Lights";
    }

    public void onHomeOthers(View view)
    {
        msg = "HomeOthers";
    }

    public void onEarrings(View view)
    {
        msg = "Earrings";
    }

    public void onNecklace(View view)
    {
        msg = "Necklace";
    }

    public void onBangles(View view)
    {
        msg = "Bangles";
    }

    public void onJewelleryOthers(View view)
    {
        msg = "JewelleryOthers";
    }

    public void onPencils(View view)
    {
        msg = "Pencils";
    }

    public void onNotebook(View view)
    {
        msg = "Notebook";
    }

    public void onSlambook(View view)
    {
        msg = "Slambook";
    }

    public void onTextbook(View view)
    {
        msg = "Textbook";
    }

    public void onStorybook(View view)
    {
        msg = "Storybook";
    }

    public void onStationeryOthers(View view)
    {
        msg = "StationeryOthers";
    }

    public void onTables(View view)
    {
        msg = "Tables";
    }

    public void onCupboards(View view)
    {
        msg = "Cupboards";
    }

    public void onBed(View view)
    {
        msg = "Bed";
    }

    public void onSofa(View view)
    {
        msg = "Sofa";
    }

    public void onChairs(View view)
    {
        msg = "Chairs";
    }

    public void onFurnitureOthers(View view)
    {
        msg = "FurnitureOthers";
    }

    public void onLipstick(View view) 
    {
        msg = "Lipstick";
    }

    public void onNail(View view) {
        msg = "Nail";
    }

    public void onEyeliner(View view) {
        msg = "Eyeliner";
    }

    public void onFaceCreams(View view) {
        msg = "FaceCreams";
    }

    public void onHairSpray(View view) {
        msg = "HairSpray";
    }

    public void onCosmeticsOthers(View view) {
        msg = "CosmeticsOthers";
    }

    public void onPlates(View view) {
        msg = "Plates";
    }

    public void onGlasses(View view) {
        msg = "Glasses";
    }

    public void onUtensils(View view) {
        msg = "Utensils";
    }

    public void onBedSheets(View view) {
        msg = "BedSheets";
    }

    public void onCurtains(View view) {
        msg = "Curtains";
    }

    public void onHouseholdOthers(View view) {
        msg = "HouseHoldOthers";
    }

    public void onKidsJeans(View view) {
        msg = "KidsJeans";
    }

    public void onKidsTops(View view) {
        msg = "KidsTops";
    }

    public void onKidsNightWear(View view) {
        msg = "KidsNightWear";
    }

    public void onKidsOthers(View view) {
        msg = "KidsOthers";
    }

    public void onWomenCasual(View view) {
        msg = "WomenCasual";
    }

    public void onWomenTraditional(View view) {
        msg = "WomenTraditional";
    }

    public void onWomenWinter(View view) {
        msg = "WomenWinter";
    }

    public void onWomenInner(View view) {
        msg = "WomenInner";
    }

    public void onWomenOthers(View view) {
        msg = "WomenOthers";
    }

    public void onMenCausal(View view) {
        msg = "MenCasual";
    }

    public void onMenTraditional(View view) {
        msg = "MenTraditional";
    }

    public void onMenWinter(View view) {
        msg = "MenWinter";
    }

    public void onMenInner(View view) {
        msg = "MenInner";
    }

    public void onMenOthers(View view) {
        msg = "MenOthers";
    }
}