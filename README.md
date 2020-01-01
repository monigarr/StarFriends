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
![Main Activity](https://github.com/monigarr/StarFriends/blob/master/Screenshot_20191231-223306.png)
![Main Activity Search Filter](https://github.com/monigarr/StarFriends/blob/master/Screenshot_20191231-223329.png)
![Details Activity](https://github.com/monigarr/StarFriends/blob/master/Screenshot_20191231-223315.png)
![Verify Faves added to Shared Prefs]()

# REFERENCES
MVVM is the project goal and most of my career has been maintaining massive mvp, mvc architecture projects. 
These links are some of the most helpful docs I found online for building this project.
- MVVM  https://www.codementor.io/@khashayaryadmand/mvvm-kotlin-android-wgo5vqnyp
