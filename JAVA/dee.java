import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolver extends JFrame {
    private JTextField[][] gridCells = new JTextField[9][9];
    private JButton solveButton, clearButton;
    private JPanel sudokuPanel, buttonPanel;
    private JLabel titleLabel;

    public SudokuSolver() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Set a modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        // Initialize the 9x9 grid of text fields
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                gridCells[row][col] = new JTextField(1);
                gridCells[row][col].setHorizontalAlignment(JTextField.CENTER);
                gridCells[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                
                // Set different background for 3x3 blocks
                if ((row / 3 + col / 3) % 2 == 0) {
                    gridCells[row][col].setBackground(new Color(240, 240, 240));
                } else {
                    gridCells[row][col].setBackground(Color.WHITE);
                }
            }
        }

        // Initialize buttons
        solveButton = new JButton("Solve");
        solveButton.setBackground(new Color(76, 175, 80)); // Green color
        solveButton.setForeground(Color.WHITE);
        solveButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(244, 67, 54)); // Red color
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Initialize title label
        titleLabel = new JLabel("SUDOKU SOLVER", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 150, 243)); // Blue color

        // Add action listeners
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearGrid();
            }
        });
    }

    private void layoutComponents() {
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add title to the north
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create sudoku panel with 9x9 grid
        sudokuPanel = new JPanel(new GridLayout(9, 9, 1, 1));
        sudokuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        // Add thicker borders for 3x3 blocks
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JPanel cellPanel = new JPanel(new BorderLayout());
                cellPanel.setBorder(BorderFactory.createMatteBorder(
                    row % 3 == 0 ? 2 : 1, 
                    col % 3 == 0 ? 2 : 1, 
                    1, 1, Color.BLACK));
                cellPanel.add(gridCells[row][col]);
                sudokuPanel.add(cellPanel);
            }
        }

        mainPanel.add(sudokuPanel, BorderLayout.CENTER);

        // Button panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void solveSudoku() {
        int[][] board = new int[9][9];
        
        // Read values from GUI
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = gridCells[row][col].getText().trim();
                try {
                    if (!text.isEmpty()) {
                        int num = Integer.parseInt(text);
                        if (num < 1 || num > 9) {
                            JOptionPane.showMessageDialog(this, "Invalid number at row " + (row+1) + 
                                ", column " + (col+1) + ". Please enter numbers between 1-9.");
                            return;
                        }
                        board[row][col] = num;
                    } else {
                        board[row][col] = 0;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid input at row " + (row+1) + 
                        ", column " + (col+1) + ". Please enter numbers only.");
                    return;
                }
            }
        }

        // Check if initial board is valid
        if (!isValidBoard(board)) {
            JOptionPane.showMessageDialog(this, "Invalid initial Sudoku configuration. " +
                "Check for duplicate numbers in rows, columns, or 3x3 boxes.");
            return;
        }

        // Solve the Sudoku
        if (solve(board)) {
            // Update the GUI with solution
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    gridCells[row][col].setText(String.valueOf(board[row][col]));
                    // Highlight the solution numbers (not the initial ones)
                    if (gridCells[row][col].getText().trim().isEmpty()) {
                        gridCells[row][col].setForeground(Color.BLUE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists for this Sudoku puzzle.");
        }
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = 0; // backtrack
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) return false;
        }
        
        // Check column
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) return false;
        }
        
        // Check 3x3 box
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;
        for (int r = boxStartRow; r < boxStartRow + 3; r++) {
            for (int c = boxStartCol; c < boxStartCol + 3; c++) {
                if (board[r][c] == num) return false;
            }
        }
        
        return true;
    }

    private boolean isValidBoard(int[][] board) {
        // Check rows
        for (int row = 0; row < 9; row++) {
            boolean[] seen = new boolean[10];
            for (int col = 0; col < 9; col++) {
                int num = board[row][col];
                if (num != 0 && seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        
        // Check columns
        for (int col = 0; col < 9; col++) {
            boolean[] seen = new boolean[10];
            for (int row = 0; row < 9; row++) {
                int num = board[row][col];
                if (num != 0 && seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        
        // Check 3x3 boxes
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                boolean[] seen = new boolean[10];
                for (int row = boxRow * 3; row < boxRow * 3 + 3; row++) {
                    for (int col = boxCol * 3; col < boxCol * 3 + 3; col++) {
                        int num = board[row][col];
                        if (num != 0 && seen[num]) {
                            return false;
                        }
                        seen[num] = true;
                    }
                }
            }
        }
        
        return true;
    }

    private void clearGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                gridCells[row][col].setText("");
                gridCells[row][col].setForeground(Color.BLACK);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuSolver().setVisible(true);
            }
        });
    }
}