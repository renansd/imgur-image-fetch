# Imgur Image Fetch #

This application fetches cat images from Imgur and displays them in a recycler view with a grid layout of 4 columns.

The networking code was created in its own module, separate from the files that constitute the interface portion of the app.

To implement this application was used, among other things:

* Dependency injection with Koin;
* HTTPS requests with Retrofit;
* API result manipulation with RxJava;
* Image downloading and manipulation with Glide.

A custom RecyclerViewAdapter was create, designed to be useful in pretty much any case where a RecyclerView would be used, with generic types to manage any kind of item displayed in its cells. The specific layout management can be performed in a class that extends ViewHolderBind and such class can be defined in the adapter constructor.