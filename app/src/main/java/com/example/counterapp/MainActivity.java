package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView counterTxt;
    private Button minBtn;
    private Button plusBtn;
    private int counterInt;
    private Button imageSelect;
    private int selectPicture = 200;

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterTxt = (TextView) findViewById(R.id.counterTxt);
        counterInt = 0;
        imageSelect = (Button) findViewById(R.id.imageSelect);

        }

     public void  decreaseCount (View view){

         counterTxt.setText(String.valueOf(--counterInt));
    }
    public void increaseCount (View view){
        counterTxt.setText(String.valueOf(++counterInt));
    }
    public void share (View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, (" The counter is now on " + counterInt));
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Android App share");
        startActivity(shareIntent);
    }
    public void selectImage (View view){
        imageSelect.setOnClickListener(clickListener);
        Intent gallery = new Intent();
        gallery.setType("image*/");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"),selectPicture);

    }
    @SuppressLint("SdCardPath")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == selectPicture) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/jpeg");
                    share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/path-to-your-image.jpg"));
                    startActivity(Intent.createChooser(share, "Share Image"));
                    //to share with Whatsapp, change the code.
                    /*Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/jpeg");
                    share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/path-to-your-image.jpg"));
                    share.setPackage("com.whatsapp");//package name of the app
                    startActivity(Intent.createChooser(share, "Share Image"));*/
                }
            }
        }
    }
}
