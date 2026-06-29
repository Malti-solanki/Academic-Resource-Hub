const BASE_URL = "http://localhost:8080/api";

const token = localStorage.getItem('token');
const role = localStorage.getItem('role');

// 🔐 Security Check
if (!token || role !== 'ADMIN') {
    window.location.href = './login.html';
}

// 🔐 Headers
const adminHeaders = {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${token}`
};

// =======================
// 📌 LOAD BRANCHES
// =======================
async function loadBranches() {
    const response = await fetch(`${BASE_URL}/branches`, {
        headers: adminHeaders
    });

    const branches = await response.json();

    const content = document.getElementById('content');

    content.innerHTML = `
        <div class="card">
            <h3>Branches</h3>

            <input id="newBranchName" placeholder="Enter branch name" />
            <button onclick="createBranch()">Add</button>

            <table border="1" width="100%" style="margin-top:10px;">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="branch-table-body"></tbody>
            </table>
        </div>
    `;

    const tableBody = document.getElementById('branch-table-body');

    branches.forEach(b => {
        tableBody.innerHTML += `
            <tr>
                <td>${b.id}</td>
                <td>
                    <input id="branch-${b.id}" value="${b.name}" />
                </td>
                <td>
                   <button onclick="updateBranch(${b.id}, '${b.name}')">Update</button>
                    <button onclick="deleteBranch(${b.id})">Delete</button>
                    <button onclick="loadSemesters(${b.id}, '${b.name}')">View Semester</button>
                </td>
            </tr>
        `;
    });
}

// =======================
// ➕ CREATE BRANCH
// =======================
async function createBranch() {
    const name = document.getElementById('newBranchName').value;

    const response = await fetch(`${BASE_URL}/branches`, {
        method: 'POST',
        headers: adminHeaders,
        body: JSON.stringify({ name })
    });

    if (response.ok) {
        loadBranches();
    } else {
        alert("Failed to create branch");
    }
}

// =======================
// ❌ DELETE BRANCH
// =======================
async function deleteBranch(id) {
    if (!confirm("Delete this branch?")) return;

    const response = await fetch(`${BASE_URL}/branches/${id}`, {
        method: 'DELETE',
        headers: adminHeaders
    });

    if (response.ok) {
        loadBranches();
    }
}


//----------update branch
async function updateBranch(id, oldName) {
    const newName = prompt("Enter new branch name:", oldName);

    if (!newName) {
        alert("Update cancelled");
        return;
    }

    if (newName.trim() === "") {
        alert("Branch name cannot be empty");
        return;
    }

    try {
        const response = await fetch(`${BASE_URL}/branches/${id}`, {
            method: "PUT",
            headers: adminHeaders,
            body: JSON.stringify({ name: newName })
        });

        if (response.ok) {
            alert("Updated successfully");
            loadBranches(); // refresh table
        } else {
            const errorText = await response.text();
            alert("Update failed: " + errorText);
        }

    } catch (error) {
        console.error(error);
        alert("Server error");
    }
}


//semester CRUD code
async function loadSemesters(branchId, branchName) {

    console.log("Loading semesters for branch:", branchId);

    const response = await fetch(`${BASE_URL}/semesters/branch/${branchId}`, {
        headers: adminHeaders
    });

    const semesters = await response.json();

    const content = document.getElementById('content');

    content.innerHTML = `
        <div class="card">
            <h3>Semesters - ${branchName}</h3>

            <input id="newSemesterName" placeholder="Enter semester name" />
            <button onclick="createSemester(${branchId})">Add</button>

            <button onclick="loadBranches()">Back</button>

            <table border="1" width="100%" style="margin-top:10px;">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Semester Number</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="semester-table-body"></tbody>
            </table>
        </div>
    `;

    const tableBody = document.getElementById('semester-table-body');

    semesters.forEach(s => {
        tableBody.innerHTML += `
            <tr>
                <td>${s.id}</td>
                <td>${s.semNumber}</td>
                <td>
                    <button onclick="updateSemester(${s.id}, '${s.semNumber}', ${branchId}, '${branchName}')">Update</button>
                    <button onclick="deleteSemester(${s.id}, ${branchId}, '${branchName}')">Delete</button>
                    <button onclick="loadSubjects(${s.id}, ${s.semNumber})">View Subjects</button>
                </td>
            </tr>
        `;
    });
}

//----------- create semester
async function createSemester(branchId) {

    const name = document.getElementById('newSemesterName').value;

    if (!name) {
        alert("Enter semester name");
        return;
    }

    const response = await fetch(`${BASE_URL}/semesters`, {
        method: "POST",
        headers: adminHeaders,
        body: JSON.stringify({
            semNumber : parseInt(name),
            branchId: branchId
        })
    });

    if (response.ok) {
        alert("Semester added successfully");
        loadSemesters(branchId, "");
    } else {
        alert("Failed to create semester");
    }
}

//-------------update semester

async function updateSemester(id, oldName, branchId, branchName) {

    const newName = prompt("Enter new semester name:", oldName);

    if (!newName) return;

    const response = await fetch(`${BASE_URL}/semesters/${id}`, {
        method: "PUT",
        headers: adminHeaders,
        body: JSON.stringify({
            semNumber : parseInt(newName),
            branchId: branchId
        })
    });

    if (response.ok) {
        alert("Updated successfully");
        loadSemesters(branchId, branchName);
    } else {
        alert("Update failed");
    }
}

//-----------delete semester
async function deleteSemester(id, branchId, branchName) {

    if (!confirm("Are you sure you want to delete?")) return;

    const response = await fetch(`${BASE_URL}/semesters/${id}`, {
        method: "DELETE",
        headers: adminHeaders
    });

    if (response.ok) {
        alert("Deleted successfully");
        loadSemesters(branchId, branchName);
    } else {
        alert("Delete failed");
    }
}

//-----------load all semesters
async function loadAllSemesters() {

    const response = await fetch(`${BASE_URL}/semesters`, {
        headers: adminHeaders
    });

    const semesters = await response.json();

    if (!Array.isArray(semesters)) {
        console.error("Invalid response:", semesters);
        alert("Failed to load semesters");
        return;
    }

    const content = document.getElementById('content');

    content.innerHTML = `
        <div class="card">
            <h3>All Semesters</h3>

            <table border="1" width="100%" style="margin-top:10px;">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Semester Number</th>
                        <th>Branch</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="all-semester-table-body"></tbody>
            </table>
        </div>
    `;

    const tableBody = document.getElementById('all-semester-table-body');

    semesters.forEach(s => {
        tableBody.innerHTML += `
            <tr>
                <td>${s.id}</td>
                <td>Semester ${s.semNumber}</td>
                <td>${s.branchName}</td>
                <td>
                    <button onclick="updateSemesterGlobal(${s.id}, ${s.semNumber}, ${s.branchId})">Update</button>
                    <button onclick="deleteSemesterGlobal(${s.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}



//------------update sem global
async function updateSemesterGlobal(id, oldSemNumber, branchId) {

    const newSemNumber = prompt("Enter semester number:", oldSemNumber);

    if (!newSemNumber) return;

    const response = await fetch(`${BASE_URL}/semesters/${id}`, {
        method: "PUT",
        headers: adminHeaders,
        body: JSON.stringify({
            semNumber: parseInt(newSemNumber),
            branchId: branchId
        })
    });

    if (response.ok) {
        loadAllSemesters();
    } else {
        alert("Update failed");
    }
}


//-----------delete sem global
async function deleteSemesterGlobal(id) {

    if (!confirm("Delete semester?")) return;

    const response = await fetch(`${BASE_URL}/semesters/${id}`, {
        method: "DELETE",
        headers: adminHeaders
    });

    if (response.ok) {
        loadAllSemesters();
    } else {
        alert("Delete failed");
    }
}


//------------load subjects
async function loadSubjects(semesterId, semNumber) {

    console.log("Loading subjects for semester:", semesterId);

    const response = await fetch(`${BASE_URL}/subjects/semester/${semesterId}`, {
        headers: adminHeaders
    });

    const subjects = await response.json();

    if (!Array.isArray(subjects)) {
        console.error("Invalid response:", subjects);
        alert("Failed to load subjects");
        return;
    }

    const content = document.getElementById('content');

    content.innerHTML = `
        <div class="card">
            <h3>Subjects - Semester ${semNumber}</h3>

            <input id="subjectName" placeholder="Enter subject name" />
            <input id="subjectCode" placeholder="Enter subject code" />

            <button onclick="createSubject(${semesterId}, ${semNumber})">Add</button>

            <button onclick="loadBranches()">Back</button>

            <table border="1" width="100%" style="margin-top:10px;">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Code</th>
                        <th>Sem</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="subject-table-body"></tbody>
            </table>
        </div>
    `;

    const tableBody = document.getElementById('subject-table-body');

    subjects.forEach(sub => {
        tableBody.innerHTML += `
            <tr>
                <td>${sub.id}</td>
                <td>${sub.name}</td>
                <td>${sub.subjectCode}</td>
                <td>${sub.semNumber}</td>
                <td>
                    <button onclick="updateSubject(${sub.id}, '${sub.name}', '${sub.subjectCode}', ${semesterId}, ${semNumber})">Update</button>
                    <button onclick="deleteSubject(${sub.id}, ${semesterId}, ${semNumber})">Delete</button>
                </td>
            </tr>
        `;
    });
}

//------------------create subject
async function createSubject(semesterId, semNumber) {

    const name = document.getElementById('subjectName').value;
    const subjectCode = document.getElementById('subjectCode').value;

    if (!name || !subjectCode) {
        alert("Enter all fields");
        return;
    }

    const response = await fetch(`${BASE_URL}/subjects`, {
        method: "POST",
        headers: adminHeaders,
        body: JSON.stringify({
            name: name,
            subjectCode: subjectCode,
            semesterId: semesterId
        })
    });

    if (response.ok) {
        alert("Subject added");
        loadSubjects(semesterId, semNumber);
    } else {
        alert("Failed to create subject");
    }
}

//--------------update subject
async function updateSubject(id, oldName, oldCode, semesterId, semNumber) {

    const newName = prompt("Enter subject name:", oldName);
    const newCode = prompt("Enter subject code:", oldCode);

    if (!newName || !newCode) return;

    const response = await fetch(`${BASE_URL}/subjects/${id}`, {
        method: "PUT",
        headers: adminHeaders,
        body: JSON.stringify({
            name: newName,
            subjectCode: newCode,
            semesterId: semesterId
        })
    });

    if (response.ok) {
        alert("Updated successfully");
        loadSubjects(semesterId, semNumber);
    } else {
        alert("Update failed");
    }
}


//-----------delete subject
async function deleteSubject(id, semesterId, semNumber) {

    if (!confirm("Delete subject?")) return;

    const response = await fetch(`${BASE_URL}/subjects/${id}`, {
        method: "DELETE",
        headers: adminHeaders
    });

    if (response.ok) {
        alert("Deleted successfully");
        loadSubjects(semesterId, semNumber);
    } else {
        alert("Delete failed");
    }
}


//------------load all subjects
async function loadAllSubjects() {

    const response = await fetch(`${BASE_URL}/subjects`, {
        headers: adminHeaders
    });

    const subjects = await response.json();

    if (!Array.isArray(subjects)) {
        console.error(subjects);
        alert("Failed to load subjects");
        return;
    }

    const content = document.getElementById('content');

    content.innerHTML = `
        <div class="card">
            <h3>All Subjects</h3>

            <table border="1" width="100%" style="margin-top:10px;">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Code</th>
                        <th>Semester</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="all-subject-table-body"></tbody>
            </table>
        </div>
    `;

    const tableBody = document.getElementById('all-subject-table-body');

    subjects.forEach(sub => {
        tableBody.innerHTML += `
            <tr>
                <td>${sub.id}</td>
                <td>${sub.name}</td>
                <td>${sub.subjectCode}</td>
                <td>Sem ${sub.semNumber}</td>
                <td>
                    <button onclick="updateSubjectGlobal(${sub.id}, '${sub.name}', '${sub.subjectCode}', ${sub.semesterId})">Update</button>
                    <button onclick="deleteSubjectGlobal(${sub.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}


//---------update subject global
async function updateSubjectGlobal(id, oldName, oldCode, semesterId) {

    const newName = prompt("Enter subject name:", oldName);
    const newCode = prompt("Enter subject code:", oldCode);

    if (!newName || !newCode) return;

    const response = await fetch(`${BASE_URL}/subjects/${id}`, {
        method: "PUT",
        headers: adminHeaders,
        body: JSON.stringify({
            name: newName,
            subjectCode: newCode,
            semesterId: semesterId
        })
    });

    if (response.ok) {
        loadAllSubjects();
    }
}


//---------delete subject global
async function deleteSubjectGlobal(id) {

    if (!confirm("Delete subject?")) return;

    const response = await fetch(`${BASE_URL}/subjects/${id}`, {
        method: "DELETE",
        headers: adminHeaders
    });

    if (response.ok) {
        loadAllSubjects();
    }
}


//-------------load all resources
async function loadAllResources() {

    const [resResponse, subjectResponse] = await Promise.all([
        fetch(`${BASE_URL}/resources`, { headers: adminHeaders }),
        fetch(`${BASE_URL}/subjects`, { headers: adminHeaders })
    ]);

    const resources = await resResponse.json();
    const subjects = await subjectResponse.json();

    const content = document.getElementById("content");

    content.innerHTML = `
        <div class="card">
            <h3>Upload Resource</h3>

            <input id="resTitle" placeholder="Title" />

            <select id="resType">
                <option value="NOTES">NOTES</option>
                <option value="BOOK">BOOK</option>
                <option value="PYQ">PYQ</option>
                <option value="SYLLABUS">SYLLABUS</option>
            </select>

            <select id="resSubject"></select>

            <input id="resFile" type="file" />

            <button onclick="uploadResource()">Upload</button>
        </div>

        <div class="card">
            <h3>All Resources</h3>

            <table border="1" width="100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Type</th>
                        <th>Subject</th>
                        <th>File</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="resource-table"></tbody>
            </table>
        </div>
    `;

    //  Fill Subject Dropdown
    const subjectDropdown = document.getElementById("resSubject");

    subjects.forEach(sub => {
        subjectDropdown.innerHTML += `
            <option value="${sub.id}">
                ${sub.name}
            </option>
        `;
    });

    //  Fill Resource Table
    const table = document.getElementById("resource-table");

    resources.forEach(res => {
        table.innerHTML += `
            <tr>
                <td>${res.id}</td>
                <td>${res.title}</td>
                <td>${res.type}</td>
                <td>${res.subjectName}</td>
                <td>${res.fileName}</td>
                <td>
                    <button onclick="downloadResource(${res.id})">Download</button>
                    <button onclick="updateResource(${res.id}, '${res.title}', '${res.type}')">Update</button>
                    <button onclick="deleteResource(${res.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}

//-----------upload resource
async function uploadResource() {

    const title = document.getElementById("resTitle").value;
    const type = document.getElementById("resType").value;
    const subjectId = document.getElementById("resSubject").value;
    const file = document.getElementById("resFile").files[0];

    if (!title || !type || !subjectId || !file) {
        alert("Please fill all fields");
        return;
    }

    const formData = new FormData();

    formData.append("title", title);
    formData.append("type", type);
    formData.append("subjectId", subjectId);

    // optional field
    formData.append("uploadedBy", 1); // you can replace with logged-in user later

    formData.append("file", file);

    const response = await fetch(`${BASE_URL}/resources/upload`, {
        method: "POST",
        headers: {
            "Authorization": adminHeaders.Authorization
        },
        body: formData
    });

    if (response.ok) {
        alert("Uploaded successfully");
        loadAllResources();
    } else {
        alert("Upload failed");
    }
}

//------update resource
async function updateResource(id, oldTitle, oldType) {

    const newTitle = prompt("Enter new title:", oldTitle);
    const newType = prompt("Enter type (NOTES/BOOK/PYQS/SYLLABUS):", oldType);

    if (!newTitle || !newType) return;

    const fileInput = document.createElement("input");
    fileInput.type = "file";

    fileInput.onchange = async () => {
        await sendUpdate(id, newTitle, newType, fileInput.files[0]);
    };

    const updateWithoutFile = confirm("Update without changing file?");

    if (updateWithoutFile) {
        await sendUpdate(id, newTitle, newType, null);
    } else {
        fileInput.click();
    }
}

//-- update resource helper function
async function sendUpdate(id, title, type, file) {

    const formData = new FormData();
    formData.append("title", title);
    formData.append("type", type);

    if (file) {
        formData.append("file", file);
    }

    const response = await fetch(`${BASE_URL}/resources/${id}`, {
        method: "PUT",
        headers: {
            "Authorization": adminHeaders.Authorization
        },
        body: formData
    });

    if (response.ok) {
        alert("Updated successfully ");
        loadAllResources();
    } else {
        alert("Update failed ");
    }
}

//--------download resource
async function downloadResource(id) {

    const response = await fetch(`${BASE_URL}/resources/download/${id}`, {
        headers: adminHeaders
    });

    const blob = await response.blob();

    const url = window.URL.createObjectURL(blob);

    const a = document.createElement("a");
    a.href = url;
    a.download = "file_" + Date.now() + ".pdf";
    a.click();
}

//---------delete resource
async function deleteResource(id) {

    if (!confirm("Delete this resource?")) return;

    const response = await fetch(`${BASE_URL}/resources/${id}`, {
        method: "DELETE",
        headers: adminHeaders
    });

    if (response.ok) {
        loadAllResources();
    }
}

//-------------load users
async function loadUsers() {

    const res = await fetch(`${BASE_URL}/users`, {
        headers: adminHeaders
    });

    const users = await res.json();

    const content = document.getElementById("content");

    content.innerHTML = `
        <h3>User Management</h3>
        <button onclick="createAdmin()">Create Admin</button>
        <br>
        <table border="1" width="100%">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody id="user-table"></tbody>
        </table>
    `;

    const table = document.getElementById("user-table");

    users.forEach(u => {
        table.innerHTML += `
            <tr>
                <td>${u.id}</td>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.role}</td>
                <td>
                    <button onclick="deleteUser(${u.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}

//------------delete user
async function deleteUser(id) {

    if (!confirm("Delete user?")) return;

    await fetch(`${BASE_URL}/users/${id}`, {
        method: "DELETE",
        headers: adminHeaders
    });

    loadUsers();
}

//------create admin
async function createAdmin() {

    const name = prompt("Enter name:");
    const email = prompt("Enter email:");
    const password = prompt("Enter password:");

    if (!name || !email || !password) return;

    const response = await fetch(`${BASE_URL}/users/admin`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": adminHeaders.Authorization
        },
        body: JSON.stringify({
            name,
            email,
            password
        })
    });

    if (response.ok) {
        alert("Admin created successfully ");
        loadUsers();
    } else {
        alert("Failed to create admin ");
    }
}

// =======================
//LOGOUT
// =======================
function logout() {
    localStorage.clear();
    window.location.href = 'login.html';
}

function gotodashboard(){
 window.location.href = 'admin-dashboard.html';
}