# -*- coding: utf-8 -*-

class TennisGame1:

    def __init__(self, player1Name, player2Name):
        self.player1Name = player1Name
        self.player2Name = player2Name
        self.p1points = 0
        self.p2points = 0

    def won_point(self, playerName):
        if playerName == self.player1Name:
            self.p1points += 1
        else:
            self.p2points += 1

    def score(self):
        result = ""
        game_tied = self.p1points == self.p2points
        if game_tied and self.p1points >= 3:
            return "Deuce"
        if game_tied:
            result = {
                0: "Love-All",
                1: "Fifteen-All",
                2: "Thirty-All",
            }[self.p1points]
            return result
        elif (self.p1points >= 4 or self.p2points >= 4):
            points_difference = self.p1points - self.p2points
            if (points_difference == 1):
                result = "Advantage player1"
            elif (points_difference == -1):
                result = "Advantage player2"
            elif (points_difference >= 2):
                result = "Win for player1"
            else:
                result = "Win for player2"
        else:
            for i in range(1, 3):
                if (i == 1):
                    tempScore = self.p1points
                else:
                    result += "-"
                    tempScore = self.p2points
                result += {
                    0: "Love",
                    1: "Fifteen",
                    2: "Thirty",
                    3: "Forty",
                }[tempScore]
        return result
