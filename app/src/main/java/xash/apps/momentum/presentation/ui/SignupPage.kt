package xash.apps.momentum.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xash.apps.momentum.data.manager.UrbanistMedium
import xash.apps.momentum.data.manager.UrbanistRegular
import xash.apps.momentum.presentation.components.AnimatedDivider
import xash.apps.momentum.presentation.components.FloatingCard
import xash.apps.momentum.presentation.components.GradientButton
import xash.apps.momentum.ui.theme.blue
import xash.apps.momentum.ui.theme.peanut
import xash.apps.momentum.ui.theme.purple

@Preview(showBackground = true)
@Composable
fun SignupPage() {
    // State for signup form fields
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Background with gradient and blur effect
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.95f)).blur(128.dp)
    ) {
        // Animated floating cards in background - same as login page but with different positions
        FloatingCard(
            baseOffsetY = 90.dp,
            baseOffsetX = 20.dp,
            baseRotation = 15f,
            width = 150.dp,
            height = 120.dp,
            color = purple,
            animationDelay = 0
        )

        FloatingCard(
            baseOffsetY = 220.dp,
            baseOffsetX = 200.dp,
            baseRotation = -10f,
            width = 160.dp,
            height = 130.dp,
            color = blue,
            animationDelay = 150
        )

        FloatingCard(
            baseOffsetY = 450.dp,
            baseOffsetX = (-20).dp,
            baseRotation = 22f,
            width = 140.dp,
            height = 110.dp,
            color = blue,
            animationDelay = 300
        )

        FloatingCard(
            baseOffsetY = 580.dp,
            baseOffsetX = 210.dp,
            baseRotation = -5f,
            width = 150.dp,
            height = 120.dp,
            color = peanut,
            animationDelay = 200
        )
    }

    // Main content column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(26.dp))

        // App title and subtitle
        Text(
            text = "Momentum",
            color = Color.White,
            style = TextStyle(
                fontFamily = UrbanistMedium,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Task Manager",
            color = Color.White.copy(alpha = 0.7f),
            style = TextStyle(
                fontFamily = UrbanistRegular,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        AnimatedDivider()

        Spacer(modifier = Modifier.height(40.dp))

        // Signup form
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White.copy(0.1f)),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CREATE ACCOUNT",
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = UrbanistMedium,
                        fontSize = 22.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Name field
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Full Name", color = Color.White.copy(0.7f)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Name Icon",
                            tint = Color.White.copy(0.7f)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = peanut,
                        unfocusedIndicatorColor = Color.White.copy(0.5f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    textStyle = TextStyle(
                        fontFamily = UrbanistRegular,
                        fontSize = 16.sp
                    ),
                    shape = RoundedCornerShape(32.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email", color = Color.White.copy(0.7f)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon",
                            tint = Color.White.copy(0.7f)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = purple,
                        unfocusedIndicatorColor = Color.White.copy(0.5f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    textStyle = TextStyle(
                        fontFamily = UrbanistRegular,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    shape = RoundedCornerShape(32.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password", color = Color.White.copy(0.7f)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password Icon",
                            tint = Color.White.copy(0.7f)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Lock else Icons.Default.Edit,
                            contentDescription = "Password Toggle",
                            tint = Color.White.copy(0.7f),
                            modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                        )
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = blue,
                        unfocusedIndicatorColor = Color.White.copy(0.5f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    textStyle = TextStyle(
                        fontFamily = UrbanistRegular,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(32.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Confirm Password field
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Confirm Password", color = Color.White.copy(0.7f)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Confirm Password Icon",
                            tint = Color.White.copy(0.7f)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = if (confirmPasswordVisible) Icons.Default.Lock else Icons.Default.Edit,
                            contentDescription = "Confirm Password Toggle",
                            tint = Color.White.copy(0.7f),
                            modifier = Modifier.clickable { confirmPasswordVisible = !confirmPasswordVisible }
                        )
                    },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = blue,
                        unfocusedIndicatorColor = Color.White.copy(0.5f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    textStyle = TextStyle(
                        fontFamily = UrbanistRegular,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(32.dp)
                )

                Spacer(modifier = Modifier.height(22.dp))

                // Terms and conditions checkbox
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var termsAccepted by remember { mutableStateOf(false) }

                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = purple,
                            uncheckedColor = Color.White.copy(0.7f),
                            checkmarkColor = Color.White
                        )
                    )

                    Text(
                        text = "I agree to the ",
                        color = Color.White.copy(0.7f),
                        style = TextStyle(
                            fontFamily = UrbanistRegular,
                            fontSize = 14.sp
                        )
                    )

                    Text(
                        text = "Terms & Conditions",
                        color = blue,
                        style = TextStyle(
                            fontFamily = UrbanistMedium,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.clickable { /* Open terms */ }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Signup button
                GradientButton(
                    text = "Create Account",
                    onClick = { /* Handle signup */ }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login option
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account? ",
                color = Color.White.copy(0.7f),
                style = TextStyle(
                    fontFamily = UrbanistRegular,
                    fontSize = 16.sp
                )
            )

            Text(
                text = "Sign In",
                color = purple,
                style = TextStyle(
                    fontFamily = UrbanistMedium,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.clickable { /* Handle login navigation */ }
            )
        }
    }
}