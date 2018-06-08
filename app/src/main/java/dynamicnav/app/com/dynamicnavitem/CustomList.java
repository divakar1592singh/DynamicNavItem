package dynamicnav.app.com.dynamicnavitem;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.*;


public class CustomList {

    public static IDrawerItem[] getHeaderList(){
        ArrayList<IDrawerItem> drawerHeaderList = new ArrayList<>();
        for(int i = 0; i < 5; i++){

            drawerHeaderList.add(new ExpandableDrawerItem().withName("Collapsable")
                    .withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(19)
                    .withSelectable(false).withSubItems(getList(3)));
        }
        return drawerHeaderList.toArray();
    }
    public static List<IDrawerItem> getList(int size){
        ArrayList<IDrawerItem> drawerList = new ArrayList<>();
        for(int i = 0; i < size; i++){

            drawerList.add(new SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2000+i));
        }
        return drawerList;
    }



}
