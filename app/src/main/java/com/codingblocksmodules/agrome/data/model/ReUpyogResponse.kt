package com.codingblocksmodules.agrome.data.model

import com.google.gson.annotations.SerializedName

data class ReUpyogResponse(
	@SerializedName("ReUpyog")val ReUpyog: ReUpyog? = null
)

data class Article(
	@SerializedName("title")val title: String? = null,
	@SerializedName("article_image")val articleImage: String? = null,
	@SerializedName("link")val link: String? = null,
)

data class Video(
	@SerializedName("title")val title: String? = null,
	@SerializedName("video_link")val videoLink: String? = null
)

data class ReUpyog(
	@SerializedName("category")val category: String? = null,
	@SerializedName("Article")val Article: List<Article>? = null,
	@SerializedName("Video")val Video: List<Video>? = null,
)
