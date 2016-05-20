package com.wojciechmaciejewski.githubapirequester

import android.test.suitebuilder.annotation.SmallTest

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@SmallTest
abstract class UnitTest {
    @Before
    fun setup() {
        initializeMocks()
        onSetup()

    }

    private fun initializeMocks() {
        MockitoAnnotations.initMocks(this)

    }

    protected abstract fun onSetup()
}
