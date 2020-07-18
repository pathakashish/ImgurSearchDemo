# ImgurSearchDemo
This repo is for the assignment that was given by one of the companies as a part of their interview process.

## TODO
  * Fetch search results for the search string from https://api.imgur.com/3/gallery/search/1?q=<search_string>.
  * Provide two-level cache for network requests(disk cache and in memory cache).
  * Fetch images in memory efficient way with two-level of cache.
  * Provide comments functionality.
      * Maintain database for keeping track of comments locally.

## Assumptions
  * App only supports static images(.jpeg) and animated GIFs(.gif).
  * App does not support playing any media(audio/video).
