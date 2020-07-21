# ImgurSearchDemo
This repo is for the assignment that was given by one of the companies as a part of their interview process.

## TODO
  * ~~Fetch search results for the search string from https://api.imgur.com/3/gallery/search/1?q=<search_string>.~~
  * ~~Provide two-level cache for network requests(disk cache and in memory cache).~~
  * ~~Fetch images in memory efficient way with two-level of cache.~~
  * ~~Show static images as well as animanated GIFs.~~
  * Provide comments functionality.
      * Maintain database for keeping track of comments locally.

## Assumptions
  * App only supports static images(.jpeg) and animated GIFs(.gif).
  * App does not support playing any media(audio/video).
  * Since **Data** has images, when the title is not available for an image, app uses **Data**'s title instead. This may result in multiple images showing the same title(as specified by parent **Data**'s title).

## Implementation
 * App **handles** network **errors**, API errors gracefully and does **not have ANRs**.
 * Supports **screen rotation**.
 * Used **MVP architecture** pattern.
 * Code is **seld explanatory**. Though there are **comments** added wherever required.
 * Used **RxJava**(and RxAndroid bindings) to simplify key **250ms debounce** for **search** field.
 * App is optimized for both **phone and tablet** using **master details** pattern
 * Used **ConstraintLayout**.
 * Used **Java** for writing **unit tests** and **instrumentation tests** to demonstrate Java skills.
 * Used **Kotlin** for writing **business logic** as well as **UI**.
 * Used the **Hilt dependency injection**.
 * Used **mockito** for unit testing presenters.
 * Used **Retrofit** for making API calls and **Moshi** adapters to parse **json** response
