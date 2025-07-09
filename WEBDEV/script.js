// Generate the 9x9 grid inputs
const gridElement = document.getElementById("sudoku-grid");

for (let i = 0; i < 81; i++) {
  const input = document.createElement("input");
  input.setAttribute("type", "text");
  input.setAttribute("maxlength", "1");
  input.setAttribute("id", `cell-${Math.floor(i / 9)}-${i % 9}`);
  input.oninput = () => {
    if (!/^[1-9]?$/.test(input.value)) input.value = "";
  };
  gridElement.appendChild(input);
}

// Helper to get the current board
function getBoard() {
  let board = [];
  for (let i = 0; i < 9; i++) {
    let row = [];
    for (let j = 0; j < 9; j++) {
      const val = document.getElementById(`cell-${i}-${j}`).value;
      row.push(val === "" ? 0 : parseInt(val));
    }
    board.push(row);
  }
  return board;
}

// Helper to set the board
function setBoard(board) {
  for (let i = 0; i < 9; i++) {
    for (let j = 0; j < 9; j++) {
      document.getElementById(`cell-${i}-${j}`).value = board[i][j] === 0 ? "" : board[i][j];
    }
  }
}

// Sudoku solver (Backtracking)
function isSafe(board, row, col, num) {
  for (let x = 0; x < 9; x++) {
    if (board[row][x] === num || board[x][col] === num) return false;
  }

  let startRow = row - row % 3;
  let startCol = col - col % 3;
  for (let i = 0; i < 3; i++) {
    for (let j = 0; j < 3; j++) {
      if (board[i + startRow][j + startCol] === num) return false;
    }
  }

  return true;
}

function solveSudoku(board) {
  for (let row = 0; row < 9; row++) {
    for (let col = 0; col < 9; col++) {
      if (board[row][col] === 0) {
        for (let num = 1; num <= 9; num++) {
          if (isSafe(board, row, col, num)) {
            board[row][col] = num;
            if (solveSudoku(board)) return true;
            board[row][col] = 0;
          }
        }
        return false;
      }
    }
  }
  return true;
}

// Public solve function
function solve() {
  let board = getBoard();
  if (solveSudoku(board)) {
    setBoard(board);
    alert("Sudoku solved!");
  } else {
    alert("No solution exists.");
  }
}

// Clear grid
function clearGrid() {
  for (let i = 0; i < 9; i++)
    for (let j = 0; j < 9; j++)
      document.getElementById(`cell-${i}-${j}`).value = "";
}
