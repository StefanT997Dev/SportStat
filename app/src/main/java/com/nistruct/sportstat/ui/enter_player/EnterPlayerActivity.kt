package com.nistruct.sportstat.ui.enter_player

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.nistruct.sportstat.R
import com.nistruct.sportstat.usecase.result.DataResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_enter_player.*
import javax.inject.Inject

@AndroidEntryPoint
class EnterPlayerActivity : AppCompatActivity() {
    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }

    @Inject
    lateinit var enterPlayerViewModelFactory: EnterPlayerViewModelFactory

    private lateinit var viewModel: EnterPlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_player)

        viewModel = ViewModelProvider(
            this,
            enterPlayerViewModelFactory
        ).get(EnterPlayerViewModel::class.java)

        savePlayerButton.setOnClickListener {
            setPlayer()
            viewModel.enterPlayer()
        }

        viewModel.playerLiveData.observe(this) { result ->
            when (result) {
                is DataResult.Success -> {
                    Log.d("Response", result.data.name)
                }
                is DataResult.Error -> {
                    Log.d("Response", "Error bro")
                }
                is DataResult.Loading -> {
                    // Implement spinner
                }
            }
        }

        addImageTextView.setOnClickListener {
            val pictureDialog = AlertDialog.Builder(this)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItems = arrayOf("Select photo from Gallery",
                "Capture photo from camera")
            pictureDialog.setItems(pictureDialogItems){
                dialog,which->
                when(which){
                    0 -> chosePhotoFromGallery()
                    1 -> {
                        if (ContextCompat.checkSelfPermission(
                                this,
                                Manifest.permission.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED
                        ){
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, CAMERA_REQUEST_CODE)
                        }else{
                            ActivityCompat.requestPermissions(
                                this,
                                arrayOf(Manifest.permission.CAMERA),
                                CAMERA_PERMISSION_CODE
                            )
                        }
                    }
                }
            }
            pictureDialog.show()

        }
    }

    private fun chosePhotoFromGallery(){

    }

    private fun setPlayer() {
        viewModel.playerName = playerNameEditText.text.toString()
        viewModel.playerEmail = playerEmailEditText.text.toString()
        viewModel.playerImage = playerImageView.toString()
        viewModel.playerPhoneNumber = playerPhoneNumberEditText.text.toString()
        viewModel.playerPosition = roleOfPlayerSpinner.selectedItem.toString()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            } else {
                Toast.makeText(
                    this,
                    "Oops you denied the permission for camera.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap
                playerImageView.setImageBitmap(thumbnail)
            }
        }
    }
}