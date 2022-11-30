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

        #right-panel{
            width: 75%;
            height: 85%;
        }
        .notes, .tasks{
            flex-direction: row;
            align-items: center;
            gap: 1.2rem;
            cursor: grab;
            overflow: auto;
            flex: 1;
        }

        .notes, .note{
            display: flex;
        }
        .note, .task{
            width: 15%;
            display: flex;
            flex-shrink: 0;
            height: 45%;
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
            <div class="left-panel-taskbar" id="home"><i class="fa-solid fa-house" ></i> Home</div>
            <div class="left-panel-taskbar" id="note" onclick="window.location.href='/my-note'"><i class="fa-solid fa-note-sticky" ></i>All notes</div>
            <div class="left-panel-taskbar" id="task" onclick="window.location.href='/my-task'"><i class="fa-solid fa-clipboard-list" ></i> Task</div>
            <div class="left-panel-taskbar active" id="notebook" onclick="window.location.href='/my-notebook'"><i class="fa-solid fa-book" ></i> Notebook</div>
            <div class="left-panel-taskbar justify-between" id="tag">
                <div class="gap-[1rem] flex items-center"><i class="fa-solid fa-tags"></i> Tag (4)</div>
                <i class="fa-solid fa-caret-down mr-7"></i>
            </div>
            <div id="tag-list"class="flex transition-[max-height] duration-400 flex-col max-h-0 overflow-hidden">
                <div class="tag left-panel-taskbar text-sm pl-[5rem]">Cooking (2)</div>
                <div class="tag left-panel-taskbar text-sm pl-[5rem]">Cooking (2)</div>
                <div class="tag left-panel-taskbar text-sm pl-[5rem]">Cooking (2)</div>
                <div class="tag left-panel-taskbar text-sm pl-[5rem]">Cooking (2)</div>
            </div>
            <div class="left-panel-taskbar" id="share"><i class="fa-solid fa-square-share-nodes"></i> Share</div>
            <div class="left-panel-taskbar" id="trash" onclick="window.location.href='/my-trash'"><i class="fa-solid fa-trash" ></i> Trash</div>
        </div>
        <button id="logout-btn"><i class="fa-solid fa-right-from-bracket"></i> Logout</button>
    </div>
    <div id="right-panel" class="justify-between flex flex-col">
        <div class="list-notebook w-full h-[25%] bg-[#141516] rounded-xl">
            <div class="list-notebook--filter flex flex-row justify-end pt-2 gap-2 items-center">
                <i class="fa-solid fa-filter text-white"></i>
                <input type="dropdown" class="bg-[#141516] text-white"/>

                <i class="fa-solid fa-sort text-white"></i>
                <input type="dropdown" class="bg-[#141516] text-white"/>
            </div>
            <div class="list-notebook--list flex flex-row gap-8 pl-4">
                <div class="notebook flex flex-col items-center gap-1 mt-4">
                    <div class="notebook-image w-[80px] h-[80px] rounded-full bg-white border-[#8c63ff] border-4"></div>
                    <div class="notebook-name text-[#8c63ff]">Diary</div>
                </div>
                <div class="notebook flex flex-col items-center gap-1 mt-4">
                    <div class="notebook-image w-[80px] h-[80px] rounded-full bg-white"></div>
                    <div class="notebook-name text-white">Diary</div>
                </div>
                <div class="notebook flex flex-col items-center gap-1 mt-4">
                    <div class="notebook-image w-[80px] h-[80px] rounded-full bg-white"></div>
                    <div class="notebook-name text-white">Diary</div>
                </div>
                <div class="notebook flex flex-col items-center gap-1 mt-4">
                    <div class="notebook-image w-[80px] h-[80px] rounded-full bg-white"></div>
                    <div class="notebook-name text-white">Diary</div>
                </div>
                <div class="notebook flex flex-col items-center gap-1 mt-4">
                    <div class="notebook-image w-[80px] h-[80px] rounded-full bg-white"></div>
                    <div class="notebook-name text-white">Diary</div>
                </div>
            </div>
        </div>
        <div class="note-list  ease-in-out	 w-full h-[70%] bg-[#141516] rounded-xl flex flex-row flex-wrap pt-4 pl-4 gap-3 items-around">
            <div class="note">
                        <div class="note--name">My First Note</div>
                        <div class="note--modify">Last modify 20/10/2022</div>

                        <div class="note--content">My first note </div>
            </div>
            <div class="note">
                <div class="note--name">My First Note</div>
                <div class="note--modify">Last modify 20/10/2022</div>

                <div class="note--content">My first note </div>
            </div>
            <div class="note">
                <div class="note--name">My First Note</div>
                <div class="note--modify">Last modify 20/10/2022</div>

                <div class="note--content">My first note </div>
            </div>
            <div class="note">
                <div class="note--name">My First Note</div>
                <div class="note--modify">Last modify 20/10/2022</div>

                <div class="note--content">My first note </div>
            </div>
            <div class="note">
                <div class="note--name">My First Note</div>
                <div class="note--modify">Last modify 20/10/2022</div>

                <div class="note--content">My first note </div>
            </div>
            <div class="note">
                <div class="note--name">My First Note</div>
                <div class="note--modify">Last modify 20/10/2022</div>

                <div class="note--content">My first note </div>
            </div>
            <div class="note">
                <div class="note--name">My First Note</div>
                <div class="note--modify">Last modify 20/10/2022</div>

                <div class="note--content">My first note </div>
            </div>
            <div class="note">
                <div class="note--name">My First Note</div>
                <div class="note--modify">Last modify 20/10/2022</div>

                <div class="note--content">My first note </div>
            </div>
        </div>
    </div>

</div>
<div id="overlay" class="absolute w-full hidden h-full bg-black z-10 top-0 left-0 opacity-40">
</div>
<div id="choices" class="container flex hidden  w-1/3 h-1/3 bg-[#151515] absolute z-10 top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 rounded-xl  flex flex-row items-center	 justify-around">
    <div id="add-note" class="container rounded flex flex-col	items-center justify-center h-2/3 w-1/4 bg-white hover:cursor-pointer hover:bg-red-500 hover:text-white">
        <i class="fa-solid fa-note-sticky text-8xl flex"></i>
        <div class="text-xl font-medium">Note</div>
        <div class="text-center">Save important thing to you</div>
    </div>
    <div id="add-task" class="container rounded flex flex-col	items-center justify-center h-2/3 w-1/4 bg-white hover:cursor-pointer hover:bg-green-500 hover:text-white">
        <i class="fa-solid fa-list-check text-8xl flex"></i>
        <div class="text-xl font-medium">Task</div>
        <div class="text-center">Reminder for what you will do</div>
    </div>
    <div id="add-tag" class="container rounded flex flex-col	items-center justify-center h-2/3 w-1/4 bg-white hover:cursor-pointer hover:bg-blue-500 hover:text-white">
        <i class="fa-solid fa-tags text-8xl flex"></i>
        <div class="text-xl font-medium">Tag</div>
        <div class="text-center">Organise your work with label</div>
    </div>
</div>
<script>
    console.log("onready")
    function onReady(){

        document.getElementById("new").addEventListener("click", function(){
            document.getElementById("overlay").classList.remove("hidden");
            document.getElementById("choices").classList.remove("hidden");
        })

        document.getElementById("overlay").addEventListener("click", function (e){
            e.target.classList.add("hidden");
            document.getElementById("choices").classList.add("hidden");
        })

        document.getElementById("add-note").addEventListener("click", function(){
            window.location.href = "/add-note";
        })

        document.getElementById("add-task").addEventListener("click", function(){
            window.location.href = "/add-task";
        })

        document.getElementById("tag").addEventListener("click", function (){
            document.getElementById("tag-list").classList.toggle("max-h-[1000px]");
        })
    }

    if (document.readyState !== "loading") {
        onReady(); // Or setTimeout(onReady, 0); if you want it consistently async
    } else {
        document.addEventListener("DOMContentLoaded", onReady);
    }

</script>
</body>