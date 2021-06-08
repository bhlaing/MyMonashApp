# MyMonashApp
## Table of content
- Overview
- Requirements
- Usage
- Technologies and Libraries 
- Architecture and Design decisions
- Standards
  - Themes/Styles
  - Model naming
  - Layout naming
  - Drawable resource naming
- Compromises and implementation decisions
- Tests
- CI/CD
- Acknowledgement


## Overview
My monash app is developed by Phyo (Billy) Hlaing in accordance with requirements defined by Monash University coding challenge. 
The app intends to provide a variety of details for Monash university students such as profile information, study week, lectures for the day, shuttle buse schedules
and parking space information.

## Requirements
[Please see this document for requirements](https://github.com/bhlaing/MyMonashApp/blob/master/challenge.docx)

## Usage
Launching - Simply launch the app by tapping on the app icon <br />
Refreshing - Kill and Launch 

## Technologies and Libraries 
App uses 100% kotlin, Architecture components, Coroutines, FirebaseFirestore, Hilt, ViewModel, LiveData, Timber

## Architecture and Design decisions
My monash app is implemented in MVVM pattern in conjunction with clean architecture
<br/><br/>
**MVVM design pattern and benefits?**
<br/><br/>
MVVM is an alternative design pattern to traditional design patterns such as MVP, MVC in android development.
Unlike MVP or MVC, MVVM allows <b>1 to many </b> relationshop betweeen the View and ViewModel.
This paradigm in conjunction with built-in support for Architecture components and HILT allows us to 
implement observable pattern utalising LiveData to stream-line development process.
It further reduce coupling between the View and it's companion in android development (in this case ViewModel)

**Clean architecture and benefits**
<br/><br/>
Clean architecture allows us to further decouple business logics from android framework. The use of domain usecases/observer(in this case)
meaning each business logic is isolated and tested seperately from view. This also allows scaling into modular architecture by
simplay extracting domain and data layer into feature modules <br/>
Each feature also holds it's own \_di module to allow easy refactor/removal <br/> 

## Standards
<br/>
**Themes and Styles**
All styles can be found under themes.xml
- Activity, Fragment tiles -> MonashApp.Text.PageTitle
- Paragraphs -> MonashApp.Text.Paragraph
- List titles, Buttons Tabs -> MonashApp.Text.Title
- List item titles, Important text snippets -> MonashApp.Text.ItemTitle
- Secondary text, captions -> MonashApp.Text.Captions
- Text inputs -> MonashApp.Text.Input

**Model naming**
- Data layer models are surfixed with xxxDO (data object) (For example, CarParkDO.kt)
- Domain layer models has no prefix nor surfix
- Presentation/ui layer models are surfixed with xxxItem (For example, CarParkItem.kt)

**Layout naming**
- Root layouts are prefixed with layout_xxx
- Child layouts are prefixed with view_xxx

**Drawable resource naming**
- Icons are surfixed with ic_xxx
- Background are surfixed with background_xx

## Compromises and Implementation decisions
### Implementation decisions 

**Asynchronous flow**
- Monash app utalises coroutine flows to achieve live data update for available carparks and shuttle bus information
- Each section in recycler-view is updated seperately to avoid refreshing the whole UI everytime data stream is updated

**Background shadow**
- A custom Shape uses _@android:drawable/dialog_holo_light_frame_ as background drawable and top position set to _2dp_ <br/>
  to achieve y-offset defined by requirement
- A custom theme _Theme.DropShadow_ is added for reusability

### Compromises

**Graphics**
- Darker background shadow as supposed to the one in the requirement. Current shadow uses readily available _@android:drawable/dialog_holo_light_frame_
  This is due to time-constraint and focus on implementation. Ideally we may want to create 9patch image for the shadow and replace current image. 
- Slight color different for TODAY box at the top. Also assumed background as such should be provided by designers ESPECIALLY when gradients are involved.
- <br />
**Data**
- User profile/Lectures is hard-coded to reduce overhead. Firebase implementation has been demonstrated by Carpark and Shuttle bus services to provide evidence of asynchronous data retrieval. 
**Tests**
- Espresso tests considered out-of-scope as assessment criteria does not include them and also due to time-constraint. Consider Screen/Robot pattern for expresso 

(It was quite a lot of code to write :D)
## Tests
100% unit-tests for Domain mappers and ViewModel

### CI/CD
Please see _.github/workflows/ci_pr_build.yml_ for implementation
<br />
[Failed CI to as proof can be found in this pull request](https://github.com/bhlaing/MyMonashApp/pull/1)

### Acknowledgement 
I would like to thank PK Heng, Linden Darling and Damien Smith for providing me with this opportunity.










