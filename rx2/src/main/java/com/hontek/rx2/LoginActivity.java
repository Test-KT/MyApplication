package com.hontek.rx2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    private Button mEmailSignInButton;
    private String[] strs = {"no1", "no2"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mEmailView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, strs));


        mPasswordView = (EditText) findViewById(R.id.password);

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);


        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLogin(mEmailView.getText().toString().trim(), mPasswordView.getText().toString());
            }
        });


    }


    /**
     * @param email
     * @param pwd
     */
    private void goLogin(final String email, final String pwd) {
        Observable<User> userObservable = Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                if (email.isEmpty() || pwd.isEmpty()) {
                    e.onError(new RuntimeException("is empty"));
                } else {
                    Thread.sleep(4000);
                    User user = new User(email, pwd);
                    e.onNext(user);
                    e.onComplete();
                }
            }
        });


        Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User value) {
                Log.e("info--->",value.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("info--->",e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        };
        userObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(userObserver);
    }





    class User {
        public String email;
        public String pwd;

        public User(String email, String pwd) {
            this.email = email;
            this.pwd = pwd;
        }

        @Override
        public String toString() {
            return "User{" +
                    "email='" + email + '\'' +
                    ", pwd='" + pwd + '\'' +
                    '}';
        }
    }





}

