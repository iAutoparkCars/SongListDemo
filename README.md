# iTunes Store Preview Demo
Preview audio from the iTunes store. Uses iTunes API.
Practiced mobile animations, MVVM architecture, and databinding.



# Progression of the UI
Started with a bare layout, initially filled with dummy data to mock populate the views.
Took a screen capture after each major internal or UI change.

![pz5KcB](https://i.makeagif.com/media/10-16-2017/pz5KcB.gif)

# Splashscreen Animation: 3 thread concurrency
Made from scratch. 
1. One thread calls iTunes API and stores JSON response; UX-wise, this is economical 
2. Another thread handled fullscreen fade animation.
3. Third thread calls my custom View class to "write animate" the "welcome"

![ezgif-2-334cb42668](https://user-images.githubusercontent.com/14288932/31610042-91ce4868-b244-11e7-9b43-15dcd692fc98.gif)

# List to preview music off iTunes
The list was created using RecyclerView. Screen capture doesn't support audio,
but audio does play!

