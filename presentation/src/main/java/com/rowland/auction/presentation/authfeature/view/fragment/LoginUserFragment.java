package com.rowland.auction.presentation.authfeature.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.view.fragment.ABaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class LoginUserFragment extends ABaseFragment {

    private onLoginFinishBtnClickListener loginFinishBtnClickListener;
    private String email;
    private String passWord;

    public interface onLoginFinishBtnClickListener {
        void onLoginFinishClicked(String email, String passWord);
    }

    @Bind(R.id.img_logcover)
    ImageView ivLogcover;
    @Bind(R.id.et_email)
    EditText etEmail;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;
    @Bind(R.id.rl_retry)
    RelativeLayout rlRetry;
    @Bind(R.id.bt_retry)
    Button btRetry;
    @Bind(R.id.bt_login)
    Button btLogin;

    public LoginUserFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View fragmentView = inflater.inflate(R.layout.fragment_login_user, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidEditTextData(etEmail,etPassword)) {
                    return;
                }
                email = etEmail.getText().toString().trim();
                passWord = etPassword.getText().toString().trim();
                loginFinishBtnClickListener.onLoginFinishClicked(email, passWord);
            }
        });
    }
}
