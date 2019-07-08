package hr.mateo.mircic.weather;

import android.accounts.Account;
import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    static final int GOOGLE_SIGNIN = 123;
    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    Button signin_button;
    Button signout_button;
    TextView email_textview;
    Button continue_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_textview = findViewById(R.id.email_textview);
        continue_button = findViewById(R.id.continue_button);
        continue_button.setText(">");

        continue_button.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        v.animate().scaleXBy(-0.1f).start();
                        v.animate().scaleYBy(-0.1f).start();
                        Intent myIntent = new Intent(MainActivity.this, SearchActivity.class);
                        MainActivity.this.startActivity(myIntent);
                        return true;
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP){
                        v.animate().cancel();
                        v.animate().scaleX(1f).start();
                        v.animate().scaleY(1f).start();
                        return true;
                    }
                    return false;
                }
            });

        signout_button = findViewById(R.id.google_SignOut_Button);
        signout_button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.animate().scaleXBy(-0.1f).scaleYBy(-0.1f).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            SignOut();
                        }
                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }
                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }
                    });

                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL){
                    v.animate().cancel();
                    v.animate().scaleX(1f).start();
                    v.animate().scaleY(1f).start();
                    return true;
                }
                return false;
            }
        });

        signin_button = findViewById(R.id.google_SignIn_Button);
        signin_button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.animate().scaleXBy(-0.1f).start();
                    v.animate().scaleYBy(-0.1f).start();
                    SignInGoogle();
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP){
                    v.animate().cancel();
                    v.animate().scaleX(1f).start();
                    v.animate().scaleY(1f).start();
                    return true;
                }
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            signin_button.setVisibility(View.GONE);
            signout_button.setVisibility(View.VISIBLE);
            email_textview.setText(mAuth.getCurrentUser().getEmail());
            email_textview.setVisibility(View.VISIBLE);
            continue_button.setVisibility(View.VISIBLE);
        }
        else{
            signin_button.setVisibility(View.VISIBLE);
            signout_button.setVisibility(View.GONE);
            email_textview.setText("");
            email_textview.setVisibility(View.INVISIBLE);
            continue_button.setVisibility(View.INVISIBLE);
        }

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    public void SignInGoogle(){
        if (mAuth.getCurrentUser() == null) {
            Intent signIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signIntent, GOOGLE_SIGNIN);

        }
    }

    private void SignOut() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(task -> {
                Toast.makeText(this,"Odjavili ste se uspješno", Toast.LENGTH_SHORT).show();
            });
        signout_button.setVisibility(View.GONE);
        signin_button.setVisibility(View.VISIBLE);
        email_textview.setText("");
        email_textview.setVisibility(View.INVISIBLE);
        continue_button.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGNIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null)
                    firebaseAuthWithGoogle(account);
            } catch (ApiException ex){
                ex.printStackTrace();
            }
    }
}

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isComplete()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent myIntent = new Intent(MainActivity.this, SearchActivity.class);
                        MainActivity.this.startActivity(myIntent);
                        signout_button.setVisibility(View.VISIBLE);
                        signin_button.setVisibility(View.GONE);
                        email_textview.setText(mAuth.getCurrentUser().getEmail());
                        email_textview.setVisibility(View.VISIBLE);
                        continue_button.setVisibility(View.VISIBLE);
                    }
                    else{
                        Toast.makeText(this, "Greška kod prijave", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }
