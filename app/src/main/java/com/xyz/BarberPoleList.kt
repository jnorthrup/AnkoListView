package com.xyz

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*


/**
 * Created by jim on 10/5/2017.
 */
class BarberPoleList : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {

            listView {
                val li: MutableList<Int> = (0..10).map {
                    Random().nextInt(100)
                }.toMutableList()
                adapter = object : BaseAdapter() {
                    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
                        return with(this@listView.context) {
                            horizontalProgressBar {
                                id = Random().nextInt()
                                progress = getItem(p0) as Int
                                onClick {
                                    li[p0] = Random().nextInt(100)
                                    notifyDataSetChanged()
                                    invalidate()
                                }
                            }
                        }
                    }

                    override fun getItem(p0: Int): Any = li[p0]

                    override fun getItemId(p0: Int): Long {
                        return getView(p0, this@listView, this@listView).id.toLong()
                    }

                    override fun getCount(): Int = li.size
                }
            }
        }
    }
}