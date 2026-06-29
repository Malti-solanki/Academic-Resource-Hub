const BASE_URL = "http://localhost:8080/api";
const token = localStorage.getItem("token");

if (!token) {
    window.location.href = "login.html";
}

const headers = {
    "Authorization": "Bearer " + token
};

let selectedSubjectId = null;

// LOAD SUBJECTS (you must call this after selecting semester)
async function loadSubjects(semesterId) {
    const res = await fetch(`${BASE_URL}/subjects/semester/${semesterId}`, { headers });
    const subjects = await res.json();

    const container = document.getElementById("subjectList");
    container.innerHTML = "";

    subjects.forEach(sub => {
        const span = document.createElement("span");
        span.innerText = sub.name;

        span.onclick = () => {
            selectedSubjectId = sub.id;

            // highlight selected
            document.querySelectorAll("#subjectList span")
                .forEach(s => s.style.background = "#ddd");

            span.style.background = "#2c3e50";
            span.style.color = "white";
        };

        container.appendChild(span);
    });
}

//  LOAD RESOURCES BY TYPE (NEW API CONNECTED HERE)
async function loadResourcesByType(type) {

    if (!selectedSubjectId) {
        alert("Please select subject first");
        return;
    }

    const res = await fetch(
        `${BASE_URL}/resources/subject/${selectedSubjectId}/type/${type}`,
        { headers }
    );

    const resources = await res.json();

    const container = document.getElementById("resourceList");
    container.innerHTML = "";

    if (resources.length === 0) {
        container.innerHTML = "<p>No resources found</p>";
        return;
    }

    resources.forEach(r => {
        const div = document.createElement("div");
        div.className = "resource";

        div.innerHTML = `
            <span>${r.title} (${formatFileSize(r.fileSize)})</span>
            <button onclick="downloadFile(${r.id})">⬇ Download</button>
        `;

        container.appendChild(div);
    });
}

async function loadBranches() {
    const res = await fetch(`${BASE_URL}/branches`, { headers });
    const branches = await res.json();

    const container = document.getElementById("branchList");
    container.innerHTML = "";

    branches.forEach(b => {
         if (b.name === "Common") return;
        const btn = document.createElement("button");
        btn.innerText = b.name;

        btn.onclick = () => {
            loadSemesters(b.id);

            // highlight
            document.querySelectorAll("#branchList button")
                .forEach(x => x.style.background = "#ccc");

            btn.style.background = "#2c3e50";
            btn.style.color = "white";
        };

        container.appendChild(btn);
    });
}


async function loadSemesters(branchId) {

    const commonBranchId = 5; // Common branch ID

    // Fetch common semesters
    const resCommon = await fetch(`${BASE_URL}/semesters/branch/${commonBranchId}`, { headers });
    const commonSem = await resCommon.json();

    // Fetch branch semesters
    const resBranch = await fetch(`${BASE_URL}/semesters/branch/${branchId}`, { headers });
    const branchSem = await resBranch.json();

    // Merge both
    const semesters = [...commonSem, ...branchSem];

    const container = document.getElementById("semesterList");
    container.innerHTML = "";

    semesters.forEach(s => {
        const btn = document.createElement("button");
        btn.innerText = "Sem " + s.semNumber;

        btn.onclick = () => loadSubjects(s.id);

        container.appendChild(btn);
    });
}

//  DOWNLOAD
function downloadFile(id) {
    window.open(`${BASE_URL}/resources/download/${id}`);
}

// 📏 FORMAT FILE SIZE
function formatFileSize(bytes) {
    if (bytes < 1024) return bytes + " B";
    else if (bytes < 1024 * 1024) return (bytes / 1024).toFixed(1) + " KB";
    else return (bytes / (1024 * 1024)).toFixed(1) + " MB";
}

loadBranches();

async function loadAllByType(type) {

    const res = await fetch(`${BASE_URL}/resources/type/${type}`, {
        headers
    });

    const resources = await res.json();

    const container = document.getElementById("resourceList");
    container.innerHTML = `<h3>${type} Resources</h3>`;

    if (resources.length === 0) {
        container.innerHTML += "<p>No resources found</p>";
        return;
    }

    resources.forEach(r => {
        const div = document.createElement("div");
        div.className = "resource";

        div.innerHTML = `
            <span>${r.title} (${r.subjectName})</span>
            <button onclick="downloadFile(${r.id})">⬇ Download</button>
        `;

        container.appendChild(div);
    });
}

async function loadUserProfile() {
    const res = await fetch("http://localhost:8080/api/users/me", {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });

    const user = await res.json();

    document.getElementById("pName").innerText = user.name;
    document.getElementById("pEmail").innerText = user.email;
    document.getElementById("pRole").innerText = user.role;

    // load extra details
    loadExtraProfile();
}

function saveExtraProfile() {
    const data = {
        branch: document.getElementById("branch").value,
        semester: document.getElementById("semester").value,
        year: document.getElementById("year").value
    };

    localStorage.setItem("extraProfile", JSON.stringify(data));
    loadExtraProfile();
}

function loadExtraProfile() {
    const data = JSON.parse(localStorage.getItem("extraProfile"));

    if(data){
        document.getElementById("pBranch").innerText = data.branch;
        document.getElementById("pSemester").innerText = data.semester;
        document.getElementById("pYear").innerText = data.year;
    }
}

function goToProfile() {
    window.location.href = "profile.html";
}

function goBack() {
    window.location.href = "student-dashboard.html";
}

function goToYtResource() {
    window.location.href = "yt-resource.html";
}

function logout() {
    // remove token
    localStorage.removeItem("token");

    // optional: remove user info
    localStorage.removeItem("user");

    // redirect to login page
    window.location.href = "login.html";
}