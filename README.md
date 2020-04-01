# MyPersonalPlaylist - A Personal Project by Jordan Watterston

## What is this Project?

This application will be a sort of interface for the user to input songs and put those songs into a playlist. A playlist
is collection of songs that can either be a collection based on **genre, artist, or by user selection**. The application 
should have a user interface that lets the user either input a song, view playlists, or sort songs by genre.
Depending on how my experience increases through the term, I may increase the scope of the project, but
initially I would like these basic functions. People who want a tailored collection of their music will use this
application. This project is of interest to me as I listen to music all the time, so It would be interesting to
understand how this functionality is built into my day to day applications.

## User Stories

- As a user, I would like to create a custom playlist with a custom name
- As a user, I would like to input a custom song with a name, artist and genre
- As a user, I want to know how long my playlist is
- As a user, I would like to view my playlists
- As a user, I would like to create a custom library that saves on my machine; 
that way all my playlists are saved, with song amounts and length.
- As a user, I would like the application to open up to my library automatically without having
to load it manually
- As a user, I would like the option to delete my library and start over with a blank slate

## Instructions for Grader

- Before hitting Start, you will want to trigger the audio component, hit ctrl-m on the keyboard. 
It should trigger a song. **It will pause at the main menu for now as I am planning to have a 
random song player at the welcome screen**
- From there, hit start and enter your name and hit the button
- From there, enter your library
- You can generate the first required event. *Create a playlist* by hitting the create a 
playlist option and fill out the fields. Once done, hit submit. Then return to the main menu
- You can generate a song in the same way as the playlists, but remember to enter the
 name of the same playlist you want to add to.
- You can generate the second required event by *viewing the contents of a playlist*. 
Hit the button in the main menu and enter the playlist you want to view. 
The playlist with the amount of songs should show
up.
- Other buttons have similar functionality. but are optional. 
- You can save the state of the application by hitting the save library button. The file will write once the
application is closed
- You can delete a library as well in the same way. Changes will only be reflected after the application is closed
- You can reload the state of the application by reopening the application. It happens automatically. If you have a 
saved library, just hit start and it will say hello.

##Phase 4: Task 2

- Test and design a class that is robust.  You must have at least one method that throws a checked exception. 
 You must have one test for the case where the exception is expected and another where the exception is not expected.
 
 For this part of the phase, I refactored my library class to be more robust by handling when the exceptions when
 a playlist does not exist in the library for a few functions. These methods are matchAndAdd, matchAndFindRuntime, and
 matchAndViewSongs. These were eventually handled down the line in the playlist app with messages to the user.
 
 ##Phase 4: Task 3
 - The first thing I fixed was creating specific scene classes for the non essential scenes. The PlaylistApp was running
 each scene within the class which introduced limited cohesion. Breaking them up increases it and opens up the option of
 easily adding in new scenes and swapping them out within the program.
 - The final thing I did was move all of the key functions for manipulating the library within the scene classes. Before
 they were also in the PlaylistApp which introduced alot of coupling between the PlaylistApp and the Library, Playlists,
 and songs. Now, these individual functions are offloaded within the scenes itself, so the PlaylistApp isnt being
 cluttered with these. Therefore, any functions for manipulating the data are consolidated to the specific scene.
