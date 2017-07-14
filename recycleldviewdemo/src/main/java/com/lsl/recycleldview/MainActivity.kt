package com.lsl.recycleldview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var leftAdapter:LeftAdapter
    lateinit var rigthAdapter:RigthAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle_left.layoutManager=LinearLayoutManager(this)
        leftAdapter= LeftAdapter()
        recycle_left.adapter=leftAdapter


        recycle_rigth.layoutManager=LinearLayoutManager(this)
    }






    class LeftAdapter :RecyclerView.Adapter<LeftHolder>(){
        override fun onBindViewHolder(holder: LeftHolder?, position: Int) {
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LeftHolder {
           return LeftHolder(itemView =LayoutInflater.from(parent?.context).inflate(R.layout.item_left,parent,false) )
        }

        override fun getItemCount()=20

    }



    class RigthAdapter : RecyclerView.Adapter<RigthHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RigthHolder {
            return RigthHolder(itemView =LayoutInflater.from(parent?.context).inflate(R.layout.item_rigth,parent,false) )
        }

        override fun onBindViewHolder(holder: RigthHolder?, position: Int) {
        }

        override fun getItemCount()=20

    }












    class LeftHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
    class RigthHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

}