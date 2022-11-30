<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
    <script src="https://cdn.tiny.cloud/1/5bqionfmj2ip2dfagxm86bzuckperf675cuancpuqyxli9g1/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="https://kit.fontawesome.com/7358458173.js" crossorigin="anonymous"></script>
    <meta charset="ISO-8859-1">
    <title>Home Page</title>
    <style>
        .linear-bg{
            background-color: #131837;
            background-image: url("https://i.ibb.co/k5z6xSs/login-background.png");
            background-size: cover;
            width: 100vw;
            height: 100vh;
        }
        #left-panel{
            width: 18%;
            height: 85%;
            background-color: #141516;
            border-radius: 10px;
            color: #FFFFFF;
            position: relative;
        }
        .tox .tox-tbtn svg{
            fill: #FFFFFF !important;
        }
        .tox .tox-tbtn svg:hover, .tox .tox-tbtn:hover{
            background-color: #4e4e4e !important;
        }
        .avatar-and-name{
            margin-top: 1rem;
            margin-bottom: 1rem;
            display: flex;
            flex-direction: row;
            align-items: center;
            gap: 10px;
        }
        .avatar-ligma{
            height: 1rem;
            width: 1rem;
            border-radius: 3rem;
            overflow: hidden;
        }
        .left-panel-header{
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: space-between;
            padding: 4px 12px;
        }
        #search{
            box-sizing: border-box;

            width: 92.8%;
            height: 30px;
            margin: 12px;
            padding-left: 2rem;
            background: linear-gradient(90deg, rgba(255, 255, 255, 0.32) 0%, rgba(255, 255, 255, 0.16) 97%);
            border: 1px solid rgba(255, 255, 255, 0.4);
            backdrop-filter: blur(25px);
            /* Note: backdrop-filter has minimal browser support */

            border-radius: 22px;
        }
        #search::placeholder{
            font-size: medium;
        }
        #new {
            box-sizing: border-box;
            display: flex;
            flex-direction: row;
            align-items: center;
            width: 92.8%;
            height: 30px;
            margin: 12px;
            background: #906AE5;
            gap: 4px;
            border-radius: 22px;
            font-size: 14px;
            justify-content: center;
        }
        #new > .icon{
            height: 14px;
            width: 14px;
        }
        .left-panel-body{
            margin-top: 3rem;
        }
        .left-panel-taskbar{
            padding-left: 3rem;
            display: flex;
            flex-direction: row;
            gap: 1rem;
            align-items: center;
            padding-top: 1rem;
            padding-bottom: 1rem;
            background: radial-gradient(#141516, #141516);
        }
        .left-panel-taskbar:hover{

            animation-name: bg-transition;
            animation-duration: 0.1s;
            animation-fill-mode: forwards;
            animation-timing-function: ease-in-out;
            cursor: pointer;
        }
        @keyframes bg-transition {
            0% {background:radial-gradient(#141516, #141516, #141516);}
            33% {background:radial-gradient(#1D1F20, #141516, #141516);}
            66% {background:radial-gradient(#1D1F20, #1D1F20, #141516);}
            100% {background:radial-gradient(#1D1F20, #1D1F20, #1D1F20);}
        }
        .active{
            color: #8c63ff;
        }
        #logout-btn{
            background-color: transparent;
            border: #9F5006 2px solid;
            border-radius: 12px;
            width: calc(100% -  24px);
            position: absolute;
            bottom: 1rem;
            padding-bottom: 0.5rem;
            padding-top: 0.5rem;
            margin-left: 12px;
            color: #9F5006;
        }
        #logout-btn:hover{
            background-color: #bf6008;
            border: #bf6008 2px solid;
            color: #171311;
        }
        .search-container{
            position: relative;
        }
        .search-icon{
            position: absolute;
            top: 1.2rem;
            left: 1.3rem;
            z-index: 2;
        }
        /*copy to here */

        .right-side{
            display: flex;
            flex-direction: column;
            height: 85%;
            width: 75%;
            gap: 5%;
        }
        .all-notes, .recently{
            background-color: #141516;
            height: 50%;
            width: 100%;
            border-radius: 10px;
            padding-left: 1rem;
            display: flex;
            flex-direction: column;
        }
        .all-notes-title{
            padding-top: 0.4rem;
            font-size: 1.5rem;
            font-weight: 600;
            color: #FFFFFF;
        }
        .notes, .tasks{
            display: flex;
            flex-direction: row;
            align-items: center;
            gap: 1.2rem;
            cursor: grab;
            overflow: auto;
            flex: 1;
        }
        .note, .task{
            width: 15%;
            flex-shrink: 0;
            height: 93%;
            display: flex;
            flex-direction: column;
            color: #FFFFFF;
            background-color: #222326;
            padding: 0.5rem 1rem;
            border-radius: 0.5rem;
            position: relative;
        }
        .note::after, .task::after{
            content: "";
            bottom: 0;
            height: 30%;
            background: linear-gradient(to bottom,rgba(34,35,38,0.3) 0%, rgba(34,35,38,1) 100%);
            width: calc(100% - 2rem);
            position: absolute;
            z-index: 2;
        }
        .note--name{
            --max-line: 2;
            display: -webkit-box;
            overflow: hidden;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: var(--max-line);
            font-size: 1.3rem;
            font-weight: 600;
        }
        .note--modify{
            font-size: 0.8rem;
            color: #b3b3b3;
        }
        .note--content, .task-description{
            margin-top: 1.4rem;
            --max-line: 8;
            display: -webkit-box;
            overflow: hidden;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: var(--max-line);
            font-size: 1rem;
        }
        .recently-filter{
            color: #808080;
            padding-top: 0.5rem;
            display: flex;
            gap: 1rem;
        }
        .recently-filter > a:hover{
            cursor: pointer;
        }
        .active-filter{
            color: #ffffff;
            font-weight: 500;
            position: relative;
        }
        .active-filter::after{
            content:"";
            background-color: #ffffff;
            width: 100%;
            height: 0.1rem;
            bottom: -0.1rem;
            left: 0;
            position: absolute;
            border-radius: 1rem;

        }
        .tasks{
            display: flex;
            flex-direction: row;
            color: #ffffff;
        }
        .task-name{
            font-size: 1.3rem;
            font-weight: 600;
        }
        .task-time{
            display: flex;
            flex-direction: column;
        }
        .start-task-time, .end-task-time{
            font-size: 0.8rem;
            color: #b3b3b3;
            display: flex;
            flex-direction: row;
            align-items: center;
            gap: 0.2rem;
        }
        .fa-ellipsis-vertical{
            padding-left: 0.3rem;
            color: #b3b3b3;
        }
    </style>
</head>
<body>
<div class="container-xl mx-auto linear-bg flex flex-row items-center justify-around">
    <div id="left-panel">
        <div class="left-panel-header">
            <div class="avatar-and-name">
                <div class="avatar-ligma"><img src="https://i.pinimg.com/736x/7a/69/fc/7a69fc7139dd2faaf696b9acc167afc0.jpg"/></div>
                <div class="name-ligma"><c:out value="${user.getEmail()}"></c:out></div>
            </div>
            <i class="fa-solid fa-gear"></i>
        </div>
        <div class="search-container">
            <i class="fa-solid fa-magnifying-glass search-icon"></i>
            <input id="search" placeholder="Search"/>
        </div>

        <button   id="new"><i class="fa-solid fa-plus icon"></i><p>New</p></button>
        <div class="left-panel-body">
            <div class="left-panel-taskbar active" id="home"><i class="fa-solid fa-house"></i> Home</div>
            <div class="left-panel-taskbar" id="note"><i class="fa-solid fa-note-sticky"></i>All notes</div>
            <div class="left-panel-taskbar" id="task"><i class="fa-solid fa-clipboard-list"></i> Task</div>
            <div class="left-panel-taskbar" id="notebook"><i class="fa-solid fa-book"></i> Notebook</div>
            <div class="left-panel-taskbar" id="tag"><i class="fa-solid fa-tags"></i> Tag</div>
            <div class="left-panel-taskbar" id="share"><i class="fa-solid fa-square-share-nodes"></i> Share</div>
            <div class="left-panel-taskbar" id="trash"><i class="fa-solid fa-trash"></i> Trash</div>
        </div>
        <button id="logout-btn"><i class="fa-solid fa-right-from-bracket"></i> Logout</button>
    </div>
    <div class="right-side">
        <div class="all-notes">
            <div class="all-notes-title">All Notes</div>
            <div class="notes">
                <c:forEach items="${notes}" var="item">
                    <div class="note">
                        <div class="note--name">${item.getTitle()}</div>
                        <c:if test = "${item.getDateUpdated() != null}">
                            <div class="note--modify">Last modify ${item.getDateUpdated().getMonth()}/${item.getDateUpdated().getDate()}/${item.getDateUpdated().getYear()}</div>
                        </c:if>
                        <c:if test = "${item.getDateUpdated() == null}">
                            <div class="note--modify">Last modify ${item.getDateCreated().getMonth()}/${item.getDateCreated().getDate()}/${item.getDateCreated().getYear()}</div>
                        </c:if>
                        <div class="note--content">${item.getContent()} </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="recently">
            <div class="recently-filter">
                <a id="recently" class="active-filter">Recently</a>
                <a id="upcoming-task" class="">Upcoming task</a>
            </div>
            <div class="notes">
                <c:forEach items="${noteRecently}" var="item" begin="0" end="4">
                    <div class="note">
                        <div class="note--name">${item.getTitle()}</div>
                        <c:if test = "${item.getDateUpdated() != null}">
                            <div class="note--modify">Last modify ${item.getDateUpdated().getMonth()}/${item.getDateUpdated().getDate()}/${item.getDateUpdated().getYear()}</div>
                        </c:if>
                        <c:if test = "${item.getDateUpdated() == null}">
                            <div class="note--modify">Last modify ${item.getDateCreated().getMonth()}/${item.getDateCreated().getDate()}/${item.getDateCreated().getYear()}</div>
                        </c:if>
                        <div class="note--content">${item.getContent()} </div>
                    </div>
                </c:forEach>
            </div>
            <div class="tasks">
                <div class="task">
                    <div class="task-name">Go sleep</div>
                    <div class="task-time">
                        <div class="start-task-time">
                            <i class="fa-regular fa-clock"></i>
                            <p>End at 4AM on 20/10/2022</p>
                        </div>
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="end-task-time">
                            <i class="fa-regular fa-clock"></i>
                            <p>Start at 4AM on 20/10/2022</p>
                        </div>
                    </div>
                    <div class="task-description"> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum porta metus dui, sed faucibus nibh lobortis sit amet. Pellentesque in iaculis ligula. Vestibulum hendrerit tortor ut nunc porttitor, eget scelerisque urna viverra. Etiam ultricies mi eget ipsum bibendum, non aliquam turpis imperdiet. Donec nisl metus, sagittis at eros nec, euismod aliquet quam. Duis dapibus enim quis magna commodo, in blandit erat aliquam. Donec maximus sapien nec elit egestas, et fringilla lacus scelerisque. Phasellus varius justo in imperdiet tempus. Nam sed sodales ante. </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    console.log("onready")
    function onReady(){
        document.getElementById("recently").addEventListener("click", function (event){
            var targetElement = event.target || event.srcElement;
            document.getElementById("upcoming-task").classList.remove("active-filter");
            document.getElementsByClassName("notes")[1].style.display = "flex";
            document.getElementsByClassName("tasks")[0].style.display = "none";
            targetElement.classList.add("active-filter");
        })
        document.getElementById("upcoming-task").addEventListener("click", function (event){
            var targetElement = event.target || event.srcElement;
            document.getElementById("recently").classList.remove("active-filter");
            document.getElementsByClassName("notes")[1].style.display = "none";
            document.getElementsByClassName("tasks")[0].style.display = "flex";
            targetElement.classList.add("active-filter");
        })

        document.getElementById("new").addEventListener("click", function(){

        })
    }

    if (document.readyState !== "loading") {
        onReady(); // Or setTimeout(onReady, 0); if you want it consistently async
    } else {
        document.addEventListener("DOMContentLoaded", onReady);
    }

</script>
</body>