package com.example.cupcake.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme

var MAX_CARD_NUMBER_LENGTH = 16
var MAX_EXP_DATE_LENGTH = 4
var MAX_CVV_LENGTH = 3
@Composable
fun PaymentInputScreen(
    onValueChanged: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    var name by rememberSaveable { mutableStateOf("") }
    var cardNumber by rememberSaveable { mutableStateOf("") }
    var expDate by rememberSaveable { mutableStateOf("") }
    var cvv by rememberSaveable { mutableStateOf("") }
    var address1 by rememberSaveable { mutableStateOf("") }
    var address2 by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var zip by rememberSaveable { mutableStateOf("") }
    var state by rememberSaveable { mutableStateOf("") }

    var isError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
    ){

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                label = { Text("Card Holder Name") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = cardNumber,
                onValueChange = {
                    cardNumber = it
                },
                isError = !isValidLength(cardNumber, MAX_CARD_NUMBER_LENGTH),
                singleLine = true,
                supportingText = {
                    if (isError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.error,
                            text = "Limit: ${cardNumber.length}/$MAX_CARD_NUMBER_LENGTH",
                        )
                    }
                 },
                label = { Text("Card Number") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
        }
        Column() {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                OutlinedTextField(
                    value = expDate,
                    onValueChange = { expDate = it  },
                    isError = !isValidLength(expDate, MAX_EXP_DATE_LENGTH),
                    singleLine = true,
                    supportingText = {
                        if (isError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                color = MaterialTheme.colorScheme.error,
                                text = "Limit: ${expDate.length}/$MAX_EXP_DATE_LENGTH",
                            )
                        }
                    },
                    label = { Text("Exp. Date") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                    //modifier = Modifier.height(30.dp),

                )
                OutlinedTextField(
                    value = cvv,
                    onValueChange = { cvv = it },
                    isError = !isValidLength(cvv, MAX_CVV_LENGTH),
                    singleLine = true,
                    supportingText = {
                        if (isError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                color = MaterialTheme.colorScheme.error,
                                text = "Limit: ${cvv.length}/$MAX_CVV_LENGTH",
                            )
                        }
                    },

                    label = { Text("CVV") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )

                )
            }

        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = address1,
                onValueChange = {
                    address1 = it
                    onValueChanged(it)
                                },
                singleLine = true,
                label = { Text(text = "Address Line 1") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = address2,
                onValueChange = { address2 = it },
                label = { Text(text = "Address Line 2") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                singleLine = true,
                label = { Text(text = "City") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column() {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                OutlinedTextField(
                    value = zip,
                    onValueChange = { zip = it },
                    singleLine = true,
                    label = { Text("Zip Code") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )


                )
                OutlinedTextField(
                    value = state,
                    onValueChange = { state = it },
                    label = { Text("State") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    modifier = Modifier.weight(1f)

                )
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        //Cancel and Next Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                //enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.done))
            }
        }
    }
}

fun isValidLength(input: String, maxLength: Int): Boolean {
    return (input.length <= maxLength) //if the input length is <= max length, it's true
}

@Preview
@Composable
fun PaymentInputPreview() {
    CupcakeTheme {
        PaymentInputScreen(
            modifier = Modifier.fillMaxHeight()
        )
    }
}