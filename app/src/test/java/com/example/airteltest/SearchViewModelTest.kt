package com.example.airteltest

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.airteltest.datamodel.AddressResponse
import com.example.airteltest.network.baseusecase.UseCaseHandler
import com.example.airteltest.network.baseusecase.UseCaseScheduler
import com.example.airteltest.search.datasource.ISearchDataSource
import com.example.airteltest.search.datasource.LocalSearchDataSource
import com.example.airteltest.search.datasource.RemoteSearchDataSource
import com.example.airteltest.search.repository.SearchRepository
import com.example.airteltest.search.usecase.SearchUseCase
import com.example.airteltest.search.viewmodel.SearchViewModel
import com.example.airteltest.utils.Utility
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito.whenNew
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = "src/main/AndroidManifest.xml",
    sdk = [21]
)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
@PrepareForTest(Utility::class)
class SearchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var searchViewModel: SearchViewModel

    @Mock
    lateinit var searchUseCase: SearchUseCase

    @Mock
    lateinit var useCaseSchedulers: UseCaseScheduler

    private lateinit var useCaseHandler: UseCaseHandler

    @Mock
    lateinit var callback: ISearchDataSource.GetAddressData

    @Mock
    lateinit var localSearchDataSource: LocalSearchDataSource

    @Mock
    lateinit var remoteSearchDataSource: RemoteSearchDataSource

    private lateinit var searchDataRepository: SearchRepository

    private lateinit var context: Context

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        context = RuntimeEnvironment.application as Context
        val instance = mock(Utility::class.java)
        whenNew<Utility>("com.example.airteltest.utils.Utility")
            .withAnyArguments()
            .thenReturn(instance)
        searchDataRepository = SearchRepository(localSearchDataSource, remoteSearchDataSource)
        useCaseHandler = UseCaseHandler(useCaseSchedulers)
        searchViewModel = SearchViewModel(searchUseCase, useCaseHandler, instance)
    }

    @Test
    fun addressFetchTest() {
        val queryString = "gurgaon"
        val response = AddressResponse()
        Mockito.`when`(searchDataRepository.getAddressList(queryString, callback))
            .then {
                callback.onAddressDataReceived(response)
            }

        searchViewModel.getAddressList(queryString)
        searchViewModel.addressListResponseLiveData.observeForever {
            assertEquals(response.data?.addressList, it)
            assertTrue(it != null)
        }
    }

    @Test
    fun addressFetchTestFail() {
        val queryString = null
        Mockito.`when`(searchDataRepository.getAddressList(queryString, callback))
            .then {
                callback.onError()
            }
        searchViewModel.getAddressList(queryString)
    }
}
