<div align="center">
    <img src="https://github.com/Origogi/Origogi/assets/35194820/9e949040-9ce2-43dc-a68d-19e0eccea54e">
</div>

# Android Compose Pokedex

This Pokedex app was built using Android Compose, allowing users to explore and search for Pokémon with a beautifully designed interface.

## Reference(Design)

<div align="center">
<img src="https://github.com/Origogi/leetcode/assets/35194820/874ee8bb-336c-4278-aa52-5c6f3b578e56" width="30%">
</div>

[Figma](https://www.figma.com/community/file/1202971127473077147)

[Creator Profile](https://www.linkedin.com/in/junior-saraiva/)

## Screenshots

<div align="center">
    <img src="https://github.com/Origogi/android-compose-pokedex/assets/35194820/1ac3c65c-32d6-4d24-a877-e03e1a26dbc3" width="25%">
    <img src="https://github.com/Origogi/android-compose-pokedex/assets/35194820/ba01a0e3-a5de-4757-8fd0-61848c889924" width="25%">
    <img src="https://github.com/Origogi/android-compose-pokedex/assets/35194820/c9daa5ac-481d-47fd-9f01-86994aebd9ab" width="25%">

</div>
<div align="center">
    <img src="https://github.com/Origogi/android-compose-pokedex/assets/35194820/30b1cc3c-ff05-456f-a5a5-a23a04de6723" width="25%">
    <img src="https://github.com/Origogi/android-compose-pokedex/assets/35194820/b4a822f0-1af4-4a2e-9963-4e52a9465c40" width="25%">
    <img src="https://github.com/Origogi/android-compose-pokedex/assets/35194820/3eedd2fd-1c7c-4545-91cf-6c09d78d267b" width="25%">
</div>
<div align="center">
    <img src="https://github.com/Origogi/android-compose-pokedex/assets/35194820/01c1fb3f-5a22-4590-bbe6-c1f80f6f46a4" width="25%">
    <img src="https://github.com/Origogi/android-compose-pokedex/assets/35194820/83411ad7-1fda-40e5-a622-9bb89d35a762" width="25%">
    <img src="https://github.com/Origogi/android-compose-pokedex/assets/35194820/b5a6a693-4fcb-4e9d-8da2-e5509bc41c12" width="25%">
</div>

## Features

- Filter Pokémon by generation
- Favorite Pokémon
- Unlimited scrolling

Filter Pokémon by type, sort Pokémon by name, number was disabled because of the Poke API limitation. but added the feature to see almost all Pokémon.

## API

![](https://raw.githubusercontent.com/PokeAPI/media/master/logo/pokeapi_256.png)

[PokeAPI](https://pokeapi.co/) is a RESTful Pokémon API that provides a lot of Pokémon data, including Pokémon, moves, abilities, types, and more.

## Libraries

### Hilt
Hilt is a dependency injection library for Android that simplifies Dagger setup. It provides a standard way to manage dependencies in Android apps, making your code more modular, testable, and maintainable. Hilt handles the lifecycle of components and supports scoped bindings for different Android classes such as Activities, Fragments, and ViewModels.

### Coil
Coil (Coroutine Image Loader) is a lightweight and efficient image loading library for Android, backed by Kotlin Coroutines. It supports image caching, transformations, and animations. Coil integrates seamlessly with Jetpack Compose and traditional Android Views, providing a flexible API to load images from various sources like URLs, resources, and files.

### Retrofit
Retrofit is a type-safe HTTP client for Android and Java, simplifying the interaction with RESTful web services. It uses annotations to define API endpoints and supports various converters to handle different data formats such as JSON and XML. Retrofit integrates with OkHttp for network operations and supports synchronous and asynchronous request execution, making it a versatile tool for handling network communication.

## Architecture

`Layered architecture`, `MVVM(Model-View-ViewModel)` is used in this project.

<div>
    <img src="https://github.com/Origogi/Origogi/assets/35194820/7074b560-0972-4880-a591-de4b88e7996f" width="80%">
</div>
