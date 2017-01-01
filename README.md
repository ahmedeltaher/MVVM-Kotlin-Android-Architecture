

**What is ReactiveX ?**
-------------------

![enter image description here](https://lh3.googleusercontent.com/-i2XiOiLr-u4/WGDnM23c8cI/AAAAAAAAHho/MnkItYDyelofBkilkIM8x99tH__EtHP5gCLcB/s0/Reactive+logo.png "Reactive logo.png")


[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-android--best--practices-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/4975)

======
 **Reactive :**
Reactive programming is a programming paradigm oriented around data flows and the propagation of change. 
 Reactive programming is programming with asynchronous data streams.
 
 **ReactiveX :** 
	 - Is a library for composing asynchronous and event-based programs by using observable sequences.
 It extends the observer pattern to support sequences of data and/or events and adds operators that allow you to compose sequences together declaratively while abstracting away concerns about things like low-level threading, synchronization, thread-safety, concurrent data structures, and non-blocking I/O.
	 - Rx* library family is widely available for many languages and platforms (**.NET**, **Java** , **Scala**, **Clojure**, **JavaScript**, **Ruby**, **Python**, **C++**, **Objective-C/Cocoa**, **Groovy**, etc).

----------

**What is RXAndroid ?**
-------------------
 - Extended version from RX-Java to Android .
 - RXAndroid provides many classes which facilities working with android , like 
			- **AndroidSchedulers**  for facilitating managing multi-threading.
			- **AndroidObservable** for facilitating dealing within the Android lifecycle.
			- **ViewObservable** & **WidgetObservable** for facilitating binding views , user clicks ... ect.
			
**How RX concept works ?**
------------
 - An Observable performs some action, and publishes the result.
 - An Observer waits and watches the Observable, and reacts whenever the
   Observable publishes results.
 - There are three different changes that can occur on an Observable
   that the Observer reacts to.
   These are:
	 - Publishing a value
	 - Throwing an error 
	 - Completed publishing all values

![enter image description here](https://lh3.googleusercontent.com/-x2b7sqpd2Sc/WGDq4bCkp5I/AAAAAAAAHiI/lhgC2hiEbdgSPlYsA2-VdVxAeJxqzf-egCLcB/s0/legend.png "legend.png")

![enter image description here](https://lh3.googleusercontent.com/-vaRTC6UYzuw/WGBj79pJEhI/AAAAAAAAHg0/LeEE3msb9JE_RFtfw34y8yy9EqPOo5KuACLcB/s0/gif-react22.gif "gif-react22.gif")

**When I can choose RXAndroid to do some behaviour ?**
--------------------------
 - *When we have concurrent tasks , like you would fetch data from Remote connections , database , any background processes .*
 - *When you would to handle stream of UI actions like : user scrolling , clicks , update UI upon some events .....ect .*
 

**What is the RXAndroid benefits?**
-----------------------------
RXAndroid will provide you to deal with below problems in one fell swoop:

 - No standard mechanism to recover from errors.
 - Lack of control over thread scheduling (unless you like to dig deep).
 - No obvious way to compose asynchronous operations.
 - No obvious and hassle-free way of attaching to Context.


**Handle Retrofit with RXAndroid**
-----------------------------

![enter image description here](https://lh3.googleusercontent.com/d2slx7bI7Ivykea1Umfo2mMvZVTnO19ifeuJidyZJB6L3u7rlhwlBtp7tsZgrshWiToqz4pW=s0 "retrofit-reactivex-300x150.png")

 - Add RX to your gradle file 

        compile 'io.reactivex:rxandroid:1.2.1'
        compile 'io.reactivex:rxjava:1.1.6'
        compile 'com.squareup.retrofit2:adapter-rxjava:2.0.2'

 - Replace Retrofit Call with Observable .
		 **From**
		 ```@GET("topstories/v2/home.json")
		 Call<NewsModel> fetchNews();```
         **to**	     	
	     ```@GET("topstories/v2/home.json")
     Observable<Response<NewsModel>> fetchNews();```

 - Create  `Observable` 
	 10. Inform your Observable to emit data in background thread with `subscribeOn(Schedulers.io())` .
	 11.  Inform your Observable to propagate its stream of data to Main Thread with`observeOn(AndroidSchedulers.mainThread())` .
	 12. Register your subscriber to current Observable with ` subscribe(mSubscriber)`
	 ```
Observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(mSubscriber);
```


	 13. create subscriber
	 
	 
	 ```
	 Subscriber mSubscriber=new Subscriber<Response<NewsModel>>() {
            @Override
            public void onCompleted() { //handle what should you do after Observable complete its emission , like dismiss dialog , build UI .
                 }
            @Override
            public void onError(Throwable e) {
                //Handle your error
                 }
            @Override
            public void onNext(Response<NewsModel> newsModelResponse) {
                // handle what should you do after receive each emission from observer , like update progress .... ect .
                }
        };
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
