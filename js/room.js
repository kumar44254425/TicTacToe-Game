function play(pos){

fetch("move?room="+roomId+"&pos="+pos)

}

function loadBoard(){

fetch("board?room="+roomId)

.then(r=>r.json())

.then(data=>{

let cells=document.querySelectorAll("#board button")

data.board.forEach((v,i)=>{

cells[i].innerHTML=v

})

})

}

setInterval(loadBoard,1000)