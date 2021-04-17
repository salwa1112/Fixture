package com.fixtureservicexyz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.fixtureservicexyz.R
import com.fixtureservicexyz.databinding.ActivityLoginBinding
import com.fixtureservicexyz.models.GoogleAccount
import com.fixtureservicexyz.utils.GoForwardTransition
import com.fixtureservicexyz.utils.StrKeys
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginSignIn.setOnClickListener {
            Intent(this, SignInUsernameActivity::class.java).apply {
                startActivity(this)
                GoForwardTransition()
            }
        }

        binding.btnLoginSignUp.setOnClickListener {
            Intent(this, SignUpPersonalDataActivity::class.java).apply {
                startActivity(this)
                GoForwardTransition()
            }
        }

        binding.btnLoginAsGuest.setOnClickListener {
            Intent(this, GuestLoginActivity::class.java).apply {
                startActivity(this)
                GoForwardTransition()
            }
        }

        binding.ivGoogleSignIn.setOnClickListener {
            // Configure Google Sign In
            val googleConfig = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConfig)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                if(account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if(it.isSuccessful){
                            val email = account.email.orEmpty()
                            Intent(this,GoogleUserLocationActivity::class.java).apply {
                                this.putExtra(StrKeys.SIGN_IN_AS_GOOGLE_ACCOUNT_EMAIL,GoogleAccount(email))
                                startActivity(this)
                                GoForwardTransition()
                                
                            }
                        }else{
                            Toast.makeText(this, "Something was wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }catch (error:ApiException){
                Toast.makeText(this, "Error AUTH", Toast.LENGTH_SHORT).show()
            }

        }
    }
}