package com.senas.waterreminder.MainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.senas.waterreminder.R
import kotlin.math.roundToInt
import kotlin.system.exitProcess

class WaterFragment: Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_water, container, false)

        val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        val textView = view.findViewById<TextView>(R.id.txtView)
        val view2 = view.findViewById<View>(R.id.viewHeight)
        val glassImage = view.findViewById<ImageView>(R.id.glases)
        val plusButon = view.findViewById<ImageButton>(R.id.plus)
        val minusButon = view.findViewById<ImageButton>(R.id.minus)
        val confirmButon = view.findViewById<ImageButton>(R.id.confirmButon)

        var defaultML = 20;
        var icilenToplamMiktar = 0;

        val density = resources.displayMetrics.density
        val imageParams = glassImage.layoutParams
        imageParams.width = (140*density).toInt()
        imageParams.height = (200*density).toInt()

        val layoutParams = view2.layoutParams

        seekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {

                textView.text = progress.toString()

                if(progress*2.2 < 210 ) {
                    val layoutParams = view2.layoutParams
                    layoutParams.height = (progress*2.2*density).toInt()
                    view2.layoutParams = layoutParams
                }
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                //    textView.text = "Progress is: " + seek.progress + "%"
            }
        })

        plusButon.setOnClickListener {
            if(icilenToplamMiktar < 210 ) {
                layoutParams.height += defaultML*2
                icilenToplamMiktar += defaultML
                view2.layoutParams = layoutParams
            }
            seekBar.progress = (icilenToplamMiktar*0.2).roundToInt()
        }
        minusButon.setOnClickListener {
            if(icilenToplamMiktar < 210 && icilenToplamMiktar > 0) {
                layoutParams.height -= defaultML*2
                icilenToplamMiktar -= defaultML
                view2.layoutParams = layoutParams
            }
             seekBar.progress = (icilenToplamMiktar*0.2).roundToInt()
        }

        confirmButon.setOnClickListener{

           // seekBar.progress = icilenToplamMiktar
            Toast.makeText(context, icilenToplamMiktar.toString(), Toast.LENGTH_SHORT).show()
        }

        return view
    }
}