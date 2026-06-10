// ========================================
// RESTORAN JAWA - Main Application
// JavaScript for Traditional Restaurant
// ========================================

// Simulated Database
const database = {
    users: [
        { userID: 1, username: 'admin', password: 'admin123', fullName: 'Administrator', userGrant: 1 },
        { userID: 2, username: 'kasir', password: 'kasir123', fullName: 'Ani Kasir', userGrant: 2 },
        { userID: 3, username: 'dapur', password: 'dapur123', fullName: 'Budi Dapur', userGrant: 3 }
    ],
    foodCategory: [
        { categoryID: 1, categoryName: 'Makanan Utama' },
        { categoryID: 2, categoryName: 'Makanan Ringan' },
        { categoryID: 3, categoryName: 'Minuman' },
        { categoryID: 4, categoryName: 'Dessert' }
    ],
    foods: [
        { foodID: 1, name: 'Nasi Gudeg', price: 25000, foodCategory: 1, isAvailable: true },
        { foodID: 2, name: 'Soto Ayam', price: 20000, foodCategory: 1, isAvailable: true },
        { foodID: 3, name: 'Rawon', price: 28000, foodCategory: 1, isAvailable: true },
        { foodID: 4, name: 'Gado-Gado', price: 18000, foodCategory: 2, isAvailable: true },
        { foodID: 5, name: 'Tempe Goreng', price: 8000, foodCategory: 2, isAvailable: true },
        { foodID: 6, name: 'Es Teh Manis', price: 5000, foodCategory: 3, isAvailable: true },
        { foodID: 7, name: 'Wedang Jahe', price: 7000, foodCategory: 3, isAvailable: true },
        { foodID: 8, name: 'Klepon', price: 5000, foodCategory: 4, isAvailable: true },
        { foodID: 9, name: 'Lumpia', price: 12000, foodCategory: 2, isAvailable: true },
        { foodID: 10, name: 'Pecel', price: 15000, foodCategory: 2, isAvailable: true },
        { foodID: 11, name: 'Nasi Rames', price: 22000, foodCategory: 1, isAvailable: true },
        { foodID: 12, name: 'Es Jeruk', price: 6000, foodCategory: 3, isAvailable: true }
    ],
    orders: [],
    orderItems: []
};

let currentOrder = [];
let orderCounter = 1;
let sessionUser = null;

// ========================================
// UTILITY FUNCTIONS
// ========================================

function formatRupiah(angka) {
    return 'Rp ' + angka.toLocaleString('id-ID');
}

function getKategoriName(id) {
    const kat = database.foodCategory.find(k => k.categoryID === id);
    return kat ? kat.categoryName : '-';
}

function getRoleName(role) {
    switch(role) {
        case 1: return 'Administrator';
        case 2: return 'Kasir';
        case 3: return 'Dapur';
        default: return '-';
    }
}

