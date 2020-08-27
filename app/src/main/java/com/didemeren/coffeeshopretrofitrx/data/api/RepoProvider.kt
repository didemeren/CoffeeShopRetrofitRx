package com.didemeren.coffeeshopretrofitrx.data.api


class RepoProvider {
    companion object {
        private val apiService = ApiClient.retrofit.create(ApiService::class.java)

        fun provideRepository(): Repo {
            return Repo(
                apiService
            )
        }
    }
}