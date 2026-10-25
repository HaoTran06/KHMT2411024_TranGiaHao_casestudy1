package com.example.buoi2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------- 1. Model dữ liệu giao dịch ----------
data class TransactionItem(
    val id: Int,
    val category: String,
    val note: String,
    val amount: Long,
    val date: String,
    val isIncome: Boolean = false
)

// ---------- 2. Các hàm tiện ích ----------
fun formatMoney(value: Long): String {
    return "%,d".format(value).replace(',', '.') + " đ"
}

fun categoryEmoji(category: String): String = when (category) {
    "Ăn uống" -> "🍴"
    "Di chuyển" -> "🚌"
    "Mua sắm" -> "🛍️"
    "Lương" -> "💰"
    else -> "📌"
}

// ---------- 3. MainActivity ----------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DashboardScreen()
            }
        }
    }
}

// ---------- 4. Màn hình Dashboard ----------
@Composable
fun DashboardScreen() {
    // Danh sách dữ liệu mẫu
    val transactions = remember {
        listOf(
            TransactionItem(1, "Lương", "Lương tháng 4", 10_000_000, "10/04/2025", true),
            TransactionItem(2, "Ăn uống", "Ăn trưa", 100_000, "12/04/2025", false),
            TransactionItem(3, "Di chuyển", "Đổ xăng", 50_000, "11/04/2025", false),
            TransactionItem(4, "Mua sắm", "Mua áo", 350_000, "09/04/2025", false)
        )
    }

    val totalIncome = transactions.filter { it.isIncome }.sumOf { it.amount }
    val totalExpense = transactions.filter { !it.isIncome }.sumOf { it.amount }
    val balance = totalIncome - totalExpense

    Scaffold(
        containerColor = Color(0xFFF5F7FB),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Xử lý mở màn hình thêm giao dịch ở buổi 4 */ },
                icon = { Icon(Icons.Default.Add, contentDescription = "Thêm") },
                text = { Text("Thêm giao dịch") },
                containerColor = Color(0xFF2563EB),
                contentColor = Color.White
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Tổng quan tài chính",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            item {
                BalanceCard(balance = balance)
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SummaryCard(
                        title = "Thu nhập",
                        value = totalIncome,
                        emoji = "📥",
                        color = Color(0xFF16A34A),
                        modifier = Modifier.weight(1f)
                    )
                    SummaryCard(
                        title = "Chi tiêu",
                        value = totalExpense,
                        emoji = "📤",
                        color = Color(0xFFDC2626),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Giao dịch gần đây",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )
            }

            items(transactions) { transaction ->
                TransactionItemRow(transaction = transaction)
            }
        }
    }
}

// ---------- 5. Các Composable thành phần phụ trợ ----------

@Composable
fun BalanceCard(balance: Long) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Số dư hiện tại",
                color = Color.LightGray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = formatMoney(balance),
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SummaryCard(
    title: String,
    value: Long,
    emoji: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = formatMoney(value),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun TransactionItemRow(transaction: TransactionItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF1F5F9)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = categoryEmoji(transaction.category),
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transaction.note,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF1E293B)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = transaction.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Text(
                text = (if (transaction.isIncome) "+" else "-") + formatMoney(transaction.amount),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = if (transaction.isIncome) Color(0xFF16A34A) else Color(0xFFDC2626)
            )
        }
    }
}