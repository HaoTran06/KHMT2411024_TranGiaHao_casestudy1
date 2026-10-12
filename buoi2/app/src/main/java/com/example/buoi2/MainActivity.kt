package com.example.buoi2 // Nhớ giữ nguyên tên package của bạn ở dòng này nhé

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                ExpenseManagerScreen()
            }
        }
    }
}

@Composable
fun ExpenseManagerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon ví tiền dạng hình tròn nền xanh
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFFE0F2FE), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "💰",
                fontSize = 40.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tiêu đề: Expense Manager
        Text(
            text = "Expense Manager",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E293B)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Phụ đề mô tả
        Text(
            text = "Quản lý chi tiêu cá nhân\nđơn giản và hiệu quả",
            fontSize = 14.sp,
            color = Color(0xFF64748B),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Nút Bắt đầu
        Button(
            onClick = { /* Xử lý sự kiện khi bấm nút */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1D4ED8)
            )
        ) {
            Text(
                text = "Bắt đầu",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,color = Color.White
            )
        }
    }
}