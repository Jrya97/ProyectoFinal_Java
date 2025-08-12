// Configuración de la API
const API_BASE_URL = 'http://localhost:8080/api';

// Variables globales
let currentEditingId = null;
let currentEditingType = null;
let mascotasData = [];

// Inicialización
document.addEventListener('DOMContentLoaded', function() {
    loadPropietarios();
    loadMascotas();
    loadConsultas();
    loadPropietariosSelect();
    loadMascotasSelect();
    
    // Event listeners para formularios
    document.getElementById('propietarioForm').addEventListener('submit', handlePropietarioSubmit);
    document.getElementById('mascotaForm').addEventListener('submit', handleMascotaSubmit);
    document.getElementById('consultaForm').addEventListener('submit', handleConsultaSubmit);
    
    // Event delegation para botones de acción
    document.addEventListener('click', function(event) {
        if (event.target.matches('[data-action]')) {
            const action = event.target.getAttribute('data-action');
            const id = parseInt(event.target.getAttribute('data-id'));
            
            switch(action) {
                case 'edit-propietario':
                    editPropietario(id);
                    break;
                case 'delete-propietario':
                    deletePropietario(id);
                    break;
                case 'edit-mascota':
                    editMascota(id);
                    break;
                case 'delete-mascota':
                    deleteMascota(id);
                    break;
                case 'edit-consulta':
                    editConsulta(id);
                    break;
                case 'delete-consulta':
                    deleteConsulta(id);
                    break;
            }
        }
    });
});

// Hacer funciones globalmente accesibles
window.editPropietario = editPropietario;
window.deletePropietario = deletePropietario;
window.editMascota = editMascota;
window.deleteMascota = deleteMascota;
window.editConsulta = editConsulta;
window.deleteConsulta = deleteConsulta;
window.showTab = showTab;
window.clearPropietarioForm = clearPropietarioForm;
window.clearMascotaForm = clearMascotaForm;
window.clearConsultaForm = clearConsultaForm;
window.loadPropietarios = loadPropietarios;
window.loadMascotas = loadMascotas;
window.loadConsultas = loadConsultas;

// Funciones de utilidad
function showLoading() {
    document.getElementById('loading').classList.remove('hidden');
}

function hideLoading() {
    document.getElementById('loading').classList.add('hidden');
}

function showMessage(message, type = 'success') {
    const messageEl = document.getElementById('message');
    messageEl.textContent = message;
    messageEl.className = `message ${type}`;
    messageEl.classList.remove('hidden');
    
    setTimeout(() => {
        messageEl.classList.add('hidden');
    }, 3000);
}

function showTab(tabName) {
    // Ocultar todas las pestañas
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.classList.remove('active');
    });
    
    // Remover clase active de todos los botones
    document.querySelectorAll('.tab-button').forEach(btn => {
        btn.classList.remove('active');
    });
    
    // Mostrar la pestaña seleccionada
    document.getElementById(tabName).classList.add('active');
    
    // Activar el botón correspondiente
    const activeButton = document.querySelector(`[onclick="showTab('${tabName}')"]`);
    if (activeButton) {
        activeButton.classList.add('active');
    }
}

// API Calls
async function apiCall(endpoint, method = 'GET', data = null) {
    console.log(`🔄 API Call: ${method} ${API_BASE_URL}${endpoint}`);
    if (data) {
        console.log('📤 Data:', JSON.stringify(data, null, 2));
    }
    
    const config = {
        method,
        headers: {
            'Content-Type': 'application/json',
        },
    };
    
    if (data) {
        config.body = JSON.stringify(data);
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, config);
        
        console.log(`📥 Response: ${response.status} ${response.statusText}`);
        
        if (!response.ok) {
            const errorText = await response.text();
            console.error('❌ Error response:', errorText);
            throw new Error(`HTTP ${response.status}: ${errorText || response.statusText}`);
        }
        
        const contentType = response.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            const result = await response.json();
            console.log('✅ Success:', result);
            return result;
        }
        
        console.log('✅ Success (no JSON)');
        return null;
    } catch (error) {
        console.error('💥 API Error:', error);
        throw error;
    }
}

// PROPIETARIOS
async function loadPropietarios() {
    try {
        showLoading();
        const propietarios = await apiCall('/propietarios');
        displayPropietarios(propietarios);
    } catch (error) {
        console.error('Error loading propietarios:', error);
    } finally {
        hideLoading();
    }
}

