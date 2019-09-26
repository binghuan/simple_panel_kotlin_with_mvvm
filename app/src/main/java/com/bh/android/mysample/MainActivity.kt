package com.bh.android.mysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bh.android.mysample.ui.main.RoutingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RoutingFragment.newInstance())
                .commitNow()
        }
    }
}
