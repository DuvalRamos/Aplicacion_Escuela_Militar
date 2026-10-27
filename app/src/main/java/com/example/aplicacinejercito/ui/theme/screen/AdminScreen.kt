package com.example.aplicacinejercito.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminScreen(onLogout: () -> Unit) {
    // Paleta de colores exacta del prototipo
    val azulOscuro = Color(0xFF0F2643)
    val doradoBoton = Color(0xFFE5B22E)
    val fondoGrisClaro = Color(0xFFF2F4F7)
    val verdeTexto = Color(0xFF2E7D32)
    val verdeFondo = Color(0xFFE8F5E9)
    val rojoTexto = Color(0xFFC62828)
    val doradoTexto = Color(0xFFB78103)
    val grisTexto = Color(0xFF616161)

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            // --- BARRA DE NAVEGACIÓN INFERIOR DE ADMIN ---
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
                    AdminNavIcon(icon = Icons.Default.Home, label = "Inicio", isSelected = selectedTab == 0) { selectedTab = 0 }
                    AdminNavIcon(icon = Icons.Default.CalendarMonth, label = "Calendario", isSelected = selectedTab == 1) { selectedTab = 1 }
                    AdminNavIcon(icon = Icons.Default.EmojiEvents, label = "Eventos", isSelected = selectedTab == 2) { selectedTab = 2 }
                    AdminNavIcon(icon = Icons.Default.Domain, label = "Instalaciones", isSelected = selectedTab == 3) { selectedTab = 3 }
                    AdminNavIcon(icon = Icons.Default.Settings, label = "Configuración", isSelected = selectedTab == 4) {
                        selectedTab = 4
                        onLogout() // Cierra sesión al hacer clic en configuración como acceso directo
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
                    .background(azulOscuro)
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Gestión deportiva · Domingo 27 de septiembre",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Botón de Notificación
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(Color.White.copy(alpha = 0.15f), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notificaciones",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(9.dp)
                                .background(doradoBoton, shape = CircleShape)
                                .align(Alignment.TopEnd)
                        )
                    }
                }
            }

            // --- CUERPO PRINCIPAL (Con Scroll) ---
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // --- SECCIÓN: ESTADO DE INSTALACIONES ---
                Text(
                    text = "ESTADO DE INSTALACIONES",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    letterSpacing = 0.5.sp
                )

                // Grilla 2x2 de métricas
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatCard(modifier = Modifier.weight(1f), number = "8", label = "Disponibles", colorText = verdeTexto)
                        StatCard(modifier = Modifier.weight(1f), number = "3", label = "Ocupadas", colorText = rojoTexto)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatCard(modifier = Modifier.weight(1f), number = "2", label = "Mantenimiento", colorText = doradoTexto)
                        StatCard(modifier = Modifier.weight(1f), number = "2", label = "No disponibles", colorText = grisTexto)
                    }
                }

                // --- TARJETA DE SOLICITUDES PENDIENTES ---
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color(0xFFEAEFF5), shape = RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Inbox,
                                contentDescription = "Solicitudes",
                                tint = azulOscuro,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Solicitudes",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1A1A1A)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "7 reservas por aprobar o rechazar",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "Ir",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // --- SECCIÓN: PRÓXIMAS ACTIVIDADES · HOY ---
                Text(
                    text = "PRÓXIMAS ACTIVIDADES · HOY",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    letterSpacing = 0.5.sp
                )

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    ActividadItem(hora = "09:00", titulo = "Pista Atlética", detalle = "Camila Pérez · 09:00 – 10:30")
                    ActividadItem(hora = "10:30", titulo = "Gimnasio de Básquetbol", detalle = "Diego Morales · 10:30 – 12:00")
                    ActividadItem(hora = "12:00", titulo = "Canchas de tenis", detalle = "Martín Leiva · 12:00 – 13:00")
                    ActividadItem(hora = "15:00", titulo = "Cancha de Rugby N.º 1", detalle = "Nicolás Vidal · 15:00 – 17:00")
                }
            }
        }
    }
}

// Componente para las 4 tarjetas cuadradas de métricas
@Composable
fun StatCard(modifier: Modifier = Modifier, number: String, label: String, colorText: Color) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = number,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorText
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = colorText
            )
        }
    }
}

// Componente para cada ítem de actividad del día
@Composable
fun ActividadItem(hora: String, titulo: String, detalle: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = hora,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F2643),
                modifier = Modifier.width(54.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = detalle,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .background(Color(0xFFE8F5E9), shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Confirmada",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
            }
        }
    }
}

// Componente auxiliar para la barra inferior del administrador
@Composable
fun AdminNavIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
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