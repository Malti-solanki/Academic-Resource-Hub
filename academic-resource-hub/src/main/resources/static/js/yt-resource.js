const youtubeChannels = [
    {
        name: "Gate Smashers",
        link: "https://www.youtube.com/@GateSmashers",
        desc: "DBMS, OS, CN explained clearly"
    },
    {
            name: "Apna College",
            link: "https://www.youtube.com/@ApnaCollegeOfficial",
            desc: "Java, DSA, Web Development, Placement preparation"
        },
        {
            name: "CodeWithHarry",
            link: "https://www.youtube.com/@CodeWithHarry",
            desc: "Complete programming tutorials (Java, Python, Web Dev)"
        },
        {
            name: "Jenny's Lectures CS IT",
            link: "https://www.youtube.com/@JennyslecturesCSIT",
            desc: "DBMS, OS, Theory subjects in simple language"
        },
        {
            name: "Neso Academy",
            link: "https://www.youtube.com/@nesoacademy",
            desc: "Computer Networks, Digital Electronics, Signals"
        },
        {
            name: "Knowledge Gate",
            link: "https://www.youtube.com/@KnowledgeGate",
            desc: "Operating System, DBMS, Compiler Design"
        },
        {
            name: "Unacademy Computer Science",
            link: "https://www.youtube.com/@UnacademyComputerScience",
            desc: "GATE preparation, core CS subjects"
        },
        {
            name: "GeeksforGeeks",
            link: "https://www.youtube.com/@GeeksforGeeksVideos",
            desc: "DSA, coding interviews, system design"
        },
        {
            name: "take U forward",
            link: "https://www.youtube.com/@takeUforward",
            desc: "DSA, interview preparation by Striver"
        },
        {
            name: "freeCodeCamp.org",
            link: "https://www.youtube.com/@freecodecamp",
            desc: "Full courses on programming, web dev, and more"
        },
        {
            name: "Telusko",
            link: "https://www.youtube.com/@Telusko",
            desc: "Java, Spring Boot, Microservices"
        },
        {
            name: "ProgrammingKnowledge",
            link: "https://www.youtube.com/@ProgrammingKnowledge",
            desc: "Java, Python, Android, databases"
        },
        {
            name: "Easy Engineering Classes",
            link: "https://www.youtube.com/@EasyEngineeringClasses",
            desc: "Compiler Design, Theory of Computation"
        },
        {
            name: "Abdul Bari",
            link: "https://www.youtube.com/@abdul_bari",
            desc: "Data Structures, Algorithms, Operating System"
        },
    {
        name: "CodeHelp - Babbar",
        link: "https://www.youtube.com/@CodeHelp",
        desc: "DSA and placement prep"
    }
];

const websites = [
    {
        name: "GeeksforGeeks",
        link: "https://www.geeksforgeeks.org",
        desc: "Coding + CS concepts"
    },
    {
            name: "Khan Academy",
            link: "https://www.khanacademy.org",
            desc: "Math, physics, and basic engineering concepts"
       },
    {
        name: "W3Schools",
        link: "https://www.w3schools.com",
        desc: "Web development basics"
    },
    {
            name: "TutorialsPoint",
            link: "https://www.tutorialspoint.com",
            desc: "Wide range of tutorials for programming, engineering subjects, and technologies"
        },
        {
            name: "Coding Ninjas",
            link: "https://www.codingninjas.com",
            desc: "DSA, coding courses, interview preparation, and guided learning paths"
        }
];

function loadResources() {

    const ytDiv = document.getElementById("youtubeResources");
    const webDiv = document.getElementById("websiteResources");

    ytDiv.innerHTML = "<h2>YouTube Channels</h2>";
    youtubeChannels.forEach(ch => {
        ytDiv.innerHTML += `
            <div class="card">
                <h3>${ch.name}</h3>
                <p>${ch.desc}</p>
                <a href="${ch.link}" target="_blank">Visit Channel</a>
            </div>
        `;
    });

    webDiv.innerHTML = "<h2>Websites</h2>";
    websites.forEach(site => {
        webDiv.innerHTML += `
            <div class="card">
                <h3>${site.name}</h3>
                <p>${site.desc}</p>
                <a href="${site.link}" target="_blank">Visit Website</a>
            </div>
        `;
    });
}
function goBack() {
    window.location.href = "student-dashboard.html";
}
loadResources();