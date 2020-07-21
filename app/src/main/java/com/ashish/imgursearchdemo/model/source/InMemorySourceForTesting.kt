package com.ashish.imgursearchdemo.model.source

import com.ashish.imgursearchdemo.model.Image
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class InMemorySourceForTesting : ImgurSource {
    override fun getImages(searchText: String): Single<List<Image>> {
        return Single.just(
            listOf(
                Image("1", "asdas", "image/png", false, "https://i.imgur.com/MSeUg1x.png"),
                Image("2", "asd", "image/png", false, "https://i.imgur.com/dihrwGG.png"),
                Image("3", "dfsg", "image/png", false, "https://i.imgur.com/nIUcOjq.png"),
                Image("4", "gdhj", "image/gif", true, "https://i.imgur.com/RmC1Oa9.gif"),
                Image("5", "adsff", "image/gif", true, "https://i.imgur.com/LqQkCfC.gifv"),
                Image("6", "gfj", "image/gif", true, "https://i.imgur.com/1E6Z3ps.gif"),
                Image("7", "ghmfgh", "image/gif", true, "https://i.imgur.com/tYno99y.gif"),
                Image("8", "ghj", "image/gif", true, "https://i.imgur.com/2NkhV0j.gif"),
                Image("9", "rrtyrtrb", "image/gif", true, "https://i.imgur.com/SmfqRna.gif"),
                Image("10", "vbnc", "image/gif", true, "https://i.imgur.com/SsyB4oB.gif"),
                Image("11", "t768", "image/gif", true, "https://i.imgur.com/rL2cXSl.gif"),
                Image("12", "dfgdg", "image/gif", true, "https://i.imgur.com/Cmvwl3O.gif"),
                Image("13", "7876gh", "image/jpeg", false, "https://i.imgur.com/bQLz7Az.jpg"),
                Image("14", "kjygdfhn", "image/jpeg", false, "https://i.imgur.com/X21THH8.jpg"),
                Image("15", "45euhrtd", "image/jpeg", false, "https://i.imgur.com/cZ8rOmF.jpg"),
                Image("16", "bnfght", "image/png", false, "https://i.imgur.com/X25ufSN.png"),
                Image("17", "po;ilkj", "image/png", false, "https://i.imgur.com/j60KYVa.png"),
                Image("18", "etrshfg", "image/png", false, "https://i.imgur.com/YGbgL0l.png"),
                Image("19", "nf", "image/jpeg", false, "https://i.imgur.com/YTtmJZb.jpg"),
                Image("20", "dsg", "image/jpeg", false, "https://i.imgur.com/j6xxf0k.jpg"),
                Image("21", "sdfgdgf", "image/gif", true, "https://i.imgur.com/YSi9z7j.gif"),
                Image("22", "dfsg", "image/gif", true, "https://i.imgur.com/pi3Vo4s.gif"),
                Image("23", "fghknh", "image/gif", true, "https://i.imgur.com/ThMeX9Y.gif")
            )
        ).delay(2, TimeUnit.SECONDS)
    }
}