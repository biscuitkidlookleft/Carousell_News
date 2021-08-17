package com.biscuitkid.carousellnews.api

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ResponseCarousellNews {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("banner_url")
    @Expose
    private var bannerUrl: String? = null

    @SerializedName("time_created")
    @Expose
    private var timeCreated: Int? = null

    @SerializedName("rank")
    @Expose
    private var rank: Int? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getBannerUrl(): String? {
        return bannerUrl
    }

    fun setBannerUrl(bannerUrl: String?) {
        this.bannerUrl = bannerUrl
    }

    fun getTimeCreated(): Int? {
        return timeCreated
    }

    fun setTimeCreated(timeCreated: Int?) {
        this.timeCreated = timeCreated
    }

    fun getRank(): Int? {
        return rank
    }

    fun setRank(rank: Int?) {
        this.rank = rank
    }
}