document.addEventListener("DOMContentLoaded", () => {
    loadRecentResources();
});

/* Replace with API later */
function loadRecentResources() {

    const data = [
        { title: "DBMS Notes (CS-502)", size: "1.2 MB" },
        { title: "OS Unit 1 Notes", size: "850 KB" },
        { title: "CN Important Questions", size: "500 KB" }
    ];

    let html = "";

    data.forEach(item => {
        html += `
        <div class="resource">
            <strong>${item.title}</strong>
            <span style="float:right">${item.size}</span>
        </div>`;
    });

    document.getElementById("recentList").innerHTML = html;
}