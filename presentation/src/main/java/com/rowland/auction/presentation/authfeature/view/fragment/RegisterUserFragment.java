package com.rowland.auction.presentation.authfeature.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.view.fragment.ABaseFragment;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterUserFragment extends ABaseFragment {

    @Bind(R.id.iv_regcover)
    ImageView ivRegcover;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_email)
    TextView tvEmail;
    @Bind(R.id.et_password)
    TextView tvPassword;
    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;
    @Bind(R.id.rl_retry)
    RelativeLayout rlRetry;
    @Bind(R.id.bt_retry)
    Button btRetry;


    public RegisterUserFragment() {
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_user, container, false);
    }

}
