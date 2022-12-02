<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
            margin: 0 13px;
        }
        #center-panel{
            width: 15%;
            height: 85%;
            background-color: #141516;
            border-radius: 10px;
            color: #FFFFFF;
            position: relative;

        }
        #right-panel{
            width: 78%;

            margin: 0 13px;

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
        .note-name{
            --max-line: 1;
            display: -webkit-box;
            overflow: hidden;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: var(--max-line);
        }

        .selected{
            background-color: rgba(179, 179, 179, 0.33);
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
        <button id="new"><i class="fa-solid fa-plus icon"></i><p>New</p></button>
        <div class="left-panel-body">
            <div class="left-panel-taskbar" id="home"><i class="fa-solid fa-house"></i> Home</div>
            <div class="left-panel-taskbar  active" id="note"><i class="fa-solid fa-note-sticky"></i>All notes</div>
            <div class="left-panel-taskbar" id="task"><i class="fa-solid fa-clipboard-list"></i> Task</div>
            <div class="left-panel-taskbar" id="notebook"><i class="fa-solid fa-book"></i> Notebook</div>
            <div class="left-panel-taskbar justify-between" id="tag">
                <div class="gap-[1rem] flex items-center"><i class="fa-solid fa-tags"></i> Tag (<c:out value="${tags.size()}"></c:out>)</div>
                <i class="fa-solid fa-caret-down mr-7"></i>
            </div>
            <div id="tag-list"class="flex transition-[max-height] duration-400 flex-col max-h-0 overflow-hidden">
                <c:forEach items="${tags}" var="tag">
                    <div class="tag left-panel-taskbar text-sm pl-[5rem]">${tag.getTagName()}</div>
                </c:forEach>
            </div>
            <div class="left-panel-taskbar" id="share"><i class="fa-solid fa-square-share-nodes"></i> Share</div>
            <div class="left-panel-taskbar" id="trash"><i class="fa-solid fa-trash"></i> Trash</div>
        </div>
        <button id="logout-btn"><i class="fa-solid fa-right-from-bracket"></i> Logout</button>
    </div>
    <div id="center-panel">
        <div style="display: block" class="center-panel-header">

            <div class="icon-note-and-note flex flex-row pl-4 pt-2">
                <i style="font-size: 18px" class="fa-regular fa-note-sticky"></i>
                <h2  style="font-size: 20px; margin-left: 5px; margin-top: -5px">Notes</h2>

            </div>

            <div style="opacity: 0.5; font-size: 14px !important; margin: 10px 0" class="center-panel-header--number-of-notes pl-4">
                <p ><c:out value="${notesWithTag.size()}"></c:out> notes</p>
            </div>

            <div style=" opacity: 0.8 !important; " class="title-tag-listnoteview w-full">
                <div style=" font-size: 14px;opacity: 0.6 !important;display: flex; border-bottom: 1px inset #b3b3b3; padding-bottom: 8px!important;" class="title-tag w-full pl-4">
                    <div class="title w-1/2">
                        <button>TITLE</button>
                    </div>

                    <div class="Tag w-1/2">
                        <button>| TAGS</button>
                    </div>
                </div>

                <div class="listnoteview">
                    <c:forEach items="${notesWithTag}" var="item">
                    <c:if test = "${item.getNoteId() != activeNote.getNoteId()}">
                    <div style=" border-bottom: 1px inset #b3b3b3;" class="note w-full flex flex-row pl-4 py-2" data-id="${item.getNoteId()}">
                        </c:if>
                        <c:if test = "${item.getNoteId() == activeNote.getNoteId()}">
                        <div style=" border-bottom: 1px inset #b3b3b3;" class="note w-full flex flex-row pl-4 py-2 selected" data-id="${item.getNoteId()}">
                            </c:if>
                            <div class="note-name w-[50%] text-clip text-ellipsis">${item.getTitle()}</div>
                            <div class="note-tag w-[50%] overflow-hidden flex-nowrap flex flex-row gap-2">
                                <c:forEach items="${item.getTags()}" var="tag">
                                    <div class="tag rounded-full px-2 border border-white  ">${tag.getTagName()}</div>
                                </c:forEach>
                            </div>
                        </div>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>
        <div class="right-panel h-[85%] relative">
            <div class="additional-info rounded-t-xl flex flex-row w-full text-white bg-[#141516] items-center justify-between">
                <div class="tags flex flex-row  text-white py-2 px-4 gap-2 items-center">
                    <div class="tag-section--title">Tags: </div>
                    <c:forEach items="${activeNote.getTags()}" var="tag">
                        <div class="tag rounded-full px-2 border border-white  ">${tag.getTagName()}</div>
                    </c:forEach>
                    <i class="fa-solid fa-circle-plus"></i>
                </div>

            </div>
            <textarea id="note-taking" class="w-[100%] h-[95.5%]">
                ${activeNote.getTitle()}${activeNote.getContent()}
            </textarea>
            <div id="save-btn" class="transition-[bottom] duration-500 rounded-full bg-[#2cd39d] w-[60px] h-[60px] flex items-center justify-center absolute bottom-6 right-6 drop-shadow-[0_3px_3px_rgba(255,255,255,0.25)]	hover:bg-[#0e8972] hover:cursor-pointer	">
                <i class="fa-regular fa-floppy-disk text-3xl text-white"></i>
            </div>
            <div id="delete-btn" class="transition-[bottom] duration-500 rounded-full bg-[#cb1234] w-[60px] h-[60px] flex items-center justify-center absolute bottom-6 right-6 drop-shadow-[0_3px_3px_rgba(255,255,255,0.25)]	hover:bg-[#890e25] hover:cursor-pointer	">
                <i class="fa-solid fa-folder-minus text-3xl text-white"></i>
            </div>
            <div id="option-btn" class="transition-transform duration-500 rounded-full bg-[#8c63ff] w-[60px] h-[60px] flex items-center justify-center absolute bottom-6 right-6 drop-shadow-[0_3px_3px_rgba(255,255,255,0.25)]	hover:bg-[#622bdb] hover:cursor-pointer	">
                <i class="fa-solid fa-ellipsis text-4xl text-white transition-transform"></i>
            </div>

        </div>


    </div>
</div>
</body>
<script>

    function onReady(){
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
            content_style:".mce-content-body{ background-color: #222224FF; color: #FFFFFF }"
        });

        var listNote = document.getElementsByClassName("note");
        for (var i=0; i<listNote.length; i++) {
            listNote[i].addEventListener('click', function (e){
                var noteId = this.getAttribute("data-id");
                window.location.href = "/my-note/?note-id=" + noteId;
            });
        }

        document.getElementById("save-btn").addEventListener("click",function (e){
            var myContent = tinymce.get("note-taking").getContent();
            let firstBreaklineIndex = myContent.indexOf("\n");
            var title = myContent.slice(0,firstBreaklineIndex) || '<h1>YOUR TITLE HERE</h1>';
            var content = myContent.slice(firstBreaklineIndex+1) || '';
            fetch('http://localhost:8080/api/v1/note/update', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "note-id": ${activeNote.getNoteId()},
                    "title": title,
                    "content":content
                } )
            })
                .then(response => response.json())
                .then(response => console.log(JSON.stringify(response)))
        })

        document.getElementById("delete-btn").addEventListener("click",function (e){
            fetch('http://localhost:8080/api/v1/note/delete?noteId=${activeNote.getNoteId()}', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({

                } )
            })
                .then(response => window.location.href="/my-note")
        })

        document.getElementById("option-btn").addEventListener("click", function (e){
            e.target.classList.toggle("rotate-90");
            document.getElementById("save-btn").classList.toggle("bottom-28");
            document.getElementById("delete-btn").classList.toggle("bottom-48");
        })

        document.getElementById("tag").addEventListener("click", function (){
            document.getElementById("tag-list").classList.toggle("max-h-[208px]");
        })
    }


    if (document.readyState !== "loading") {
        onReady(); // Or setTimeout(onReady, 0); if you want it consistently async
    } else {
        document.addEventListener("DOMContentLoaded", onReady);
    }
</script>
</html>