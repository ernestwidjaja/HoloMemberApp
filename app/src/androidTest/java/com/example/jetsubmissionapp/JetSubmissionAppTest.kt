package com.example.jetsubmissionapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.jetsubmissionapp.ui.navigation.Screen
import com.example.jetsubmissionapp.ui.theme.JetSubmissionAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JetSubmissionAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetSubmissionAppTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetSubmissionApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_check_bottomNavigation() {
        composeTestRule.onNodeWithStringId(R.string.home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithStringId(R.string.about).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navHost_itemClick_navigateToDetail_navigateBack() {
        composeTestRule.waitUntilExactlyOneExists(hasTestTag("MemberList"), 10000L)
        composeTestRule.onNodeWithTag("MemberList").assertExists()
        composeTestRule.onNodeWithTag("MemberList").performScrollToIndex(10)
        composeTestRule.onNodeWithText("Yuzuki Choco").performClick()
        navController.assertCurrentRouteName(Screen.DetailMember.route)
        composeTestRule.waitUntilExactlyOneExists(hasText("Yuzuki Choco"), 10000L)
        composeTestRule.onNodeWithText("Yuzuki Choco").assertExists()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navHost_itemClick_navigateToDetail_addToFavorite() {
        composeTestRule.waitUntilExactlyOneExists(hasTestTag("MemberList"), 10000L)
        composeTestRule.onNodeWithTag("MemberList").assertExists()
        composeTestRule.onNodeWithTag("MemberList").performScrollToIndex(10)
        composeTestRule.onNodeWithText("Yuzuki Choco").performClick()
        navController.assertCurrentRouteName(Screen.DetailMember.route)
        composeTestRule.waitUntilExactlyOneExists(hasText("Yuzuki Choco"), 10000L)
        composeTestRule.onNodeWithText("Yuzuki Choco").assertExists()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.add_to_favorite)).performClick()
    }
}