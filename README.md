# traverse_string
Re-set string to Android app by override get resource and intercept inflating layout

<h3> Idea </h3>

It's not completed yet, so this readme file will be updated in future.

The basic idea is handle the resource caller of android app. So that after I'd googled a lot then I realized I should override 2 things:
- The ContextWrapper: Override the getText function to return my text instead of app's text.
- The LayoutInflater: From xml files. I need to intercept the app's process of inflating layout, then finding all the set text attributes and override then to return my text.

For that idea, I wrote TraverseString library in a very simple implementation. And as it is simple, basically, it can only handle some basical views like: TextView, Button, Edittext, ...

<h3> Usage </h3>

```kotlin
RestringApp.initialize(this)
```
Initialize Restring library when the app create. So it must be called in Application level

```kotlin
val data = mutableMapOf<String, Map<String, String>>()
        val en = mutableMapOf<String, Map<String, String>>(
            Pair(
                "en",
                mutableMapOf(Pair("hello", "Hello"))
            )
        )
        data.putAll(en)
        RestringApp.getInstance().injectData(data)
```

Inject data into library.
As soon as you get the strings data. Put it into Restring with format as I define above: 
```text
Map<languageId: String, Map<stringId: String, stringValue: String>>
```

The key of stringId should be the same as stringId in your strings.xml file. Otherwise, it's won't work correctly.
With stringId that is not exist in the data you input, then it would take the string in strings.xml file.

If you want to know more about how this library work, see my [Medium](https://thdng-39982.medium.com/android-development-handle-set-text-for-android-app-ac93dc3a1522) for detail.
