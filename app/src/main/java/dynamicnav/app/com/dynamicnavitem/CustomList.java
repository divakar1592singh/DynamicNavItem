package dynamicnav.app.com.dynamicnavitem;

import android.graphics.Color;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.*;


public class CustomList {

    public static IDrawerItem[] getHeaderList(){
//        ArrayList<IDrawerItem> drawerHeaderList = new ArrayList<>();
        IDrawerItem[] iDrawerItems = new IDrawerItem[10];
        for(int i = 0; i < 5; i++){

            iDrawerItems[0] = new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home);
            iDrawerItems[1] = new CustomPrimaryDrawerItem().withBackgroundRes(R.color.accent).withName(R.string.drawer_item_free_play).withIcon(FontAwesome.Icon.faw_gamepad);
            iDrawerItems[2] = new CustomUrlPrimaryDrawerItem().withName(R.string.drawer_item_fragment_drawer).withDescription(R.string.drawer_item_fragment_drawer_desc).withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460");
            iDrawerItems[3] = new ExpandableBadgeDrawerItem().withName("Collapsable Badge").withIcon(GoogleMaterial.Icon.gmd_format_bold).withIdentifier(18).withSelectable(false).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)).withBadge("100").withSubItems(
                    getList(5)
            );
            iDrawerItems[4] = new ExpandableDrawerItem().withName("Collapsable").withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(19).withSelectable(false).withSubItems(
                    getListWithBadge(3)
            );

        }
        return iDrawerItems;
    }
    public static List<IDrawerItem> getList(int size){
        ArrayList<IDrawerItem> drawerList = new ArrayList<>();
        for(int i = 0; i < size; i++){

            drawerList.add(new SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2000+i));
        }
        return drawerList;
    }
    public static List<IDrawerItem> getListWithBadge(int size){
        ArrayList<IDrawerItem> drawerList = new ArrayList<>();
        for(int i = 0; i < size; i++){

            drawerList.add(new SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2000+i).withSelectable(true).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)).withBadge("100"));
        }
        return drawerList;
    }


}