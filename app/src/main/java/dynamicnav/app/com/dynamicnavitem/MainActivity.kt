package dynamicnav.app.com.dynamicnavitem

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        actionNav.setOnClickListener{goto()}


        SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2002)

        ExpandableDrawerItem().withName("Collapsable").withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(19).withSelectable(false).withSubItems(
                CustomList.getList()
        )

/*
ExpandableDrawerItem().withName("Collapsable").withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(19).withSelectable(false).withSubItems(
                SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2002),
                SecondaryDrawerItem().withName("CollapsableItem 2").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2003)
        )

*/


    }

    private fun goto(){
        val intent = Intent(this, BaseDrawerActivity::class.java)
        startActivity(intent)
    }
}
