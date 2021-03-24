package com.nistruct.sportstat.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.nistruct.sportstat.R
import com.nistruct.sportstat.ui.enter_player.EnterPlayerViewModel
import com.nistruct.sportstat.ui.enter_player.EnterPlayerViewModelFactory
import com.nistruct.sportstat.ui.players.PlayersActivity
import com.nistruct.sportstat.usecase.result.DataResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var googleSignInClient:GoogleSignInClient

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)

        loginButton.setOnClickListener{
            setTrainer()
            viewModel.trainerLiveDataResult.observe(this){ result ->
                when (result) {
                    is DataResult.Success -> {
                        Log.d("Response", result.data.email)
                    }
                    is DataResult.Error -> {
                        Log.d("Response", "Error bro")
                    }
                    is DataResult.Loading -> {
                        // Implement spinner
                    }
                }
            }
        }

        googleSignInButton.setOnClickListener{
            signIn()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun setTrainer() {
        viewModel.trainerEmail=emailEditText.text.toString()
        viewModel.trainerPassword=passwordEditText.text.toString()
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            val intent = Intent(this, PlayersActivity::class.java)
            startActivity(intent)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.statusCode)
        }
    }

    companion object{
        private const val RC_SIGN_IN:Int=0
    }

}