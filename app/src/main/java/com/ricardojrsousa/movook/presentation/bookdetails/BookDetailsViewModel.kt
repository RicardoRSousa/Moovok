package com.ricardojrsousa.movook.presentation.bookdetails

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.framework.BookUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import kotlinx.coroutines.launch

class BookDetailsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val bookUseCases: BookUseCases
) : BaseViewModel() {

    private val _bookDetails: MutableLiveData<Book> = MutableLiveData()
    val bookDetails = _bookDetails

    fun init(bookId: String) {
        coroutineScope.launch {
            val book = bookUseCases.getBookDetails.invoke(bookId)
            _bookDetails.postValue(book)
        }
    }

}