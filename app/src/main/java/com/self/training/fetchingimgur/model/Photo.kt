package com.self.training.fetchingimgur.model

class Photo {
    var id: String
    var title: String

    constructor() {
        this.id = ""
        this.title = ""
    }

    constructor(id: String, title: String) {
        this.id = id
        this.title = title
    }

}