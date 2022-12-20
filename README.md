<!-- # Title -->
# Giphy
![Demo](https://cdn.arstechnica.net/wp-content/uploads/2020/05/ZoC9YqyA-800x246.png)


<!-- # Short Description -->

>- The application was designed to display the list of available *Gifs* from the **Giphy** library
>- The user can *Favorite* the **Gifs** and **Share** their URL through different apps
>- The application allows the user to **Search** through all the **Giphy** database

This application was designed to supply the needed requisites from a *Android Code Challenge*, creating an app that allows the user to navigate 
trought the **Giphy** list available using [Giphy API](https://developers.giphy.com). It also must allow the user to *Favorite*, *Search* and *Share*
the **Gifs**.


<!-- # Badges -->
<div style="display: inline_block"><br>
    <img height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/androidstudio/androidstudio-original.svg">
    <img height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg">
</div>

---

# Tags

`Android Studio` `Kotlin` `Giphy API` `OkHTTP` `Coroutines` `MVVM` `Retrofit` `Hilt` `Room`

---

# Android Code Challenge
## Rules: 
> Description
>- List of **Gifs** available from the [Giphy API](https://developers.giphy.com)
>- Be able to *Search* and *Favorite* the **Gifs** but also **Share** the URL between another applications
>- A local database should be used to store the *Favorites*, which I'm using [Room](https://developer.android.com/training/data-storage/room)


---

# Demo


![](https://media.discordapp.net/attachments/655489748885831713/1054740575481511987/navigating.gif)
>- Navigate through the **List**, **Search** and **Favorites** interfaces

![](https://media.discordapp.net/attachments/655489748885831713/1054740575129186395/giffunctionalities.gif)
>- The user can **Share** the URL with other apps and **Favorite** the *Gif* to easily find it in the **Favorites** interface

![](https://media.discordapp.net/attachments/655489748885831713/1054740574688788480/search.gif)
>- **Search** through all the *Giphy* database and find all related *Gifs* to the user query

![](https://media.discordapp.net/attachments/655489748885831713/1054740574252576778/favorite.gif)
>- **Favorite** any *Gif* to easily find it whenever the user wants
>- The user can **Delete** or **Share** any of them with few clicks


---

# Code Example
```kotlin
override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val giphy = giphies[position]
        holder.binding.apply {
            Glide.with(context)
                .load(giphy.images.downsized.url)
                .into(imageGiphy)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(giphy)
            }
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.let {
                it(giphy)
            }
            true
        }
    }
    
private var onItemClickListener: ((GiphyModel) -> Unit)? = null

    private var onItemLongClickListener: ((GiphyModel) -> Unit)? = null

    fun setOnClickListener(listener: (GiphyModel) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnLongClickListener(listener: (GiphyModel) -> Unit) {
        onItemLongClickListener = listener
    }
```

As one of the requisites of the **Technical Challenge** was the implementation of a *ClickListener* for sharing the link of the **Giphy** between apps that 
can receive the *URL* and the *OnLongItemClickListener* create a new *Favorite*, the code above shows how to add both click events to the adapter and allow
those functions

---

# Libraries

>- [Timber](https://github.com/JakeWharton/timber)
>- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
>- [Coroutines](https://developer.android.com/kotlin/coroutines?hl=pt-br)
>- [KTX](https://developer.android.com/kotlin/ktx)
>- [Retrofit](https://square.github.io/retrofit/)
>- [OkHTTP](https://square.github.io/okhttp/)
>- [Navigation Components](https://developer.android.com/guide/navigation)
>- [Hilt](https://dagger.dev/hilt/)
>- [Room](https://developer.android.com/training/data-storage/room)
>- [Glide](https://github.com/bumptech/glide)
>- [Giphy API](https://developers.giphy.com)

---

# Contributors

- [Thiago Rodrigues](https://www.linkedin.com/in/tods/)
