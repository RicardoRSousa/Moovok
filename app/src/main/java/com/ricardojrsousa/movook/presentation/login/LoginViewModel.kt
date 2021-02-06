package com.ricardojrsousa.movook.presentation.login

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.framework.BookUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel

/**
 * Created by Ricardo Sousa on 03/02/2021.
 */
class LoginViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
}