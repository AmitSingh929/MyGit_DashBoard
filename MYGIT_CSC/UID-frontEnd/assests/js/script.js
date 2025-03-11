function toggleSidebar() {
  const sidebar = document.getElementById("sidebar");
  const content = document.getElementById("content");
  const dashboardTitle = document.getElementById("dashboard-title");
  const headerLogo = document.getElementById("header-logo");

  sidebar.classList.toggle("collapsed");
  content.classList.toggle("collapsed");

  if (sidebar.classList.contains("collapsed")) {
    dashboardTitle.style.display = "none";
    headerLogo.classList.add("visible");
  } else {
    dashboardTitle.style.display = "inline";
    headerLogo.classList.remove("visible");
  }
}

// function detailPage($text) {
//   console.log($text, "text");
//   location.href = "CIDRDetails.html";
// }
function CIDRdetailPage(type) {
    location.href = `CIDRDetails.html?type=${type}`;
}

// function ASAdetailPage(){
//     // console.log($text, "text");
//     location.href = "ASADetails.html";  
// }
function ASAdetailPage(type) {
    location.href = `ASADetails.html?type=${type}`;
}

// function AUAdetailPage(){
//   // console.log($text, "text");
//   location.href = "AUADetails.html";  
// }
function AUAdetailPage(type) {
    location.href = `AUADetails.html?type=${type}`;
}

let selectedOption = "option1"; 

// Highlight the selected option
function selectOption(optionId) {
  // Remove the active class from the previously selected option
  if (selectedOption) {
    document.getElementById(selectedOption).classList.remove("active");
  }

  // Set the new selected option
  selectedOption = optionId;
  document.getElementById(optionId).classList.add("active");
}

// Proceed button action
function proceed() {
  if (!selectedOption) {
    alert("Please select an option before proceeding.");
    return;
  }

  // Redirect based on the selected option
  if (selectedOption === "option1") {
    location.href = "ASA.html"; // Redirect to ASA page
  } else if (selectedOption === "option2") {
    location.href = "AUA.html"; // Redirect to CIDR page
  }
  else if (selectedOption === "option3") {
    location.href = "CIDR.html"; // Redirect to CIDR page
  }
}

// Ensure the default option is visually highlighted on page load
document.addEventListener("DOMContentLoaded", () => {
  document.getElementById(selectedOption).classList.add("active");
});

document.addEventListener("DOMContentLoaded", () => {
  const menuItems = document.querySelectorAll(".menu a");

  menuItems.forEach((item) => {
    // Add hover effect
    item.addEventListener("mouseover", () => {
      if (!item.classList.contains("active")) {
        item.style.backgroundColor = "rgba(255,255,255,0.2)";
      }
    });
    item.addEventListener("mouseout", () => {
      if (!item.classList.contains("active")) {
        item.style.backgroundColor = "transparent";
      }
    });

    // Add click event to mark active link
    item.addEventListener("click", () => {
      // Remove active class from all menu items
      menuItems.forEach((menu) => {
        menu.classList.remove("active");
        menu.style.backgroundColor = "transparent";
      });

      // Add active class to the clicked item
      item.classList.add("active");
      item.style.backgroundColor = "rgba(255,255,255,0.4)";
    });
  });
});

document.addEventListener("DOMContentLoaded", function () {
  $("#example").DataTable();
});

// document.addEventListener("DOMContentLoaded", () => {
//     const dateInput = document.getElementById("start-date");
//     const enddateInput = document.getElementById("end-date");
//     const today = new Date();
//     const formattedDate = today.toISOString().split("T")[0]; 
//     dateInput.value = formattedDate;
//     enddateInput.value = formattedDate;
// });


// const sidebar = document.querySelector('.sidebar');
// const content = document.querySelector('.content');
// const toggleBtn = document.getElementById('sidebarToggle');

// toggleBtn.addEventListener('click', () => {
//   sidebar.classList.toggle('collapsed');
//   content.classList.toggle('collapsed');
// });

// JavaScript to handle dropdown menu toggle
const dropdownToggle = document.getElementById('dropdownToggle');
const dropdownMenu = document.getElementById('dropdownMenu');

dropdownToggle.addEventListener('click', () => {
  dropdownMenu.classList.toggle('open');
});

// Optional: Close dropdown when clicking outside
document.addEventListener('click', (event) => {
  if (!dropdownToggle.contains(event.target) && !dropdownMenu.contains(event.target)) {
    dropdownMenu.classList.remove('open');
  }
});




document.addEventListener("DOMContentLoaded", function () {
  const toggleBtn = document.getElementById("dropdownToggle");
  const icon = toggleBtn.querySelector("i");

  // Listen for Bootstrap dropdown events
  toggleBtn.addEventListener("click", function () {
      setTimeout(() => {
          if (toggleBtn.getAttribute("aria-expanded") === "true") {
              icon.classList.remove("fa-bars");
              icon.classList.add("fa-times"); // Show 'X' icon
          } else {
              icon.classList.remove("fa-times");
              icon.classList.add("fa-bars"); // Show bars icon
          }
      }, 50); // Short delay to ensure Bootstrap updates first
  });
});
