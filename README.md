<h1 align="center">Movook</h1>
<h4 align="center">Where IMDb meets Goodreads.</h4>

<p align="center">
  <a href="https://opensource.org/licenses/MIT"><img alt="License" src="https://img.shields.io/badge/License-MIT-blue.svg?style=flat"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">  
Movook is a small personal project that aims to function as a playground to new android development techniques. Completely based on modern Android application tech-stacks and Clean architecture based on MVVM.
</br>

<p align="center">
<img src="/images/preview.png"/>
  <a href='https://play.google.com/store/apps/details?id=com.ricardojrsousa.movook&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' align="center" height=70 src='https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png'/></a>
</p>


## Tech stack
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/) 
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) 
- [Hilt](https://dagger.dev/hilt/)
- [Navigation](https://developer.android.com/guide/navigation)
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM & Clean Architecture 
- [Retrofit](https://github.com/square/retrofit) - A type-safe HTTP client for Android and Java.
- [Picasso](https://square.github.io/picasso/) - A powerful image downloading and caching library for Android
- [Material-Components](https://github.com/material-components/material-components-android)
- [LeakCanary](https://square.github.io/leakcanary/)

## MAD Score
<img height=450 src="/images/summary.png"/>
<img height=450 src="/images/kotlin.png"/>
<img  height=450 src="/images/jetpack.png"/>

## Architecture
Architecture base on the [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) by Robert C. Martin
<img src="/images/clean_mvvm.webp"/>


## API

<img src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_long_2-9665a76b1ae401a510ec1e0ca40ddcb3b0cfe45f1d51b77a308fea0845885648.svg" align="right" width="30%"/>

Movies data provided by [TMDb API](https://developers.themoviedb.org/3/getting-started/introduction)

Books data provided by [Google Books API](https://developers.google.com/books)

# License
```xml
MIT License

Copyright (c) 2021 Ricardo Sousa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
