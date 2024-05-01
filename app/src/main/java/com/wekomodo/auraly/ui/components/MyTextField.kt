package com.wekomodo.auraly.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun MyTextField(
    @StringRes label: Int,
    @StringRes supportText: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        label = { Text(stringResource(id = label)) },
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        supportingText = { Text(text = stringResource(id = supportText)) },
        shape = RoundedCornerShape(10.dp),
        /* shape = OutlinedTextFieldDefaults.shape,
         colors = OutlinedTextFieldDefaults.colors()*/
    )
}