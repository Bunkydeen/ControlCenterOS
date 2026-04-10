let page = 0;

function toggle(el){
    el.classList.toggle("active");
    saveState();
}

function nextPage(){
    page = (page + 1) % 2;
    document.querySelectorAll(".page").forEach((p,i)=>{
        p.style.transform = `translateX(${-page * 100}%)`;
    });
}

function saveState(){
    let state = [];
    document.querySelectorAll(".tile").forEach(t=>{
        state.push({text:t.innerText, active:t.classList.contains("active")});
    });
    localStorage.setItem("cc_state", JSON.stringify(state));
}

function loadState(){
    let state = JSON.parse(localStorage.getItem("cc_state") || "[]");
    let tiles = document.querySelectorAll(".tile");
    tiles.forEach((t,i)=>{
        if(state[i]?.active) t.classList.add("active");
    });
}

loadState();