function displayPropietarios(propietarios) {
    const tbody = document.querySelector('#propietariosTable tbody');
    tbody.innerHTML = '';
    
    propietarios.forEach(propietario => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${propietario.id}</td>
            <td>${propietario.nombre}</td>
            <td>${propietario.apellido || ''}</td>
            <td>${propietario.telefono}</td>
            <td>${propietario.email}</td>
            <td>${propietario.direccion || ''}</td>
            <td>
                <button class="action-btn edit-btn" data-id="${propietario.id}" data-action="edit-propietario">Editar</button>
                <button class="action-btn delete-btn" data-id="${propietario.id}" data-action="delete-propietario">Eliminar</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

async function handlePropietarioSubmit(event) {
    event.preventDefault();
    
    const formData = {
        nombre: document.getElementById('propietarioNombre').value,
        apellido: document.getElementById('propietarioApellido').value,
        telefono: document.getElementById('propietarioTelefono').value,
        email: document.getElementById('propietarioEmail').value,
        direccion: document.getElementById('propietarioDireccion').value
    };
    
    const id = document.getElementById('propietarioId').value;
    
    try {
        showLoading();
        
        if (id) {
            // Actualizar
            await apiCall(`/propietarios/${id}`, 'PUT', formData);
            showMessage('Propietario actualizado exitosamente');
        } else {
            // Crear
            await apiCall('/propietarios', 'POST', formData);
            showMessage('Propietario creado exitosamente');
        }
        
        clearPropietarioForm();
        loadPropietarios();
        loadPropietariosSelect();
    } catch (error) {
        console.error('Error saving propietario:', error);
        showMessage('Error al guardar propietario: ' + (error.message || 'Error desconocido'), 'error');
    } finally {
        hideLoading();
    }
}

async function editPropietario(id) {
    try {
        showLoading();
        const propietario = await apiCall(`/propietarios/${id}`);
        
        document.getElementById('propietarioId').value = propietario.id;
        document.getElementById('propietarioNombre').value = propietario.nombre;
        document.getElementById('propietarioApellido').value = propietario.apellido || '';
        document.getElementById('propietarioTelefono').value = propietario.telefono;
        document.getElementById('propietarioEmail').value = propietario.email;
        document.getElementById('propietarioDireccion').value = propietario.direccion || '';
    } catch (error) {
        console.error('Error loading propietario:', error);
    } finally {
        hideLoading();
    }
}

async function deletePropietario(id) {
    if (!confirm('¿Está seguro de que desea eliminar este propietario?')) {
        return;
    }
    
    try {
        showLoading();
        await apiCall(`/propietarios/${id}`, 'DELETE');
        showMessage('Propietario eliminado exitosamente');
        loadPropietarios();
        loadPropietariosSelect();
    } catch (error) {
        console.error('Error deleting propietario:', error);
    } finally {
        hideLoading();
    }
}

function clearPropietarioForm() {
    document.getElementById('propietarioForm').reset();
    document.getElementById('propietarioId').value = '';
}

// MASCOTAS
async function loadMascotas() {
    try {
        showLoading();
        const mascotas = await apiCall('/mascotas');
        displayMascotas(mascotas);
    } catch (error) {
        console.error('Error loading mascotas:', error);
    } finally {
        hideLoading();
    }
}

function displayMascotas(mascotas) {
    const tbody = document.querySelector('#mascotasTable tbody');
    tbody.innerHTML = '';
    
    mascotas.forEach(mascota => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${mascota.id}</td>
            <td>${mascota.nombre}</td>
            <td>${mascota.especie}</td>
            <td>${mascota.raza}</td>
            <td>${mascota.edad}</td>
            <td>ID: ${mascota.propietarioId}</td>
            <td>
                <button class="action-btn edit-btn" data-id="${mascota.id}" data-action="edit-mascota">Editar</button>
                <button class="action-btn delete-btn" data-id="${mascota.id}" data-action="delete-mascota">Eliminar</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

async function handleMascotaSubmit(event) {
    event.preventDefault();
    
    const formData = {
        nombre: document.getElementById('mascotaNombre').value,
        especie: document.getElementById('mascotaEspecie').value,
        raza: document.getElementById('mascotaRaza').value,
        edad: parseInt(document.getElementById('mascotaEdad').value),
        propietarioId: parseInt(document.getElementById('mascotaPropietarioId').value)
    };
    
    const id = document.getElementById('mascotaId').value;
    
    try {
        showLoading();
        
        if (id) {
            // Actualizar
            await apiCall(`/mascotas/${id}`, 'PUT', formData);
            showMessage('Mascota actualizada exitosamente');
        } else {
            // Crear
            await apiCall('/mascotas', 'POST', formData);
            showMessage('Mascota creada exitosamente');
        }
        
        clearMascotaForm();
        loadMascotas();
        loadMascotasSelect();
    } catch (error) {
        console.error('Error saving mascota:', error);
    } finally {
        hideLoading();
    }
}

async function editMascota(id) {
    try {
        showLoading();
        const mascota = await apiCall(`/mascotas/${id}`);
        
        document.getElementById('mascotaId').value = mascota.id;
        document.getElementById('mascotaNombre').value = mascota.nombre;
        document.getElementById('mascotaEspecie').value = mascota.especie;
        document.getElementById('mascotaRaza').value = mascota.raza;
        document.getElementById('mascotaEdad').value = mascota.edad;
        document.getElementById('mascotaPropietarioId').value = mascota.propietarioId;
    } catch (error) {
        console.error('Error loading mascota:', error);
    } finally {
        hideLoading();
    }
}

async function deleteMascota(id) {
    if (!confirm('¿Está seguro de que desea eliminar esta mascota?')) {
        return;
    }
    
    try {
        showLoading();
        await apiCall(`/mascotas/${id}`, 'DELETE');
        showMessage('Mascota eliminada exitosamente');
        loadMascotas();
        loadMascotasSelect();
    } catch (error) {
        console.error('Error deleting mascota:', error);
    } finally {
        hideLoading();
    }
}

function clearMascotaForm() {
    document.getElementById('mascotaForm').reset();
    document.getElementById('mascotaId').value = '';
}

// CONSULTAS
async function loadConsultas() {
    try {
        showLoading();
        // Cargar mascotas primero para tener los nombres disponibles
        if (mascotasData.length === 0) {
            mascotasData = await apiCall('/mascotas');
        }
        const consultas = await apiCall('/consultas');
        displayConsultas(consultas);
    } catch (error) {
        console.error('Error loading consultas:', error);
    } finally {
        hideLoading();
    }
}

function displayConsultas(consultas) {
    const tbody = document.querySelector('#consultasTable tbody');
    tbody.innerHTML = '';
    
    consultas.forEach(consulta => {
        const row = document.createElement('tr');
        const fechaFormateada = consulta.fecha ? consulta.fecha.split('T')[0] : 'N/A';
        
        // Buscar el nombre de la mascota
        const mascota = mascotasData.find(m => m.id === consulta.mascotaId);
        const mascotaInfo = mascota ? `${mascota.nombre} (${mascota.especie})` : `ID: ${consulta.mascotaId}`;
        
        row.innerHTML = `
            <td>${consulta.id}</td>
            <td>${fechaFormateada}</td>
            <td>${consulta.motivo}</td>
            <td>${consulta.tratamiento}</td>
            <td>${mascotaInfo}</td>
            <td>
                <button class="action-btn edit-btn" data-id="${consulta.id}" data-action="edit-consulta">Editar</button>
                <button class="action-btn delete-btn" data-id="${consulta.id}" data-action="delete-consulta">Eliminar</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

async function handleConsultaSubmit(event) {
    event.preventDefault();
    
    const formData = {
        fecha: document.getElementById('consultaFecha').value,
        motivo: document.getElementById('consultaMotivo').value,
        tratamiento: document.getElementById('consultaTratamiento').value,
        mascotaId: parseInt(document.getElementById('consultaMascotaId').value)
    };
    
    const id = document.getElementById('consultaId').value;
    
    try {
        showLoading();
        
        if (id) {
            // Actualizar
            await apiCall(`/consultas/${id}`, 'PUT', formData);
            showMessage('Consulta actualizada exitosamente');
        } else {
            // Crear
            await apiCall('/consultas', 'POST', formData);
            showMessage('Consulta creada exitosamente');
        }
        
        clearConsultaForm();
        loadConsultas();
    } catch (error) {
        console.error('Error saving consulta:', error);
        showMessage('Error al guardar consulta: ' + (error.message || 'Error desconocido'), 'error');
    } finally {
        hideLoading();
    }
}

async function editConsulta(id) {
    try {
        showLoading();
        const consulta = await apiCall(`/consultas/${id}`);
        
        document.getElementById('consultaId').value = consulta.id;
        document.getElementById('consultaFecha').value = consulta.fecha ? consulta.fecha.split('T')[0] : '';
        document.getElementById('consultaMotivo').value = consulta.motivo;
        document.getElementById('consultaTratamiento').value = consulta.tratamiento;
        document.getElementById('consultaMascotaId').value = consulta.mascotaId;
    } catch (error) {
        console.error('Error loading consulta:', error);
    } finally {
        hideLoading();
    }
}

async function deleteConsulta(id) {
    if (!confirm('¿Está seguro de que desea eliminar esta consulta?')) {
        return;
    }
    
    try {
        showLoading();
        await apiCall(`/consultas/${id}`, 'DELETE');
        showMessage('Consulta eliminada exitosamente');
        loadConsultas();
    } catch (error) {
        console.error('Error deleting consulta:', error);
    } finally {
        hideLoading();
    }
}

function clearConsultaForm() {
    document.getElementById('consultaForm').reset();
    document.getElementById('consultaId').value = '';
}

// FUNCIONES AUXILIARES PARA SELECTS
async function loadPropietariosSelect() {
    try {
        const propietarios = await apiCall('/propietarios');
        const select = document.getElementById('mascotaPropietarioId');
        
        // Limpiar opciones existentes excepto la primera
        select.innerHTML = '<option value="">Seleccionar propietario...</option>';
        
        propietarios.forEach(propietario => {
            const option = document.createElement('option');
            option.value = propietario.id;
            option.textContent = `${propietario.id} - ${propietario.nombre} ${propietario.apellido || ''}`;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading propietarios for select:', error);
    }
}

async function loadMascotasSelect() {
    try {
        mascotasData = await apiCall('/mascotas');
        const select = document.getElementById('consultaMascotaId');
        
        // Limpiar opciones existentes excepto la primera
        select.innerHTML = '<option value="">Seleccionar mascota...</option>';
        
        mascotasData.forEach(mascota => {
            const option = document.createElement('option');
            option.value = mascota.id;
            option.textContent = `${mascota.id} - ${mascota.nombre} (${mascota.especie})`;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading mascotas for select:', error);
    }
}