package com.example.nearnestapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearnestapp.ui.components.NearNestLogo
import com.example.nearnestapp.ui.theme.NearNestPrimary
import com.example.nearnestapp.ui.theme.NearNestTextPrimary
import com.example.nearnestapp.ui.theme.NearNestTextSecondary

data class DemoCredential(
    val role: String,
    val name: String,
    val email: String,
    val color: Color
)

@Composable
fun LoginScreen(
    onLoginSuccess: (name: String, email: String, role: String) -> Unit,
    onNavigateToSignup: () -> Unit = {}
) {
    var email by remember { mutableStateOf("runner1@nearnest.local") }
    var password by remember { mutableStateOf("12345678") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf("RUNNER") }
    var selectedName by remember { mutableStateOf("Rohan (Delivery)") }

    val demoCredentials = listOf(
        DemoCredential("CUSTOMER", "Badal Prasad", "customer@nearnest.local", Color(0xFF059669)),
        DemoCredential("SELLER", "Aarav (Stationery)", "seller1@nearnest.local", Color(0xFF9333EA)),
        DemoCredential("RUNNER", "Rohan (Delivery)", "runner1@nearnest.local", Color(0xFF2563EB)),
        DemoCredential("ADMINISTRATOR", "System Admin", "admin@nearnest.local", Color(0xFFDC2626))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(horizontal = 20.dp, vertical = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Top Logo Icon
        NearNestLogo(size = 56.dp)

        Spacer(modifier = Modifier.height(16.dp))

        // Header Titles
        Text(
            text = "Sign in to NearNest",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = NearNestTextPrimary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Need It. Get It Nearby.",
            fontSize = 14.sp,
            color = NearNestTextSecondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Main Login Card Container
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                // Email Label & Input
                Text(
                    text = "Campus Email Address",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("runner1@nearnest.local", color = NearNestTextSecondary) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = NearNestTextSecondary
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NearNestPrimary,
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Password Label & Input
                Text(
                    text = "Password",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NearNestTextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("••••••••••", color = NearNestTextSecondary) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = NearNestTextSecondary
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Visibility",
                                tint = NearNestTextSecondary
                            )
                        }
                    },
                    singleLine = true,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NearNestPrimary,
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Sign In Button
                Button(
                    onClick = { onLoginSuccess(selectedName, email, selectedRole) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF059669))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Sign In",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = Color(0xFFF1F5F9))
                Spacer(modifier = Modifier.height(20.dp))

                // INSTANT DEMO CREDENTIALS Section Header
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = NearNestPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "INSTANT DEMO CREDENTIALS",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF64748B),
                        letterSpacing = 0.5.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 2x2 Grid of Demo Credentials
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        DemoCredentialCard(
                            credential = demoCredentials[0],
                            isSelected = selectedRole == demoCredentials[0].role,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                selectedRole = demoCredentials[0].role
                                selectedName = demoCredentials[0].name
                                email = demoCredentials[0].email
                                onLoginSuccess(selectedName, email, selectedRole)
                            }
                        )
                        DemoCredentialCard(
                            credential = demoCredentials[1],
                            isSelected = selectedRole == demoCredentials[1].role,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                selectedRole = demoCredentials[1].role
                                selectedName = demoCredentials[1].name
                                email = demoCredentials[1].email
                                onLoginSuccess(selectedName, email, selectedRole)
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        DemoCredentialCard(
                            credential = demoCredentials[2],
                            isSelected = selectedRole == demoCredentials[2].role,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                selectedRole = demoCredentials[2].role
                                selectedName = demoCredentials[2].name
                                email = demoCredentials[2].email
                                onLoginSuccess(selectedName, email, selectedRole)
                            }
                        )
                        DemoCredentialCard(
                            credential = demoCredentials[3],
                            isSelected = selectedRole == demoCredentials[3].role,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                selectedRole = demoCredentials[3].role
                                selectedName = demoCredentials[3].name
                                email = demoCredentials[3].email
                                onLoginSuccess(selectedName, email, selectedRole)
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Register Link for 3 Roles: Customer, Seller, or Runner
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account yet?",
                fontSize = 13.sp,
                color = NearNestTextSecondary
            )
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(
                onClick = onNavigateToSignup,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Register as Customer, Seller, or Runner",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF059669)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DemoCredentialCard(
    credential: DemoCredential,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(if (isSelected) Color(0xFFF8FAFC) else Color(0xFFF8FAFC))
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) Color(0xFF0F172A) else Color(0xFFE2E8F0),
                shape = RoundedCornerShape(14.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Column {
            Text(
                text = credential.role,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = credential.color,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = credential.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = NearNestTextPrimary,
                maxLines = 1
            )
        }
    }
}
