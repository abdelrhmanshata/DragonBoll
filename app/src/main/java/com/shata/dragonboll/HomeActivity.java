package com.shata.dragonboll;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.dmoral.toasty.Toasty;

public class HomeActivity extends AppCompatActivity {

    private ImageButton Play, Shop, More;
    private long backPressedTime;

    /*    @Override
        public boolean dispatchKeyEvent(KeyEvent event) {

            if (event.getAction() == KeyEvent.ACTION_DOWN){
                switch (event.getKeyCode()){
                    case  KeyEvent.KEYCODE_BACK:
                        return true;
                }
            }
            return  super.dispatchKeyEvent(event);
        }*/
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Definition();

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PlayActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ShopActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(HomeActivity.this, More);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        Intent intent, chooser;
                        int id = menuItem.getItemId();
                        if (id == R.id.feedback) {
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setData(Uri.parse("mailto:"));
                            String[] to = {"ae6545170@gmail.com"}; // my email
                            intent.putExtra(Intent.EXTRA_EMAIL, to);
                            intent.setType("message/rfc822");
                            chooser = Intent.createChooser(intent, "Send Feedback");
                            startActivity(chooser);
                        }

                        if (id == R.id.share) {
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Vermiculi");
                            String sAux = "\n Let me recommend you this Game \n\n";
                            sAux = sAux + "https://play.google.com/store/apps/details?id=com.angrymonkey.angrymonkey \n\n";
                            intent.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(intent, "Share"));
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            //super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
            return;
        } else {
            backToast = Toasty.info(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    void Definition() {
        Play = findViewById(R.id.play);
        Shop = findViewById(R.id.shop);
        More = findViewById(R.id.more);
    }
}
