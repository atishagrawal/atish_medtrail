# Atish MedTrail

Atish MedTrail is a test app, assigned as a task to evaluate my coding. The app must fetch searched images from Flickr API and display the Images in a RecyclerView.

## Tasks:
 - Create an Android application which displays images based on a keyword based search.
 - There are several open source image APIs available, one of which is Flickr API which has a comprehensive image library. You need to consume the following API to search and display images. https://www.flickr.com/services/api/flickr.photos.search.htm 
 - The API results in search results for a keyword. Refer to documentation on this page to get the image urls: https://www.flickr.com/services/api/misc.urls.html
 - You can use the following API Key: 3189212285dcb4cf5b2f044edcb0544e
 - The application is a single screen application with a search bar and search glass icon and a grid of images as the search results.
 - By default the screen should display the message “Search for images”
 - When the user enters text in search bar and presses search glass icon, fetch the search results and load the images in the form of a grid. You can use image loading libraries such as Glide, Fresco, etc. to load the images.
 - Pagination should be supported.
 - We know making a great-looking UI takes a lot of time. Your UI should be functional and responsive, but is not expected to be attractive. You will be judged on the quality of your code, not the style of your UI.
 - You must be able to handle configuration, orientation changes, and process death, by properly implementing onSaveInstanceState without loading the weather data over the internet again. The app must adapt correctly and not prevent rotation to landscape.
 - The app should not crash under any reasonable circumstances, but robust error handling is not necessary. Logging exceptions is sufficient.
 - Consider what would happen if the user is on a slow or unavailable network.
 - Feel free to use any public third-party libraries, and write in Java or Kotlin
 - Once finished, please zip up the folder and send it back to us. (Don’t delete the .git directory please)--

# Implementation

### MVVM
  - MVVM is one of the architectural patterns which enhances separation of concerns, and it allows separating the user interface logic from the business logic. Its target is to achieve Keeping UI code free and straightforward of app logic to make it easier to manage.

### Retrofit
  - Retrofit is a type-safe REST client for Android, Java and Kotlin developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp.


### LiveData
  - A part of Android Jetpack
  - LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

### Room Library (Database)
  - Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

License
----
MIT