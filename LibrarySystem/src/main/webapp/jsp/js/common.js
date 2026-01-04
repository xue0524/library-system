// 编辑图书弹窗（简易实现）
function editBook(bookId) {
    // 实际项目中可通过AJAX获取图书信息并填充表单
    document.getElementById("editModal").style.display = "block";
    document.getElementById("editId").value = bookId;
}

// 日期格式化工具函数
function formatDate(date) {
    let d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}