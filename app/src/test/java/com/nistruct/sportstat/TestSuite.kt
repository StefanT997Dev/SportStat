package com.nistruct.sportstat

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Root test suite which consists of Unit, Integration and UI suites.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    UnitTestSuite::class
)
class TestSuite {
}