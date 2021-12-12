package com.example.stepcounter.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stepcounter.R;
import com.example.stepcounter.Utils.LoginPreferenceUtils;
import com.example.stepcounter.UI.ActivityMain;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    Button loginBtn;
    EditText emailId;
    EditText password;
    FirebaseAuth firebaseAuth;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        loginBtn = (Button) rootView.findViewById(R.id.loginBtn);

        if((LoginPreferenceUtils.getEmail(getActivity()) != null) && (LoginPreferenceUtils.getPassword(getActivity()) != null)){
            Intent intent = new Intent(getActivity(), ActivityMain.class);
            startActivity(intent);
        }
        firebaseAuth = FirebaseAuth.getInstance();
        emailId = (EditText) rootView.findViewById(R.id.emailId);
        password = (EditText) rootView.findViewById(R.id.password);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithFirebase(emailId.getText().toString(),password.getText().toString());

            }
        });
        return rootView;
    }

    private void signInWithFirebase(String email,String pass){
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                LoginPreferenceUtils.saveEmail(emailId.getText().toString(), getActivity());
                LoginPreferenceUtils.savePassword(password.getText().toString(), getActivity());
                Intent intent = new Intent(getActivity(), ActivityMain.class);
                startActivity(intent);
                getActivity().finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}