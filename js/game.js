let current = "X";

let gameOver = false;

function play(btn){

if(gameOver) return;

if(btn.innerHTML !== "") return;

btn.innerHTML = current;

checkWinner();

current = current === "X" ? "O" : "X";

}

function checkWinner(){

let cells = document.querySelectorAll("#board button");

let combos = [

[0,1,2],[3,4,5],[6,7,8],

[0,3,6],[1,4,7],[2,5,8],

[0,4,8],[2,4,6]

];

for(let c of combos){

if(

cells[c[0]].innerHTML !== "" &&

cells[c[0]].innerHTML === cells[c[1]].innerHTML &&

cells[c[1]].innerHTML === cells[c[2]].innerHTML

){

gameOver = true;

let win = cells[c[0]].innerHTML;

document.getElementById("winner").value = win;

alert("Winner: " + win);

}

}

}