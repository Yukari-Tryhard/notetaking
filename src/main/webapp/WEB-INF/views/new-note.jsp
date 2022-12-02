<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head >
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
    <script src="https://cdn.tiny.cloud/1/5bqionfmj2ip2dfagxm86bzuckperf675cuancpuqyxli9g1/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="https://kit.fontawesome.com/7358458173.js" crossorigin="anonymous"></script>

    <meta charset="ISO-8859-1">
    <title>Spring MVC 5 - form handling | Java Guides</title>
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
        #right-panel{
            width: 78% !important;
        }
        .tox-editor-header{
            background-color: #141516 !important;
        }
        .tox-toolbar__primary{
            background-color: #141516 !important;
        }
        .tox .tox-tbtn svg{
            fill: #FFFFFF !important;
        }
        .tox .tox-tbtn svg:hover, .tox .tox-tbtn:hover{
            background-color: #4e4e4e !important;
        }
        .tox-tinymce{
            border: none !important;
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
        .tox-editor-header{
            background-color: #141516 !important;
        }
        .tox-toolbar__primary{
            background-color: #141516 !important;
        }
        .tox .tox-tbtn svg{
            fill: #FFFFFF !important;
        }
        .tox .tox-tbtn svg:hover, .tox .tox-tbtn:hover{
            background-color: #4e4e4e !important;
        }
        .tox-tinymce{
            border: none !important;
            border-radius: 0 0 0.5rem 0.5rem !important;
        }
    </style>
</head>
<body>
<div class="container-xl mx-auto linear-bg flex flex-row items-center justify-around">
    <div id="left-panel">
        <div class="left-panel-header">
            <div class="avatar-and-name">
                <div class="avatar-ligma"><img src="https://i.pinimg.com/736x/7a/69/fc/7a69fc7139dd2faaf696b9acc167afc0.jpg"/></div>
                <div class="name-ligma">${user.getEmail()}</div>
            </div>
            <i class="fa-solid fa-gear"></i>
        </div>
        <div class="search-container">
            <i class="fa-solid fa-magnifying-glass search-icon"></i>
            <input id="search" placeholder="Search"/>
        </div>

        <button id="new"><i class="fa-solid fa-plus icon"></i><p>New</p></button>
        <div class="left-panel-body">
            <div class="left-panel-taskbar active" id="home"><i class="fa-solid fa-house"></i> Home</div>
            <div class="left-panel-taskbar" onclick="window.location.href='/my-note'" id="note"><i class="fa-solid fa-note-sticky"></i>All notes</div>
            <div class="left-panel-taskbar" onclick="window.location.href='/my-task'" id="task"><i class="fa-solid fa-clipboard-list"></i> Task</div>
            <div class="left-panel-taskbar" onclick="window.location.href='/my-notebook'" id="notebook"><i class="fa-solid fa-book"></i> Notebook</div>
            <div class="left-panel-taskbar justify-between" id="tag">
                <div class="gap-[1rem] flex items-center"><i class="fa-solid fa-tags"></i> Tag (<c:out value="${tags.size()}"></c:out>)</div>
                <i class="fa-solid fa-caret-down mr-7"></i>
            </div>
            <div id="tag-list"class="flex transition-[max-height] duration-400 flex-col max-h-0 overflow-auto">
                <c:forEach items="${tags}" var="tag">
                    <div class="tag left-panel-taskbar text-sm pl-[5rem]">${tag.getTagName()}</div>
                </c:forEach>
            </div>
        </div>
        <button id="logout-btn"><i class="fa-solid fa-right-from-bracket"></i> Logout</button>
    </div>

    <div class="right-panel h-[85%] relative">
        <div class="additional-info rounded-t-xl flex flex-row justify-start w-full text-white bg-[#141516] items-center gap-2">
            <div class="tags flex flex-row  text-white py-2 px-4 gap-2 items-center">
                <div class="tag-section--title">Tags: </div>
            </div>
            <i id="add-tag" class="fa-solid fa-circle-plus hover:cursor-pointer"></i>

        </div>
        <textarea id="note-taking" class="w-[100%] h-[95.5%]">
            <h1>New notetitle</h1>
        </textarea>

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
    <div id="add-notebook" class="container rounded flex flex-col	items-center justify-center h-2/3 w-1/4 bg-white hover:cursor-pointer hover:bg-blue-500 hover:text-white">
        <i class="fa-solid fa-tags text-8xl flex"></i>
        <div class="text-xl font-medium">Notebook</div>
        <div class="text-center">Create your own moment</div>
    </div>
</div>
</body>
<script>
    function onReady(){

        function updateNoteHandlder(){
            var myContent = tinymce.get("note-taking").getContent();
            let firstBreaklineIndex = myContent.indexOf("\n");
            var title = myContent.slice(0,firstBreaklineIndex) || '<h1>YOUR TITLE HERE</h1>';
            var content = myContent.slice(firstBreaklineIndex+1) || '';
            tagsEle = document.querySelectorAll('.tags > .tag');

            var tags = [];
            for (var i = 0; i < tagsEle.length; i++){
                tags.push(tagsEle[i].innerHTML);

            }

            fetch('http://localhost:8080/api/v1/note/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "user-id": ${user.getId()},
                    "title": title,
                    "content":content,
                    "tags": tags
                } )
            })
                .then(response => response.json())
                .then(response => console.log(JSON.stringify(response)))
        }


        tinymce.init({
            selector: 'textarea#note-taking',
            plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage tinycomments tableofcontents footnotes mergetags autocorrect',
            toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
            width: '100%',
            height: '95.5%',
            statusbar: false,
            menubar: false,
            tinycomments_mode: 'embedded',
            tinycomments_author: 'Author name',
            mergetags_list: [
                { value: 'First.Name', title: 'First Name' },
                { value: 'Email', title: 'Email' },
            ],
            content_style:".mce-content-body{ background-color: #222224FF; color: #FFFFFF }",

        });

        document.getElementById("new").addEventListener("click", function(){
            document.getElementById("overlay").classList.remove("hidden");
            document.getElementById("choices").classList.remove("hidden");
        })

        document.getElementById("add-note").addEventListener("click", function(){
            window.location.href = "/new-note";
        })

        document.getElementById("add-notebook").addEventListener("click", function(){
            window.location.href = "/new-notebook";
        })

        document.getElementById("add-task").addEventListener("click", function(){
            window.location.href = "/new-task";
        })

        document.getElementById("tag").addEventListener("click", function (){
            document.getElementById("tag-list").classList.toggle("max-h-[208px]");
        })

        var tagsEle = document.querySelectorAll('.tags > .tag');

        document.addEventListener("click", function (e){
            for (var i = 0; i < tagsEle.length; i++){
                if (tagsEle[i].innerHTML == ""){
                    tagsEle[i].remove();
                }
            }
        }, true)

        document.getElementById("add-tag").addEventListener("click",function (){
            let newTag = document.createElement("div");
            newTag.classList.add(...["tag", "rounded-full", "px-2" ,"border","border-white"]);
            newTag.contentEditable = "true";
            newTag.innerHTML = "DefaultTag";
            document.querySelector(".tags").appendChild(newTag);
            tagsEle = document.querySelectorAll('.tags > .tag');
            document.addEventListener("click", function (e){
                for (var i = 0; i < tagsEle.length; i++){
                    if (tagsEle[i].innerHTML == ""){
                        tagsEle[i].remove();
                    }
                }
            }, true)
        })

        window.onbeforeunload = function(){
            updateNoteHandlder();
        };
    }



    if (document.readyState !== "loading") {
        onReady(); // Or setTimeout(onReady, 0); if you want it consistently async
    } else {
        document.addEventListener("DOMContentLoaded", onReady);
    }

</script>
</html>