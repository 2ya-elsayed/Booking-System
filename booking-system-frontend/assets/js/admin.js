// ✅ Redirect non-admins
// if (localStorage.getItem("isAdmin") !== "true") {
//     alert("Access Denied. Admins only.");
//     window.location.href = "index.html";
// }

let events = JSON.parse(localStorage.getItem("adminEvents")) || [];
const eventTable = document.getElementById("eventTableBody");

// Render event table
function renderTable() {
    eventTable.innerHTML = "";
    events.forEach(event => {
        const row = document.createElement("tr");
        row.innerHTML = `
        <td>${event.title}</td>
        <td>${event.date}</td>
        <td>${event.venue}</td>
        <td>${event.category}</td>
        <td>
          <button class="btn btn-sm btn-warning me-2" onclick="openEditModal(${event.id})">Edit</button>
          <button class="btn btn-sm btn-danger" onclick="deleteEvent(${event.id})">Delete</button>
        </td>
      `;
        eventTable.appendChild(row);
    });
}

// Handle Add/Edit Modal
function openCreateModal() {
    document.getElementById("eventForm").reset();
    document.getElementById("eventId").value = "";
    document.getElementById("eventModalLabel").innerText = "Add New Event";
}

function openEditModal(id) {
    const event = events.find(e => e.id === id);
    if (!event) return;
    document.getElementById("eventId").value = event.id;
    document.getElementById("eventTitle").value = event.title;
    document.getElementById("eventDate").value = event.date;
    document.getElementById("eventVenue").value = event.venue;
    document.getElementById("eventCategory").value = event.category;
    document.getElementById("eventPrice").value = event.price;
    document.getElementById("eventImage").value = event.image;
    document.getElementById("eventDescription").value = event.description;
    document.getElementById("eventModalLabel").innerText = "Edit Event";
    new bootstrap.Modal(document.getElementById("eventModal")).show();
}

// Save event (create or update)
document.getElementById("eventForm").addEventListener("submit", function (e) {
    e.preventDefault();
    const id = document.getElementById("eventId").value;
    const event = {
        id: id ? parseInt(id) : Date.now(),
        title: document.getElementById("eventTitle").value,
        date: document.getElementById("eventDate").value,
        venue: document.getElementById("eventVenue").value,
        category: document.getElementById("eventCategory").value,
        price: document.getElementById("eventPrice").value,
        image: document.getElementById("eventImage").value,
        description: document.getElementById("eventDescription").value
    };

    if (id) {
        // Update
        const index = events.findIndex(e => e.id === parseInt(id));
        events[index] = event;
    } else {
        // Create
        events.push(event);
    }

    localStorage.setItem("adminEvents", JSON.stringify(events));
    renderTable();
    bootstrap.Modal.getInstance(document.getElementById("eventModal")).hide();
});

// Delete
function deleteEvent(id) {
    if (confirm("Are you sure you want to delete this event?")) {
        events = events.filter(e => e.id !== id);
        localStorage.setItem("adminEvents", JSON.stringify(events));
        renderTable();
    }
}

renderTable();
