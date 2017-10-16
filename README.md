# iTunes Store Preview Demo
Preview audio from the iTunes store. Uses iTunes API.
Practiced mobile animations, MVVM architecture, and databinding.



# Progression of the UI
Started with a bare layout, filled with dummy data to populate the views.
Took a screen capture after each major internal or UI change.

![pz5KcB](https://i.makeagif.com/media/10-16-2017/pz5KcB.gif)

# Splashscreen Animation: 3 thread concurrency
Made from scratch. 
1. One thread calls iTunes API and stores JSON response; UX-wise, this is economical 
2. Another thread handled fullscreen fade animation.
3. Third thread calls my custom View class to "write animate" the "welcome"

![pz5KcB](https://im2.ezgif.com/tmp/ezgif-2-55f6767ad2.gif)

# List to preview music off iTunes
The list was created using RecyclerView. Screen capture doesn't support audio,
but audio does play!

