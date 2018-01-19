# productlist_sunil

This repository contains a assignment of good Android Architecture.

Application Detail: 
This application has 4 screens.
Aim of app is to give a user feature to create a basic product list. User can update & delete product in product list.
App follows MVVM project architecture.


Libraries used in the project

•	Design : AppCompat, CardView, RecyclerView ,DesignLibrary
•	Reactive Programming : RxJava2 & RxAndroid
•	Image Loading: Glide
•	Json parsing: Gson
•	Depending Injection: Dagger2
•	View Injection: Butterknife
•	Sqlite ORM: Room
•	Android Architecture: MVVM
•	Testing Tools: Robolectric, Junit, Mockito, Espresso



The app has following packages:
1.	dao: It contains a interface for database queries.
2.	db: It contatins a abstract databse class
3.	entity: Pojo class for product list.
4.	repository: Interface & its implemenation for database async interaction  .
5.	injection: Dependency providing classes using Dagger2.
6.	ui.product: View classes along with their corresponding ViewModel.
7.	utils: Utility classes.
Classes have been designed in such a way that it could be inherited and maximize the code reuse.
