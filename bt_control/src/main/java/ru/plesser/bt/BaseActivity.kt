package ru.plesser.bt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        //initRcView()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeHolder, DeviceListFragment())
            .commit()
    }

    private fun initRcView(){
        val rcView = findViewById<RecyclerView>(R.id.rcViewPaired)
        rcView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter()
        rcView.adapter = adapter
        adapter.submitList(fakeList())
    }

    private fun fakeList(): List<ListItem>{
        var list = ArrayList<ListItem>()
        for (i in 0 until 10){
            list.add(ListItem("Device $i", "Mac ${i*1056+56}"))
        }
        return list
    }
}