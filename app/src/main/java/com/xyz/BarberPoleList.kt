package com.xyz

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import xyz.com.xyz.R
import java.util.*


class BarberPoleList : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        relativeLayout {
            val outOfOrderButton: Button = button {
                id = Random().nextInt()//required for relativeLayout "above"
                text = context.getString(R.string.buttonText)
            }.lparams { alignParentBottom() }
            val lv = listView().lparams {
                alignParentTop()
                above(outOfOrderButton)
            }
            val poleAd = PoleAdapter(lv)
            lv.adapter = poleAd

            outOfOrderButton.onClick {
                poleAd += java.util.Random().nextInt(100)
            }
        }
    }
}

class PoleAdapter(private val listView: ListView) : BaseAdapter() {
    private val li = MutableList(10, { Random().nextInt(100) })

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View = with(listView.context) {
        horizontalProgressBar {
            id = java.util.Random().nextInt()
            progress = getItem(p0) as Int
            onClick {
                li[p0] = java.util.Random().nextInt(100)
                notifyDataSetChanged()
                invalidate()
            }
        }
    }

    override fun getItem(p0: Int): Any = li[p0]

    override fun getItemId(p0: Int): Long = 0

    override fun getCount(): Int = li.size
    operator fun plusAssign(nextInt: Int) {
        li += nextInt
        notifyDataSetChanged()
        listView.invalidate()
    }
}

