# StarFriends
Coding Challenge: Make a 2 activity android app (kotlin) with the following requirements:
- Purpose: demo ability to write thoughtful, reusable, and scalable code.
- Purpose: ability to read and understand API documentation. 
- Judging Criteria: general usability, efficiency, and architecture.


# Main Activity:

- Search Bar at top

- Recycler view

- Name and “Favorite” Status (See Below)

- As user types in the search bar, display live search results to the user inside the recycler view.

- Free Star Wars API at https://swapi.co/.

- Tap person in recycler view to open details activity

# Details Activity

- Show person's name, height, mass, hair color, skin color, eye color, birth year, and gender

- "Favorite" button. 

- Characters that are faved should be saved to shared preferences. 

- You should be able to see that a character is favorited on the recycler view cells (Main activity) and on the detail activity. 

- You should be able to navigate back and forth between these 2 activities. 


# DEV ENVIRONMENT
- Android Studio 3.5.3
- Min: Android 5.0 Lollipop (API 21) 
- Max: Android 10 (API 29)
- Microsoft Windows 10 Home. Version: 10.0.17763
- VR Ready Laptop: MSI, GS65 Stealth 9SD, X64-based PC

# TEST DEVICES
- Emulator: Pixel XL, Android 10, API 29
- Physical Device: Pixel 3, Android 9.0 Lollipop, API 28

# SCREENSHOTS
<p align="center">
  <img width="270px" height="480px" src="https://github.com/monigarr/StarFriends/blob/master/Screenshot_20191231-223306.png">&nbsp;&nbsp;&nbsp;<img width="270px" height="480px" src="https://github.com/monigarr/StarFriends/blob/master/Screenshot_20191231-223329.png">&nbsp;&nbsp;&nbsp;<img width="270px" height="480px" src="https://github.com/monigarr/StarFriends/blob/master/Screenshot_20191231-223315.png">
</p>

# REFERENCES
MVVM is the project goal and most of my career has been maintaining massive mvp, mvc architecture projects. 
These links are some of the most helpful docs I found online for building this project.
- MVVM  https://www.codementor.io/@khashayaryadmand/mvvm-kotlin-android-wgo5vqnyp
- Star Wars API https://swapi.co/documentation#base
- 

# NOTES WIP
THESE WILL BE REMOVED WHEN I COMPLETE SHAREDPREF FAVE FEATURES & VERIFYING ENTIRE PROJECT IS MVVM and not mvp, mvc
https://blog.usejournal.com/how-to-use-viewmodel-livedata-pagination-searchview-room-database-5b8bc3631ac7

- Search Filter
 - https://camposha.info/kotlin-android-search-filter-listview-with-searchview/

- https://developer.android.com/reference/android/text/TextWatcher

- https://developer.android.com/reference/android/widget/SearchView.OnQueryTextListener


- https://www.soinside.com/question/rRqkxYcZMxDvUtEZC24mxM

- ADD TEXT CHANGE LISTENER
 - https://www.programcreek.com/java-api-examples/?class=android.support.design.widget.TextInputEditText&method=addTextChangedListener

- CONSTRAINT LAYOUT
 - https://developer.android.com/training/constraint-layout

- IMPROVE LAYOUT PERFORMANCE
 - https://developer.android.com/training/improving-layouts

- CUSTOM BUTTON ON RECYCLERVIEW
 - https://developer.android.com/guide/topics/ui/controls/button

- ANIMATIONS & TRANSITIONS
 - https://developer.android.com/training/animation

- STAR WARS COLOR PALETTE
 - https://www.google.com/search?biw=1536&bih=722&tbm=isch&sxsrf=ACYBGNQn-E80zQ-1WyPWwLqrhnTx3IN_IA%3A1577423978596&sa=1&ei=apQFXo2DJJDJtQa4w7XwAQ&q=star+wars+color+palette&oq=star+wars+color+palette&gs_l=img.3..0l2.2222.4601..4758...0.0..0.130.1483.10j6......0....1..gws-wiz-img.......35i39j0i67j0i131i67j0i131j0i5i30j0i8i30j0i24.J7cYkLHQM84&ved=0ahUKEwiN4J2fitXmAhWQZM0KHbhhDR4Q4dUDCAc&uact=5


- SHARED PREFERENCES
 - https://github.com/AchmadHafid/SimplePref

 - https://blog.mindorks.com/implementing-android-jetpack-preferences

 - https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/
 - https://github.com/MFaisalHyder/RecylerView-And-SharedPreferences/blob/master/src/com/faisal/easyprounounce/adapter/RecyclerAdapter.java

 - https://android.jlelse.eu/android-observe-shared-preferences-as-livedata-27e25e7d3172

- ANDROID HELPERS
 - https://android-arsenal.com/


- Using the MVVM with repository pattern (your data source class)  do something like the following:

1.) Create a ViewModel which holds an instance of your data source/repository class.

2.) From your view (activity/fragment), obtain an instance of PreferenceManager and fetch the settings you need.

3.) Pass them along to your viewModel which then passes it along to your data source/repository.

4.) Do whatever you need in your data source class with those settings

5.) ... inside your view class
  - val pref = PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_KEY_NAME, null)
  - viewModel.yourMethod(pref)

  - ... inside your viewmodel class
  - fun yourMethod(pref: String?) {repository.doSomething(pref)}

  - ... inside your repository/data source class
  - fun doSomething(pref: String?) {// whatever you need to do with this pref. // e.g. api call api.doMethod(pref)
  - }

