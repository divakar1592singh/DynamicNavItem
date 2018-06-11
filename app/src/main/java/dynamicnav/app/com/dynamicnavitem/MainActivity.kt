package dynamicnav.app.com.dynamicnavitem

import android.content.Intent
import android.os.Bundle
import android.widget.Button


class MainActivity : BaseDrawer2Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionNav  = findViewById(R.id.actionNav) as Button
        actionNav.setOnClickListener{goto()}


    }

    private fun goto(){
        val intent = Intent(this, BaseDrawerActivity::class.java)
        startActivity(intent)
    }
}
