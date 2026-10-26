package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize())
                    {
//                        CircleTopRight()
//                        CircleCenteredPurple()
//                        ContactListPlain()
//                        ContactListWithCards()
//                        ContactListWithStars()
                        WaterTracker()
                    }
                }
            }
        }
    }
}

// Задание 6
@Composable
fun CircleTopRight() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Black),
        contentAlignment = Alignment.TopEnd
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "Circle Top Right"
        )
    }
}

@Composable
fun CircleCenteredPurple() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Blue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "Purple Circle",
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(Color(0xFF800080)),
            contentScale = ContentScale.FillBounds
        )
    }
}


// --- ДАННЫЕ ДЛЯ ЗАДАНИЯ 7 ---
data class Contact(
    val name: String,
    val patronymic: String,
    val surname: String,
    val phone: String,
    val address: String
)

val contactsList = listOf(
    Contact("Евгений", "Андреевич", "Лукашин", "+7 495 495 95 95", "г. Москва, 3-я улица Строителей, д. 25, кв. 12"),
    Contact("Василий", "Егорович", "Кузин", "—", "Ивановская область, дер. Крутово, д. 4"),
    Contact("Людмила", "Прокофьевна", "Калугина", "+7 495 788 78 78", "Москва, Большая Никитская, д. 43, кв. 290")
)
// --- ВСПОМОГАТЕЛЬНАЯ ФУНКЦИЯ ДЛЯ ТЕКСТА ---
@Composable
fun ContactTextBlock(contact: Contact) {
    Text(text = "Имя: ${contact.name}", fontSize = 14.sp)
    Text(text = "Отчество: ${contact.patronymic}", fontSize = 14.sp)
    Text(text = "Фамилия: ${contact.surname}", fontSize = 14.sp)
    Text(text = "Мобильный телефон: ${contact.phone}", fontSize = 14.sp)
    Text(text = "Адрес: ${contact.address}", fontSize = 14.sp)
}

// --- ВАРИАНТ 1: Простой список (левый скриншот) ---
@Composable
fun ContactListPlain() {
    Column(modifier = Modifier.padding(16.dp)) {
        contactsList.forEach { contact ->
            ContactTextBlock(contact)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// --- ВАРИАНТ 2: Список с серыми карточками (правый скриншот) ---
@Composable
fun ContactListWithCards() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        contactsList.forEach { contact ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp)
            ) {
                ContactTextBlock(contact)
            }
        }
    }
}

// --- ВАРИАНТ 3: Карточка со звездочкой (скриншот из первого сообщения) ---
@Composable
fun ContactCardWithStar(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            ContactTextBlock(contact)
        }
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Favorite",
            tint = Color(0xFFFFA500),
            modifier = Modifier.size(24.dp).padding(start = 8.dp)
        )
    }
}

@Composable
fun ContactListWithStars() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        contactsList.forEach { contact ->
            ContactCardWithStar(contact)
        }
    }
}

// Задание 8
@Composable
fun WaterTracker() {
    var waterCount by remember { mutableIntStateOf(0) }
    var consecutiveDays by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { waterCount += 250 },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (waterCount >= 1500) {
                    consecutiveDays++
                } else {
                    consecutiveDays = 0
                }
                waterCount = 0
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Завершить день")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Дней подряд с нормой: $consecutiveDays",
            fontSize = 18.sp,
            color = Color.Gray
        )
    }
}