function getRandomString(length) {
    const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let result = '';
    for (let i = 0; i < length; i++) {
        result += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return result;
}

function generateOrderNumber() {
    const date = new Date();
    const dateStr = date.getDate().toString().padStart(2, '0') + 
                   (date.getMonth() + 1).toString().padStart(2, '0') +
                   date.getFullYear().toString().slice(-2);
    return 'ORD' + dateStr + '-' + (orderCounter++).toString().padStart(3, '0');
}

// ========================================
// NAVIGATION FUNCTIONS
// ========================================

function showPage(pageId) {
    document.querySelectorAll('.page').forEach(page => {
        page.classList.remove('active');
    });
    document.getElementById(pageId).classList.add('active');
}

function showSection(sectionId) {
    document.querySelectorAll('.content-section').forEach(section => {
        section.classList.remove('active');
    });
    document.getElementById(sectionId).classList.add('active');
    
    document.querySelectorAll('.nav-menu li').forEach(li => {
        li.classList.remove('active');
    });
    document.querySelector(`[data-page="${sectionId}"]`)?.classList.add('active');
}

function navigateTo(pageId) {
    showSection(pageId);
}

// ========================================
// AUTHENTICATION
// ========================================

function handleLogin(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value;
    const userAlert = document.getElementById('userAlert');
    const passAlert = document.getElementById('passAlert');
    const loginAlert = document.getElementById('loginAlert');
    
    // Reset alerts
    userAlert.textContent = '';
    passAlert.textContent = '';
    loginAlert.style.display = 'none';
    
    let hasError = false;
    
    if (username === '') {
        userAlert.textContent = 'Masukkan username!';
        hasError = true;
    }
    
    if (password === '') {
        passAlert.textContent = 'Masukkan password!';
        hasError = true;
    }
    
    if (hasError) return;
    
    // Find user
    const user = database.users.find(u => u.username === username && u.password === password);
    
    if (user) {
        sessionUser = user;
        
        // Clear fields
        document.getElementById('username').value = '';
        document.getElementById('password').value = '';
        
        // Redirect based on role
        if (user.userGrant === 1) {
            showPage('adminPage');
            document.getElementById('adminName').textContent = user.fullName;
            loadAdminDashboard();
            showSection('dashboard');
        } else if (user.userGrant === 2) {
            showPage('kasirPage');
            document.getElementById('kasirName').textContent = user.fullName;
            document.getElementById('kasirWelcome').textContent = user.fullName;
            loadKasirDashboard();
            showSection('kasir-dashboard');
        } else if (user.userGrant === 3) {
            showPage('dapurPage');
            document.getElementById('dapurName').textContent = user.fullName;
            document.getElementById('dapurWelcome').textContent = user.fullName;
            loadDapurDashboard();
            showSection('dapur-dashboard');
        }
    } else {
        loginAlert.textContent = 'Username atau password salah!';
        loginAlert.style.display = 'block';
    }
}

function logout() {
    sessionUser = null;
    currentOrder = [];
    showPage('loginPage');
}

// ========================================
// ADMIN DASHBOARD
// ========================================

function loadAdminDashboard() {
    // Stats
    document.getElementById('totalMenu').textContent = database.foods.length;
    document.getElementById('totalUser').textContent = database.users.length;
    
    const today = new Date().toDateString();
    const todayOrders = database.orders.filter(o => 
        new Date(o.orderTime).toDateString() === today
    );
    document.getElementById('totalOrder').textContent = todayOrders.length;
    
    const todayRevenue = todayOrders.reduce((sum, o) => sum + o.totalPrice, 0);
    document.getElementById('totalPendapatan').textContent = formatRupiah(todayRevenue);
    
    // Recent Orders
    loadRecentOrders();
}

function loadRecentOrders() {
    const table = document.getElementById('recentOrdersTable');
    const recentOrders = database.orders.slice(-5).reverse();
    
    if (recentOrders.length === 0) {
        table.innerHTML = '<tr><td colspan="5" class="text-center">Belum ada order</td></tr>';
        return;
    }
    
    table.innerHTML = recentOrders.map(order => {
        const statusBadge = getStatusBadge(order.status);
        return `
            <tr>
                <td>${order.orderNumber}</td>
                <td>${new Date(order.orderTime).toLocaleString('id-ID')}</td>
                <td>${order.orderQuantity}</td>
                <td>${formatRupiah(order.totalPrice)}</td>
                <td><span class="status-badge ${statusBadge.class}">${statusBadge.text}</span></td>
            </tr>
        `;
    }).join('');
}

function getStatusBadge(status) {
    switch(status) {
        case 0: return { class: 'pending', text: 'Menunggu' };
        case 1: return { class: 'processing', text: 'Diproses' };
        case 2: return { class: 'completed', text: 'Selesai' };
        default: return { class: 'pending', text: 'Unknown' };
    }
}

// ========================================
// MENU MANAGEMENT
// ========================================

function loadMenuTable() {
    const table = document.getElementById('menuTable');
    const search = document.getElementById('searchMenu')?.value.toLowerCase() || '';
    const kategori = document.getElementById('filterKategori')?.value || '';
    
    let filteredFoods = database.foods.filter(food => {
        const matchSearch = food.name.toLowerCase().includes(search);
        const matchKategori = kategori === '' || food.foodCategory === parseInt(kategori);
        return matchSearch && matchKategori;
    });
    
    if (filteredFoods.length === 0) {
        table.innerHTML = '<tr><td colspan="6" class="text-center">Tidak ada menu ditemukan</td></tr>';
        return;
    }
    
    table.innerHTML = filteredFoods.map(food => `
        <tr>
            <td>${food.foodID}</td>
            <td>${food.name}</td>
            <td>${getKategoriName(food.foodCategory)}</td>
            <td>${formatRupiah(food.price)}</td>
            <td><span class="status-badge ${food.isAvailable ? 'available' : 'unavailable'}">${food.isAvailable ? 'Tersedia' : 'Tidak Tersedia'}</span></td>
            <td>
                <div class="action-btns">
                    <button class="edit" onclick="editMenu(${food.foodID})">
                        <i class="fas fa-edit"></i> Edit
                    </button>
                    <button class="delete" onclick="deleteMenu(${food.foodID})">
                        <i class="fas fa-trash"></i> Hapus
                    </button>
                </div>
            </td>
        </tr>
    `).join('');
}

function loadKategoriDropdown() {
    const selects = ['filterKategori', 'filterKategoriKasir'];
    selects.forEach(selectId => {
        const select = document.getElementById(selectId);
        if (!select) return;
        
        const currentValue = select.value;
        select.innerHTML = '<option value="">Semua Kategori</option>' + 
            database.foodCategory.map(kat => 
                `<option value="${kat.categoryID}">${kat.categoryName}</option>`
            ).join('');
        select.value = currentValue;
    });
}

function filterMenu() {
    loadMenuTable();
}

function filterMenuKasir() {
    loadMenuGrid();
}

function showAddMenuModal(id = null) {
    const modal = document.getElementById('modal');
    const title = document.getElementById('modalTitle');
    const body = document.getElementById('modalBody');
    
    const food = id ? database.foods.find(f => f.foodID === id) : null;
    title.textContent = food ? 'Edit Menu' : 'Tambah Menu Baru';
    
    body.innerHTML = `
        <form onsubmit="saveMenu(event, ${id || 'null'})">
            <div class="form-group">
                <label for="menuName">Nama Menu</label>
                <input type="text" id="menuName" value="${food?.name || ''}" required>
            </div>
            <div class="form-group">
                <label for="menuKategori">Kategori</label>
                <select id="menuKategori" required>
                    ${database.foodCategory.map(kat => 
                        `<option value="${kat.categoryID}" ${food?.foodCategory === kat.categoryID ? 'selected' : ''}>${kat.categoryName}</option>`
                    ).join('')}
                </select>
            </div>
            <div class="form-group">
                <label for="menuHarga">Harga</label>
                <input type="number" id="menuHarga" value="${food?.price || ''}" min="0" required>
            </div>
            <div class="form-group">
                <label for="menuAvailable">
                    <input type="checkbox" id="menuAvailable" ${food?.isAvailable !== false ? 'checked' : ''}>
                    Tersedia
                </label>
            </div>
            <div class="form-actions">
                <button type="button" class="btn-secondary" onclick="closeModal()">Batal</button>
                <button type="submit" class="btn-primary">
                    <i class="fas fa-save"></i> Simpan
                </button>
            </div>
        </form>
    `;
    
    modal.classList.add('active');
}

function saveMenu(event, id) {
    event.preventDefault();
    
    const name = document.getElementById('menuName').value.trim();
    const kategori = parseInt(document.getElementById('menuKategori').value);
    const harga = parseInt(document.getElementById('menuHarga').value);
    const tersedia = document.getElementById('menuAvailable').checked;
    
    if (id) {
        // Edit
        const index = database.foods.findIndex(f => f.foodID === id);
        if (index !== -1) {
            database.foods[index] = {
                ...database.foods[index],
                name, foodCategory: kategori, price: harga, isAvailable: tersedia
            };
        }
    } else {
        // Add new
        const newId = Math.max(...database.foods.map(f => f.foodID)) + 1;
        database.foods.push({
            foodID: newId,
            name, foodCategory: kategori, price: harga, isAvailable: tersedia
        });
    }
    
    closeModal();
    loadMenuTable();
    loadMenuGrid();
}

function editMenu(id) {
    showAddMenuModal(id);
}

function deleteMenu(id) {
    if (confirm('Apakah Anda yakin ingin menghapus menu ini?')) {
        const index = database.foods.findIndex(f => f.foodID === id);
        if (index !== -1) {
            database.foods.splice(index, 1);
            loadMenuTable();
            loadMenuGrid();
        }
    }
}

// ========================================
// KATEGORI MANAGEMENT
// ========================================

function loadKategoriTable() {
    const table = document.getElementById('kategoriTable');
    
    if (database.foodCategory.length === 0) {
        table.innerHTML = '<tr><td colspan="4" class="text-center">Tidak ada kategori</td></tr>';
        return;
    }
    
    table.innerHTML = database.foodCategory.map(kat => {
        const menuCount = database.foods.filter(f => f.foodCategory === kat.categoryID).length;
        return `
            <tr>
                <td>${kat.categoryID}</td>
                <td>${kat.categoryName}</td>
                <td>${menuCount} menu</td>
                <td>
                    <div class="action-btns">
                        <button class="edit" onclick="editKategori(${kat.categoryID})">
                            <i class="fas fa-edit"></i> Edit
                        </button>
                        <button class="delete" onclick="deleteKategori(${kat.categoryID})">
                            <i class="fas fa-trash"></i> Hapus
                        </button>
                    </div>
                </td>
            </tr>
        `;
    }).join('');
}

function showAddKategoriModal(id = null) {
    const modal = document.getElementById('modal');
    const title = document.getElementById('modalTitle');
    const body = document.getElementById('modalBody');
    
    const kat = id ? database.foodCategory.find(k => k.categoryID === id) : null;
    title.textContent = kat ? 'Edit Kategori' : 'Tambah Kategori Baru';
    
    body.innerHTML = `
        <form onsubmit="saveKategori(event, ${id || 'null'})">
            <div class="form-group">
                <label for="kategoriName">Nama Kategori</label>
                <input type="text" id="kategoriName" value="${kat?.categoryName || ''}" required>
            </div>
            <div class="form-actions">
                <button type="button" class="btn-secondary" onclick="closeModal()">Batal</button>
                <button type="submit" class="btn-primary">
                    <i class="fas fa-save"></i> Simpan
                </button>
            </div>
        </form>
    `;
    
    modal.classList.add('active');
}

function saveKategori(event, id) {
    event.preventDefault();
    
    const name = document.getElementById('kategoriName').value.trim();
    
    if (id) {
        const index = database.foodCategory.findIndex(k => k.categoryID === id);
        if (index !== -1) {
            database.foodCategory[index].categoryName = name;
        }
    } else {
        const newId = Math.max(...database.foodCategory.map(k => k.categoryID)) + 1;
        database.foodCategory.push({ categoryID: newId, categoryName: name });
    }
    
    closeModal();
    loadKategoriTable();
    loadKategoriDropdown();
}

function editKategori(id) {
    showAddKategoriModal(id);
}

function deleteKategori(id) {
    const menuCount = database.foods.filter(f => f.foodCategory === id).length;
    if (menuCount > 0) {
        alert(`Tidak dapat menghapus kategori. Masih ada ${menuCount} menu menggunakan kategori ini.`);
        return;
    }
    
    if (confirm('Apakah Anda yakin ingin menghapus kategori ini?')) {
        const index = database.foodCategory.findIndex(k => k.categoryID === id);
        if (index !== -1) {
            database.foodCategory.splice(index, 1);
            loadKategoriTable();
            loadKategoriDropdown();
        }
    }
}

// ========================================
// USER MANAGEMENT
// ========================================

function loadUsersTable() {
    const table = document.getElementById('usersTable');
    
    if (database.users.length === 0) {
        table.innerHTML = '<tr><td colspan="5" class="text-center">Tidak ada pengguna</td></tr>';
        return;
    }
    
    table.innerHTML = database.users.map(user => `
        <tr>
            <td>${user.userID}</td>
            <td>${user.username}</td>
            <td>${user.fullName}</td>
            <td>${getRoleName(user.userGrant)}</td>
            <td>
                <div class="action-btns">
                    <button class="edit" onclick="editUser(${user.userID})">
                        <i class="fas fa-edit"></i> Edit
                    </button>
                    <button class="delete" onclick="deleteUser(${user.userID})">
                        <i class="fas fa-trash"></i> Hapus
                    </button>
                </div>
            </td>
        </tr>
    `).join('');
}

function showAddUserModal(id = null) {
    const modal = document.getElementById('modal');
    const title = document.getElementById('modalTitle');
    const body = document.getElementById('modalBody');
    
    const user = id ? database.users.find(u => u.userID === id) : null;
    title.textContent = user ? 'Edit Pengguna' : 'Tambah Pengguna Baru';
    
    body.innerHTML = `
        <form onsubmit="saveUser(event, ${id || 'null'})">
            <div class="form-group">
                <label for="userUsername">Username</label>
                <input type="text" id="userUsername" value="${user?.username || ''}" required ${user ? 'readonly' : ''}>
            </div>
            <div class="form-group">
                <label for="userFullName">Nama Lengkap</label>
                <input type="text" id="userFullName" value="${user?.fullName || ''}" required>
            </div>
            <div class="form-group">
                <label for="userPassword">Password ${user ? '(kosongkan jika tidak diubah)' : ''}</label>
                <input type="password" id="userPassword" ${user ? '' : 'required'}>
            </div>
            <div class="form-group">
                <label for="userRole">Role</label>
                <select id="userRole" required>
                    <option value="1" ${user?.userGrant === 1 ? 'selected' : ''}>Administrator</option>
                    <option value="2" ${user?.userGrant === 2 ? 'selected' : ''}>Kasir</option>
                    <option value="3" ${user?.userGrant === 3 ? 'selected' : ''}>Dapur</option>
                </select>
            </div>
            <div class="form-actions">
                <button type="button" class="btn-secondary" onclick="closeModal()">Batal</button>
                <button type="submit" class="btn-primary">
                    <i class="fas fa-save"></i> Simpan
                </button>
            </div>
        </form>
    `;
    
    modal.classList.add('active');
}

function saveUser(event, id) {
    event.preventDefault();
    
    const username = document.getElementById('userUsername').value.trim();
    const fullName = document.getElementById('userFullName').value.trim();
    const password = document.getElementById('userPassword').value;
    const role = parseInt(document.getElementById('userRole').value);
    
    if (id) {
        const index = database.users.findIndex(u => u.userID === id);
        if (index !== -1) {
            database.users[index].fullName = fullName;
            database.users[index].userGrant = role;
            if (password) {
                database.users[index].password = password;
            }
        }
    } else {
        const newId = Math.max(...database.users.map(u => u.userID)) + 1;
        database.users.push({
            userID: newId,
            username, password, fullName, userGrant: role
        });
    }
    
    closeModal();
    loadUsersTable();
}

function editUser(id) {
    showAddUserModal(id);
}

function deleteUser(id) {
    if (id === sessionUser?.userID) {
        alert('Anda tidak dapat menghapus akun Anda sendiri!');
        return;
    }
    
    if (confirm('Apakah Anda yakin ingin menghapus pengguna ini?')) {
        const index = database.users.findIndex(u => u.userID === id);
        if (index !== -1) {
            database.users.splice(index, 1);
            loadUsersTable();
        }
    }
}

// ========================================
// KASIR FUNCTIONS
// ========================================

function loadKasirDashboard() {
    const today = new Date().toDateString();
    const todayOrders = database.orders.filter(o => 
        new Date(o.orderTime).toDateString() === today
    );
    
    document.getElementById('pendingOrders').textContent = 
        todayOrders.filter(o => o.status === 0).length;
    document.getElementById('completedOrders').textContent = 
        todayOrders.filter(o => o.status === 2).length;
    
    loadMenuGrid();
    loadOrderLists();
}

function loadMenuGrid() {
    const grid = document.getElementById('menuGrid');
    if (!grid) return;
    
    const search = document.getElementById('searchMenuKasir')?.value.toLowerCase() || '';
    const kategori = document.getElementById('filterKategoriKasir')?.value || '';
    
    let filteredFoods = database.foods.filter(food => {
        const matchSearch = food.name.toLowerCase().includes(search);
        const matchKategori = kategori === '' || food.foodCategory === parseInt(kategori);
        const matchAvailable = food.isAvailable;
        return matchSearch && matchKategori && matchAvailable;
    });
    
    if (filteredFoods.length === 0) {
        grid.innerHTML = '<p class="text-center">Tidak ada menu tersedia</p>';
        return;
    }
    
    grid.innerHTML = filteredFoods.map(food => `
        <div class="menu-item" onclick="addToOrder(${food.foodID})">
            <h4>${food.name}</h4>
            <p class="category">${getKategoriName(food.foodCategory)}</p>
            <p class="price">${formatRupiah(food.price)}</p>
        </div>
    `).join('');
}

function addToOrder(foodId) {
    const food = database.foods.find(f => f.foodID === foodId);
    if (!food) return;
    
    const existing = currentOrder.find(item => item.foodID === foodId);
    if (existing) {
        existing.qty += 1;
    } else {
        currentOrder.push({
            foodID: food.foodID,
            name: food.name,
            price: food.price,
            qty: 1
        });
    }
    
    updateOrderDisplay();
}

function updateOrderDisplay() {
    const container = document.getElementById('orderItems');
    const totalItemEl = document.getElementById('totalItem');
    const totalHargaEl = document.getElementById('totalHarga');
    
    if (currentOrder.length === 0) {
        container.innerHTML = '<p class="empty-order">Belum ada item dalam order</p>';
        totalItemEl.textContent = '0';
        totalHargaEl.textContent = formatRupiah(0);
        return;
    }
    
    let totalItem = 0;
    let totalHarga = 0;
    
    container.innerHTML = currentOrder.map((item, index) => {
        const subtotal = item.price * item.qty;
        totalItem += item.qty;
        totalHarga += subtotal;
        
        return `
            <div class="order-item">
                <div class="order-item-info">
                    <h4>${item.name}</h4>
                    <span>${formatRupiah(item.price)} x ${item.qty}</span>
                </div>
                <div class="order-item-actions">
                    <input type="number" value="${item.qty}" min="1" 
                           onchange="updateItemQty(${index}, this.value)">
                    <button class="remove-btn" onclick="removeFromOrder(${index})">
                        <i class="fas fa-trash"></i>
                    </button>
                </div>
            </div>
        `;
    }).join('');
    
    totalItemEl.textContent = totalItem;
    totalHargaEl.textContent = formatRupiah(totalHarga);
}

function updateItemQty(index, qty) {
    qty = parseInt(qty);
    if (qty <= 0) {
        removeFromOrder(index);
    } else {
        currentOrder[index].qty = qty;
        updateOrderDisplay();
    }
}

function removeFromOrder(index) {
    currentOrder.splice(index, 1);
    updateOrderDisplay();
}

function resetOrder() {
    if (currentOrder.length > 0 && !confirm('Apakah Anda yakin ingin mereset order ini?')) {
        return;
    }
    currentOrder = [];
    updateOrderDisplay();
}

function submitOrder() {
    if (currentOrder.length === 0) {
        alert('Order masih kosong!');
        return;
    }
    
    const totalPrice = currentOrder.reduce((sum, item) => sum + (item.price * item.qty), 0);
    const totalQty = currentOrder.reduce((sum, item) => sum + item.qty, 0);
    
    const order = {
        orderID: database.orders.length + 1,
        orderNumber: generateOrderNumber(),
        orderTime: new Date(),
        orderQuantity: totalQty,
        totalPrice: totalPrice,
        status: 0,
        items: [...currentOrder],
        createdBy: sessionUser?.userID
    };
    
    database.orders.push(order);
    currentOrder = [];
    updateOrderDisplay();
    
    alert(`Order berhasil disimpan!\n\nNo. Order: ${order.orderNumber}\nTotal: ${formatRupiah(order.totalPrice)}`);
    
    loadOrderLists();
    loadKasirDashboard();
}

function loadOrderLists() {
    const table = document.getElementById('orderListsTable');
    if (!table) return;
    
    const orders = database.orders.slice().reverse();
    
    if (orders.length === 0) {
        table.innerHTML = '<tr><td colspan="6" class="text-center">Belum ada order</td></tr>';
        return;
    }
    
    table.innerHTML = orders.map(order => {
        const statusBadge = getStatusBadge(order.status);
        const viewBtn = order.status === 0 ? 
            `<button class="btn-primary" onclick="processPayment(${order.orderID})">
                <i class="fas fa-money-bill"></i> Bayar
            </button>` : '';
        
        return `
            <tr>
                <td>${order.orderNumber}</td>
                <td>${new Date(order.orderTime).toLocaleString('id-ID')}</td>
                <td>${order.orderQuantity}</td>
                <td>${formatRupiah(order.totalPrice)}</td>
                <td><span class="status-badge ${statusBadge.class}">${statusBadge.text}</span></td>
                <td>${viewBtn}</td>
            </tr>
        `;
    }).join('');
}

function processPayment(orderId) {
    const order = database.orders.find(o => o.orderID === orderId);
    if (order) {
        order.status = 1; // Diproses
        alert('Pembayaran berhasil! Order sedang diproses di dapur.');
        loadOrderLists();
        loadKasirDashboard();
    }
}

// ========================================
// DAPUR FUNCTIONS
// ========================================

function loadDapurDashboard() {
    const today = new Date().toDateString();
    const todayOrders = database.orders.filter(o => 
        new Date(o.orderTime).toDateString() === today
    );
    
    document.getElementById('pendingDapur').textContent = 
        todayOrders.filter(o => o.status === 1).length;
    document.getElementById('completedDapur').textContent = 
        todayOrders.filter(o => o.status === 2).length;
    
    loadDapurOrderCards();
}

function loadDapurOrderCards() {
    const container = document.getElementById('dapurOrderCards');
    const allContainer = document.getElementById('allOrderCards');
    
    const processingOrders = database.orders.filter(o => o.status === 1);
    
    const html = processingOrders.length === 0 ? 
        '<p class="text-center">Tidak ada pesanan yang sedang diproses</p>' :
        processingOrders.map(order => createOrderCard(order)).join('');
    
    if (container) container.innerHTML = html;
    if (allContainer) allContainer.innerHTML = html;
}

function createOrderCard(order) {
    const statusClass = order.status === 2 ? 'completed' : 'processing';
    const doneBtn = order.status === 1 ? 
        `<button class="btn-done" onclick="completeOrder(${order.orderID})">
            <i class="fas fa-check"></i> Selesai
        </button>` : '';
    
    return `
        <div class="order-card ${statusClass}">
            <div class="order-card-header">
                <h3>${order.orderNumber}</h3>
                <span>${new Date(order.orderTime).toLocaleTimeString('id-ID')}</span>
            </div>
            <div class="order-card-body">
                <div class="order-card-items">
                    ${order.items.map(item => `
                        <div class="order-card-item">
                            <span>${item.qty}x ${item.name}</span>
                            <span>${formatRupiah(item.price * item.qty)}</span>
                        </div>
                    `).join('')}
                </div>
            </div>
            <div class="order-card-footer">
                <span>Total: ${formatRupiah(order.totalPrice)}</span>
                ${doneBtn}
            </div>
        </div>
    `;
}

function completeOrder(orderId) {
    const order = database.orders.find(o => o.orderID === orderId);
    if (order) {
        order.status = 2; // Selesai
        alert('Order telah selesai!');
        loadDapurDashboard();
    }
}

// ========================================
// MODAL FUNCTIONS
// ========================================

function closeModal() {
    document.getElementById('modal').classList.remove('active');
}

// ========================================
// REPORT FUNCTIONS
// ========================================

function generateReport() {
    const tanggalAwal = document.getElementById('tanggalAwal').value;
    const tanggalAkhir = document.getElementById('tanggalAkhir').value;
    
    let filteredOrders = database.orders;
    
    if (tanggalAwal && tanggalAkhir) {
        const start = new Date(tanggalAwal);
        const end = new Date(tanggalAkhir);
        
        filteredOrders = database.orders.filter(o => {
            const orderDate = new Date(o.orderTime);
            return orderDate >= start && orderDate <= end;
        });
    }
    
    const totalSales = filteredOrders.reduce((sum, o) => sum + o.totalPrice, 0);
    const totalOrders = filteredOrders.length;
    
    // Find best seller
    const itemCounts = {};
    filteredOrders.forEach(order => {
        order.items.forEach(item => {
            itemCounts[item.name] = (itemCounts[item.name] || 0) + item.qty;
        });
    });
    
    let bestSeller = '-';
    let maxCount = 0;
    Object.entries(itemCounts).forEach(([name, count]) => {
        if (count > maxCount) {
            maxCount = count;
            bestSeller = name;
        }
    });
    
    document.getElementById('reportTotal').textContent = formatRupiah(totalSales);
    document.getElementById('reportOrder').textContent = totalOrders;
    document.getElementById('reportBestSeller').textContent = bestSeller !== '-' ? `${bestSeller} (${maxCount})` : '-';
}

// ========================================
// NAV MENU CLICK HANDLER
// ========================================

document.addEventListener('DOMContentLoaded', () => {
    // Add click handlers to nav menu items
    document.querySelectorAll('.nav-menu li').forEach(item => {
        item.addEventListener('click', () => {
            const page = item.dataset.page;
            if (page) {
                showSection(page);
            }
        });
    });
    
    // Close modal on outside click
    document.getElementById('modal').addEventListener('click', (e) => {
        if (e.target.id === 'modal') {
            closeModal();
        }
    });
});

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    loadKategoriDropdown();
    
    // Set default dates for report
    const today = new Date();
    const firstDay = new Date(today.getFullYear(), today.getMonth(), 1);
    document.getElementById('tanggalAwal').valueAsDate = firstDay;
    document.getElementById('tanggalAkhir').valueAsDate = today;
});