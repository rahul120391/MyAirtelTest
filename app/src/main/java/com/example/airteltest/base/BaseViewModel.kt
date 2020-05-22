package com.example.airteltest.base

import androidx.lifecycle.ViewModel
import com.example.airteltest.network.baseusecase.UseCaseHandler

abstract class BaseViewModel(val useCaseHandler: UseCaseHandler) : ViewModel()