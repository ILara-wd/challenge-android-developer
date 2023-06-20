package mx.openpay.challenge.ui.upload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import mx.openpay.challenge.databinding.FragmentUploadBinding
import mx.openpay.challenge.tools.Extension.getImageByteArray
import mx.openpay.challenge.ui.upload.adapter.ImagePickerAdapter

class UploadFragment : Fragment() {
    companion object {
        private const val TAG = "UploadFragment"
        private const val PICK_PHOTO_CODE = 655
    }

    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnUpload.setOnClickListener { handleImageUploading() }
        binding.textHome.setOnClickListener { launchIntentForPhotos() }

        adapter = ImagePickerAdapter(requireContext(), chosenImageUris)
        binding.rvImagePicker.adapter = adapter
        binding.rvImagePicker.setHasFixedSize(true)
        binding.rvImagePicker.layoutManager = GridLayoutManager(requireContext(), 4)

        getAllData()
        return root
    }

    private var chosenImageUris = mutableListOf<Uri>()
    private val storage = Firebase.storage
    private lateinit var adapter: ImagePickerAdapter

    private fun handleImageUploading() {
        binding.pbUploading.visibility = View.VISIBLE
        val uploadedImageUrls = mutableListOf<String>()
        var didEncounterError = false
        for ((index, photoUri) in chosenImageUris.withIndex()) {
            val imageByteArray = requireActivity().getImageByteArray(photoUri)
            val filePath = "images/${System.currentTimeMillis()}-${index}.jpg"
            val photoReference = storage.reference.child(filePath)
            photoReference.putBytes(imageByteArray)
                .continueWithTask { photoUploadTask ->
                    Log.i(TAG, "uploaded bytes: ${photoUploadTask.result?.bytesTransferred}")
                    photoReference.downloadUrl
                }.addOnCompleteListener { downloadUrlTask ->
                    if (!downloadUrlTask.isSuccessful) {
                        Log.e(TAG, "Exception with Firebase storage", downloadUrlTask.exception)
                        Toast.makeText(
                            requireContext(),
                            "Error al subir la imagen",
                            Toast.LENGTH_SHORT
                        ).show()
                        didEncounterError = true
                        return@addOnCompleteListener
                    }
                    if (didEncounterError) {
                        binding.pbUploading.visibility = View.GONE
                        chosenImageUris = mutableListOf()
                        return@addOnCompleteListener
                    }
                    val downloadUrl = downloadUrlTask.result.toString()
                    uploadedImageUrls.add(downloadUrl)
                    Log.i(
                        TAG,
                        "Finished uploading $photoUri, Num uploaded: ${uploadedImageUrls.size}"
                    )
                    chosenImageUris = mutableListOf()
                    adapter.notifyDataSetChanged()
                    binding.pbUploading.visibility = View.GONE
                }
        }
    }

    private fun launchIntentForPhotos() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/* "
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "Elige las fotos"), PICK_PHOTO_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != PICK_PHOTO_CODE || resultCode != Activity.RESULT_OK || data == null) {
            Log.w(TAG, "No hubo recall desde la activity lanzada , el usuario cancelo")
            return
        }
        val selectedUri = data.data
        val clipData = data.clipData
        if (clipData != null) {
            Log.i(TAG, "clipData numImages ${clipData.itemCount}: $clipData")
            for (i in 0 until clipData.itemCount) {
                val clipItem = clipData.getItemAt(i)
                chosenImageUris.add(clipItem.uri)
            }
        } else if (selectedUri != null) {
            Log.i(TAG, "data: $selectedUri")
            chosenImageUris.add(selectedUri)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getAllData(){
        val storage = Firebase.storage
        val storageRef = storage.reference.child("/images").listAll()
        storageRef.addOnSuccessListener {
            it.prefixes.forEach {
                it.name.toString()
            }
        }
    }

}
