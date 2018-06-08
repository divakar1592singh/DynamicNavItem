package dynamicnav.app.com.dynamicnavitem;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

public class CustomList {

    public static List<IDrawerItem> getList(){
        ArrayList<IDrawerItem> drawerList = new ArrayList<>();
        for(int i = 0; i < 5; i++){

            drawerList.add(new SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2000+i));
        }
        return drawerList;
    }

}
