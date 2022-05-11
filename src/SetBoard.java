import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SetBoard extends JFrame{

    private CardUI[][] cardMatrix = new CardUI[3][4];
    private CardUI[][] add3Matrix = new CardUI[3][1];
    Card first, second, third;
    Card cardClass = new Card();
    Deck deckClass = new Deck();
    ArrayList<Card> deck;
    ArrayList<Card> board = new ArrayList<>();
    int points = 0;
    JPanel cardsPanel = new JPanel();
    JPanel scorePanel = new JPanel();
    JLabel cardLeftLabel;
    JLabel setLabel;
    JPanel add3Panel = new JPanel();
    JButton add3Cards = new JButton();
    JLabel add;

    public SetBoard() {
        super("Set Card Game");
        deck = deckClass.createDeck();
        moveCards(Deck.shuffle(deck), board, 12);

        while (!cardClass.setExists(board)) {
            moveCards(board, deck, 12);
            moveCards(Deck.shuffle(deck), board, 12);
        }

        updateBoard();

        add3Panel.setBackground(Color.YELLOW);
        add3Panel.setLayout(new GridLayout(3, 1));
        add3Panel.setSize(700, 600);
        add3Cards.setBackground(Color.white);
        add3Cards.setSize(50, 20);
        cardsPanel.setBackground(Color.GRAY);
        cardsPanel.setLayout(new GridLayout(3, 4));
        cardsPanel.setSize(700, 600);
        add = new JLabel("Add 3 cards");
        add.setFont(new Font("Serif", Font.PLAIN, 50));
        add3Cards.add(add);
        scorePanel.setBackground(Color.GREEN);
        scorePanel.setLayout(new GridLayout(1, 2));
        scorePanel.setSize(700, 100);
        cardLeftLabel = new JLabel("Card Left : " + deck.size());
        cardLeftLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        setLabel = new JLabel("Points : " + points);
        setLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        scorePanel.add(cardLeftLabel);
        scorePanel.add(setLabel);
        scorePanel.add(add3Cards);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        add(cardsPanel, BorderLayout.NORTH);
        add(scorePanel);
        setVisible(true);

        if(deck.size() == 0 && cardClass.isSetPresent(board) == false) {
            System.out.println("Game over.");
        }
    }

    private void updateBoard() {
        String fileName;
        Card card;
        int index = 0;
        for (int i = 0; i < cardMatrix.length; i++) {
            for (int j = 0; j < cardMatrix[0].length; j++) {
                card = board.get(index++);
                if(cardMatrix[i][j] != null){
                    cardsPanel.remove(cardMatrix[i][j]);
                }

                cardMatrix[i][j] = new CardUI(i, j, card.getColor(), card.getShading(), card.getShape(), card.getNumber());
                fileName = "/images/" + card.toString() + ".jpeg";
                try {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(fileName)).getImage().getScaledInstance(170, 180, Image.SCALE_DEFAULT));
                    cardMatrix[i][j].setIcon(imageIcon);
                } catch (Exception e) {
                    System.out.println("File not found for" + fileName);

                }

                cardMatrix[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CardUI clickedCard = (CardUI) e.getSource();
                        handleClickEvent(clickedCard.getCoordinates());
                    }
                });
                cardsPanel.add(cardMatrix[i][j]);
            }
        }
    }

    private void add3CardsBoard() {
        String fileName;
        Card card;
        int index = 0;
        for (int i = 0; i < add3Matrix.length; i++) {
            for (int j = 0; j < add3Matrix[0].length; j++) {
                card = board.get(index++);
                if(add3Matrix[i][j] != null){
                    add3Panel.remove(add3Matrix[i][j]);
                }

                add3Matrix[i][j] = new CardUI(i, j, card.getColor(), card.getShading(), card.getShape(), card.getNumber());
                fileName = "/images/" + card.toString() + ".jpeg";
                try {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(fileName)).getImage().getScaledInstance(170, 180, Image.SCALE_DEFAULT));
                    add3Matrix[i][j].setIcon(imageIcon);
                } catch (Exception e) {
                    System.out.println("File not found for" + fileName);
                }

                add3Matrix[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CardUI clickedCard = (CardUI) e.getSource();
                        handleClickEventFor3Cards(clickedCard.getCoordinates());
                    }
                });
                add3Panel.add(add3Matrix[i][j]);
            }
        }
    }

    private void handleClickEventFor3Cards(int[] clickedCardXY) {
        int i = clickedCardXY[0];
        int j = clickedCardXY[1];
        add3Matrix[i][j].setHighlight(true);
        CardUI card = add3Matrix[i][j];
        Card tempCard = new Card(card.getColor(), card.getShading(), card.getShape(), card.getNum());

        if (first == null) {
            first = tempCard;
        } else if (first != null && second == null) {
            second = tempCard;
        } else if (first != null && second != null && third == null) {
            third = tempCard;

            if (cardClass.isSet(first, second, third)) {
                points++;
                for (int k = 0; k < board.size(); k++) {
                    String tableCardString = board.get(k).toString();
                    if(first.toString().equals(tableCardString) ||
                            second.toString().equals(tableCardString) ||
                            third.toString().equals(tableCardString)
                    ){
                        board.remove(k);
                    }
                }
                moveCards(deck, board, 3);
                add3CardsBoard();
                updateTitleBoard();
            }
            first = second = third = null;
            setColorToDefault();

        }

    }

    private void handleClickEvent(int[] clickedCardXY) {
        int i = clickedCardXY[0];
        int j = clickedCardXY[1];
        cardMatrix[i][j].setHighlight(true);
        CardUI card = cardMatrix[i][j];
        Card tempCard = new Card(card.getColor(), card.getShading(), card.getShape(), card.getNum());
        if (first == null) {
            first = tempCard;
        } else if (first != null && second == null) {
            second = tempCard;
        } else if (first != null && second != null && third == null) {
            third = tempCard;
            if (cardClass.isSet(first, second, third)) {
                points++;
                for (int k = 0; k < board.size(); k++) {
                    String tableCardString = board.get(k).toString();
                    if(first.toString().equals(tableCardString) ||
                            second.toString().equals(tableCardString) ||
                            third.toString().equals(tableCardString)) {
                        board.remove(k);
                    }
                }

                moveCards(deck, board, 3);
                updateBoard();
                updateTitleBoard();
            }
            first = second = third = null;
            setColorToDefault();
        }
    }


    private void updateTitleBoard() {
        cardLeftLabel.setText("Card Left : " + deck.size());
        setLabel.setText("Points : " + points);
    }


    private void setColorToDefault() {
        for (int i = 0; i < cardMatrix.length; i++) {
            for (int j = 0; j < cardMatrix[0].length; j++) {
                cardMatrix[i][j].setHighlight(false);
            }
        }
    }

    void moveCards(ArrayList<Card> from, ArrayList<Card> to, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            if (from.isEmpty()) break;
            to.add(from.remove(from.size() - 1));
        }
    }

    public void setButtonPressed() {
        ActionListener addPressed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() != null) {
                    add(add3Panel);

                }
            }
        };
        add3Cards.addActionListener(addPressed);
    }
}



