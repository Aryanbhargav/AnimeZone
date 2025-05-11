package com.manga.mangazone.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.manga.mangazone.data.repository.AnimeRepositoryImpl
import com.manga.mangazone.domain.model.AnimeDetailModel
import com.manga.mangazone.util.Resource

//import com.manga.mangazone.presentation.manga_listings.SortingOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MangaViewModel @Inject constructor(var AnimeRepository : AnimeRepositoryImpl)  : ViewModel(){

     var _animeDetail = MutableStateFlow<AnimeDetailModel?>((null))

    val loading = mutableStateOf(false)
    val items = AnimeRepository.getMangaListing(true).cachedIn(viewModelScope)
    fun getAnimeDetail(animeId:Int){
        viewModelScope.launch {
            AnimeRepository.getAnimeDetail(animeId = animeId)
                .onEach{ result->
                    when(result) {
                        is Resource.Loading -> {
                            loading.value=true
                        }

                        is Resource.Success -> {

                            result.data?.let { result ->
                                _animeDetail.value=result
                                println("getAnimeDetail $result")
                            }
                            loading.value=false

                        }
                        is Resource.Error -> Unit

                    }

                }.launchIn(this)
        }

    }
}