# [Model-View-ViewModel (ie MVVM)](https://github.com/ahmedeltaher/Android-MVVM-architecture)

I have reused my open source temple in this task.
**In this task you find:**
- **Splash Screen** → this page will take to ***Login screen***
- **Login Screen** → hard coded login data, this page will take you to ***Recipes List Screen***.
- **Recipes List Screen**→ coming from Github raw page by using retrofit, if you click on an item it will take you to  ***Recipe Details***
- **Recipe Details** → I have tried to make it simple title, image, description, add to favorites which is cached in Sheared Preferences .
- I have implemented some **Unit test** for the view models *LoginViewModel*, *RecipesListViewModel*, *RecipeDetailsViewModel*.
- I have implemented some **UI test** for the screens *LoginActivity*, *RecipesListActivity*, *RecipeDetailsActivity*.
---
  **As Technology Stack in this task, I'm using**
-------------------
- Kotlin
- MVVM
- LiveData
- Flow
- Coroutines
- View Biniding
- Dagger 2.x for DI.
- Retrofit 2.x for
- mockk

**Why Promoting MVVM VS MVP:**
- ViewModel has Built in LifeCycleOwerness, on the other hand Presenter not, and you have to take this responsiblty in your side.
- ViewModel doesn't have a reference for View, on the other hand Presenter still hold a reference for view, even if you made it as weakreference.
- ViewModel survive configuration changes, while it is your own responsiblities to survive the configuration changes in case of Presenter. (Saving and restoring the UI state)

**I'm Using Coroutines**
-------------------

 **Coroutines :**
Is light wight threads for asynchronous programming, Coroutines not only open the doors to asynchronous programming, but also provide a wealth of other possibilities such as concurrency, actors, etc.
**Build the Coroutines by coroutines builders in ViewModel, under the ViewModel Scope, or under specific scope+job**

----------
**Why Coroutines?**
-----------------------------
 - Coroutines Is light wight threads for asynchronous programming.
 - Writing an asynchronous code is sequential manner.
 - Costing of create coroutines are much cheaper to crate threads.
 - parent coroutine can automatically manage the life cycle of its child coroutines for you.


**Why Kotlin Flow?**
------------
 - Because it is cold streams, it will emit data when there’s an active observer registered to it.
 - we can map it to live date by its extension method **_asLiveData_()**

**Keep your code clean according to MVVM**
-----------------------------
 - Data layer is Repository pattern.
 - scince it is simple task there is not a lot logic I made only ViewModels, if the logic is complex we can and use case layer.
 - Flow is easy, powerful, but you should know how to use it.
 - Flow which will emit data, it has to be in data layer, by injecting the Dispatchers will be easier in testing
 - Livedata which will emit UI binding events, it has to be in your ViewModel Layer.
 - Observers in UI Consume and react to live data values and bind it.

  ![mvvm2](https://user-images.githubusercontent.com/1812129/68319008-e9d39d00-00bd-11ea-9245-ebedd2a2c067.png)

