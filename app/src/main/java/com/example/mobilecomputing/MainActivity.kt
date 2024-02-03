package com.example.mobilecomputing

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.navigation.NavHostController
import com.example.mobilecomputing.ui.theme.MobileComputingTheme
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil.compose.AsyncImage
import java.io.File
import java.io.InputStream

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileComputingTheme{

                var selectedImageUri by remember {
                    mutableStateOf<Uri?>(null)
                }

                var uploadedImageUri by remember {
                    mutableStateOf<Uri?>(null)
                }

                val uploadedImageFile = File(filesDir, "uploaded_image.jpg")
                if (uploadedImageFile.exists()) {
                    uploadedImageUri = uploadedImageFile.toUri()
                }

                val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.PickVisualMedia(),
                    onResult = { uri -> selectedImageUri = uri }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(onClick = {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }) {
                                Text(text = "Pick one photo")
                            }

                            Text(text = "Pick multiple photo")

                        }
                    }

                    item {
                        AsyncImage(
                            model = uploadedImageUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )

                        if (selectedImageUri != null) {
                            uploadImageToLocal(selectedImageUri = selectedImageUri!!) { uploadedUri ->
                                uploadedImageUri = uploadedUri
                            }
                        }
                    }

                }

            }
        }
    }


}


@Composable
private fun uploadImageToLocal(selectedImageUri: Uri,onImageUploaded: (Uri) -> Unit) {
    val context = LocalContext.current
    val contentResolver = context.contentResolver

    val inputStream: InputStream? = contentResolver.openInputStream(selectedImageUri)

    if (inputStream != null) {
        val outputStream = context.openFileOutput("uploaded_image.jpg", Context.MODE_PRIVATE)
        inputStream.copyTo(outputStream)
        outputStream.close()
        inputStream.close()

        val uploadedImageUri = Uri.fromFile(File(context.filesDir, "uploaded_image.jpg"))
        onImageUploaded(uploadedImageUri)

        Toast.makeText(context, "Image uploaded to local storage", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()
    }
}





