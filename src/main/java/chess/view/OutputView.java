package chess.view;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String CHESS_GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String GAME_START_COMMAND_MESSAGE = "> 게임 시작 : start";
    private static final String GAME_END_COMMAND_MESSAGE = "> 게임 종료 : end";
    private static final String GAME_MOVE_COMMAND_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String TEAM_SCORE_DELIMITER = ": ";
    private static final String WINNER_FORMAT = "우승 팀: %s\n";
    private static final String EMPTY_SPACE = ".";
    private static final List<Integer> rows = List.of(8, 7, 6, 5, 4, 3, 2, 1);
    private static final List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);

    private OutputView() {}

    public static void printChessGameStart() {
        String stringBuilder = CHESS_GAME_START_MESSAGE
                + System.lineSeparator()
                + GAME_START_COMMAND_MESSAGE
                + System.lineSeparator()
                + GAME_END_COMMAND_MESSAGE
                + System.lineSeparator()
                + GAME_MOVE_COMMAND_MESSAGE;
        System.out.println(stringBuilder);
    }

    public static void printCurrentChessBoard(Map<ChessBoardPosition, ChessPiece> mapData) {
        for (int row : rows) {
            printRow(mapData, row);
            System.out.println();
        }
    }

    private static void printRow(Map<ChessBoardPosition, ChessPiece> boardData, int row) {
        for (int column : columns) {
            printSector(boardData, row, column);
        }
    }

    private static void printSector(Map<ChessBoardPosition, ChessPiece> boardData, int row, int column) {
        ChessBoardPosition target = ChessBoardPosition.of(column, row);
        if (boardData.containsKey(target)) {
            printChessPiece(boardData.get(target));
            return;
        }
        System.out.print(EMPTY_SPACE);
    }

    private static void printChessPiece(ChessPiece chessPiece) {
        if (chessPiece.isSameTeam(Team.BLACK)) {
            System.out.print(ChessPieceName.of(chessPiece).toUpperCase());
            return;
        }
        System.out.print(ChessPieceName.of(chessPiece));
    }

    public static void printStatus(Map<Team, Double> teamScore, Team winner) {
        for (Entry<Team, Double> entry : teamScore.entrySet()) {
            System.out.println(entry.getKey() + TEAM_SCORE_DELIMITER + entry.getValue());
        }
        System.out.printf(WINNER_FORMAT, winner);
    }
}
