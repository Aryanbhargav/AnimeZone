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
    val animeDetail: StateFlow<AnimeDetailModel?> get() = _animeDetail

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

/*
    val showSortingOptions = mutableStateOf(false)
    var state by mutableStateOf(MangaListingState())
    val selectedMangaDetails = mutableStateOf(AnimeListing("","","",0,0,0.00,"",false,false))
    private val _favoriteMangaList = MutableStateFlow<List<AnimeListing>>(emptyList())
    val favoriteMangaList: StateFlow<List<AnimeListing>> get() = _favoriteMangaList

    val selectedRead= mutableSetOf<String>()

    private val _singleMangaListing = MutableStateFlow<AnimeListing?>((null))
    val singleMangaListing: StateFlow<AnimeListing?> get() = _singleMangaListing

    private val _recommendedManga=MutableStateFlow<List<AnimeListing>>(emptyList())
    val recommendedManga:StateFlow<List<AnimeListing>> get()=_recommendedManga

   // var sortingOption =  mutableStateOf<SortingOption>(SortingOption.YEAR)
    init {
        getMangaList()
        loadFavorites()
    }

    fun updateSingleMangaList(manga: AnimeListing) {
        _singleMangaListing.value=manga
    }

    fun getRecommendedList(manga:List<AnimeListing>, category:String):List<AnimeListing>{

        return manga.filter { it.category==category }

    }

    fun getRecommendedList(category: String)
    {
        viewModelScope.launch {
            _recommendedManga.value=AnimeRepository.getRecommendedManga(category)

        }
    }
    fun getMangaList( fetchFromRemote: Boolean = false){
        viewModelScope.launch {
            AnimeRepository.getMangaListing(fetchFromRemote)
                .collect{ result->
                    when(result) {
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            println("getMangaList Listing $listings")
                            state = state.copy(
                                manga = listings
                            )
                        }
                    }
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                       state = state.copy(isLoading = result.isLoading)
                    }
                }

                }
        }

    }
    private fun loadFavorites() {
        viewModelScope.launch {
            _favoriteMangaList.value = AnimeRepository.getFavoriteMangas()
        }
    }
    fun toggleFavorite(manga: AnimeListing) {
        viewModelScope.launch {
            val newFavoriteStatus = !manga.isFavourite
            AnimeRepository.updateFavouriteManga(manga.copy(isFavourite = newFavoriteStatus))

            val updatedManga = state.manga.map {
                if (it.id == manga.id) {
                    it.copy( isFavourite = newFavoriteStatus)
                } else it
            }
            state=state.copy(
                manga=updatedManga
            )

            loadFavorites()
        }
    }
    fun toggleRead(manga: AnimeListing){
        viewModelScope.launch {
            val newReadStatus=!manga.isRead
            AnimeRepository.updateReadManga(manga.copy(isRead=newReadStatus))
           state.manga=state.manga.toMutableList().onEach {
           if(it.id==  manga.id){
               it.copy(isRead = newReadStatus)
       }
       }
            *//*state=state.copy(
                manga=updatedManga
            )*//*
        }
    }*/

}