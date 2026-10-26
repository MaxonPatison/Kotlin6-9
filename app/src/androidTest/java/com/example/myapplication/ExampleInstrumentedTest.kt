package com.example.myapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WaterTrackerUiTest {

    // Правило для запуска Compose-тестов
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    /**
     * Тест 1: Проверка наличия и корректной работы кнопки добавления жидкости.
     * Нажимаем кнопку и проверяем, что счетчик изменился на 250 мл.
     */
    @Test
    fun testAddWaterButtonWorks() {
        // Запускаем экран трекера
        composeTestRule.setContent {
            WaterTracker()
        }

        // Находим кнопку по тексту и кликаем
        composeTestRule.onNodeWithText("+250 мл").performClick()

        // Проверяем, что на экране появился текст "250 мл"
        composeTestRule.onNodeWithText("250 мл").assertExists()
    }

    /**
     * Тест 2: Проверка наличия и корректной работы кнопки "Завершить день".
     * Сценарий: выпили норму (1500 мл), завершили день -> счетчик дней стал 1.
     */
    @Test
    fun testFinishDayButtonWithNorm() {
        composeTestRule.setContent {
            WaterTracker()
        }

        // Добавляем воду 6 раз (6 * 250 = 1500 мл)
        repeat(6) {
            composeTestRule.onNodeWithText("+250 мл").performClick()
        }

        // Нажимаем "Завершить день"
        composeTestRule.onNodeWithText("Завершить день").performClick()

        // Проверяем, что счетчик дней увеличился до 1
        // ВАЖНО: Текст должен точно совпадать с вашим WaterTracker
        composeTestRule.onNodeWithText("Дней подряд с нормой: 1").assertExists()
    }

    /**
     * Тест 3: Проверка сброса счетчика дней, если норма не достигнута.
     * Сценарий: выпили мало (500 мл), завершили день -> счетчик дней стал 0.
     */
    @Test
    fun testFinishDayButtonWithoutNorm() {
        composeTestRule.setContent {
            WaterTracker()
        }

        // Добавляем воду 2 раза (2 * 250 = 500 мл) - это меньше нормы
        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("+250 мл").performClick()

        // Нажимаем "Завершить день"
        composeTestRule.onNodeWithText("Завершить день").performClick()

        // Проверяем, что счетчик дней остался 0 (сбросился)
        composeTestRule.onNodeWithText("Дней подряд с нормой: 0").assertExists()
    }
}