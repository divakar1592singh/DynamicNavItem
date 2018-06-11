package dynamicnav.app.com.dynamicnavitem

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.FrameLayout
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IProfile


open class BaseDrawer2Activity : AppCompatActivity() {

    private val PROFILE_SETTING = 1

    //save our header or result
    private lateinit var headerResult: AccountHeader
    private lateinit var result: Drawer

    private lateinit var profile: IProfile<*>
    private lateinit var profile2: IProfile<*>
    private lateinit var profile3: IProfile<*>
    private lateinit var profile4: IProfile<*>
    private lateinit var profile5: IProfile<*>

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)

        // Handle Toolbar
        val rootView : View = LayoutInflater.from(this).inflate(R.layout.activity_base_drawer2, null);
        val activityContainer = rootView.findViewById(R.id.activity_content) as FrameLayout
        layoutInflater.inflate(layoutResID, activityContainer, true)

        super.setContentView(rootView)
        val toolbar = rootView.findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(R.string.drawer_item_advanced_drawer)

        // Create a few sample profile
        profile = ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(resources.getDrawable(R.drawable.profile))
        profile2 = ProfileDrawerItem().withName("Max Muster").withEmail("max.mustermann@gmail.com").withIcon(resources.getDrawable(R.drawable.profile2)).withIdentifier(2)
        profile3 = ProfileDrawerItem().withName("Felix House").withEmail("felix.house@gmail.com").withIcon(resources.getDrawable(R.drawable.profile3))
        profile4 = ProfileDrawerItem().withName("Mr. X").withEmail("mister.x.super@gmail.com").withIcon(resources.getDrawable(R.drawable.profile4)).withIdentifier(4)
        profile5 = ProfileDrawerItem().withName("Batman").withEmail("batman@gmail.com").withIcon(resources.getDrawable(R.drawable.profile5))

        // Create the AccountHeader
//        buildHeader(false, savedInstanceState)
        buildHeader(false)

        //Create the drawer
        result = DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(

                        *CustomList.getHeaderList()
                ) // add the items we want to use with our Drawer
                .withOnDrawerNavigationListener {
                    //this method is only called if the Arrow icon is shown. The hamburger is automatically managed by the MaterialDrawer
                    //if the back arrow is shown. close the activity
                    this.finish()
                    //return true if we have consumed the event
                    true
                }
                .addStickyDrawerItems(
                        SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(10),
                        SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github)
                )
//                .withSavedInstance(savedInstanceState)
                .build()


    }

    private fun buildHeader(compact: Boolean) {
        // Create the AccountHeader
        headerResult = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withCompactStyle(compact)
                .addProfiles(
                        profile,
                        profile2,
                        profile3,
                        profile4,
                        profile5,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBar().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(PROFILE_SETTING.toLong()),
                        ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withOnAccountHeaderListener(AccountHeader.OnAccountHeaderListener { view, profile, current ->
                    //sample usage of the onProfileChanged listener
                    //if the clicked item has the identifier 1 add a new profile ;)
                    if (profile is IDrawerItem<*, *> && (profile as IDrawerItem<*, *>).identifier == PROFILE_SETTING.toLong()) {
                        val newProfile = ProfileDrawerItem().withNameShown(true).withName("Batman").withEmail("batman@gmail.com").withIcon(resources.getDrawable(R.drawable.profile5))
                        if (headerResult.getProfiles() != null) {
                            //we know that there are 2 setting elements. set the new profile above them ;)
                            headerResult.addProfile(newProfile, headerResult.getProfiles().size - 2)
                        } else {
                            headerResult.addProfiles(newProfile)
                        }
                    }

                    //false if you have not consumed the event and it should close the drawer
                    false
                })
//                .withSavedInstance(savedInstanceState)
                .build()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.menu_1 -> {
                //update the profile2 and set a new image.
                profile2.withIcon(IconicsDrawable(this, GoogleMaterial.Icon.gmd_android).backgroundColorRes(R.color.accent).sizeDp(48).paddingDp(4))
                headerResult.updateProfileByIdentifier(profile2)
                return true
            }
            R.id.menu_2 -> {
                //show the arrow icon
                result.getActionBarDrawerToggle().isDrawerIndicatorEnabled = false
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                return true
            }
            R.id.menu_3 -> {
                //show the hamburger icon
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                result.getActionBarDrawerToggle().isDrawerIndicatorEnabled = true
                return true
            }
            R.id.menu_4 -> {
                //we want to replace our current header with a compact header
                //build the new compact header
                buildHeader(true)
                //set the view to the result
                result.setHeader(headerResult.getView())
                //set the drawer to the header (so it will manage the profile list correctly)
                headerResult.setDrawer(result)
                return true
            }
            R.id.menu_5 -> {
                //we want to replace our current header with a normal header
                //build the new compact header
                buildHeader(false)
                //set the view to the result
                result.setHeader(headerResult.getView())
                //set the drawer to the header (so it will manage the profile list correctly)
                headerResult.setDrawer(result)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        var outState = outState
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState)
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer()
        } else {
            super.onBackPressed()
        }
    }


}
