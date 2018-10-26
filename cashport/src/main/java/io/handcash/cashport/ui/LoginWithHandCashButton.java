package io.handcash.cashport.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import io.handcash.cashport.R;

public class LoginWithHandCashButton extends RelativeLayout {

    public LoginWithHandCashButton(Context context) {
        super(context);
        init();
    }

    public LoginWithHandCashButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoginWithHandCashButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.login_button, this);
    }

}