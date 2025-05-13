let defaultEvents = [
    {
        id: 1,
        title: "Music Fest",
        date: "2025-06-01",
        image: "https://firstbenefits.org/wp-content/uploads/2017/10/placeholder.png",
        description: "A huge outdoor music celebration featuring top artists.",
        venue: "Central Park",
        category: "Music",
        price: "Free"
    },
    {
        id: 2,
        title: "Tech Conference",
        date: "2025-07-15",
        image: "https://firstbenefits.org/wp-content/uploads/2017/10/placeholder.png",
        description: "Annual gathering for developers, startups, and tech enthusiasts.",
        venue: "Silicon Hall",
        category: "Technology",
        price: "$20"
    },
    {
        id: 3,
        title: "Food Carnival",
        date: "2025-08-10",
        image: "https://firstbenefits.org/wp-content/uploads/2017/10/placeholder.png",
        description: "Taste cuisines from around the world in one place.",
        venue: "Downtown Plaza",
        category: "Food",
        price: "$10"
    }
];

// 🔁 Load from localStorage if available
let events = JSON.parse(localStorage.getItem("adminEvents")) || defaultEvents;

const bookedEvents = JSON.parse(localStorage.getItem("bookedEvents") || "[]");

// Check what page we're on
const path = window.location.pathname;

// --------------------------
// HOME PAGE (index.html)
// --------------------------
if (path.includes("index.html") || path === "/" || path.endsWith("index")) {
    const eventList = document.getElementById("event-list");

    events.forEach(event => {
        const isBooked = bookedEvents.includes(event.id);
        const card = document.createElement("div");
        card.className = "col-md-4 mb-4";
        card.innerHTML = `
    <div class="card shadow-sm position-relative">
        <img src="${event.image}" class="card-img-top" alt="${event.title}">
        <div class="card-body">
        <h5 class="card-title">${event.title}</h5>
        <p class="card-text">📅 ${event.date}</p>
        ${isBooked
                ? '<span class="badge bg-success">Booked</span>'
                : `<button class="btn btn-primary book-btn" data-id="${event.id}">Book Now</button>`}
        <a href="event-details.html?id=${event.id}" class="stretched-link"></a>
        </div>
    </div>
    `;
        eventList.appendChild(card);
    });

    document.querySelectorAll(".book-btn").forEach(button => {
        button.addEventListener("click", function () {
            const id = parseInt(this.getAttribute("data-id"));
            if (!bookedEvents.includes(id)) {
                bookedEvents.push(id);
                localStorage.setItem("bookedEvents", JSON.stringify(bookedEvents));
                alert("Successfully booked!");
                location.reload();
            }
        });
    });
}

// --------------------------
// EVENT DETAILS PAGE (event-details.html)
// --------------------------
if (path.includes("event-details.html")) {
    const params = new URLSearchParams(window.location.search);
    const eventId = parseInt(params.get("id"));
    const event = events.find(e => e.id === eventId);
    const container = document.getElementById("event-details");

    if (!event) {
        container.innerHTML = "<h2>Event not found.</h2>";
    } else {
        const isBooked = bookedEvents.includes(event.id);

        container.innerHTML = `
            <div class="card shadow w-25 m-auto">
                <img src="${event.image}" class="card-img-top" alt="${event.title}">
                <div class="card-body">
                <h2 class="card-title">${event.title}</h2>
                <p class="card-text"><strong>Date:</strong> ${event.date}</p>
                <p class="card-text"><strong>Category:</strong> ${event.category}</p>
                <p class="card-text"><strong>Venue:</strong> ${event.venue}</p>
                <p class="card-text"><strong>Price:</strong> ${event.price}</p>
                <p class="card-text">${event.description}</p>
                ${isBooked
                ? '<span class="badge bg-success">Already Booked</span>'
                : `<button class="btn btn-primary" onclick="bookEvent(${event.id})">Book Now</button>`}
                </div>
            </div>
        `;
    }

    window.bookEvent = function (id) {
        if (!bookedEvents.includes(id)) {
            bookedEvents.push(id);
            localStorage.setItem("bookedEvents", JSON.stringify(bookedEvents));
            window.location.href = "congratulations.html";
        }
    };
}
