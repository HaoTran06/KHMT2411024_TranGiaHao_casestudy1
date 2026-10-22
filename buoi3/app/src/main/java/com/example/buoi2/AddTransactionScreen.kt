package com.example.buoi2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    onBackClick: () -> Unit = {}
) {
    var isExpense by remember { mutableStateOf(true) }
    var selectedCategory by remember { mutableStateOf("Ăn uống") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("12/04/2025") }
    var note by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val categories = listOf("Ăn uống", "Đi lại", "Mua sắm", "Giải trí", "Lương", "Thưởng")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Thêm giao dịch",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    TextButton(onClick = onBackClick) {
                        Text(text = "‹", fontSize = 28.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Toggle Tab Chi tiêu / Thu nhập
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(Color(0xFFF3F4F6), shape = RoundedCornerShape(24.dp))
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                if (isExpense) Color(0xFFFF5252) else Color.Transparent,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable { isExpense = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Chi tiêu",
                            color = if (isExpense) Color.White else Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                if (!isExpense) Color(0xFF4CAF50) else Color.Transparent,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable { isExpense = false },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Thu nhập",
                            color = if (!isExpense) Color.White else Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Chọn Danh mục
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "Danh mục", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Box(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = selectedCategory,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            trailingIcon = {
                                TextButton(onClick = { isDropdownExpanded = true }) {
                                    Text(text = "▼", fontSize = 12.sp, color = Color.Gray)
                                }
                            },
                            leadingIcon = {
                                Text(text = "🍴", fontSize = 18.sp, modifier = Modifier.padding(start = 8.dp))
                            }
                        )
                        DropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false }
                        ) {
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(category) },
                                    onClick = {
                                        selectedCategory = category
                                        isDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                // Số tiền
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "Số tiền", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        placeholder = { Text("Nhập số tiền", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        trailingIcon = { Text("đ", color = Color.Gray, modifier = Modifier.padding(end = 12.dp)) }
                    )
                }

                // Ngày giao dịch
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "Ngày giao dịch", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    OutlinedTextField(
                        value = date,
                        onValueChange = { date = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        trailingIcon = {
                            Text(text = "📅", fontSize = 16.sp, modifier = Modifier.padding(end = 12.dp))
                        }
                    )
                }

                // Ghi chú
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "Ghi chú", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    OutlinedTextField(
                        value = note,
                        onValueChange = { note = it },
                        placeholder = { Text("Nhập ghi chú (tùy chọn)", color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RoundedCornerShape(12.dp),
                        maxLines = 3
                    )
                }
            }

            // Nút Lưu
            Button(
                onClick = { /* Xử lý lưu giao dịch */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
            ) {
                Text(text = "Lưu", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}