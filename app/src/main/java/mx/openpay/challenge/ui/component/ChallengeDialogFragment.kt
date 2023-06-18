package mx.openpay.challenge.ui.component

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ChallengeDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(arguments?.getString("MSG"))
            .setPositiveButton("OK") { _,_ ->
                dismiss()
            }
            .create()

    companion object {
        const val TAG = "ChallengeDialogFragment"
    }

}
