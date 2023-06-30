## APPOINT
APPOINT is an application that can be used by organizations or individuals to allow their clients to book an appointment for several services that are offered. This application can be used for different types of businesses, in this project i used a medical institution as an examplele. This medical institution offer several services such as dental services, laboratory services etc. 

## Demo
### Registration
<img src="screenshots/welcome.png" width="250"/> <img src="screenshots/register.png" width="250"/> <img src="screenshots/profile_create.png" width="250"/>

### Home
Application use dynamic theming,  enables users to personalize their devices to align tonally with the color scheme of their personal wallpaper or through a selected color in the wallpaper picker
</br>
<img src="screenshots/home.png" width="250"/> <img src="screenshots/home_dynamic.png" width="250"/>

### Booking steps

<img src="screenshots/booking_step_1.png" width="250"/> <img src="screenshots/booking_location_search.png" width="250"/> <img src="screenshots/booking_date_time.png" width="250"/>

## Techstack
 - [Kotlin](https://developer.android.com/kotlin) - Kotlin is a programming language has first class support from Google for developing android applications
 - [Material 3](https://developer.android.com/jetpack/compose/designsystems/material3) -  latest version of Google’s open-source design system.
 - [Coroutines](https://developer.android.com/kotlin/coroutines) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
 - [Flows](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and are a type that can provide multiple values as opposed to suspend functions that return only a single value.
 - [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
 - [Accompanist](https://google.github.io/accompanist/) - A group of libraries that aim to supplement Jetpack Compose with features that are commonly required by developers but not yet available
 - [Coil](https://coil-kt.github.io/coil/compose/) - An image loading library for Android backed by Kotlin Coroutines
 - [Lottie](http://airbnb.io/lottie/#/) -  Lottie is a JSON-based animation file format that allows you to ship animations on different platforms
 - [Retrofit](https://square.github.io/retrofit) - Retrofit is type-safe REST client to consume RESTful web services
 - [Gson](https://github.com/google/gson) - JSON Parser,used to parse requests on the data layer for Entities 
 - [OkHttp](https://square.github.io/okhttp/) - An HTTP client which is used in Appoint application, its [Interceptor](https://square.github.io/okhttp/features/interceptors/) and [Authenticator](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-authenticator/) for managing JWTs
 - [Encrypted sharedpreference](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences) - To securely store JWTs
 - [sheets-compose-dialog](https://github.com/maxkeppeler/sheets-compose-dialogs) - An Android library that offers dialogs & views for various use cases. In Appoint application this was used for intergrating a highly customizable calendar.
- Jetpack libraries:
  - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
  - [Compose-ui](https://developer.android.com/jetpack/androidx/releases/compose-ui)
  - [Compose-material3](https://developer.android.com/jetpack/androidx/releases/compose-material3)
  - [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation)
