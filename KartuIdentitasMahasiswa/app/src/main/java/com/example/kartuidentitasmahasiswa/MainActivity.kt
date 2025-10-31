package com.example.kartuidentitasmahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFECECEC)),
                contentAlignment = Alignment.Center
            ) {
                KartuIdentitasMahasiswa()
            }
        }
    }
}

@Composable
fun KartuIdentitasMahasiswa() {
    Card(
        modifier = Modifier
            .width(320.dp)
            .height(200.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E88E5)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // FOTO MAHASISWA
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                // pastikan kamu sudah menambahkan foto_profil.jpg di folder res/drawable
                Image(
                    painter = painterResource(id = R.drawable.myprofil),
                    contentDescription = "Foto Mahasiswa",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(76.dp)
                        .background(Color.White, RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // DATA MAHASISWA
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Kartu Identitas Mahasiswa",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Nama: Muhamad Rifky Jaelani", color = Color.White)
                Text(text = "NIM: 23010035", color = Color.White)
                Text(text = "Jurusan: Teknik Informatika", color = Color.White)
                Text(text = "Universitas Teknologi Nusantara", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewKartuIdentitasMahasiswa() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC)),
        contentAlignment = Alignment.Center
    ) {
        KartuIdentitasMahasiswa()
    }
}
