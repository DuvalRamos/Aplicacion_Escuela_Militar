package com.example.aplicacinejercito.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CadeteScreen(onLogout: () -> Unit) {
    // Colores basados en el prototipo
    val azulOscuro = Color(0xFF0F2643)
    val doradoBoton = Color(0xFFE5B22E)
    val fondoGrisClaro = Color(0xFFF2F4F7)
    val verdeEstado = Color(0xFFD4EDDA)
    val verdeTexto = Color(0xFF155724)

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            // Barra de Navegación Inferior (Figma)
            Surface(
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavIcon(icon = Icons.Default.Home, label = "Inicio", isSelected = selectedTab == 0) { selectedTab = 0 }
                    BottomNavIcon(icon = Icons.Default.DateRange, label = "Mis reservas", isSelected = selectedTab == 1) { selectedTab = 1 }
                    BottomNavIcon(icon = Icons.Default.Star, label = "Competencias", isSelected = selectedTab == 2) { selectedTab = 2 }
                    BottomNavIcon(icon = Icons.Default.CalendarMonth, label = "Calendario", isSelected = selectedTab == 3) { selectedTab = 3 }
                    BottomNavIcon(icon = Icons.Default.Person, label = "Perfil", isSelected = selectedTab == 4) {
                        selectedTab = 4
                        onLogout() // Cierra sesión al tocar perfil como acceso directo
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(fondoGrisClaro)
                .padding(paddingValues)
        ) {
            // --- ENCABEZADO AZUL SUPERIOR ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = azulOscuro,
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 22.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Teniente · Domingo 27 de septiembre",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Hola, Valentina",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Botón de notificaciones con insignia amarilla
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(Color.White.copy(alpha = 0.15f), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notificaciones",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                        // Punto indicador de notificación
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(doradoBoton, shape = CircleShape)
                                .align(Alignment.TopEnd)
                        )
                    }
                }
            }

            // --- CUERPO PRINCIPAL CON SCROLL ---
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // --- TARJETA DE TOKENS SEMANALES ---
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "TOKENS SEMANALES",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )
                            Icon(
                                imageVector = Icons.Default.PieChart,
                                contentDescription = "Tokens",
                                tint = doradoBoton,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Tienes 2 de 3 tokens disponibles esta semana",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Barra de progreso de tokens
                        LinearProgressIndicator(
                            progress = { 0.66f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = doradoBoton,
                            trackColor = Color(0xFFE0E0E0),
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Cada reserva consume 1 token. Se renuevan cada lunes.",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                }

                // --- BOTÓN NUEVA RESERVA ---
                Button(
                    onClick = { /* Acción para crear reserva */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = doradoBoton)
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Icono",
                        tint = azulOscuro,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Nueva Reserva",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = azulOscuro
                    )
                }

                // --- SECCIÓN: PRÓXIMA RESERVA ---
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "PRÓXIMA RESERVA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        letterSpacing = 0.5.sp
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Cuadro de Fecha (LUN 28 SEP)
                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .background(azulOscuro, shape = RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "LUN", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                    Text(text = "28", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                    Text(text = "SEP", fontSize = 9.sp, color = Color.White.copy(alpha = 0.8f))
                                }
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Pista Atlética",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1A1A1A)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "09:00 – 10:30 · Pista de atletismo",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }

                            // Badge Confirmada
                            Box(
                                modifier = Modifier
                                    .background(verdeEstado, shape = RoundedCornerShape(20.dp))
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Text(
                                    text = "Confirmada",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = verdeTexto
                                )
                            }
                        }
                    }
                }

                // --- SECCIÓN: EVENTOS Y COMPETENCIAS ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "EVENTOS Y COMPETENCIAS",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        letterSpacing = 0.5.sp
                    )
                    TextButton(onClick = { /* Ver competencias */ }) {
                        Text(
                            text = "Ver competencias",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = azulOscuro
                        )
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Evento 1
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .background(Color(0xFFFCE8B2), shape = RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "SÁB 3", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8F6B00))
                                    Text(text = "OCT", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8F6B00))
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Campeonato interescuelas de atletismo",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1A1A1A)
                                )
                                Text(
                                    text = "09:00 · Estadio",
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }
                        }

                        Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)

                        // Evento 2
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .background(Color(0xFFFCE8B2), shape = RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "DOM 4", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8F6B00))
                                    Text(text = "OCT", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8F6B00))
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Copa de Fútbol Intercompañías",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1A1A1A)
                                )
                                Text(
                                    text = "09:00 · Cancha de Fútbol N.° 1",
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Componente auxiliar para los iconos de la barra inferior
@Composable
fun BottomNavIcon(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit) {
    val tintColor = if (isSelected) Color(0xFF0F2643) else Color.Gray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = tintColor, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = label, fontSize = 10.sp, color = tintColor, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
    }
}