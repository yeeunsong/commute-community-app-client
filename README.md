# commute-community-app
COMCOM is a Commute and Community feature **Android app with NodeJS and MySQL backend** for office workers.   
The features include: 
- Login and making an account   
- Commute check via app based on distance with workplace  
- Community interface - writing and deleting posts   
   
   
## Android Explanation
### 1. Login and Making an Account
By creating MySQL userinfo table, COMCOM can save new created accounts and verify login.   
Also, COMCOM integrated Google login SDK for SNS login.  
Features include:   
* Creating an account  
* Verify Login
* Login via Google login SDK 

Login via Google SDK    
<img src="https://user-images.githubusercontent.com/49232148/125485309-ce95a76a-6d98-4fe9-9298-c5333401c5ef.gif" width="400" height="800">

Custom user login   
<img src="https://user-images.githubusercontent.com/49232148/125485295-9d43a8af-a713-4c84-a80e-0a2e3723dfe3.gif" width="400" height="800">

### 2. Commute Checking
Page that enables to check commute via app.    
The user can press the commute button if the user is within 100m distance from the company.    
Features include: 
* Integration with **Google Map API**   
* Getting and updating current location   
* Calculate the distance of the user and the company   
* Calculate hours of duty using the commute button 
* Send commute status to database on pressing commute button
<img src="https://user-images.githubusercontent.com/49232148/125485316-fd64e1cf-70e4-4011-9e3d-748e375c8766.gif" width="400" height="800">
   

### 3. Community Interface  
Office worker app community. Users can post and share their postings. 
Features include:  
* Writing posts  
* Deleting posts
* Writing Comments 
   

     
      
## Server Explanation  
Utilized NodeJS, Express, and MySQL.   
   
Nodejs Code link: [commute-community-app-server-github](https://github.com/yeeunsong/commute-community-app-server)   
MySQL database schema: 
   
![image](https://user-images.githubusercontent.com/49232148/125463808-7360b3ab-7bc8-498f-a896-eaaa8fc63beb.png) 




   
   

## Contacts
Contributors   
- Yeeun Song, yeeunsong1019@gmail.com
- Janghyun Gim, big01ad@kaist.ac.kr  
