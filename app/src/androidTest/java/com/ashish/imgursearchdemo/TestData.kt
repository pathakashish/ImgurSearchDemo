package com.ashish.imgursearchdemo

import androidx.test.platform.app.InstrumentationRegistry
import com.ashish.imgursearchdemo.di.ApplicationContextModule
import com.ashish.imgursearchdemo.di.ImgurApiModule
import com.ashish.imgursearchdemo.di.OkHttpModule
import com.ashish.imgursearchdemo.di.RetrofitModule
import com.ashish.imgursearchdemo.model.Data
import com.ashish.imgursearchdemo.model.Image
import com.ashish.imgursearchdemo.model.SearchResult
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class TestData {
    companion object {

        fun getDispatcher() =
            object : Dispatcher() {
                @Throws(InterruptedException::class)
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return when (request.path) {
                        "/3/gallery/search/0?q=test" -> {
                            MockResponse()
                                .setResponseCode(HttpURLConnection.HTTP_OK)
                                .addHeader("content-type", "application/json")
                                .setBody(TestData.getResponseString())
                        }
                        // TODO, if you need other unit test for other methods
                        else -> MockResponse().setResponseCode(404)
                    }
                }
            }

        fun getImgurApi(mockWebServer: MockWebServer) = ImgurApiModule.provideImgurApi(
            RetrofitModule.provideRetrofit(
                OkHttpModule.provideAuthInterceptorOkHttpClient(
                    OkHttpModule.provideAuthInterceptor(),
                    OkHttpModule.provideHttpLoggingInterceptor(),
                    ApplicationContextModule.provideCacheFile(InstrumentationRegistry.getInstrumentation().targetContext)
                ), RetrofitModule.provideMoshiConverterFactory(),
                mockWebServer.url("/")
            )
        )

        fun getExpectedSearchResult(): SearchResult {
            return SearchResult(
                data = listOf(
                    getDataWithImages(),
                    getDataWithGif(),
                    getDataWithNonGif(),
                    getDataWithStaticJpeg()
                ),
                success = true, status = 200
            )
        }

        fun getDataWithNonGif() = Data(
            id = "aYmcqEH",
            title = "Finally out of lockdown and visited one of my favourite places",
            type = "video/mp4",
            animated = true,
            link = "https://imgur.com/a/aYmcqEH",
            images = null
        )

        fun getDataWithGif() = Data(
            id = "lEBF1kl",
            title = "51 years ago...",
            type = "image/gif",
            animated = true,
            link = "https://imgur.com/a/lEBF1kl",
            images = null
        )

        fun getDataWithStaticJpeg() = Data(
            id = "rfV0ljM",
            title = "San Jose, CA to Portland, OR",
            type = "image/jpeg",
            animated = false,
            link = "https://imgur.com/a/rfV0ljM",
            images = null
        )

        fun getDataWithImages() = Data(
            id = "78mBcjV",
            title = "Road trip photos",
            type = null,
            animated = false,
            link = "https://imgur.com/a/78mBcjV",
            images = listOf(
                getStaticJpegImage(),
                getGifImage(),
                getVideoImage()
            )
        )

        fun getVideoImage() = Image(
            id = "aWj3AsE",
            title = null,
            type = "video/mp4",
            animated = true,
            link = "https://i.imgur.com/aWj3AsE.jpg"
        )

        fun getGifImage() = Image(
            id = "c1jnEjM",
            title = null,
            type = "image/gif",
            animated = true,
            link = "https://i.imgur.com/c1jnEjM.jpg"
        )

        fun getStaticJpegImage() = Image(
            id = "6MU96OI",
            title = null,
            type = "image/jpeg",
            animated = false,
            link = "https://i.imgur.com/6MU96OI.jpg"
        )

        fun getResponseString() =
            """
            {
              "data": [
                {
                  "id": "78mBcjV",
                  "title": "Road trip photos",
                  "description": null,
                  "datetime": 1595179505,
                  "cover": "6MU96OI",
                  "cover_width": 3000,
                  "cover_height": 1688,
                  "account_url": "Accurse",
                  "account_id": 96613993,
                  "privacy": "hidden",
                  "layout": "blog",
                  "views": 761,
                  "link": "https://imgur.com/a/78mBcjV",
                  "ups": 18,
                  "downs": 1,
                  "points": 17,
                  "score": 17,
                  "is_album": true,
                  "vote": null,
                  "favorite": false,
                  "nsfw": false,
                  "section": "",
                  "comment_count": 9,
                  "favorite_count": 2,
                  "topic": "No Topic",
                  "topic_id": 29,
                  "images_count": 11,
                  "in_gallery": true,
                  "is_ad": false,
                  "tags": [
                    {
                      "name": "art",
                      "display_name": "art",
                      "followers": 1021082,
                      "total_items": 256943,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "07cWFIo",
                      "thumbnail_hash": null,
                      "accent": "9DCEC9",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "creative creations",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "america",
                      "display_name": "america",
                      "followers": 3008,
                      "total_items": 7888,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "f8B0kEw",
                      "thumbnail_hash": null,
                      "accent": "5B62A5",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "travel",
                      "display_name": "travel",
                      "followers": 1334174,
                      "total_items": 20822,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "HEQs6u2",
                      "thumbnail_hash": null,
                      "accent": "4880B0",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "embrace wanderlust",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "landscape",
                      "display_name": "landscape",
                      "followers": 5085,
                      "total_items": 8591,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "cYVFdTy",
                      "thumbnail_hash": null,
                      "accent": null,
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    }
                  ],
                  "ad_type": 0,
                  "ad_url": "",
                  "in_most_viral": false,
                  "include_album_ads": false,
                  "images": [
                    {
                      "id": "6MU96OI",
                      "title": null,
                      "description": null,
                      "datetime": 1595179373,
                      "type": "image/jpeg",
                      "animated": false,
                      "width": 3000,
                      "height": 1688,
                      "size": 834884,
                      "views": 695,
                      "bandwidth": 580244380,
                      "vote": null,
                      "favorite": false,
                      "nsfw": null,
                      "section": null,
                      "account_url": null,
                      "account_id": null,
                      "is_ad": false,
                      "in_most_viral": false,
                      "has_sound": false,
                      "tags": [],
                      "ad_type": 0,
                      "ad_url": "",
                      "edited": "0",
                      "in_gallery": false,
                      "link": "https://i.imgur.com/6MU96OI.jpg",
                      "comment_count": null,
                      "favorite_count": null,
                      "ups": null,
                      "downs": null,
                      "points": null,
                      "score": null
                    },
                    {
                      "id": "c1jnEjM",
                      "title": null,
                      "description": null,
                      "datetime": 1595179387,
                      "type": "image/gif",
                      "animated": true,
                      "width": 2250,
                      "height": 4000,
                      "size": 911966,
                      "views": 703,
                      "bandwidth": 641112098,
                      "vote": null,
                      "favorite": false,
                      "nsfw": null,
                      "section": null,
                      "account_url": null,
                      "account_id": null,
                      "is_ad": false,
                      "in_most_viral": false,
                      "has_sound": false,
                      "tags": [],
                      "ad_type": 0,
                      "ad_url": "",
                      "edited": "0",
                      "in_gallery": false,
                      "link": "https://i.imgur.com/c1jnEjM.jpg",
                      "comment_count": null,
                      "favorite_count": null,
                      "ups": null,
                      "downs": null,
                      "points": null,
                      "score": null
                    },
                    {
                      "id": "aWj3AsE",
                      "title": null,
                      "description": null,
                      "datetime": 1595179412,
                      "type": "video/mp4",
                      "animated": true,
                      "width": 4032,
                      "height": 1645,
                      "size": 568216,
                      "views": 426,
                      "bandwidth": 242060016,
                      "vote": null,
                      "favorite": false,
                      "nsfw": null,
                      "section": null,
                      "account_url": null,
                      "account_id": null,
                      "is_ad": false,
                      "in_most_viral": false,
                      "has_sound": false,
                      "tags": [],
                      "ad_type": 0,
                      "ad_url": "",
                      "edited": "0",
                      "in_gallery": false,
                      "link": "https://i.imgur.com/aWj3AsE.jpg",
                      "comment_count": null,
                      "favorite_count": null,
                      "ups": null,
                      "downs": null,
                      "points": null,
                      "score": null
                    }
                  ],
                  "ad_config": {
                    "safeFlags": [
                      "album",
                      "in_gallery",
                      "sixth_mod_safe",
                      "gallery"
                    ],
                    "highRiskFlags": [],
                    "unsafeFlags": [],
                    "wallUnsafeFlags": [],
                    "showsAds": true
                  }
                },
                {
                  "id": "lEBF1kl",
                  "title": "51 years ago...",
                  "type": "image/gif",
                  "animated": true,
                  "description": null,
                  "datetime": 1595172735,
                  "cover": "OJ6OL8I",
                  "cover_width": 2966,
                  "cover_height": 3276,
                  "account_url": "Gamzeyaavor",
                  "account_id": 7157363,
                  "privacy": "hidden",
                  "layout": "blog",
                  "views": 627,
                  "link": "https://imgur.com/a/lEBF1kl",
                  "ups": 13,
                  "downs": 1,
                  "points": 12,
                  "score": 12,
                  "is_album": true,
                  "vote": null,
                  "favorite": false,
                  "nsfw": false,
                  "section": "",
                  "comment_count": 3,
                  "favorite_count": 1,
                  "topic": "No Topic",
                  "topic_id": 29,
                  "images_count": 1,
                  "in_gallery": true,
                  "is_ad": false,
                  "tags": [
                    {
                      "name": "space",
                      "display_name": "space",
                      "followers": 1312803,
                      "total_items": 24902,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "2wD3VJA",
                      "thumbnail_hash": null,
                      "accent": "26606e",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "the final frontier",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "selfie",
                      "display_name": "selfie",
                      "followers": 3431,
                      "total_items": 11698,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "HNvpoqb",
                      "thumbnail_hash": null,
                      "accent": null,
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "photography",
                      "display_name": "photography",
                      "followers": 78269,
                      "total_items": 68381,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "g37hMZ3",
                      "thumbnail_hash": null,
                      "accent": "AA81B4",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "travel",
                      "display_name": "travel",
                      "followers": 1334180,
                      "total_items": 20822,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "HEQs6u2",
                      "thumbnail_hash": null,
                      "accent": "4880B0",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "embrace wanderlust",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "science_and_tech",
                      "display_name": "science and tech",
                      "followers": 3444539,
                      "total_items": 64174,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "bdRjrrI",
                      "thumbnail_hash": null,
                      "accent": "E47D57",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "the cutting edge",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    }
                  ],
                  "ad_type": 0,
                  "ad_url": "",
                  "in_most_viral": false,
                  "include_album_ads": false,
                  "ad_config": {
                    "safeFlags": [
                      "album",
                      "in_gallery",
                      "sixth_mod_safe",
                      "gallery"
                    ],
                    "highRiskFlags": [],
                    "unsafeFlags": [],
                    "wallUnsafeFlags": [],
                    "showsAds": true
                  }
                },
                {
                  "id": "aYmcqEH",
                  "title": "Finally out of lockdown and visited one of my favourite places",
                  "type": "video/mp4",
                  "animated": true,
                  "description": null,
                  "datetime": 1595164493,
                  "cover": "D6zHQX5",
                  "cover_width": 4000,
                  "cover_height": 1945,
                  "account_url": "Llandedd",
                  "account_id": 18500244,
                  "privacy": "hidden",
                  "layout": "blog",
                  "views": 735,
                  "link": "https://imgur.com/a/aYmcqEH",
                  "ups": 13,
                  "downs": 1,
                  "points": 12,
                  "score": 12,
                  "is_album": true,
                  "vote": null,
                  "favorite": false,
                  "nsfw": false,
                  "section": "",
                  "comment_count": 1,
                  "favorite_count": 1,
                  "topic": "No Topic",
                  "topic_id": 29,
                  "images_count": 14,
                  "in_gallery": true,
                  "is_ad": false,
                  "tags": [
                    {
                      "name": "beautiful",
                      "display_name": "beautiful",
                      "followers": 35040,
                      "total_items": 27397,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "Vz3EoK6",
                      "thumbnail_hash": null,
                      "accent": "0A6D5D",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "travel",
                      "display_name": "travel",
                      "followers": 1334177,
                      "total_items": 20822,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "HEQs6u2",
                      "thumbnail_hash": null,
                      "accent": "4880B0",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "embrace wanderlust",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "holiday",
                      "display_name": "holiday",
                      "followers": 93,
                      "total_items": 665,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "1eQyuB1",
                      "thumbnail_hash": null,
                      "accent": "276860",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "north_wales",
                      "display_name": "north wales",
                      "followers": 18,
                      "total_items": 88,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "A2MU7EC",
                      "thumbnail_hash": null,
                      "accent": "50535A",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    }
                  ],
                  "ad_type": 0,
                  "ad_url": "",
                  "in_most_viral": false,
                  "include_album_ads": false,
                  "ad_config": {
                    "safeFlags": [
                      "album",
                      "in_gallery",
                      "sixth_mod_safe",
                      "gallery"
                    ],
                    "highRiskFlags": [],
                    "unsafeFlags": [],
                    "wallUnsafeFlags": [],
                    "showsAds": true
                  }
                },
                {
                  "id": "rfV0ljM",
                  "title": "San Jose, CA to Portland, OR",
                  "type": "image/jpeg",
                  "animated": false,
                  "description": null,
                  "datetime": 1595138378,
                  "cover": "tOMZC8X",
                  "cover_width": 854,
                  "cover_height": 480,
                  "account_url": "420supercoolusername69",
                  "account_id": 79387559,
                  "privacy": "hidden",
                  "layout": "blog",
                  "views": 550,
                  "link": "https://imgur.com/a/rfV0ljM",
                  "ups": 6,
                  "downs": 0,
                  "points": 6,
                  "score": 6,
                  "is_album": true,
                  "vote": null,
                  "favorite": false,
                  "nsfw": false,
                  "section": "",
                  "comment_count": 7,
                  "favorite_count": 0,
                  "topic": "No Topic",
                  "topic_id": 29,
                  "images_count": 2,
                  "in_gallery": true,
                  "is_ad": false,
                  "tags": [
                    {
                      "name": "travel",
                      "display_name": "travel",
                      "followers": 1334174,
                      "total_items": 20822,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "HEQs6u2",
                      "thumbnail_hash": null,
                      "accent": "4880B0",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "embrace wanderlust",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "portland",
                      "display_name": "portland",
                      "followers": 184,
                      "total_items": 967,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "f8B0kEw",
                      "thumbnail_hash": null,
                      "accent": "5B62A5",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "oregon",
                      "display_name": "oregon",
                      "followers": 567,
                      "total_items": 2067,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "zIGIQFa",
                      "thumbnail_hash": null,
                      "accent": "50535A",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    },
                    {
                      "name": "timelapse",
                      "display_name": "timelapse",
                      "followers": 1444,
                      "total_items": 1599,
                      "following": false,
                      "is_whitelisted": false,
                      "background_hash": "7bemn9H",
                      "thumbnail_hash": null,
                      "accent": "51535A",
                      "background_is_animated": false,
                      "thumbnail_is_animated": false,
                      "is_promoted": false,
                      "description": "",
                      "logo_hash": null,
                      "logo_destination_url": null,
                      "description_annotations": {}
                    }
                  ],
                  "ad_type": 0,
                  "ad_url": "",
                  "in_most_viral": false,
                  "include_album_ads": false,
                  "ad_config": {
                    "ad_config": {
                      "safeFlags": [
                        "album",
                        "in_gallery",
                        "sixth_mod_safe"
                      ],
                      "highRiskFlags": [],
                      "unsafeFlags": [],
                      "wallUnsafeFlags": [],
                      "showsAds": true
                    }
                  }
                }
              ],
              "success": true,
              "status": 200
            }
        """.trimIndent()
    }
}