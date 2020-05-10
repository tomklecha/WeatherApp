# WeatherApp

WeatherApp created as a test skill app for recrutation process.

The aim of this test is to build a simple Android app 
that displays the current weather info in London and weather forecasts for the next 5 days.

[February 2020]
TODO issues:
- URL error handling 
- while screen turn, data is fetched again, instead of being stored
- AppDialog turn off on turn on screen

Future implementations:
- search the weather by the city
- fetch icons coresponding to the weather from OpenWeather API
- change of background regarding to current time (day/night mode)
- language localisation
- implement Retrofit swap to Kotlin language

[May 2020]
Implemented:
 - app design in kotlin
 - all fetch data provided by Retrofit, including fetching icon
 - MVP archtecture

TODO:
- JUnit tests
- holding instance of recycler adapter
