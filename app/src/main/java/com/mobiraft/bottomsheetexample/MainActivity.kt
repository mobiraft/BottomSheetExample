package com.mobiraft.bottomsheetexample

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.persistent_bottom_sheet.*
class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomSheetBehavior = BottomSheetBehavior.from<LinearLayout>(persistent_bottom_sheet)

        bottomSheetBehavior.setBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, state: Int) {
                print(state)
                when (state) {

                    BottomSheetBehavior.STATE_HIDDEN -> {
                        persistentBtn.text = "Show Bottom Sheet"
                    }
                    BottomSheetBehavior.STATE_EXPANDED ->
                        persistentBtn.text = "Close Bottom Sheet"
                    BottomSheetBehavior.STATE_COLLAPSED ->
                        persistentBtn.text = "Show Bottom Sheet"
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {

                    }
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        persistentBtn.setOnClickListener(View.OnClickListener {
            expandCollapseSheet()
        })

        bottomSheetDialogBtn.setOnClickListener{
            val modalSheetView = layoutInflater.inflate(R.layout.fragment_modal_bottom_sheet,null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(modalSheetView)
            dialog.show()

        }

        bottomSheetDialogFragmentBtn.setOnClickListener {
            val modalbottomSheetFragment = ModalBottomSheet()
            modalbottomSheetFragment.show(supportFragmentManager,modalbottomSheetFragment.tag)
        }

    }
    private fun expandCollapseSheet() {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            persistentBtn.text = "Close Bottom Sheet"
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            persistentBtn.text = "Show Bottom Sheet"
        }
    }

}
