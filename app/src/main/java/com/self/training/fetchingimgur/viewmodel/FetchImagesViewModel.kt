package com.self.training.fetchingimgur.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.self.training.fetchingimgur.model.Photo
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class FetchImagesViewModel : ViewModel() {
    private var httpClient: OkHttpClient? = null
    lateinit var requestedPhotos: ObservableArrayList<Photo>
    private var request: Request

    init {
        this.request = Request.Builder().url("https://api.imgur.com/3/gallery/search/?q=cats")
            .header("Authorization","Client-ID 57e156df888f37c")
            .header("User-Agent","FetchingImgur")
            .build()
    }

    private fun fetchData() {
        httpClient = OkHttpClient.Builder().build()
    }

    private fun backgroundRequest() {
        httpClient!!.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val data = JSONObject(response.body()!!.string())
                val items: JSONArray = data.getJSONArray("data")

                for(i in 0..items.length()){
                    val item = items.getJSONObject(i)
                    val photo = Photo()

                    if(item.getBoolean("is_album")) {
                        photo.id = item.getString("cover")

                    } else {
                        photo.id = item.getString("id")
                    }

                    photo.title = item.getString("title")

                    requestedPhotos.add(photo)
                }
            }
        })
    }

    fun selectImage(pos: Int, som: Int) {}

}