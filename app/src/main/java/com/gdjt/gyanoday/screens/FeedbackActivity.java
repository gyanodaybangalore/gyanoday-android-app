package com.gdjt.gyanoday.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigInteger;

import com.gdjt.gyanoday.Constant;
import com.gdjt.gyanoday.R;
import com.gdjt.gyanoday.utils.UIUtils;
import com.gdjt.gyanoday.utils.ValidatorUtils;

public class FeedbackActivity extends AppCompatActivity {

    private EditText mNameEt;
    private EditText mEmailEt;
    private EditText mPhoneNoEt;
    private EditText mFeedbackEt;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_feedback_hindi);
            getSupportActionBar().setSubtitle(R.string.title_activity_feedback);

        }
        mContext = this;

        mNameEt = findViewById(R.id.et_feedback_name);
        mEmailEt = findViewById(R.id.et_feedback_email);
        mPhoneNoEt = findViewById(R.id.et_feedback_phone);
        mFeedbackEt = findViewById(R.id.et_feedback_text);
        Button submitBtn = findViewById(R.id.btn_feedback_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmitClick();
            }
        });
    }

    private void handleSubmitClick() {
        if(isValidName() && isValidEmail() && isValidMobileNo() && isValidFeedback()){
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "gyanodaybangalore@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "Thanks " +mNameEt.getText().toString()+ " for the feedback");
            email.putExtra(Intent.EXTRA_TEXT, "Name: "+mNameEt.getText().toString()+"\nMobile:"+mPhoneNoEt.getText().toString()+"\nEmail:"+mEmailEt.getText().toString()+"\n"+mFeedbackEt.getText().toString());

//need this to prompts email client only
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose an Email client :"));

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private boolean isValidName() {
        boolean valid = false;
        String str = mNameEt.getText().toString().trim();
        if (!str.isEmpty()) {
                valid = true;
        } else {
            UIUtils.showToast(mContext, getString(R.string.InvalidNameMsg));
        }
        return valid;
    }


    private boolean isValidFeedback() {
        boolean valid = false;
        String str = mFeedbackEt.getText().toString().trim();
        if (!str.isEmpty()) {
            valid = true;
        } else {
            UIUtils.showToast(mContext, getString(R.string.InvalidFeedbackMsg));
        }
        return valid;
    }

    private boolean isValidEmail() {
        boolean valid = false;
        String str = mEmailEt.getText().toString().trim();
        if (!str.isEmpty()) {
            if (ValidatorUtils.EmailValidator(mContext, mEmailEt, false, null)) {
                valid = true;
            } else {
                UIUtils.showToast(mContext, getString(R.string.InvalidEmailValidationMsg));
            }
        } else {
            UIUtils.showToast(mContext, getString(R.string.InvalidEmailMsg));
        }
        return valid;
    }

    private boolean isValidMobileNo() {
        boolean valid = false;
        String str = mPhoneNoEt.getText().toString().trim();
        if (!str.isEmpty()) {
            if (str.length() >= Constant.USER_PHONE_NO_MIN_LENGTH) {
                BigInteger bigNumber = new BigInteger(str);
                if (bigNumber.compareTo(BigInteger.ZERO) > 0) {
                    valid = true;
                } else {
                    UIUtils.showToast(mContext, getString(R.string.InvalidMobileMsg));
                }
            } else {
                UIUtils.showToast(mContext, getString(R.string.InvalidMobileLengthMsg));
            }

        } else {
            UIUtils.showToast(mContext, getString(R.string.InvalidMobileMsg));
        }
        return valid;
    }


}
