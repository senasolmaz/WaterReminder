package com.senas.waterreminder.MainFragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.senas.waterreminder.R
import kotlin.math.roundToInt
import kotlin.system.exitProcess

class WaterFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_water, container, false)

        val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        val textView = view.findViewById<TextView>(R.id.txtView)
        val bugunToplam = view.findViewById<TextView>(R.id.bugun_toplam)
        val view2 = view.findViewById<View>(R.id.viewHeight)
        val glassImage = view.findViewById<ImageView>(R.id.glases)
        val plusButon = view.findViewById<ImageButton>(R.id.plus)
        val minusButon = view.findViewById<ImageButton>(R.id.minus)
        val confirmButon = view.findViewById<Button>(R.id.confirmButon)

        var defaultML = 100;
        var icilenToplamMiktar = 0;
        confirmButon.isVisible = false

        val density = resources.displayMetrics.density
        val imageParams = glassImage.layoutParams
        imageParams.width = (130 * density).toInt()
        imageParams.height = (190 * density).toInt()
        val layoutParams = view2.layoutParams

        seekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                textView.text = icilenToplamMiktar.toString() + "/3000 ML"
            }
            override fun onStartTrackingTouch(seek: SeekBar) {
            }
            override fun onStopTrackingTouch(seek: SeekBar) {
            }
        })

        seekBar.setOnTouchListener(View.OnTouchListener { view, motionEvent -> true })

        plusButon.setOnClickListener {
            if (layoutParams.height < 190*density && icilenToplamMiktar < 3000) {
                layoutParams.height += (defaultML * 190 / 3000*density).toInt()
                icilenToplamMiktar += defaultML
                view2.layoutParams = layoutParams
           }
            seekBar.progress = icilenToplamMiktar / defaultML * 3
            confirmButon.isVisible = true
            Log.d("top", icilenToplamMiktar.toString())
        }
        minusButon.setOnClickListener {
            if (icilenToplamMiktar < 3001 && icilenToplamMiktar > 0) {
                layoutParams.height -=   (defaultML * 190 / 3000*density).toInt()
                icilenToplamMiktar -= defaultML
                view2.layoutParams = layoutParams
            } else {
                confirmButon.isVisible = false
            }
            seekBar.progress = icilenToplamMiktar / defaultML * 3
        }

        confirmButon.setOnClickListener {

            // seekBar.progress = icilenToplamMiktar
            //Toast.makeText(context, icilenToplamMiktar.toString(), Toast.LENGTH_SHORT).show()
            val dialog = Dialog(context!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.water_added_popup)

            val infoText = dialog .findViewById(R.id.bilgiText) as TextView
            val okBtn = dialog.findViewById(R.id.okButon) as Button
            okBtn.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
            infoText.text = icilenToplamMiktar.toString() + "ML iceceğiniz başarıyla eklendi!"

            bugunToplam.text = "Bugün " + icilenToplamMiktar.toString() + " / 3000 ML"

        }
        bugunToplam.text = "Bugün " + icilenToplamMiktar.toString() + " / 3000 ML"

        return view
    }
}