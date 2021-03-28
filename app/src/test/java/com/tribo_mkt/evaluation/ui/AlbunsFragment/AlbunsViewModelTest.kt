package com.tribo_mkt.evaluation.ui.AlbunsFragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tribo_mkt.evaluation.MainCoroutineRule
import com.tribo_mkt.evaluation.getOrAwaitValueTest
import com.tribo_mkt.evaluation.model.AlbumResposta
import com.tribo_mkt.evaluation.repository.FakeEvaluationRepository
import com.tribo_mkt.evaluation.utils.NetworkState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class AlbunsViewModelTest : KoinTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val viewModel: AlbunsViewModel by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(module {
                viewModel { AlbunsViewModel(FakeEvaluationRepository()) }
            })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun pegarListaComUsuarioIdInexistente_returnarListaVazia() {
        viewModel.getAlbumUsuario("0")
        val lista: List<AlbumResposta> = viewModel.albumResposta.getOrAwaitValueTest()
        assert(lista.isEmpty())
    }

    @Test
    fun pegarListaComUsuarioExistente_returnarListaComTresItens() {
        viewModel.getAlbumUsuario("1")
        val lista: List<AlbumResposta> = viewModel.albumResposta.getOrAwaitValueTest()
        assert(lista.count() == 3)
    }

    @Test
    fun pegarListaComUsuarioExistente_retornarStatusCarregado() {
        viewModel.getAlbumUsuario("5")
        val status = viewModel.albumRespostaStatus.getOrAwaitValueTest()
        assert(status == NetworkState.LOADED)
    }

    @Test
    fun pegarListaComErro_retornarStatusErro() {
        viewModel.getAlbumUsuario("ERRO")
        val status = viewModel.albumRespostaStatus.getOrAwaitValueTest()
        assert(status == NetworkState.ERROR)
    }
}