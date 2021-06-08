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
- Compromises and implementation decisions
- Tests
- CI
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
<br/>
### MVVM design pattern and benefits?
MVVM is an alternative design pattern to traditional design patterns such as MVP, MVC in android development.
Unlike MVP or MVC, MVVM allows <b>1 to many </b> relationshop betweeen the view and viewmodel.
This paradigm in conjunction with built-in support for Architecture components and HILT allows us to 
implement observable pattern utalising LiveData to stream-line development process.
It further reduce coupling between the View and it's companion in android development (in this case ViewModel)

### Clean architecture and benefits
Clean architecture allows us to further decouple business logics from android framework. The use of domian usecases/observer(in this case)
meaning each business logic is isloated and tested seperately from view. This also allows scaling into modular architecture by
simplay extracting domain and data layer into feature modules.<br/>
Each feature also holds it's own \_di module to allow easy refactor/removal <br/> 

## Standards
### Themes and Styles
All styles can be found under themes.xml
- Activity, Fragment tiles -> MonashApp.Text.PageTitle
- Paragraphs -> MonashApp.Text.Paragraph
- List titles, Buttons Tabs -> MonashApp.Text.Title
- List item titles, Important text snippets -> MonashApp.Text.ItemTitle
- Secondary text, captions -> MonashApp.Text.Captions
- Text inputs -> MonashApp.Text.Input

### Model naming 
- Data layer models are surfixed with xxxDO (data object) (For example, CarParkDO.kt)
- Domain layer models has no prefix nor surfix
- Presentation/ui layer models are surfixed with xxxItem (For example, CarParkItem.kt)

### Layout naming
- Root layouts are prefixed with layout_xxx
- Child layouts are prefixed with view_xxx

### Drawable resource naming
- Icons are surfixed with ic_xxx
- Background are surfixed with background_xx







