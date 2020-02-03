package com.ajsherrell.weatherapp

import com.ajsherrell.weatherapp.model.Category
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.Exception

@ExtendWith(MockKExtension::class)
class WeatherListViewModelTest {

    @Test
    fun `test api call`() {
        val model = mockk<WeatherListViewModel>()
        every { model.weatherApi.getWeather() } throws Exception("called getWeather()")
    }

    @Test
    fun `test api return`() {
        val model= mockk<WeatherListViewModel>()
        every { model["loadWeather"]() } throws Exception("called loadWeather()")
    }

    @Test
    fun `test data call`() {
        val response = mockk<Category> {
            every { weather[0].main } returns "cloudy"
            every { weather[0].description } returns "partly cloudy"
            every { weather[0].icon } returns "01d"
            every { main.temp } returns 50.0
            every { main.temp_min } returns -99.0
            every { main.temp_max } returns 99.0
            every { wind.speed } returns 10.0
        }
    }
}