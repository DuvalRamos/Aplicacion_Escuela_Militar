package com.example.aplicacinejercito.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onLoginSuccess: (Boolean) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val azulOscuro = Color(0xFF0F2643)
    val doradoIcono = Color(0xFFE5B22E)
    val fondoGrisClaro = Color(0xFFF2F4F7)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = fondoGrisClaro
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // --- PARTE SUPERIOR (Encabezado Azul) ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.38f)
                    .background(
                        color = azulOscuro,
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(doradoIcono, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Insignia",
                            tint = azulOscuro,
                            modifier = Modifier.size(35.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Gestión Deportiva",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Escuela Militar · Reserva de instalaciones",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }

            // --- PARTE INFERIOR (Formulario y Validaciones Específicas) ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.62f)
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Campo: Correo Institucional
                Text(
                    text = "Correo institucional",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = emailError.isNotEmpty(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color(0xFFCCCCCC),
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    )
                )
                if (emailError.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = emailError, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Campo: Contraseña
                Text(
                    text = "Contraseña",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = passwordError.isNotEmpty(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color(0xFFCCCCCC),
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    )
                )
                if (passwordError.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = passwordError, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botón Ingresar con validaciones independientes
                Button(
                    onClick = {
                        emailError = ""
                        passwordError = ""
                        var isValid = true

                        // 1. Validación específica para el Correo
                        if (email.isBlank()) {
                            emailError = "El correo no puede estar vacío."
                            isValid = false
                        } else if (!email.endsWith("@escuelamilitar.cl")) {
                            emailError = "El correo debe terminar estrictamente en @escuelamilitar.cl"
                            isValid = false
                        }

                        // 2. Validaciones específicas y separadas para la Contraseña
                        if (password.isBlank()) {
                            passwordError = "La contraseña no puede estar vacía."
                            isValid = false
                        } else {
                            val erroresPassword = mutableListOf<String>()

                            // Condición 1: Primera letra en mayúscula
                            if (!password.first().isUpperCase()) {
                                erroresPassword.add("Debe empezar con letra mayúscula.")
                            }
                            // Condición 2: Incluir mínimo 1 número
                            if (!password.any { it.isDigit() }) {
                                erroresPassword.add("Debe incluir al menos 1 número.")
                            }
                            // Condición 3: Máximo 8 caracteres
                            if (password.length > 8) {
                                erroresPassword.add("No debe superar los 8 caracteres.")
                            }

                            if (erroresPassword.isNotEmpty()) {
                                // Une solo los mensajes específicos que estén fallando
                                passwordError = erroresPassword.joinToString(" ")
                                isValid = false
                            }
                        }

                        // 3. Si todo cumple, avanza al panel correspondiente
                        if (isValid) {
                            val esAdmin = email.startsWith("admin", ignoreCase = true)
                            onLoginSuccess(esAdmin)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = azulOscuro)
                ) {
                    Text(
                        text = "Ingresar",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Nota: Correo institucional obligatorio. Contraseña: 1ª mayúscula, mín. 1 número y máx. 8 caracteres.",
                    fontSize = 10.sp,
                    color = Color(0xFF757575),
                    lineHeight = 14.sp
                )
            }
        }
    }
}