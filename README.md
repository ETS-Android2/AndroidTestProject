# AndroidTestProject
## [Visual Workflow Log](https://trello.com/b/6btNXwmI/andorid-test-project)
Each unit of *effort* in the [Trello Visual Workflow](https://trello.com/b/6btNXwmI/andorid-test-project) is equal to approximately 1 hour of time. 

This project is part of a coding test to recreate a model application.

## Total Hours: ~15 hrs
### Splash Screen: 1 hrs
The splash screen I was able to implement almost immediately. The image being a nonstandard splashscreen (full background image as oppose to a logo) did cost
me some time trying to optimize the screen for most devices. It came down to choosing a fullwindow splash screen with slightly missing ends or non-fullwindow splash screen
but with massive black bars. I chose the former.

## Home Screen: 2 hrs
The home screen was also designed and implemented within an hour. However, I spent almost 1.5 hrs trying to implent a Ripple effect for devices under API 21 (min API for
project is 19). 

The Ripple effect is supported natively for API 21+, but has no official support otherwise. After attempting multiple solutions, I concluded I would need a 
third-party library to implement the functionality. Assessing the risk, I decided to implement a separate animation, though not Ripple, for API < 21 devices instead of relying
on third party libraries that may create compatibility issues in the future.

## Login Screen: 3 hrs
The login screen, although not complicated in hindsight, took me the longest time to complete because I was completely unaware of what Retrofit was and what its uses were for.
I spent about an hour researching the technology so I can get a good understanding of how it works and how it is implemented. 

Within the next hour, I was able to complete the UI for the login screen. A bit more time was spent optimizing the fields and revisiting the Ripple Effect issue before 
implementing the Retrofit logic.

## BUG - ActionBar not Working: 1 hrs
The back button in the Action Bar did nothing. So within an hour, I implemented the logic to link all Activity screens to the Home screenand then added animations 
to both the physical and Action Bar back button. I notice there was a loop when hitting the back button on the Home screen that return users to the previous screen, so I 
implemented an Alert Dialogue to prompt to exit the app.

## Debug - Ripple Effect pre API 21: 2 hrs
Call me stubborn, but I was determined to find a solution outside of third-party libraries. I attempted a few solutions including creating a button mask to overlay over 
UI buttons and attempting to *draw* ovals over the button UIs but that ended in futility.

## Chat Log Screen: 2 hrs
Now familar with the Retrofit HTTP client, I was able to implement the Retrofit logic within an hour and fetch the data from the REST web service before creating the UI
interface. I attempted to implement a Mock Web Server (Mockito) to create some unit test, but it quickly became combersome and was creating inconsistent results 
and unexpected crashes. It was scraped.

The next hour was spent designing and implementing the UI for the Chat Screen. I am familar with Picasso, so I understood I need to use it and transformations to properly
model the Chat Log UI.

## Animation Screen (Drag and Drop): 2 hrs
I was unfamilar with Drag and Drop, so I spent a bit of time going through Google Docs learning the implementation. I realize after an hour that the Google Doc was actually
dated and a new library exist to accomplish the same thing. Luckily, the logic was pretty similar, so I had no issue implementing it. 

I did have some bugs with the anchor on the ShadowBuilder object being fixating to the top, corner but I was able to resolve it with half an hour.

## Animation Screen (Animation): 2 hrs
I spent about 2 hrs working on the Animations for the the Animation screen. Most of the time was spent on leanring about how animations are structured in Android and how they
interact with Views and Layouts. I spent an hour creating sub-animations before settling for the final animation in the project.

# Continuation:
Given this is a base, there is a lot that can be added to this framework and I plan on doing so purely for the knowledge. A sample of what I have in the backlog can be found in
the Product Backlog of the Trello board. Hopefully, my work was able to meet your standards or I demonstrated aspects of what you were looking for. Either way, I enjoyed doing
this coding test.
