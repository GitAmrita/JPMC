package com.example.jpmc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/* ***********This application is a single activity multi fragment application.******************
NYCSchoolFragment -> Displays a list of NYC School
SATScoreFragment -> Displays the SAT scores of the selected school.

********************Design Pattern***************
MVVM design pattern.
View is represented by fragments.
Data is represented by models.
Repository module is responsible for talking to the apis.
NetworkResult and NetworkUtils has been used to capture the success and error response from api.
The business logic resides in ViewModels.

********************Libraries used in this application***************
Coroutines with live data and Retrofit are used to talk to the api server.
Gson converter is used to convert the incoming response to JSON objects.
The fragment navigation is done by nav graph.

********************Error handling and unit testing***************
Error scenarios are handled by displaying Toast.
JUnit test framework is used for the view model tests.

*********************Extra functionality:****************************
The app is RTL language compatible.
On the first screen, the user has the option to filter the list of schools based on city or zip.
On the second screen, the school phone and website open the default dialer and browser when clicked.

*********************Some hacks in the interest of expediency:****************************
The strings and dimens are hard coded in the code instead of using the res folders.
ViewModels directly call Repository functions. Since there is no need for common api functionalities
between the two screens, I didn't use UseCase. If such a need arises and also for better scalability
of the app, UseCases could be used.
Dependency injection was not used.
* */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}