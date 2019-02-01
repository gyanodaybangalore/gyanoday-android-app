package com.gdjt.gyanoday.utils;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {
    public static boolean NotEmptyValidator(Context aContext,
                                            EditText aEditText,
                                            boolean aShowToast,
                                            String aToastMsg) {
        boolean haveText = false;

        String text = aEditText.getText().toString().trim();
        boolean empty = TextUtils.isEmpty(text);
        if (empty) {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
            haveText = false;
        } else {
            haveText = true;
        }

        return haveText;
    }

    public static boolean NumericValidator(Context aContext,
                                           EditText aEditText,
                                           boolean aShowToast,
                                           String aToastMsg) {
        boolean isNumber = false;
        String text = aEditText.getText().toString().trim();
        if (text.length() > 0) {
            isNumber = text.matches("[0-9]+");
            if (!isNumber && aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        } else {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return isNumber;
    }

    public static boolean MinLengthValidator(Context aContext,
                                             EditText aEditText,
                                             int aMinLength,
                                             boolean aShowToast,
                                             String aToastMsg) {
        boolean validMinLength = false;
        if (aEditText.getText().toString().trim().length() >= aMinLength) {
            validMinLength = true;
        } else {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return validMinLength;
    }

    public static boolean MaxLengthValidator(Context aContext,
                                             EditText aEditText,
                                             int aMaxLength,
                                             boolean aShowToast,
                                             String aToastMsg) {
        boolean validManLength = false;
        if (aEditText.getText().toString().trim().length() <= aMaxLength) {
            validManLength = true;
        } else {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return validManLength;
    }


    public static boolean EmailValidator(Context aContext,
                                         EditText aEditText,
                                         boolean aShowToast,
                                         String aToastMsg) {
        boolean valid = false;
        String emailStr = aEditText.getText().toString().trim();
        valid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches();
        if (!valid) {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }


    public static boolean SpecialCharacterValidator(Context aContext,
                                                    EditText aEditText,
                                                    boolean aShowToast,
                                                    String aToastMsg) {
        boolean valid = false;
        String str = aEditText.getText().toString().trim();
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(str);
        valid = m.find();
        if (!valid) {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }

    public static boolean MinimumLengthValidator(Context aContext,
                                                 EditText aEditText,
                                                 int aMinLength,
                                                 boolean aShowToast,
                                                 String aToastMsg) {
        boolean valid = false;
        String str = aEditText.getText().toString().trim();
        if (str.length() >= aMinLength) {
            valid = true;
        }
        if (!valid) {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }

    public static boolean RangeValidator(Context aContext,
                                         EditText aEditText,
                                         int aStartRange,
                                         int aEndRange,
                                         boolean aShowToast,
                                         String aToastMsg) {
        boolean valid = false;
        String str = aEditText.getText().toString().trim();
        int number;

        try {
            number = Integer.parseInt(str);
        } catch (Exception e) {
            number = -1;
        }
        if (number != -1 && number > aStartRange && number < aEndRange) {
            valid = true;
        } else {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }

    public static boolean UrlValidator(Context aContext,
                                       EditText aEditText,
                                       boolean aShowToast,
                                       String aToastMsg) {

        boolean valid = false;
        String str = aEditText.getText().toString().trim();
        valid = URLUtil.isValidUrl(str);
        if (!valid) {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }

    public static boolean UrlPhoneValidator(Context aContext,
                                            EditText aEditText,
                                            int aMinLength,
                                            int aMaxLength,
                                            boolean aShowToast,
                                            String aToastMsg) {
        if (aMinLength == 0) {
            aMinLength = 6;
        }
        if (aMaxLength == 0) {
            aMaxLength = 13;
        }
        boolean valid = false;
        String str = aEditText.getText().toString().trim();

        if (!Pattern.matches("[a-zA-Z]+", str)) {
            if (str.length() >= aMinLength && str.length() <= aMaxLength) {
                valid = true;
            } else {
                valid = false;
            }
        } else {
            valid = false;
        }
        if (!valid) {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }

    public static boolean EqualStrValidator(Context aContext,
                                            EditText aFirstEditText,
                                            EditText aSecondEditText,
                                            boolean aShowToast,
                                            String aToastMsg) {
        boolean equal = false;
        String firstText = aFirstEditText.getText().toString().trim();
        String secondText = aSecondEditText.getText().toString().trim();
        if (firstText.equals(secondText)) {
            equal = true;
        } else {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return equal;
    }


    public static boolean IsDateValidator(Context aContext,
                                          EditText aEditText,
                                          String aDateFormat,
                                          boolean aShowToast,
                                          String aToastMsg) {
        boolean valid = false;
        String text = aEditText.getText().toString().trim();
        SimpleDateFormat sdf = new SimpleDateFormat(aDateFormat);
        Date date = null;
        try {
            date = sdf.parse(text);
        } catch (ParseException pe) {
            //Date is invalid, try next format
        }
        if (date != null) {
            valid = true;
        } else {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }


    public static boolean YearValidator(Context aContext,
                                        EditText aEditText,
                                        boolean aShowToast,
                                        String aToastMsg
    ) {
        boolean valid = false;
        String firstText = aEditText.getText().toString().trim();
        int year = -1;
        try {
            year = Integer.parseInt(firstText);
        } catch (Exception e) {
        }

        if (year != -1 && year > 1970 && year < 2100) {
            valid = true;
        } else {
            if (aShowToast) {
                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
            }
        }
        return valid;
    }

//    public static boolean YearValidator(Context aContext,
//                                        int aYear,
//                                        boolean aShowToast,
//                                        String aToastMsg
//    ){
//        boolean valid= false;
//        if(aYear > 1970 && aYear < 2100){
//            valid = true;
//        }
//        else{
//            if(aShowToast){
//                Toast.makeText(aContext, aToastMsg, Toast.LENGTH_SHORT).show();
//            }
//        }
//        return valid;
//    }

}
