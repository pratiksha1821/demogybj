import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuSolver extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private JButton solveButton, clearButton;

    public SudokuSolver() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        Font font = new Font("SansSerif", Font.BOLD, 20);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(font);
                cell.setBackground(Color.WHITE);
                cell.setForeground(Color.BLACK);
                cell.setBorder(BorderFactory.createMatteBorder(
                    (row % 3 == 0) ? 2 : 1,
                    (col % 3 == 0) ? 2 : 1,
                    (row == SIZE - 1) ? 2 : 1,
                    (col == SIZE - 1) ? 2 : 1,
                    Color.BLACK));

                // Restrict to 1–9 input
                cell.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c) || c == '0') {
                            e.consume(); // reject non-1-9 input
                        }
                    }
                });

                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }

        // Optional: Fill example puzzle
        // fillExamplePuzzle();

        solveButton = new JButton("Solve");
        solveButton.setFont(new Font("Arial", Font.BOLD, 18));
        solveButton.setBackground(new Color(46, 204, 113));
        solveButton.setForeground(Color.WHITE);
        solveButton.addActionListener(e -> solveSudoku());

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.setBackground(new Color(231, 76, 60));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> clearGrid());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        getContentPane().add(gridPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void solveSudoku() {
        int[][] board = new int[SIZE][SIZE];

        try {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    String text = cells[row][col].getText().trim();
                    if (!text.isEmpty()) {
                        int value = Integer.parseInt(text);
                        if (value < 1 || value > 9) throw new NumberFormatException();
                        board[row][col] = value;
                    } else {
                        board[row][col] = 0;
                    }
                }
            }

            if (solve(board)) {
                for (int row = 0; row < SIZE; row++) {
                    for (int col = 0; col < SIZE; col++) {
                        cells[row][col].setText(Integer.toString(board[row][col]));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No solution found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter only digits 1–9", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearGrid() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].setText("");
                cells[row][col].setBackground(Color.WHITE);
            }
        }
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) return true;
                            board[row][col] = 0; // backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }

        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;

        for (int r = 0; r < 3; r++) {
            for (int d = 0; d < 3; d++) {
                if (board[boxRowStart + r][boxColStart + d] == num)
                    return false;
            }
        }

        return true;
    }

    // OPTIONAL: You can enable this for demo purposes
    private void fillExamplePuzzle() {
        int[][] sample = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (sample[i][j] != 0) {
                    cells[i][j].setText(String.valueOf(sample[i][j]));
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuSolver solver = new SudokuSolver();
            solver.setVisible(true);
        });
    }
}
