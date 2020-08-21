package com.example.atish_medtrail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atish_medtrail.view.DataFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if there is a saved instance (for cases of orientation change)
        if (savedInstanceState != null) {
//            Hide TextView
            ((TextView) findViewById(R.id.txtPlainSearchText)).setVisibility(View.GONE);

            ((FrameLayout) findViewById(R.id.container)).setVisibility(View.VISIBLE);
            return;
        }


    }

    public void loadImages(View view) {


        String searchedText = ((EditText) findViewById(R.id.editTextSearchText)).getText().toString();

        if (TextUtils.isEmpty(searchedText)) {
            Toast.makeText(this, "Please enter your search text", Toast.LENGTH_SHORT).show();
            return;
        }


        //Hide TextView
        ((TextView) findViewById(R.id.txtPlainSearchText)).setVisibility(View.GONE);

        ((FrameLayout) findViewById(R.id.container)).setVisibility(View.VISIBLE);

        //Load Fragment

        Bundle bundle = new Bundle();
        bundle.putString("search", searchedText);

        DataFragment dataFragment = new DataFragment();
        dataFragment.setArguments(bundle);

        getViewModelStore().clear();

        //Load and initialize new fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.container, dataFragment).commit();
    }
}