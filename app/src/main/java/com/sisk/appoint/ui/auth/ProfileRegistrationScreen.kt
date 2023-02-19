package com.sisk.appoint.ui.auth

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarTimeline
import com.sisk.appoint.R
import com.sisk.appoint.ui.components.MenuDropDown
import kotlinx.coroutines.flow.receiveAsFlow
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileRegistrationScreen(
    viewModel: ProfileRegistrationViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navigate: () -> Unit = {}
) {
    val state by viewModel.profileState.collectAsState()
    var date by rememberSaveable { mutableStateOf(false) }
    val loader by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.ap_loading))
    val progress by animateLottieCompositionAsState(composition = loader, iterations = LottieConstants.IterateForever)
    val snackBarHostState = remember{ SnackbarHostState() }

    val sheetState = rememberSheetState()

//    LaunchedEffect(key1 = viewModel.channel){
//        viewModel.channel.receiveAsFlow().collect{
//            snackBarHostState.showSnackbar(message = it)
//        }
//    }

    val datePickerDialog = CalendarDialog(
        state = sheetState,
        config = CalendarConfig(disabledTimeline = CalendarTimeline.FUTURE, yearSelection = true, monthSelection = true),
        selection = CalendarSelection.Date {
          viewModel.onDobChange(it.toString())
        }
    )

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
        )
        {


            Column(modifier = Modifier.padding(vertical = 24.dp)) {
                Text(text = "Let's create your profile", style = MaterialTheme.typography.displaySmall)
                Text(
                    text = "This should take few minutes",
                    style = MaterialTheme.typography.titleLarge
                )
            }


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = state.name ?: "",
                    onValueChange = {
                        viewModel.onNameChange(it)
                    },
                    placeholder = { Text(text = "Name") },
                    enabled = !state.loading
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = state.surname ?: "",
                    onValueChange = {
                          viewModel.onSurnameChange(it)
                    },
                    placeholder = { Text(text = "Surname") },
                     enabled = !state.loading
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = state.cell ?: "",
                    onValueChange = {
                          viewModel.onCellChange(it)
                    },
                    placeholder = { Text(text = "Cell") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    enabled = !state.loading
                )

                MenuDropDown(options = state.genderOptions, selected = state.gender){
                    viewModel.onGenderChange(it)
                }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = state.dob ?: "",
                    onValueChange = {
                       //   viewModel.onDobChange(it)
                    },
                    placeholder = { Text(text = "Birthday") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    trailingIcon = { IconButton(onClick = { sheetState.show() }) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                    }},
                    readOnly = true,
                    enabled = !state.loading
                )



                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    enabled = !state.loading,
                    onClick = {

//                        viewModel.register{
//                            Log.d("mama", "navigate $it")
//                            if(it){
//                                navigate()
//                            }
//                        }
                    }
                ) {

                    if (state.loading) LottieAnimation(
                        composition = loader,
                        progress = { progress },
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(70.dp)
                    ) else Text(text = "Create profile")

                }

            }

        }

    }
}

@Preview
@Composable
fun ProfileRegistrationScreenPreview() {
    ProfileRegistrationScreen()
}