

**What is Coroutines ?**
-------------------

![enter image description here](https://github.com/ahmedeltaher/Kotlin-MVP/blob/migrate-rxjava-to-coroutines/readme-images/androkotlin.png?raw=true "Kotlin logo.png")



[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-android--best--practices-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/4975)  
 [![kotlin](https://img.shields.io/badge/Kotlin-1.3-brightgreen.svg)](https://kotlinlang.org/)  [![coroutines](https://img.shields.io/badge/coroutines-asynchronous-red.svg)](https://kotlinlang.or/docs/reference/coroutines-overview.html) [![ANKO](https://img.shields.io/badge/Anko-commons-blue.svg)](https://github.com/Kotlin/anko) [![Mockk](https://img.shields.io/badge/Mockk-testing-yellow.svg)](https://mockk.io/)      [![Junit5](https://img.shields.io/badge/Junit5-testing-yellowgreen.svg)](https://junit.org/junit5/)   [![Espresso](https://img.shields.io/badge/Espresso-testing-lightgrey.svg)](https://developer.android.com/training/testing/espresso/)  [![Dagger 2](https://img.shields.io/badge/Dagger-2-orange.svg)](https://google.github.io/dagger/)  [![Kotlin-Android-Extensions ](https://img.shields.io/badge/Kotlin--Android--Extensions-plugin-red.svg)](https://kotlinlang.org/docs/tutorials/android-plugin.html) [![MVP ](https://img.shields.io/badge/Clean--Code-MVP-brightgreen.svg)](https://github.com/googlesamples/android-architecture) 
 

 **Coroutines :**
Is light wight threads for asynchronous programming, Coroutines not only open the doors to 
asynchronous programming, but also provide a wealth of other possibilities such as concurrency, actors, etc. 

----------

**Coroutines VS RXJava**
-------------------
They're different tools with different strengths. Like a tank and a cannon, they have a lot of overlap but are more or less desirable under different circumstances.
        - Coroutines Is light wight threads for asynchronous programming.
        - RX-Kotlin/RX-Java is functional reactive programming, its core pattern relay on 
        observer design pattern, so you can use it to handle user interaction with UI while you 
        still using coroutines as main core for background work.
			
**How does Coroutines concept work ?**
------------
 - Kotlin coroutine is a way of doing things asynchronously in a sequential manner. Creating a coroutine is a lot cheaper vs creating a thread.


**When I can choose Coroutines or RX-Kotlin to do some behaviour ?**
--------------------------
 - Coroutines : *When we have concurrent tasks , like you would fetch data from Remote connections 
 , database , any background processes , sure you can use RX in such cases too, but it looks like
  you use a tank to kill ant.* 
 - RX-Kotlin : *When you would to handle stream of UI actions like : user scrolling , clicks , 
 update UI upon some events .....ect .*
 

**What is the Coroutines benefits?**
-----------------------------

 - Writing an asynchronous code is sequential manner.
 - Costing of create coroutines are much cheaper to crate threads.
 - Don't be over engineered to use observable pattern, when no need to use it.
 - parent coroutine can automatically manage the life cycle of its child coroutines for you.  


**Handle Retrofit with Coroutines**
-----------------------------

![enter image description here](https://github.com/ahmedeltaher/Kotlin-MVP/blob/migrate-rxjava-to-coroutines/readme-images/8399.png "retrofit-reactivex-300x150.png")

 - Add Coroutines to your gradle file 
            ```           
           implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.1'
           implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1'
           implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.0.1'
            ```

 - Make Retrofit Calls.
		 	     	
	     ```
	     @GET("topstories/v2/home.json")
         fun  fetchNews(): Call<NewsModel>
	     ```
	     
 - With ```async``` we create new coroutine and returns its future result as an implementation of [Deferred].
 - The coroutine builder called ```launch``` allow us to start a coroutine in background and keep working in the meantime.
 - so async will run in background then return its promised result to parent coroutine which 
 created by launch. 
 - when we get a result, it is up to us to do handle the result.
```
        launch{
             try {
                val serviceResponse = async(Dispatchers.IO) { dataRepository.requestNews() }.await()
                if (serviceResponse?.code == ServiceError.SUCCESS_CODE) {
                    val newsModel = serviceResponse.data as NewsModel
                    callback.onSuccess(newsModel)
                } else {
                    callback.onFail()
                }
            } catch (e: Exception) {
                callback.onFail()
            }
        }
```

**Keep your code clean according to MVP**
-----------------------------
 - yes , RXAndroid is easy , powerful , but you should know in which MVP
          layer you will put it .
 - for observables which will emit data stream , it has to be in your
   data layer , and don't inform those observables any thing else like
   in which thread those will consume , cause it is another
   responsibility , and according to `Single responsibility principle`
   in `SOLID (object-oriented design)` , so don't break this concept by
   mixing every thing due to RXAndroid ease .


![enter image description here](https://lh3.googleusercontent.com/-C7BXAK1LhZk/WGFSXnV6UvI/AAAAAAAAHiw/7-r9dmdNyAIsjsOueZICV7PSoLtkPOEBACLcB/s0/MVP.jpg "MVP.jpg")



----------
**LICENSE**
-------------------


Copyright [2016] [Ahmed Eltaher]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
