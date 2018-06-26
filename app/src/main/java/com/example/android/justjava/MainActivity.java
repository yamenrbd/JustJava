/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    public int quantity = 0;
    public boolean cream = false;
    public boolean choclate = false ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        createOrderSummary(quantity);

    }

    private void createOrderSummary(int numOfCup){
        int price = calculatePrice(numOfCup);

        CheckBox state2 = (CheckBox) findViewById(R.id.choclate);
        EditText name_editBox = (EditText) findViewById(R.id.name_editBox);
        CheckBox state1 = (CheckBox) findViewById(R.id.cream);
         cream = state1.isChecked();
         choclate = state2.isChecked();
        String name  = name_editBox.getText().toString();
        String message = getString(R.string.name_hint)+" : "+ getString(R.string.order_summary_name,name)+"\n";
        message+= getString(R.string.add_wipped_cream)+getString(R.string.wipped_cream_checkBox)+"\n";
        message+= getString(R.string.add_choclate)+getString(R.string.choclate_checkBox)  +"\n";
        message+=getString(R.string.quantity_textBox)+" : "+numOfCup+"\n";
        message += "total : "+price+"$\n";
        message += getString(R.string.thank_message);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_SUBJECT,"just java order email for"+name);
        intent.putExtra(intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private int calculatePrice (int num ){
        int finalPrice = 5 ;
        if(cream ){
            finalPrice = finalPrice+1;
        }
         if (choclate) {
             finalPrice = finalPrice + 2;
         }
        finalPrice = finalPrice * num;
        return finalPrice;

    }

    private void display(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    public void increment(View view) {

        if(quantity == 10  ) {
            Toast.makeText(MainActivity.this,"you cant order more than 10 cups of coffe ",Toast.LENGTH_SHORT).show();
            return;
        }else{
            quantity = quantity + 1;
        }
        display(quantity);

    }

    public void decrement(View view) {

            if(quantity < 2 ){
                Toast.makeText(this,"you should order at least 1 cup of coffe",Toast.LENGTH_SHORT).show();
               return;
            }else{
                quantity = quantity-1;
            }
            display(quantity);
    }
    public void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}