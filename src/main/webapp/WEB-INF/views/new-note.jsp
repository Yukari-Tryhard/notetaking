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
        }
        #right-panel{
            width: 78%;
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
    </style>
</head>
<body>
<div class="container-xl mx-auto linear-bg flex flex-row items-center justify-around">
    <div id="left-panel">
        <div class="left-panel-header">
            <div class="avatar-and-name">
                <div class="avatar-ligma"><img src="https://i.pinimg.com/736x/7a/69/fc/7a69fc7139dd2faaf696b9acc167afc0.jpg"/></div>
                <div class="name-ligma">Hoki</div>
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
            <div class="left-panel-taskbar" id="note"><i class="fa-solid fa-note-sticky"></i>All notes</div>
            <div class="left-panel-taskbar" id="task"><i class="fa-solid fa-clipboard-list"></i> Task</div>
            <div class="left-panel-taskbar" id="notebook"><i class="fa-solid fa-book"></i> Notebook</div>
            <div class="left-panel-taskbar" id="tag"><i class="fa-solid fa-tags"></i> Tag</div>
            <div class="left-panel-taskbar" id="share"><i class="fa-solid fa-square-share-nodes"></i> Share</div>
            <div class="left-panel-taskbar" id="trash"><i class="fa-solid fa-trash"></i> Trash</div>
        </div>
        <button id="logout-btn"><i class="fa-solid fa-right-from-bracket"></i> Logout</button>
    </div>

    <textarea id="right-panel">
      <h1>Hoki note</h1>
    </textarea>
</div>
</body>
<script>
    tinymce.init({
        selector: 'textarea#right-panel',
        plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage tinycomments tableofcontents footnotes mergetags autocorrect',
        toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
        width: '78%',
        height: '85%',
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
</script>
</html>