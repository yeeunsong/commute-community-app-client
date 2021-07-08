# Tab-Android-App

Tab-Android-App is an android java app with 4 tabs using ViewPager. Used java and android studio.  
The pages include:  
- **TAB**: tab for switching to main, contacts, album, commute page   
- **MAIN**: tap to display area, current time, temperature, weather and more
- **CONTACTS**: includes save, delete, and bookmark phone address features
- **ALBUM**: gallery page with album folders
- **COMMUTE**: enables to check commute via app 



## Page Explanation
### 0. Tab
By connecting ViewPager2 and Tablayer, the fragment can be switched.        
Connets main, contacts, album, and commute page.   


### 1. MAIN   
A page that displays the region, current time, temperature, weather, and etc.     
* Display system time on main fragment
* Display time and weather information using OpenWeatherMap API and Volley
<img src="https://user-images.githubusercontent.com/49232148/124562213-bab89480-de79-11eb-90e3-9252ef1eb90b.gif" width="400" height="800">


### 2. CONTACTS
Phone address page with save, delete, and bookmark features.    
* Store names and contact information using SQLite database
* List contact information using RecyclerView
* Implemented Add, Wish, and Delete buttons
* Press the phone icon to switch to dial the number
* Implemented swipe refresh function

<p float="left">
<img src="https://user-images.githubusercontent.com/49232148/124561484-e8510e00-de78-11eb-8dbb-6998364ce800.jpg" width="400" height="800">
<img src="https://user-images.githubusercontent.com/49232148/124561489-e9823b00-de78-11eb-933d-0d9e9f537cd2.jpg" width="400" height="800">
</p>


### 3. ALBUM   
Album page with multiple folders
* Transition to each album activity from album fragment using Intent
* Utilized GridLayout, CardView, and ImageView for image display
* Includes album and image display page
<img src="https://user-images.githubusercontent.com/49232148/124562202-b7250d80-de79-11eb-8961-6003b7090923.gif" width="400" height="800">

### 4. COMMUTE 
Page that enables to check commute via app.    
The user can press the commute button if the user is within 100m distance from the company.    
Features include: 
* Integration with **Google Map API**   
* Getting and updating current location   
* Calculate the distance of the user and the company   
* Calculate hours of duty using the commute button   
<img src="https://user-images.githubusercontent.com/49232148/124562208-b9876780-de79-11eb-86fc-187c08083006.gif" width="400" height="800">



## Contacts
Contributors
- Yeeun Song, yeeunsong1019@gmail.com
- Taewoo Kim, rlaxodntttt@kaist.ac.kr

