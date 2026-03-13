let gameOver = false;
let currentTurn = "";

/* CONNECT WEBSOCKET */

let socket = new WebSocket(
 (location.protocol === "https:" ? "wss://" : "ws://") +
 location.host +
 "/TicTacToe/game"
);

/* WHEN CONNECTION OPENS */

socket.onopen = function(){

 console.log("WebSocket connected");

 /* send room join message */

 socket.send(JSON.stringify({
  type:"join",
  room:roomId,
  player:player
 }));

};

/* RECEIVE MESSAGE FROM SERVER */

socket.onmessage = function(event){

 let data = JSON.parse(event.data);

 console.log("Server data:",data);

 /* update board */

 if(data.board){
  updateBoard(data.board);
 }

 /* update turn */

 if(data.turn){
  updateTurn(data.turn);
 }

 /* update player names */

 if(data.player1 && data.player2){
  document.querySelector(".players").innerText =
  data.player1 + " VS " + data.player2;
 }

};

socket.onclose = function(){

 console.log("WebSocket disconnected");

};

/* PLAYER MOVE */

function play(index){

 if(gameOver) return;

 let cells = document.querySelectorAll("#board button");

 /* prevent clicking filled cell */

 if(cells[index].innerText !== "") return;

 /* check turn */

 if(currentTurn !== getSymbol()) return;

 /* send move to server */

 socket.send(JSON.stringify({
  room:roomId,
  player:player,
  index:index
 }));

}

/* UPDATE BOARD */

function updateBoard(board){

 let cells = document.querySelectorAll("#board button");

 let arr = board.split(",");

 for(let i=0;i<9;i++){

  if(arr[i] === "-"){

   cells[i].innerText="";
   cells[i].disabled=false;

  }else{

   cells[i].innerText=arr[i];
   cells[i].disabled=true;

  }

 }

 checkWinner(arr);

}

/* UPDATE TURN */

function updateTurn(turn){

 currentTurn = turn;

 document.getElementById("turn").innerText =
 "Turn : " + turn;

}

/* GET PLAYER SYMBOL */

function getSymbol(){

 let names =
 document.querySelector(".players").innerText.split(" VS ");

 if(player === names[0]) return "X";
 else return "O";

}

/* CHECK WINNER */

function checkWinner(cells){

 let combos=[

 [0,1,2],[3,4,5],[6,7,8],
 [0,3,6],[1,4,7],[2,5,8],
 [0,4,8],[2,4,6]

 ];

 for(let c of combos){

  let a=cells[c[0]];
  let b=cells[c[1]];
  let d=cells[c[2]];

  if(a === "" || a === "-") continue;

  if(a===b && b===d){

   gameOver=true;

   document.getElementById("winner").innerText =
   "Winner : " + a;

   alert("Winner : " + a);

   return;

  }

 }

}

/* GO BACK BUTTON */

function goBack(){

 window.location.href="index.jsp";

}

/* PLAY AGAIN BUTTON */

function playAgain(){

 location.reload();

}